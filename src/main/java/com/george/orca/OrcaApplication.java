package com.george.orca;

import com.george.orca.config.FileConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableConfigurationProperties(FileConfig.class)
public class OrcaApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrcaApplication.class, args);
    }
}
