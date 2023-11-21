package br.com.spring.pais.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

// Anotação indicando que a classe é uma configuração do Spring.
@Configuration

// Anotação que indica que a classe possui propriedades configuráveis com o prefixo "file".
@ConfigurationProperties(prefix = "file")
public class ArquivoConfig {
    private String uploadDir;

    public String getUploadDir() {
        return uploadDir;
    }

    public void setUploadDir(String uploadDir) {
        this.uploadDir = uploadDir;
    }
}
