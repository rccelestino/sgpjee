package br.com.efinoveworks.sgpjee.listener;

import java.io.Serializable;

import org.hibernate.envers.RevisionListener;
import org.springframework.stereotype.Service;

import br.com.efinoveworks.sgpjee.model.classes.Entidade;
import br.com.efinoveworks.sgpjee.model.classes.InformacaoDeRevisao;
import br.com.efinoveworks.sgpjee.util.UtilFramework;

@Service
public class CustomListener implements RevisionListener, Serializable {

	private static final long serialVersionUID = -88370724552960774L;

	@SuppressWarnings("unused")
	@Override
	public void newRevision(Object revisionEntity) {

		InformacaoDeRevisao informacaoRevisao = (InformacaoDeRevisao) revisionEntity;

		Long codUser = UtilFramework.geThreadLocal().get();

		Entidade entidade = new Entidade();

		if (codUser != null && codUser != 0L) {
	
			entidade.setEnt_codigo(codUser);
			informacaoRevisao.setEntidade(entidade);

		}

		
	}

}
