package br.com.efinoveworks.sgpjee.dao.implementacao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.efinoveworks.sgpjee.dao.interfaces.InterfaceDao;
import br.com.efinoveworks.sgpjee.hibernate.session.HibernateUtil;

@Component
@Transactional
public class ImplementacaoDao<T> implements InterfaceDao<T> {

	private static final long serialVersionUID = 2709482220763940324L;

	private static SessionFactory sessionFactory = HibernateUtil.getSesionFactory();

	@Autowired
	private JdbcTemplateImpl jdbcTemplate;

	@Autowired
	private SimpleJdbcTemplateImpl simpleJdbcTemplate;

	@Autowired
	private SimpleJdbcInsertImpl simpleJdbcInsert;

	@Autowired
	private SimpleJdbcClassImpl simpleJdbcClass;

	@Autowired
	private SimpleJdbcTemplateImpl simpleJdbcTemplateImpl;

	private SimpleJdbcTemplateImpl getSimpleJdbcTemplateImpl() {
		return simpleJdbcTemplateImpl;
	}

	@Override
	public void save(T obj) throws Exception {
		validaSessionFactory();
		sessionFactory.getCurrentSession().save(obj);
		executeFlushSession();
	}

	@Override
	public void persist(T obj) throws Exception {
		validaSessionFactory();
		sessionFactory.getCurrentSession().save(obj);
		executeFlushSession();

	}

	@Override
	public void saveOrUpdate(T obj) throws Exception {
		validaSessionFactory();
		sessionFactory.getCurrentSession().saveOrUpdate(obj);
		executeFlushSession();

	}

	@Override
	public void update(T obj) throws Exception {
		validaSessionFactory();
		sessionFactory.getCurrentSession().update(obj);
		executeFlushSession();

	}

	@Override
	public void delete(T obj) throws Exception {
		validaSessionFactory();
		sessionFactory.getCurrentSession().delete(obj);
		executeFlushSession();

	}

	@SuppressWarnings("unchecked")
	@Override
	public T merge(T obj) throws Exception {
		validaSessionFactory();
		obj = (T) sessionFactory.getCurrentSession().merge(obj);
		executeFlushSession();
		return obj;
	}

	@Override
	public List<T> findList(Class<T> classe) throws Exception {

		validaSessionFactory();

		StringBuilder query = new StringBuilder();
		query.append("select distinct (class) from  ")
		     .append(classe.getSimpleName())
		     .append(" class ");

		@SuppressWarnings({ "unchecked" })
		List<T> lista = sessionFactory.getCurrentSession().createQuery(query.toString()).list();

		return lista;
	}

	@Override
	public Object findById(Class<T> entidade, Long id) throws Exception {
		
		validaSessionFactory();
		Object obj = sessionFactory.getCurrentSession().load(getClass(), id);
		return obj;
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public T findByPorId(Class<T> entidade, Long id) throws Exception {
		validaSessionFactory();
		T obj = (T) sessionFactory.getCurrentSession().load(getClass(), id);
		return obj;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findListByQueryDinamica(String s) throws Exception {
		
		validaSessionFactory();
		List<T> lista = new ArrayList<T>();
		lista = sessionFactory.getCurrentSession().createQuery(s).list();
		
		return lista;
	}

	@Override
	public void executeUpdateQueryDinamica(String s) throws Exception {
		validaSessionFactory();
		sessionFactory.getCurrentSession().createQuery(s).executeUpdate();
		executeFlushSession();
		
	}

	@Override
	public void executeUpdateSQLDinamica(String s) throws Exception {
		
		validaSessionFactory();
		sessionFactory.getCurrentSession().createSQLQuery(s).executeUpdate();
		executeFlushSession();
		
	}

	@Override
	public void clearSession() throws Exception {
		sessionFactory.getCurrentSession().clear();

	}

	@Override
	public void evict(Object obj) throws Exception {
		validaSessionFactory();
		sessionFactory.getCurrentSession().evict(obj);

	}

	@Override
	public Session getSession() throws Exception {
		validaSessionFactory();
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<?> getListSQLDinamica(String sql) throws Exception {
		validaSessionFactory();
		List<T> lista = sessionFactory.getCurrentSession().createQuery(sql).list();
		return lista;
	}

	@Override
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	@Override
	public SimpleJdbcTemplate getSimpleJdbcTemplate() {
		return simpleJdbcTemplate;
	}

	@Override
	public SimpleJdbcInsert getSimpleJdbcInsert() {
		return simpleJdbcInsert;
	}

	@Override
	public Long totalRegistro(String table) throws Exception {
		StringBuilder strSQL = new StringBuilder();
		strSQL.append("select count(1) from ").append(table);
		return jdbcTemplate.queryForLong(strSQL.toString());
	}

	@Override
	public Query obterQuery(String query) throws Exception {
		validaSessionFactory();
		Query qry = sessionFactory.getCurrentSession().createQuery(query.toString());
		return qry;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findListByQueryDinamica(String query, int iniciaNoRegsitro, int maximoResultado) throws Exception {
		validaSessionFactory();
		List<T> lista = new ArrayList<T>();
		lista = sessionFactory.getCurrentSession().createQuery(query).setFirstResult(iniciaNoRegsitro).setMaxResults(maximoResultado).list();
		return lista;
	}

	private void validaTransaction() {
		if (!sessionFactory.getCurrentSession().getTransaction().isActive()) {
			sessionFactory.getCurrentSession().beginTransaction();
		}
	}

	@SuppressWarnings("unused")
	private void commitProcessoAjax() {
		sessionFactory.getCurrentSession().beginTransaction().commit();
	}

	@SuppressWarnings("unused")
	private void rollbackProcessoAjax() {
		sessionFactory.getCurrentSession().beginTransaction().rollback();
	}

	private void validaSessionFactory() {
		if (sessionFactory == null) {
			sessionFactory = HibernateUtil.getSesionFactory();
		}
		validaTransaction();
	}

	private void executeFlushSession() {
		sessionFactory.getCurrentSession().flush();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getListSQLDinamicaArray(String sql) throws Exception {
		validaSessionFactory();
		List<Object[]> lista = (List<Object[]>) sessionFactory.getCurrentSession().createQuery(sql).list();
		return lista;
		
	}

}
