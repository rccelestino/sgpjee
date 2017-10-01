package br.com.efinoveworks.sgpjee.model.classes;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

@Entity
@Table(name = "Entidade")
@Audited
public class Entidade implements Serializable {

	private static final long serialVersionUID = -5002463209707163053L;

	@Id
	@Column(name = "Codigo", nullable = false, unique = true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ent_codigo;

	@Column(name = "Login", nullable = false, length = 100)
	private String ent_login = null;

	@Column(name = "Senha", nullable = false, length = 100)
	private String ent_senha = null;

	public Entidade() {

	}

	public Long getEnt_codigo() {
		return ent_codigo;
	}

	public void setEnt_codigo(Long ent_codigo) {
		this.ent_codigo = ent_codigo;
	}

	public String getEnt_login() {
		return ent_login;
	}

	public void setEnt_login(String ent_login) {
		this.ent_login = ent_login;
	}

	public String getEnt_senha() {
		return ent_senha;
	}

	public void setEnt_senha(String ent_senha) {
		this.ent_senha = ent_senha;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ent_codigo == null) ? 0 : ent_codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Entidade other = (Entidade) obj;
		if (ent_codigo == null) {
			if (other.ent_codigo != null)
				return false;
		} else if (!ent_codigo.equals(other.ent_codigo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Entidade [id=" + ent_codigo + ", ent_login=" + ent_login + ", ent_senha=" + ent_senha + "]";
	}

}
