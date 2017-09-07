package br.com.efinoveworks.sgpjee.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value = ElementType.FIELD)
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
public abstract @interface IdentificaCampoPesquisa {

	String descricaoCampo(); // descri��o do campo na tela de pesquisa

	String campoConsulta(); // nome do campo da tabela no banco de dados

	int principal() default 10000; // posi��o que ir� aparecer no combobox

}
