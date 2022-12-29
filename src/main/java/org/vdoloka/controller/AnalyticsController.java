package org.vdoloka.controller;

import com.lowagie.text.DocumentException;
import org.vdoloka.dto.HubResourcesDTO;
import org.vdoloka.service.export.PDFExporter;
import org.vdoloka.service.export.ExcelExporter;
import org.vdoloka.service.export.WordExporter;
import org.vdoloka.service.impl.AnalyticsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class AnalyticsController {
    private final AnalyticsServiceImpl analyticsService;
    private final DateFormat dateFormatter;
    private final String headerKey;

    @Autowired
    public AnalyticsController(AnalyticsServiceImpl analyticsService, DateFormat dateFormatter) {
        this.analyticsService = analyticsService;
        this.dateFormatter = dateFormatter;
        headerKey = "Content-Disposition";
    }

    @GetMapping("analytics")
    public String getAnalytics() {
        return "analytics";
    }


    @GetMapping("analyticsLack")
    public String getAnalyticsLack() {
        return "analyticsLack";
    }

    @GetMapping("analyticsTop")
    public String getAnalyticsTop() {
        return "analyticsTop";
    }

    @GetMapping("/export/pdf")
    public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        String currentDateTime = dateFormatter.format(new Date());
        String headerValue = "attachment; filename=resourcesOnHubs_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
        final int page = 1;
        final int itemPerPage = 1000;
        List<HubResourcesDTO> hubResourcesDTOEntities = analyticsService.getResourcesOnHubs(page, itemPerPage);
        PDFExporter exporter = new PDFExporter(hubResourcesDTOEntities);
        exporter.export(response);
    }

    @GetMapping("/export/xlsx")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        String currentDateTime = dateFormatter.format(new Date());
        String headerValue = "attachment; filename=lackResources_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);
        final int page = 1;
        final int itemPerPage = 1000;
        List<HubResourcesDTO> hubResourcesDTOEntities = analyticsService.getLackResources(page, itemPerPage);
        ExcelExporter excelExporter = new ExcelExporter(hubResourcesDTOEntities);
        excelExporter.export(response);
    }

    @GetMapping("/export/docx")
    public void exportToWord(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        String currentDateTime = dateFormatter.format(new Date());
        String headerValue = "attachment; filename=topOrderingResources_" + currentDateTime + ".docx";
        response.setHeader(headerKey, headerValue);
        final int page = 1;
        final int itemPerPage = 10;
        List<HubResourcesDTO> hubResourcesDTOEntities = analyticsService.getCountOrderingResources(page, itemPerPage);
        WordExporter wordExporter = new WordExporter(hubResourcesDTOEntities);
        wordExporter.export(response);
    }
}