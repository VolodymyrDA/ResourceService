package org.vdoloka.service.export;

import lombok.RequiredArgsConstructor;
import org.apache.poi.xwpf.usermodel.*;
import org.vdoloka.dto.HubResourcesDTO;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
public class WordExporter {
    private XWPFDocument document;
    private final List<HubResourcesDTO> data;
    private final String description;

    public void writeContent() {
        document = new XWPFDocument();
        XWPFParagraph paragraph = document.createParagraph();
        paragraph.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun r2 = paragraph.createRun();
        r2.setBold(true);
        r2.setItalic(true);
        r2.setFontSize(16);
        r2.setText(description);
        r2.setFontFamily("Arial");
        XWPFTable table = document.createTable();
        XWPFTableRow rowHeader = table.getRow(0);
        rowHeader.getCell(0).setText("resource id");
        rowHeader.addNewTableCell().setText("resource name");
        rowHeader.addNewTableCell().setText("quantity");
        for (HubResourcesDTO hubResourcesDTO : data) {
            XWPFTableRow row = table.createRow();
            row.getCell(0).setText(String.valueOf(hubResourcesDTO.getResourceId()));
            row.getCell(1).setText(hubResourcesDTO.getResourceName());
            row.getCell(2).setText(String.valueOf(hubResourcesDTO.getQuantity()));
        }
    }

    public void export(HttpServletResponse response) throws IOException {
        writeContent();
        ServletOutputStream outputStream = response.getOutputStream();
        document.write(outputStream);
        document.close();
        outputStream.close();
    }
}