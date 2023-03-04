package org.vdoloka.service;

import org.vdoloka.model.AnalyticsType;
import org.vdoloka.model.ExportFormat;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ExportService {
    void exportData(AnalyticsType analyticsType, ExportFormat format, HttpServletResponse response)  throws IOException;
}
