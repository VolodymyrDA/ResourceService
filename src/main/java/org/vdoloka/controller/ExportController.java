package org.vdoloka.controller;

import com.lowagie.text.DocumentException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.vdoloka.model.AnalyticsType;
import org.vdoloka.model.ExportFormat;
import org.vdoloka.service.ExportService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
@Controller
public class ExportController {
    private final ExportService exportService;

    @GetMapping("/export")
    public void exportData(@RequestParam("type") AnalyticsType analyticsType,
                           @RequestParam("format") ExportFormat format,
                           HttpServletResponse response) throws DocumentException, IOException {
        exportService.exportData(analyticsType, format, response);
    }
}