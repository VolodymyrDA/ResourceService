package org.vdoloka.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.vdoloka.model.AnalyticsType;
import org.vdoloka.model.ExportFormat;
import org.vdoloka.service.ExportService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.verify;

class ExportControllerTest {

    @Mock
    private ExportService exportService;

    @Mock
    private HttpServletResponse response;

    @InjectMocks
    private ExportController controller;

    @Test
    @ExtendWith(MockitoExtension.class)
    void shouldExportData() throws IOException {
        ArgumentCaptor<AnalyticsType> analyticsTypeCaptor = ArgumentCaptor.forClass(AnalyticsType.class);
        ArgumentCaptor<ExportFormat> formatCaptor = ArgumentCaptor.forClass(ExportFormat.class);

        controller.exportData(AnalyticsType.RESOURCES_ON_HUBS, ExportFormat.PDF, response);

        verify(exportService).exportData(analyticsTypeCaptor.capture(), formatCaptor.capture(), eq(response));

        assertThat(analyticsTypeCaptor.getValue()).isEqualTo(AnalyticsType.RESOURCES_ON_HUBS);
        assertThat(formatCaptor.getValue()).isEqualTo(ExportFormat.PDF);
    }
}