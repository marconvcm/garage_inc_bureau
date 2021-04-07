package com.garage_inc.bureau.config;

import com.garage_inc.bureau.interactor.ViewScheduleBookInteractor;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
class WebConfig  implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new ViewScheduleBookInteractor.StringToViewWhenConverter());
    }
}