package org.example;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(CocococoProperties.class)
public class CocococoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public Cocococo cocococo(CocococoProperties properties) {
        Cocococo cocococo = new Cocococo();
        cocococo.setName(properties.getName());
        cocococo.setType(properties.getType());
        return cocococo;
    }
}
