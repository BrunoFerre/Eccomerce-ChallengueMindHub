package com.example.mate.Eccomerce.controllers;


import com.example.mate.Eccomerce.models.Details;
import com.itextpdf.text.*;
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
            Paragraph title = new Paragraph("Purchase Order", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            title.setSpacingAfter(20); // Espaciado después del título
            document.add(title);

            //Crear la Tabla
            PdfPTable table = new PdfPTable(5);
            table.setWidthPercentage(100);

            //Agregar los titulos de columna
            table.addCell("Id");
            table.addCell("Name");
            table.addCell("Quantity");
            table.addCell("Price");
            table.addCell("Sub Total");


            //Agregar los datos
            for (Details details : purchaseOrder.getDetails()){
                System.out.println(details.getId());
                table.addCell(String.valueOf(details.getId()));
                table.addCell(details.getProduct().getName());
                table.addCell(String.valueOf(details.getQuantity()));
                table.addCell(String.valueOf(details.getPrice()));
                table.addCell(String.valueOf(details.getQuantity()*details.getPrice()));
            }
            document.add(table);

            //Calcular Total
            double total=0;
            for(Details details : purchaseOrder.getDetails()) {
                total += details.getQuantity() * details.getPrice();
            }

            //Agregar footer
            PdfPTable footer = new PdfPTable(5);
            footer.setWidthPercentage(100);
            footer.addCell("Total");
            footer.addCell("");
            footer.addCell("");
            footer.addCell("");
            footer.addCell(String.valueOf(total));

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
