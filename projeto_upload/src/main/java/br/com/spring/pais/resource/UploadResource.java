package br.com.spring.pais.resource;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import br.com.spring.pais.entity.Arquivo;
import br.com.spring.pais.entity.Pais;
import br.com.spring.pais.repository.ArquivoRepository;
import br.com.spring.pais.repository.PaisRepository;

@RestController
@RequestMapping("/upload")
public class UploadResource {

	@Autowired
	private PaisRepository paisRepository;

	@Autowired
	private ArquivoRepository arquivoRepository;

	// Path é uma Interface em Java que representa o caminho de um arquivo ou
	// diretório no sistema de arquivos
	private final Path fileStorageLocation;

	// Vamos começar a programar aqui :)
}
