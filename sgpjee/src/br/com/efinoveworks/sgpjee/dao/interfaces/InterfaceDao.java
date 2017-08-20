package br.com.efinoveworks.sgpjee.dao.interfaces;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public interface InterfaceDao<T> extends Serializable {

	void save(T obj) throws Exception;

	void persist(T obj) throws Exception;

	void saveOrUpdate(T obj) throws Exception;

	void update(T obj) throws Exception;

	void delete(T obj) throws Exception;

	T merge(T obj) throws Exception;

	List<T> findList(Class<T> objs) throws Exception;

	Object findById(Class<T> entidade, Long id) throws Exception;

	T findByPorId(Class<T> entidade, Long id) throws Exception;

	List<T> findListByQueryDinamica(String s) throws Exception;

	void executeUpdateQueryDinamica(String s) throws Exception;

	void executeUpdateSQLDinamica(String s) throws Exception;

	void clearSession() throws Exception;

	void evict(Object obj) throws Exception;

	Session getSession() throws Exception;

	List<?> getListSQLDinamica(String g) throws Exception;

	JdbcTemplate getJdbcTemplate();

	SimpleJdbcTemplate getSimpleJdbcTemplate();

	SimpleJdbcInsert getSimpleJdbcInsert();

	Long totalRegistro(String table) throws Exception;

	Query obterQuery(String query) throws Exception;

	List<T> findListByQueryDinamica(String query, int iniciaNoRegsitro, int maximoResultado) throws Exception;

}
