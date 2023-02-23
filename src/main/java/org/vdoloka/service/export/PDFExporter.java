package org.vdoloka.service.export;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import lombok.RequiredArgsConstructor;
import org.vdoloka.dto.HubResourcesDTO;

@RequiredArgsConstructor
public class PDFExporter {
    private final List<HubResourcesDTO> data;
    private final String description;

    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.ORANGE);
        cell.setPadding(5);
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);
        cell.setPhrase(new Phrase("resource id", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("resource name", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("quantity", font));
        table.addCell(cell);
    }

    private void writeTableData(PdfPTable table) {
        for (HubResourcesDTO hubResourcesDTO : data) {
            table.addCell(String.valueOf(hubResourcesDTO.getResourceId()));
            table.addCell(hubResourcesDTO.getResourceName());
            table.addCell(String.valueOf(hubResourcesDTO.getQuantity()));
        }
    }

    public void export(HttpServletResponse response) throws DocumentException, IOException {
        try (Document document = new Document(PageSize.A4)) {
            PdfWriter.getInstance(document, response.getOutputStream());
            document.open();
            Font font = FontFactory.getFont(FontFactory.HELVETICA, Font.BOLD, 18, Color.BLUE);
            final Paragraph p = new Paragraph(description, font);
            p.setAlignment(Element.ALIGN_CENTER);
            document.add(p);
            final PdfPTable table = new PdfPTable(3);
            table.setWidthPercentage(100f);
            table.setWidths(new float[]{1.5f, 5f, 1.5f});
            table.setSpacingBefore(10);
            writeTableHeader(table);
            writeTableData(table);
            document.add(table);
        }
    }
}