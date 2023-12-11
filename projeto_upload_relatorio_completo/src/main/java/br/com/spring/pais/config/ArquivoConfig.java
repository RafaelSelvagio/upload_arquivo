// Configuração da classe que define propriedades relacionadas a arquivos.
package br.com.spring.pais.config;

// Importa as classes necessárias do Spring.
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

// Anotação indicando que a classe é uma configuração do Spring.
@Configuration

// Anotação que indica que a classe possui propriedades configuráveis com o prefixo "file".
@ConfigurationProperties(prefix = "file")
public class ArquivoConfig {

    // Variável para armazenar o diretório de upload configurado.
    private String uploadDir;

    // Método getter para obter o diretório de upload.
    public String getUploadDir() {
        return uploadDir;
    }

    // Método setter para configurar o diretório de upload.
    public void setUploadDir(String uploadDir) {
        this.uploadDir = uploadDir;
    }
}
