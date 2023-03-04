package org.vdoloka.service.export;

import com.lowagie.text.Font;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.vdoloka.dto.HubResourcesDTO;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.List;

import static com.lowagie.text.Element.ALIGN_CENTER;

@Component
@NoArgsConstructor
public class PDFExporter {
    private List<HubResourcesDTO> data;
    private String description;

    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.ORANGE);
        cell.setPadding(5);
        Font font = FontFactory.getFont(FontFactory.HELVETICA, 12, Color.WHITE);
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
     public void getData( List<HubResourcesDTO> data, String description){
        this.data=data;
        this.description=description;
     }
    public void export(HttpServletResponse response) throws DocumentException, IOException {
        try (Document document = new Document(PageSize.A4)) {
            PdfWriter.getInstance(document, response.getOutputStream());
            document.open();
            Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16, Color.BLUE);
            Paragraph p = new Paragraph(description, font);
            p.setAlignment(ALIGN_CENTER);
            document.add(p);
            PdfPTable table = new PdfPTable(3);
            table.setWidthPercentage(100f);
            table.setWidths(new float[]{1.5f, 5f, 1.5f});
            table.setSpacingBefore(10);
            writeTableHeader(table);
            writeTableData(table);
            document.add(table);
        }
    }
}