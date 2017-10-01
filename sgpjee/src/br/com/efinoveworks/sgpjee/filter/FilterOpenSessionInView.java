package br.com.efinoveworks.sgpjee.filter;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.filter.DelegatingFilterProxy;

import br.com.efinoveworks.sgpjee.hibernate.session.HibernateUtil;
import br.com.efinoveworks.sgpjee.listener.ContextLoaderListenerSgpjeeUtils;
import br.com.efinoveworks.sgpjee.model.classes.Entidade;
import br.com.efinoveworks.sgpjee.util.UtilFramework;

@WebFilter(filterName = "conexaoFilter")
public class FilterOpenSessionInView extends DelegatingFilterProxy implements Serializable {

	private static final long serialVersionUID = -3425303393699309467L;

	private static SessionFactory sf;

	/**
	 * executado quando a aplicação está sendo iniciada no servidor e apenas uma
	 * vez
	 */
	@Override
	protected void initFilterBean() throws ServletException {
		sf = HibernateUtil.getSesionFactory();

	}

	@SuppressWarnings("unused")
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws ServletException, IOException {

		// JDBC do Spring
		BasicDataSource springDataSource = (BasicDataSource) ContextLoaderListenerSgpjeeUtils
				.getBean("springDataSource");

		DefaultTransactionDefinition def = new DefaultTransactionDefinition();

		PlatformTransactionManager transactionManager = new DataSourceTransactionManager(springDataSource);

		TransactionStatus status = transactionManager.getTransaction(def);

		try {

			request.setCharacterEncoding("UTF-8");

			// captura o usuario que fez a operação
			HttpServletRequest hsr = (HttpServletRequest) request;

			// pega a sessão do usuário logado no sistema
			HttpSession session = hsr.getSession();

			Entidade userLogadoSession = (Entidade) session.getAttribute("userLogadoSession");

			if (userLogadoSession != null) {
				UtilFramework.geThreadLocal().set(userLogadoSession.getEnt_codigo());
			}

			sf.getCurrentSession().beginTransaction();

			// antes de executar ação (Requesição)
			chain.doFilter(request, response); // executa a ação no servidor

			// após executar a ação (Resposta)

			transactionManager.commit(status);

			if (sf.getCurrentSession().getTransaction().isActive()) {
				sf.getCurrentSession().flush();
				sf.getCurrentSession().getTransaction().commit();
			}

			if (sf.getCurrentSession().isOpen()) {
				sf.getCurrentSession().close();
			}

			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");

		} catch (Exception e) {

			transactionManager.rollback(status);

			e.printStackTrace();

			if (sf.getCurrentSession().getTransaction().isActive()) {
				sf.getCurrentSession().getTransaction().rollback();
			}

			if (sf.getCurrentSession().isOpen()) {
				sf.getCurrentSession().close();

			}

		} finally {

			if (sf.getCurrentSession().isOpen()) {

				if (sf.getCurrentSession().getTransaction().isActive()) {
					sf.getCurrentSession().flush();
					sf.getCurrentSession().clear();
				}

				sf.getCurrentSession().close();

			}
		}

	}

}
