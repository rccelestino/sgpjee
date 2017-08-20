package br.com.efinoveworks.sgpjee.util;

import java.io.Serializable;

/**
 * Nome do caminho do JNDI datasource Tomcat
 * @author silva
 *
 */

public class ConexaoUtil implements Serializable{

	private static final long serialVersionUID = -3938407481632285624L;
	
	public static String JAVA_JDBC_DATASOURCE = "java:/comp/env/jdbc/datasource";
	

}
