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

	public UploadResource(ArquivoConfig arquivoConfig) {
		/*
		 * Constrói o caminho absoluto normalizado para o diretório de armazenamento de arquivos,
		 * com base na configuração fornecida (arquivoConfig.getUploadDir()).
		 * O caminho absoluto é obtido a partir do caminho relativo fornecido pela configuração,
		 * e o método `normalize()` ajuda a garantir uma representação padronizada do caminho.
		 */
		this.fileStorageLocation = Paths.get(arquivoConfig.getUploadDir()).toAbsolutePath().normalize();
	}

	@PostMapping
	public boolean uploadFile(@RequestParam("file") MultipartFile file) {
		/*
		 * @RequestParam("file") indica que o método espera receber um parâmetro chamado file
		 * MultipartFile é um tipo de variável utilizado pelo spring para lidar com o upload de arquivos
		 */
		boolean upload = salvarArquivo(file);

		if (upload) {
			return lerArquivo(file);
		} else {
			return false;
		}
	}

	private boolean salvarArquivo(MultipartFile file) {
		/*
		 * StringUtils é uma classe do Spring que fornece métodos para trabalhar com strings
		 * .cleanPath limpa a string de caminho do arquivo (remove ../, ./, etc)
		 * .getOriginalFilename() retorna o nome original do arquivo
		 */
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());

		try {
			// Constrói o caminho do arquivo de destino concatenando o nome do arquivo ao diretório de armazenamento.
			// Programe aqui

			// Transfere o arquivo recebido (file) para o local de destino no sistema de arquivos.
			// Programe aqui


			Arquivo arquivo = new Arquivo();
			arquivo.setCaminhoNoServidor(targetLocation.toString());
			arquivoRepository.save(arquivo);

			return true;
		} catch (IOException ex) {
			ex.printStackTrace();
			return false;
		}
	}

	private boolean lerArquivo(MultipartFile file) {
		try {
			// Configura a fábrica de construção de documentos XML.
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();

			// Cria um construtor de documentos XML a partir da fábrica.
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

			// Faz o parse do arquivo XML, obtendo um objeto Document.
			org.w3c.dom.Document doc = dBuilder.parse(file.getInputStream());

			// Normaliza a estrutura do documento XML.
			doc.getDocumentElement().normalize();

			// Obtém uma lista de elementos "pais" do documento.
			NodeList nodeList = doc.getElementsByTagName("pais");

			// Percorre a lista de elementos "pais" no documento XML.
			for (int i = 0; i < nodeList.getLength(); i++) {
				
				// Obtém um nó da lista.
				Node node = nodeList.item(i);
				
				// Verifica se o nó é um elemento XML.
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					
					// Converte o nó para um elemento XML.
					Element element = (Element) node;

					// Obtém os valores dos elementos filhos do elemento "pais".
					String codigo = element.getElementsByTagName("codigo").item(0).getTextContent();
					String nome = ""; // Obtenha o elemento nome
					String ordem = ""; // Obtenha o elemento ordem
					String sigla2 = ""; // Obtenha o elemento sigla2
					String sigla3 = ""; // Obtenha o elemento sigla3

					// Cria um objeto Pais com os dados obtidos.
					Pais pais = new Pais();
					// Crie os sets de todos os atributos

					// Salva no banco de dados
					paisRepository.save(pais);
				}
			}

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
