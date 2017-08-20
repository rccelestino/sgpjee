package br.com.efinoveworks.sgpjee.hibernate.session;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;

import javax.faces.bean.ApplicationScoped;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.engine.SessionFactoryImplementor;

import br.com.efinoveworks.sgpjee.util.ConexaoUtil;

/**
 * Responsável por estabelecer conexao com Hibernate
 * 
 * @author silva
 *
 */

@ApplicationScoped
public class HibernateUtil implements Serializable {
	
	private static final long serialVersionUID = 2283534435312959818L;
	
	public static String JAVA_JDBC_DATASOURCE = "java:/comp/env/jdbc/datasource";
	
	private static SessionFactory sessionFactory = buildSessionFactory();

	/**
	 * Responsável por ler o arquivo de configuração hibernate.cfg.xml
	 *
	 * @return
	 */
	private static SessionFactory buildSessionFactory() {

		try {
			
			if(sessionFactory == null){
				sessionFactory = new Configuration().configure().buildSessionFactory();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExceptionInInitializerError("Erro ao criar conexão SessionFactory");
		}
		
		return sessionFactory;
	}
	
	/**
	 * Retorna o sessionFactory corrente
	 * @return sessionFactory;
	 */
	public static SessionFactory getSesionFactory(){
		return sessionFactory;
	}
	
	/**
	 * Retorna o Session Corrente
	 * @return session
	 */
	public static Session getCurrentSession(){
		return getSesionFactory().getCurrentSession();
	}
	
	/**
	 * Abre uma nova sessão no SessionFactory()
	 * @return Session
	 */
	public static Session openSession(){
		
		if (sessionFactory == null){
			buildSessionFactory();
		}
		
		return sessionFactory.openSession();
	}
	
	/**
	 * Obtem a connection do provedor de conexões configurado
	 * @return Connection SQL
	 * @throws SQLException
	 */
	public static Connection getConnectionProvider() throws SQLException {
		
		return ((SessionFactoryImplementor) sessionFactory).getConnectionProvider().getConnection();

	}
	
	/**
	 * 
	 * @return Connection no InitialContext  java:/comp/env/jdbc/datasource
	 * @throws Exception
	 */
	public static Connection getConnection() throws Exception {
		
		InitialContext context = new InitialContext();
		
		DataSource ds = (DataSource) context.lookup(JAVA_JDBC_DATASOURCE);
		
		return ds.getConnection();	
		
	}
	
	/**
	 * 
	 * @return DataSource
	 * @throws NamingException
	 */
	public DataSource getDataSourceJndi() throws NamingException{
		
		InitialContext context = new InitialContext();
		
		return (DataSource) context.lookup(ConexaoUtil.JAVA_JDBC_DATASOURCE);
		
		
	}

}
