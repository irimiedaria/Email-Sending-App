package org.projectemail.emailapp.services;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
public class EmailTemplateLoader {
    public String loadTemplate(String filePath) {
        try {
            return StreamUtils.copyToString(
                    new ClassPathResource(filePath).getInputStream(),
                    StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
