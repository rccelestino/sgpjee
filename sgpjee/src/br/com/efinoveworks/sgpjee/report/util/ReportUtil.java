package br.com.efinoveworks.sgpjee.report.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.io.InputStream;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.menu.Separator;
import org.springframework.stereotype.Component;

import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.oasis.JROdtExporter;
import net.sf.jasperreports.engine.util.JRLoader;

@Component
public class ReportUtil implements Serializable {

	private static final long serialVersionUID = 4117531359276765613L;

	private static final String UNDERLINE = "_";
	private static final String FOLDER_RELATORIOS = "/relatorios";
	private static final String SUBREPORT_DIR = "SUBREPORT_DIR";
	private static final String EXTENSION_ODS = "ods";
	private static final String EXTENSION_XLS = "xls";
	private static final String EXTENSION_HTML = "html";
	private static final String EXTENSION_PDF = "pdf";
	private static final String EXTENSION_CSV = "csv";

	private String SEPARATOR = File.separator;

	private static final int RELATORIO_PDF = 1;
	private static final int RELATORIO_EXCEL = 2;
	private static final int RELATORIO_HTML = 3;
	private static final int RELATORIO_PLANILHA_OPEN_OFFICE = 4;
	private static final int RELATORIO_CSV = 5;
	private static final String PONTO = ".";

	private StreamedContent arquivoRetorno = null;
	private String caminhoArquivoRelatorio = null;
	private String caminhoSubreport_dir = "";

	private JRExporter tipoArquivoExportado = null;
	private String extensaoArquivoExporrtado = "";
	private File arquivoGerado = null;

	public StreamedContent geraRelatorio(List<?> listDataBeanColletionReport, HashMap parametrosRelatorio,
			String nomeRelatorioJasper, String nomeRelatorioSaida, int tipoRelatorio) throws Exception {

		/**
		 * Cria a lista de collectionDataSource de beans que carregam os dados
		 * para o relatório
		 */
		JRBeanCollectionDataSource jrbcds = new JRBeanCollectionDataSource(listDataBeanColletionReport);

		/**
		 * Pega o contexto corrrente
		 */
		FacesContext context = FacesContext.getCurrentInstance();
		context.responseComplete();

		ServletContext scontext = (ServletContext) context.getExternalContext().getContext();

		/**
		 * Define o caminho / pasta padrão do relatório
		 */
		String caminhoRelatorio = scontext.getRealPath(FOLDER_RELATORIOS);

		/**
		 * Definindo o nome do arquivo do relatório jasper
		 * 
		 * Ex.: c:/aplicacao/relatorios/lista_de_clientes.jasper
		 */
		File file = new File(caminhoRelatorio + SEPARATOR + nomeRelatorioJasper + PONTO + "jasper");

		if (caminhoRelatorio == null || (caminhoRelatorio != null && caminhoRelatorio.isEmpty()) || !file.exists()) {
			caminhoRelatorio = this.getClass().getResource(FOLDER_RELATORIOS).getPath();
			SEPARATOR = "";
		}

		/**
		 * Definindo imagens para o relatórios
		 */
		parametrosRelatorio.put("REPORT_PARAMENTERS_IMG", caminhoRelatorio);

		/**
		 * Caminho completo até o relatório compilado indicado
		 */
		String caminhoArquivoJasper = caminhoRelatorio + SEPARATOR + nomeRelatorioJasper + PONTO + "jasper";

		/**
		 * Faz o carregamento do relatório indicado
		 */
		JasperReport relatorioJasper = (JasperReport) JRLoader.loadObjectFromFile(caminhoArquivoJasper);

		/**
		 * Seta paramentro SUBREPORT_DIR como caminho físico para sub-reports
		 */
		caminhoSubreport_dir = caminhoRelatorio + SEPARATOR;
		parametrosRelatorio.put(SUBREPORT_DIR, caminhoSubreport_dir);

		/**
		 * Carrega o arquivo compilado para a memória
		 */
		JasperPrint impressoraJasper = JasperFillManager.fillReport(relatorioJasper, parametrosRelatorio, jrbcds);

		/**
		 * Verifica qual tipo de relatório a ser exportado
		 */
		switch (tipoRelatorio) {
		case RELATORIO_PDF:
			tipoArquivoExportado = new JRPdfExporter();
			extensaoArquivoExporrtado = EXTENSION_PDF;
			break;
		case RELATORIO_HTML:
			tipoArquivoExportado = new JRHtmlExporter();
			extensaoArquivoExporrtado = EXTENSION_HTML;
			break;
		case RELATORIO_EXCEL:
			tipoArquivoExportado = new JRXlsExporter();
			extensaoArquivoExporrtado = EXTENSION_XLS;
			break;
		case RELATORIO_PLANILHA_OPEN_OFFICE:
			tipoArquivoExportado = new JROdtExporter();
			extensaoArquivoExporrtado = EXTENSION_ODS;
			break;
		case RELATORIO_CSV:
			tipoArquivoExportado = new JRCsvExporter();
			extensaoArquivoExporrtado = EXTENSION_CSV;
			break;
		default:
			tipoArquivoExportado = new JRPdfExporter();
			extensaoArquivoExporrtado = EXTENSION_PDF;
		}

		/**
		 * Relatório de saída
		 */
		nomeRelatorioSaida += UNDERLINE + DateUtils.getDateAtualReportName();

		/**
		 * Caminho do relatório exportado
		 */
		caminhoRelatorio = caminhoRelatorio + SEPARATOR + nomeRelatorioSaida + PONTO + extensaoArquivoExporrtado;

		/**
		 * Cria novo file exportado
		 */
		arquivoGerado = new File(caminhoRelatorio);

		/**
		 * Preparar para impressão
		 */
		tipoArquivoExportado.setParameter(JRExporterParameter.JASPER_PRINT, impressoraJasper);

		/**
		 * Nome do arquivo físico a ser impresso / exportado
		 */
		tipoArquivoExportado.setParameter(JRExporterParameter.OUTPUT_FILE, arquivoGerado);

		/**
		 * Executa a exportação
		 */
		tipoArquivoExportado.exportReport();

		/**
		 * Remove o arquivo gerado do servidor, após ser feito download do mesmo
		 * pelo o usuário
		 */
		arquivoGerado.deleteOnExit();

		/**
		 * Cria o inputStream para ser usado pelo PrimeFaces
		 */
		InputStream conteudoRelatorio = new FileInputStream(arquivoGerado);

		/**
		 * Faz o retorno para aplicação
		 */
		arquivoRetorno = new DefaultStreamedContent(conteudoRelatorio, "application/" + extensaoArquivoExporrtado,
				nomeRelatorioSaida + PONTO + extensaoArquivoExporrtado);
		
		return arquivoRetorno;

	}

}
