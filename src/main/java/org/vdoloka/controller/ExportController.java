package org.vdoloka.controller;

import com.lowagie.text.DocumentException;
import lombok.RequiredArgsConstructor;
import org.vdoloka.dto.HubResourcesDTO;
import org.vdoloka.service.export.PDFExporter;
import org.vdoloka.service.export.ExcelExporter;
import org.vdoloka.service.export.WordExporter;
import org.vdoloka.service.impl.AnalyticsServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;


@RequiredArgsConstructor
@Controller
public class ExportController {
    private final AnalyticsServiceImpl analyticsService;
    private final DateFormat dateFormatter;
    private static final int PAGE = 1;
    private static final int ITEMS_PER_PAGE = 1000;
    private static final String HEADER_KEY = "Content-Disposition";



    @GetMapping("/export/pdf")
    public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        String currentDateTime = dateFormatter.format(new Date());
        String headerValue = "attachment; filename=resourcesOnHubs_" + currentDateTime + ".pdf";
        response.setHeader(HEADER_KEY, headerValue);
        List<HubResourcesDTO> hubResourcesDTOEntities = analyticsService.getResourcesOnHubs(PAGE, ITEMS_PER_PAGE);
        PDFExporter exporter = new PDFExporter(hubResourcesDTOEntities);
        exporter.export(response);
    }

    @GetMapping("/export/xlsx")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        String currentDateTime = dateFormatter.format(new Date());
        String headerValue = "attachment; filename=lackResources_" + currentDateTime + ".xlsx";
        response.setHeader(HEADER_KEY, headerValue);
        List<HubResourcesDTO> hubResourcesDTOEntities = analyticsService.getLackResources(PAGE, ITEMS_PER_PAGE);
        ExcelExporter excelExporter = new ExcelExporter(hubResourcesDTOEntities);
        excelExporter.export(response);
    }

    @GetMapping("/export/docx")
    public void exportToWord(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        String currentDateTime = dateFormatter.format(new Date());
        String headerValue = "attachment; filename=topOrderingResources_" + currentDateTime + ".docx";
        response.setHeader(HEADER_KEY, headerValue);
        List<HubResourcesDTO> hubResourcesDTOEntities = analyticsService.getCountOrderingResources(PAGE, ITEMS_PER_PAGE);
        WordExporter wordExporter = new WordExporter(hubResourcesDTOEntities);
        wordExporter.export(response);
    }
}