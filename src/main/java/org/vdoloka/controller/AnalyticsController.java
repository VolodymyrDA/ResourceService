package org.vdoloka.controller;

import com.lowagie.text.DocumentException;
import org.vdoloka.entity.HubEntity;
import org.vdoloka.service.PDFExporter;
import org.vdoloka.service.ExcelExporter;
import org.vdoloka.service.WordExporter;
import org.vdoloka.service.impl.AnalyticsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class AnalyticsController {
    private final AnalyticsServiceImpl analitycsService;

    @Autowired
    public AnalyticsController(AnalyticsServiceImpl analyticsService) {
        this.analitycsService = analyticsService;
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
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=resourcesOnHubs_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
        final int page = 1;
        final int itemPerPage = 1000;
        List<HubEntity> hubEntities = analitycsService.getResourcesOnHubs(page, itemPerPage);
        PDFExporter exporter = new PDFExporter(hubEntities);
        exporter.export(response);
    }

    @GetMapping("/export/xlsx")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=lackResources_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);
        final int page = 1;
        final int itemPerPage = 1000;
        List<HubEntity> hubEntities = analitycsService.getLackResources(page, itemPerPage);
        ExcelExporter excelExporter = new ExcelExporter(hubEntities);
        excelExporter.export(response);
    }

    @GetMapping("/export/docx")
    public void exportToWord(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=topOrderingResources_" + currentDateTime + ".docx";
        response.setHeader(headerKey, headerValue);
        final int page = 1;
        final int itemPerPage = 10;
        List<HubEntity> hubEntities = analitycsService.getCountOrderingResources(page, itemPerPage);
        WordExporter wordExporter = new WordExporter(hubEntities);
        wordExporter.export(response);
    }
}