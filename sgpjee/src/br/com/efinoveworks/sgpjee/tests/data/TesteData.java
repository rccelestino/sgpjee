package br.com.efinoveworks.sgpjee.tests.data;

import static org.junit.Assert.*;

import java.io.Serializable;
import java.util.Calendar;

import org.junit.Test;

import br.com.efinoveworks.sgpjee.report.util.DateUtils;

public class TesteData implements Serializable {

	private static final long serialVersionUID = 8892599436394490561L;

	@Test
	public void testData() {

		try {

			assertEquals("20092017", DateUtils.getDateAtualReportName());

		} catch (Exception e) {

			e.printStackTrace();
			fail(e.getMessage());

		}

	}

	@Test
	public void testDataFormatSql() {

		try {

			assertEquals("'2017-09-20'", DateUtils.formatSql(Calendar.getInstance().getTime()));

		} catch (Exception e) {

			e.printStackTrace();
			fail(e.getMessage());

		}

	}
	
	@Test
	public void testDataFormatSqlSimple() {

		try {

			assertEquals("2017-09-20", DateUtils.formatSqlSimple(Calendar.getInstance().getTime()));

		} catch (Exception e) {

			e.printStackTrace();
			fail(e.getMessage());

		}

	}	

}
