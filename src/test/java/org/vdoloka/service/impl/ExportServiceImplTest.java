package org.vdoloka.service.impl;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletResponse;
import org.vdoloka.dto.HubResourcesDTO;
import org.vdoloka.model.AnalyticsType;
import org.vdoloka.model.ExportFormat;
import org.vdoloka.service.ResourcesService;
import org.vdoloka.service.export.ExcelExporter;
import org.vdoloka.service.export.PDFExporter;
import org.vdoloka.service.export.WordExporter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ExportServiceImplTest {
    @Mock
    private ExcelExporter excelExporter;
    @Mock
    private WordExporter wordExporter;
    @Mock
    private PDFExporter pdfExporter;
    @Mock
    private ResourcesService resourcesService;
    @Mock
    MockHttpServletResponse response;
    @InjectMocks
    ExportServiceImpl exportService;
    private static final int PAGE = 1;
    private static final int ITEMS_PER_PAGE = 1000;

    @ParameterizedTest
    @EnumSource(ExportFormat.class)
    void testExportData(ExportFormat format) throws IOException {
        List<HubResourcesDTO> hubResourcesDTOEntities = new ArrayList<>();
        AnalyticsType analyticsType = AnalyticsType.RESOURCES_ON_HUBS;

        when(resourcesService.getAnalytics(analyticsType, PAGE, ITEMS_PER_PAGE)).thenReturn(hubResourcesDTOEntities);

        exportService.exportData(analyticsType, format, response);

        verify(response, times(1)).setContentType(format.getMimeType());
        verify(response, times(1)).setHeader("Content-Disposition",
                String.format("attachment; filename=%s_%s.%s", analyticsType.getFileName(), LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss")), format.getFileExtension()));
        switch (format) {
            case PDF -> {
                verify(pdfExporter, times(1)).getData(hubResourcesDTOEntities, analyticsType.getDescription());
                verify(pdfExporter, times(1)).export(response);
                verifyNoInteractions(excelExporter, wordExporter);
            }
            case XLSX -> {
                verify(excelExporter, times(1)).getData(hubResourcesDTOEntities, analyticsType.getDescription());
                verify(excelExporter, times(1)).export(response);
                verifyNoInteractions(pdfExporter, wordExporter);
            }
            case DOCX -> {
                verify(wordExporter, times(1)).getData(hubResourcesDTOEntities, analyticsType.getDescription());
                verify(wordExporter, times(1)).export(response);
                verifyNoInteractions(excelExporter, pdfExporter);
            }
        }

        verifyNoMoreInteractions(excelExporter, wordExporter, pdfExporter, resourcesService);
    }
}