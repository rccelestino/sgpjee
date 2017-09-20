package br.com.efinoveworks.sgpjee.report.util;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils implements Serializable {

	private static final long serialVersionUID = 7796809212705400082L;

	public static String getDateAtualReportName() {

		DateFormat df = new SimpleDateFormat("ddMMyyyy");

		return df.format(Calendar.getInstance().getTime());

	}

	public static String formatSql(Date data) {

		StringBuffer retorno = new StringBuffer();

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

		retorno.append("'");
		retorno.append(df.format(data));
		retorno.append("'");
		
		return retorno.toString();

	}
	
	public static String formatSqlSimple(Date data) {

		StringBuffer retorno = new StringBuffer();

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

		retorno.append(df.format(data));
		
		return retorno.toString();

	}

}
