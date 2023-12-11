package br.com.spring.pais.service;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import br.com.spring.pais.entity.Pais;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class RelatorioService {

	public void exporta(HttpServletResponse response, Iterable<Pais> paises) throws IOException {
		Document document = new Document();
		try {
			PdfWriter.getInstance(document, response.getOutputStream());

			document.open();
			
			// Adiciona o logo
			Image logo = Image.getInstance("https://upload.wikimedia.org/wikipedia/commons/8/8c/SENAI_S%C3%A3o_Paulo_logo.png");
            logo.setAlignment(Image.ALIGN_CENTER);
            logo.scaleToFit(400, 100); // Ajuste o tamanho conforme necessário
            document.add(logo);
            
			document.add(new Paragraph("\n"));

			// Adiciona um título
			Font fonteTitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
			fonteTitulo.setSize(18);
			
			Paragraph titulo = new Paragraph("Relatório de Países", fonteTitulo);
			titulo.setAlignment(Paragraph.ALIGN_CENTER);
			document.add(titulo);
			

			document.add(new Paragraph("\n"));
			
			// Adiciona parágrafo
			Font fonte = FontFactory.getFont(FontFactory.TIMES_ROMAN);
			fonte.setSize(12);
			
			Paragraph h1 = new Paragraph("Esta lista de países em PDF foi criada utilizando os conhecimentos adquiridos no curso Técnico em Desenvolvimento de Sistemas do Senai São Carlos.", fonte);
			h1.setAlignment(Paragraph.ALIGN_JUSTIFIED);
			document.add(h1);
			
			Paragraph h2 = new Paragraph("Tecnologias utilizadas: HTML, Javascript, CSS, Bootstrap, JQuery, Java, Spring, MVC, Banco de Dados.", fonte);
			h2.setAlignment(Paragraph.ALIGN_JUSTIFIED);
			document.add(h2);
			
			document.add(new Paragraph("\n"));
			document.add(new Paragraph("\n"));

			// Adiciona uma tabela com informações dos países
			PdfPTable table = new PdfPTable(5); // 5 colunas para Código, Nome, Ordem, Sigla2 e Sigla3

			// Configuração das células da tabela
			Font fontHeader = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
			fontHeader.setSize(12);

			PdfPCell headerCell = new PdfPCell();
			headerCell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
			headerCell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);

			String[] headers = { "Código", "Nome", "Ordem", "Sigla2", "Sigla3" };
			for (String header : headers) {
				headerCell.setPhrase(new Paragraph(header, fontHeader));
				table.addCell(headerCell);
			}

			// Adiciona dados dos países à tabela
			Font fontData = FontFactory.getFont(FontFactory.HELVETICA);
			fontData.setSize(10);

			for (Pais pais : paises) {
                table.addCell(new Paragraph(String.valueOf(pais.getCodigo()), fontData));
                table.addCell(new Paragraph(pais.getNome(), fontData));
                table.addCell(new Paragraph(String.valueOf(pais.getOrdem()), fontData));
                table.addCell(new Paragraph(pais.getSigla2(), fontData));
                table.addCell(new Paragraph(pais.getSigla3(), fontData));
            }
			document.add(table);
			
			
			Paragraph rodape = new Paragraph("Instrutores responsáveis: \n Eduardo Vieira do Nascimento \n Matheus Michilino de Oliveira \n Rafael de Sena Selvagio", fonte);
			rodape.setAlignment(Paragraph.ALIGN_JUSTIFIED);
			document.add(rodape);
			
		} catch (DocumentException e) {
			e.printStackTrace();
		} finally {
			document.close();
		}
	}
}
