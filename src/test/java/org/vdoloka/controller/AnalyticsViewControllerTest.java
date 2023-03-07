package org.vdoloka.controller;

import org.junit.jupiter.api.Test;
import org.springframework.ui.Model;
import org.springframework.validation.support.BindingAwareModelMap;
import org.vdoloka.model.AnalyticsType;

import static org.assertj.core.api.Assertions.assertThat;

class AnalyticsViewControllerTest {
    @Test
    void shouldReturnViewAnalytics() {
        AnalyticsViewController controller = new AnalyticsViewController();
        Model model = new BindingAwareModelMap();

        String viewName = controller.viewAnalytics(model, AnalyticsType.LACK_RESOURCES);

        assertThat(viewName).isEqualTo("analytics");
        assertThat(model.getAttribute("analyticsType")).isEqualTo(AnalyticsType.LACK_RESOURCES);
    }
}
