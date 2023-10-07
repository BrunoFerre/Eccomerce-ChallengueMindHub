package com.example.mate.Eccomerce.controllers;


import com.example.mate.Eccomerce.models.Details;
import com.example.mate.Eccomerce.models.Product;
import com.example.mate.Eccomerce.repositories.DetailsRepository;

import com.example.mate.Eccomerce.models.Person;
import com.example.mate.Eccomerce.models.PurchaseOrder;
import com.example.mate.Eccomerce.service.POService;
import com.example.mate.Eccomerce.service.PersonService;
import com.example.mate.Eccomerce.service.ProductService;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ExportTicketController {

    @Autowired
    private PersonService personRepository;
    @Autowired
    private DetailsRepository detailsRepository;
    @Autowired
    private POService poService;
    @Autowired
    private ProductService productService;

    /* @GetMapping(value = "/ticket", produces = MediaType.APPLICATION_PDF_VALUE)*/
    /*public ResponseEntity<Object> exportTicket(@RequestBody PurchaseOrderDTO purchaseOrderDTO, Authentication authentication) throws IOException, DocumentException{
        if (purchaseOrderDTO==null){
            return new ResponseEntity<>("The product was not found", HttpStatus.NOT_FOUND);
        }
        if (authentication == null){
            return new ResponseEntity<>("User is not authenticated", HttpStatus.FORBIDDEN);
        }
        if (purchaseOrderDTO.getAdress()==null){
            return new ResponseEntity<>("Address was not found", HttpStatus.FORBIDDEN);
        }
        if (purchaseOrderDTO.getPaymentMethod()==null){
            return new ResponseEntity<>("Payment method was not found", HttpStatus.FORBIDDEN);
        }
        if (purchaseOrderDTO.getDetails().size()<=0){
            return new ResponseEntity<>("The details cannot be empty", HttpStatus.FORBIDDEN);
        }
        if (purchaseOrderDTO.getPersonID()<=0){
            return new ResponseEntity<>("The person id cannot be 0 or less than 0", HttpStatus.FORBIDDEN);
        }
        if (purchaseOrderDTO.getAmount()<=0){
            return new ResponseEntity<>("The amount cannot be 0 or less than 0", HttpStatus.FORBIDDEN);
        }
        if (purchaseOrderDTO.getDate()==null){
            return new ResponseEntity<>("The date was not found", HttpStatus.FORBIDDEN);
        }
        Person person = personRepository.findById(purchaseOrderDTO.getPersonID());
        if (person==null){
            return new ResponseEntity<>("The person was not found", HttpStatus.FORBIDDEN);
        }
        PurchaseOrder purchaseOrder = poService.getPurchaseOrder(purchaseOrderDTO.getId(), person);
        if (purchaseOrder==null){
            return new ResponseEntity<>("The purchase order was not found", HttpStatus.FORBIDDEN);
        }
        try {

            Document document = new Document(PageSize.A4, 50, 50, 50, 50);

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

            PdfWriter.getInstance(document, byteArrayOutputStream);

            BaseColor lightGreen = new BaseColor(223, 240, 216);

            // Abrir el documento
            document.open();

            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, Font.BOLD);
            Paragraph title = new Paragraph("Matoffee || Purchase Order", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            title.setSpacingAfter(20); // Espaciado después del título
            document.add(title);

            addParagraphWithBoldLabel(document, "Id Order", String.valueOf(purchaseOrder.getId()));
            addParagraphWithBoldLabel(document, "Date: ", purchaseOrder.getDate().toString());
            addParagraphWithBoldLabel(document, "Client: ", person.getFirstname() + " " + person.getLastname());
            addParagraphWithBoldLabel(document, "Address: ", purchaseOrder.getAdress().getStreet());
            addParagraphWithBoldLabel(document, "Number: ", String.valueOf(purchaseOrder.getAdress().getNumber()));
            addParagraphWithBoldLabel(document, "City: ", purchaseOrder.getAdress().getCity());
            addParagraphWithBoldLabel(document, "Apartment: ", purchaseOrder.getAdress().getApartament());
            addParagraphWithBoldLabel(document, "Postal Code: ", purchaseOrder.getAdress().getZipCode());


            //Agrego espacio entre tabla y datos
            Paragraph space= new Paragraph(" ");
            document.add(space);

            //Crear la Tabla
            PdfPTable table = new PdfPTable(5);
            table.setWidthPercentage(100);

            //Manejar ancho de las columnas
            float[] columnWidths = {1f, 2f, 1f, 1f, 1f};
            table.setWidths(columnWidths);

            //Agregar los titulos de columna
            table.addCell("Id");
            table.addCell("Name");
            table.addCell("Quantity");
            table.addCell("Price");
            table.addCell("Sub Total");

            // Aplicar padding a todas las celdas de la tabla
            float cellPadding = 5f; // Ajusta el valor según tus preferencias
            for (PdfPCell cell : table.getRow(0).getCells()) {
                cell.setPadding(cellPadding);
            }

            //Agregar los datos
            for (Details details : purchaseOrder.getDetails()){
                PdfPCell idCell = new PdfPCell(new Phrase(String.valueOf(details.getId())));
                PdfPCell nameCell = new PdfPCell(new Phrase(details.getProduct().getName()));
                PdfPCell quantityCell = new PdfPCell(new Phrase(String.valueOf(details.getQuantity())));
                PdfPCell priceCell = new PdfPCell(new Phrase(formatAmount(details.getPrice())));
                PdfPCell subTotalCell = new PdfPCell(new Phrase(formatAmount(details.getQuantity() * details.getPrice())));

                // Aplicar padding a las celdas de datos
                idCell.setPadding(cellPadding);
                idCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                idCell.setVerticalAlignment(Element.ALIGN_CENTER);
                nameCell.setPadding(cellPadding);
                nameCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                nameCell.setVerticalAlignment(Element.ALIGN_CENTER);
                quantityCell.setPadding(cellPadding);
                quantityCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                quantityCell.setVerticalAlignment(Element.ALIGN_CENTER);
                priceCell.setPadding(cellPadding);
                priceCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                priceCell.setVerticalAlignment(Element.ALIGN_CENTER);
                subTotalCell.setPadding(cellPadding);
                subTotalCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                subTotalCell.setVerticalAlignment(Element.ALIGN_CENTER);

                subTotalCell.setBackgroundColor(lightGreen);
                table.addCell(idCell);
                table.addCell(nameCell);
                table.addCell(quantityCell);
                table.addCell(priceCell);
                table.addCell(subTotalCell);
            }
            table.getDefaultCell().setBackgroundColor(BaseColor.LIGHT_GRAY);
            document.add(table);

            //Calcular Total
            double total=0;
            for(Details details : purchaseOrder.getDetails()) {
                total += details.getQuantity() * details.getPrice();
            }

            //Agregar footer

            PdfPTable footer = new PdfPTable(5);
            Font font = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD);
            PdfPCell total1= new PdfPCell(new Phrase("Total",font));
            PdfPCell total2= new PdfPCell(new Phrase(formatAmount(total)));
            total1.setPadding(cellPadding);
            total2.setPadding(cellPadding);
            total2.setBackgroundColor(lightGreen);
            total1.setBackgroundColor(lightGreen);
            total2.setHorizontalAlignment(Element.ALIGN_CENTER);
            total1.setVerticalAlignment(Element.ALIGN_CENTER);
            total2.setVerticalAlignment(Element.ALIGN_CENTER);
            footer.setWidthPercentage(100);
            footer.addCell(total1);
            footer.addCell("");
            footer.addCell("");
            footer.addCell("");
            footer.addCell(total2);

            float[] columnWidths2 = {5f, 0f, 0f, 0f, 1f};
            footer.setWidths(columnWidths2);


            document.add(footer);

            //Cerrar el documento
            document.close();

            // Convertir el contenido del PDF a un array de bytes
            byte[] content = byteArrayOutputStream.toByteArray();

            // Crear la respuesta con el PDF como contenido
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "ticket.pdf");
            headers.setContentLength(content.length);

            ByteArrayResource resource = new ByteArrayResource(content);

            return new ResponseEntity<>(resource, headers, HttpStatus.OK);
        } catch (DocumentException e) {
            return new ResponseEntity<>("Error generating the ticket: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/
    @GetMapping("/ticket")
    public ResponseEntity<Object> getTicket(@RequestParam String ticket, Authentication authentication)throws DocumentException, IOException {
        if(ticket.isBlank()){
            return new ResponseEntity<>("El ticket no puede estar vacio", HttpStatus.BAD_REQUEST);
        }
        Person person = personRepository.findByEmail(authentication.getName());
        PurchaseOrder purchaseOrder = poService.findByTicket(ticket);
        if(purchaseOrder==null){
            return new ResponseEntity<>("El ticket no existe", HttpStatus.BAD_REQUEST);
        }
        if(purchaseOrder.getPerson().getId()!=person.getId()){
            return new ResponseEntity<>("El ticket no pertenece al usuario", HttpStatus.BAD_REQUEST);
        }
        List<Details> details = detailsRepository.findByPurchaseOrder(purchaseOrder);
        List<Product> products = new ArrayList<>();
        for (Details d : details) {
            Product product = productService.findById(d.getProduct().getId());
            products.add(product);
        }
        Document document = new Document(PageSize.A4);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, byteArrayOutputStream);
        document.open();
        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, Font.BOLD);
        Paragraph title = new Paragraph("Matoffee || Purchase Order", titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        title.setSpacingAfter(20); // Espaciado después del título
        document.add(title);

        addParagraphWithBoldLabel(document, "Ticket number:", ticket);
        addParagraphWithBoldLabel(document, "Date: ", purchaseOrder.getDate().toString());
        addParagraphWithBoldLabel(document, "Client: ", person.getFirstname() + " " + person.getLastname());
        addParagraphWithBoldLabel(document, "Address: ", purchaseOrder.getAdress().getStreet());
        addParagraphWithBoldLabel(document, "Number: ", String.valueOf(purchaseOrder.getAdress().getNumber()));
        addParagraphWithBoldLabel(document, "City: ", purchaseOrder.getAdress().getCity());
        addParagraphWithBoldLabel(document, "Apartment: ", purchaseOrder.getAdress().getApartament());
        addParagraphWithBoldLabel(document, "Postal Code: ", purchaseOrder.getAdress().getZipCode());
        Paragraph space= new Paragraph(" ");
        document.add(space);

        //Crear la Tabla
        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100);

        //Manejar ancho de las columnas
        float[] columnWidths = {1f, 2f, 1f, 1f, 1f};
        table.setWidths(columnWidths);

        //Agregar los titulos de columna
        table.addCell("Id");
        table.addCell("Name");
        table.addCell("Quantity");
        table.addCell("Price");
        table.addCell("Sub Total");
        float cellPadding = 5f;
        for (PdfPCell cell : table.getRow(0).getCells()) {
            cell.setPadding(cellPadding);
        }
        //Agregar los datos
        for (Details PR : details){
            PdfPCell idCell = new PdfPCell(new Phrase(String.valueOf(PR.getId())));
            PdfPCell nameCell = new PdfPCell(new Phrase(PR.getProduct().getName()));
            PdfPCell quantityCell = new PdfPCell(new Phrase(String.valueOf(PR.getQuantity())));
            PdfPCell priceCell = new PdfPCell(new Phrase(String.valueOf(PR.getPrice())));
            PdfPCell subTotalCell = new PdfPCell(new Phrase(String.valueOf(PR.getQuantity() * PR.getPrice())));
            idCell.setPadding(cellPadding);
            idCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            idCell.setVerticalAlignment(Element.ALIGN_CENTER);
            nameCell.setPadding(cellPadding);
            nameCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            nameCell.setVerticalAlignment(Element.ALIGN_CENTER);
            quantityCell.setPadding(cellPadding);
            quantityCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            quantityCell.setVerticalAlignment(Element.ALIGN_CENTER);
            priceCell.setPadding(cellPadding);
            priceCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            priceCell.setVerticalAlignment(Element.ALIGN_CENTER);
            subTotalCell.setPadding(cellPadding);
            table.addCell(idCell);
            table.addCell(nameCell);
            table.addCell(quantityCell);
            table.addCell(priceCell);
            table.addCell(subTotalCell);
        }
        document.add(table);
        PdfPTable footer = new PdfPTable(5);
        Font font = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD);
        PdfPCell total1= new PdfPCell(new Phrase("Total",font));
        PdfPCell total2= new PdfPCell(new Phrase(formatAmount(purchaseOrder.getAmount())));
        total1.setPadding(cellPadding);
        total2.setPadding(cellPadding);
        total2.setHorizontalAlignment(Element.ALIGN_CENTER);
        total1.setVerticalAlignment(Element.ALIGN_CENTER);
        total2.setVerticalAlignment(Element.ALIGN_CENTER);
        footer.setWidthPercentage(100);
        footer.addCell(total1);
        footer.addCell("");
        footer.addCell("");
        footer.addCell("");
        footer.addCell(total2);
        document.add(footer);
        document.close();
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/pdf");
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=ticket.pdf");
        byte[] pdf = byteArrayOutputStream.toByteArray();
        return new ResponseEntity<>(pdf, headers, HttpStatus.OK);
    }

    public  static String formatAmount(double amount){
        // Crear un formato para valores monetarios
        DecimalFormat formatoMoneda = new DecimalFormat("$ #,##0.00");

        // Formatear el valor como un valor monetario

        return formatoMoneda.format(amount);
    }

    private static void addParagraphWithBoldLabel(Document document, String label, String text) throws DocumentException {
        Paragraph paragraph = new Paragraph();
        paragraph.add(new Chunk(label, FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD)));
        paragraph.add(new Chunk(text));
        document.add(paragraph);
    }
}