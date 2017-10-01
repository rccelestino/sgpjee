package br.com.efinoveworks.sgpjee.listener;

import java.io.Serializable;

import javax.faces.bean.ApplicationScoped;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

@ApplicationScoped
public class ContextLoaderListenerSgpjeeUtils extends ContextLoader implements Serializable {

	private static final long serialVersionUID = -2961238190949417664L;

	@SuppressWarnings("unused")
	private static WebApplicationContext getWebApplicationContext() {
		return WebApplicationContextUtils
				.getWebApplicationContext(getCurrentWebApplicationContext().getServletContext());
	}

	public static Object getBean(String idNomeBean) {
		return getWebApplicationContext().getBean(idNomeBean);
	}

	public static Object getBean(Class<?> classe) {
		return getWebApplicationContext().getBean(classe);
	}
	
}
