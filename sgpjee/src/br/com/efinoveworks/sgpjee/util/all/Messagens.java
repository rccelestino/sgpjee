package br.com.efinoveworks.sgpjee.util.all;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public abstract class Messagens extends FacesContext implements Serializable {

	private static final long serialVersionUID = 6972997964676750970L;

	public Messagens() {

	}

	public static FacesContext getFacesContext() {
		return FacesContext.getCurrentInstance();
	}

	private static boolean facesContextValido() {
		return getFacesContext() != null;
	}

	public static void msgSeverityWarning(String msg) {

		if (facesContextValido()) {
			getFacesContext().addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_WARN, msg, msg));
		}

	}

	public static void msgSeverityFatal(String msg) {

		if (facesContextValido()) {
			getFacesContext().addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_FATAL, msg, msg));
		}

	}

	public static void msgSeverityError(String msg) {

		if (facesContextValido()) {
			getFacesContext().addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
		}

	}

	public static void msgSeverityInfo(String msg) {

		if (facesContextValido()) {
			getFacesContext().addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_WARN, msg, msg));
		}

	}

	public static void sucesso() {
		msgSeverityInfo(Constante.OPERACAO_REALIZADA_COM_SUCESSO);
	}

	public static void erroNaOperacao() {

		if (facesContextValido()) {
			msgSeverityFatal(Constante.ERRO_NA_OPERACAO);
		}

	}

	public static void msg(String msg) {

		if (facesContextValido()) {
			getFacesContext().addMessage("msg", new FacesMessage(msg));
		}

	}

	public static void responseOperation(EstatusPersistencia estatusPersistencia) {

		if (estatusPersistencia != null && estatusPersistencia.equals(EstatusPersistencia.SUCESSO)) {
			sucesso();
		} else if (estatusPersistencia != null && estatusPersistencia.equals(EstatusPersistencia.OBJETO_REFERENCIADO)) {
			msgSeverityFatal(EstatusPersistencia.OBJETO_REFERENCIADO.toString());
		} else {
			erroNaOperacao();
		}

	}

}