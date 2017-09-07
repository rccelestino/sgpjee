package br.com.efinoveworks.sgpjee.util;

import java.io.Serializable;

public class UtilFramework implements Serializable {

	private static final long serialVersionUID = -1960539791178485615L;

	private static ThreadLocal<Long> threadLocal = new ThreadLocal<Long>();

	public synchronized static ThreadLocal<Long> geThreadLocal() {
		return threadLocal;
	}

}
