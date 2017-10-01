package br.com.efinoveworks.sgpjee.model.classes;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;
import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionEntity;

import br.com.efinoveworks.sgpjee.listener.CustomListener;

@Entity
@Table(name = "revinfo")
@RevisionEntity(CustomListener.class)
public class InformacaoDeRevisao extends DefaultRevisionEntity implements Serializable {

	private static final long serialVersionUID = -5248147310688872145L;

	@ManyToOne
	@ForeignKey(name = "entidade_fk")
	@JoinColumn(nullable = false, name = "entidade")
	private Entidade entidade;

	public InformacaoDeRevisao() {

	}

	public Entidade getEntidade() {
		return entidade;
	}

	public void setEntidade(Entidade entidade) {
		this.entidade = entidade;
	}

}
