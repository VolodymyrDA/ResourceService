package org.vdoloka.controller;

import com.lowagie.text.DocumentException;
import lombok.RequiredArgsConstructor;
import org.vdoloka.dto.HubResourcesDTO;
import org.vdoloka.model.AnalyticsType;
import org.vdoloka.service.AnalyticsService;
import org.vdoloka.service.export.ExcelExporter;
import org.vdoloka.service.export.PDFExporter;
import org.vdoloka.service.export.WordExporter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class ExportController {
    private final AnalyticsService analyticsService;
    private final DateFormat dateFormatter;
    private static final int PAGE = 1;
    private static final int ITEMS_PER_PAGE = 1000;

    @GetMapping("/export")
    public void exportData(@RequestParam("type") AnalyticsType type,
                           @RequestParam("format") ExportFormat format,
                           HttpServletResponse response) throws IOException, DocumentException {
        response.setContentType(format.getMimeType());
        String currentDateTime = dateFormatter.format(new Date());
        String headerValue = String.format("attachment; filename=%s_%s.%s", type.getFileName(), currentDateTime, format.getFileExtension());
        response.setHeader("Content-Disposition", headerValue);
        List<HubResourcesDTO> hubResourcesDTOEntities = analyticsService.getData(type, PAGE, ITEMS_PER_PAGE);
        switch (format) {
            case PDF -> {
                PDFExporter pdfExporter = new PDFExporter(hubResourcesDTOEntities,type.getDescription());
                pdfExporter.export(response);
            }
            case XLSX -> {
                ExcelExporter excelExporter = new ExcelExporter(hubResourcesDTOEntities,type.getDescription());
                excelExporter.export(response);
            }
            case DOCX -> {
                WordExporter wordExporter = new WordExporter(hubResourcesDTOEntities,type.getDescription());
                wordExporter.export(response);
            }
        }
    }

    enum ExportFormat {
        PDF("application/pdf", "pdf"),
        XLSX("application/octet-stream", "xlsx"),
        DOCX("application/octet-stream", "docx");

        private final String mimeType;
        private final String fileExtension;

        ExportFormat(String mimeType, String fileExtension) {
            this.mimeType = mimeType;
            this.fileExtension = fileExtension;
        }

        public String getMimeType() {
            return mimeType;
        }

        public String getFileExtension() {
            return fileExtension;
        }
    }
}