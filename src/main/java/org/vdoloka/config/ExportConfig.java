package org.vdoloka.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Configuration
public class ExportConfig {
    @Bean
    public DateFormat dateFormatter() {
        return new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
    }
}