package com.example.mate.Eccomerce.controllers;


import com.example.mate.Eccomerce.models.Details;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.PdfPTable;
import org.springframework.core.io.ByteArrayResource;
import com.example.mate.Eccomerce.dtos.PurchaseOrderDTO;
import com.example.mate.Eccomerce.models.Person;
import com.example.mate.Eccomerce.models.PurchaseOrder;
import com.example.mate.Eccomerce.service.POService;
import com.example.mate.Eccomerce.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@RestController
@RequestMapping("/api")
public class ExportTicketController {

    @Autowired
    private PersonService personRepository;

    @Autowired
    private POService poService;

    @GetMapping(value = "/ticket", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<Object> exportTicket(@RequestBody PurchaseOrderDTO purchaseOrderDTO, Authentication authentication) throws IOException, DocumentException{
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

            // Abrir el documento
            document.open();

            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, Font.BOLD);
            Paragraph title = new Paragraph("Matoffee || Purchase Order", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            title.setSpacingAfter(20); // Espaciado después del título
            document.add(title);

            Paragraph clientName= new Paragraph("Client: "+person.getFirstname()+" "+person.getLastname());
            Paragraph addressStreet= new Paragraph("Address: "+purchaseOrder.getAdress().getStreet());
            Paragraph addressNumber= new Paragraph("Number: "+purchaseOrder.getAdress().getNumber());
            Paragraph addressCity= new Paragraph("City: "+purchaseOrder.getAdress().getCity());
            Paragraph addressApartment= new Paragraph("Apartment: "+purchaseOrder.getAdress().getApartament());
            Paragraph addressPostalCode= new Paragraph("Postal Code: "+purchaseOrder.getAdress().getZipCode());

            document.add(clientName);
            document.add(addressStreet);
            document.add(addressNumber);
            document.add(addressCity);
            document.add(addressApartment);
            document.add(addressPostalCode);


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
                PdfPCell priceCell = new PdfPCell(new Phrase(String.valueOf(details.getPrice())));
                PdfPCell subTotalCell = new PdfPCell(new Phrase(String.valueOf(details.getQuantity() * details.getPrice())));

                // Aplicar padding a las celdas de datos
                idCell.setPadding(cellPadding);
                nameCell.setPadding(cellPadding);
                quantityCell.setPadding(cellPadding);
                priceCell.setPadding(cellPadding);
                subTotalCell.setPadding(cellPadding);

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
            PdfPCell total1= new PdfPCell(new Phrase("Total"));
            PdfPCell total2= new PdfPCell(new Phrase(String.valueOf(total)));
            total1.setPadding(cellPadding);
            total2.setPadding(cellPadding);
            footer.setWidthPercentage(100);
            footer.addCell(total1);
            footer.addCell("");
            footer.addCell("");
            footer.addCell("");
            footer.addCell(total2);

            float[] columnWidths2 = {5f, 0f, 0f, 0f, 1f};
            footer.setWidths(columnWidths2);

            footer.getDefaultCell().setBackgroundColor(BaseColor.GREEN);
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
    }
}
