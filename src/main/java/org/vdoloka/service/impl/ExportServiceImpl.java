package org.vdoloka.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.vdoloka.dto.HubResourcesDTO;
import org.vdoloka.model.AnalyticsType;
import org.vdoloka.model.ExportFormat;
import org.vdoloka.service.AnalyticsService;
import org.vdoloka.service.ExportService;
import org.vdoloka.service.export.ExcelExporter;
import org.vdoloka.service.export.PDFExporter;
import org.vdoloka.service.export.WordExporter;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ExportServiceImpl implements ExportService {

    private final ExcelExporter excelExporter;
    private final WordExporter wordExporter;
    private final PDFExporter pdfExporter;
    private final AnalyticsService analyticsService;
    private final DateFormat dateFormatter;
    private static final int PAGE = 1;
    private static final int ITEMS_PER_PAGE = 1000;

    @Override
    public void exportData(AnalyticsType analyticsType, ExportFormat format, HttpServletResponse response) throws IOException {
        response.setContentType(format.getMimeType());
        String currentDateTime = dateFormatter.format(new Date());
        String headerValue = String.format("attachment; filename=%s_%s.%s", analyticsType.getFileName(), currentDateTime, format.getFileExtension());
        response.setHeader("Content-Disposition", headerValue);
        List<HubResourcesDTO> hubResourcesDTOEntities = analyticsService.getData(analyticsType, PAGE, ITEMS_PER_PAGE);
        switch (format) {
            case PDF -> {
                pdfExporter.getData(hubResourcesDTOEntities, analyticsType.getDescription());
                pdfExporter.export(response);
            }
            case XLSX -> {
                excelExporter.getData(hubResourcesDTOEntities, analyticsType.getDescription());
                excelExporter.export(response);
            }
            case DOCX -> {
                wordExporter.getData(hubResourcesDTOEntities, analyticsType.getDescription());
                wordExporter.export(response);
            }
        }
    }
}