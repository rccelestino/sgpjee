package br.com.efinoveworks.sgpjee.dao.implementacao;

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
	
	private SimpleJdbcTemplateImpl getSimpleJdbcTemplateImpl(){
		return simpleJdbcTemplateImpl;
	}
	
	@Override
	public void save(T obj) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void persist(T obj) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void saveOrUpdate(T obj) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(T obj) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(T obj) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public T merge(T obj) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> findList(Class<T> objs) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object findById(Class<T> entidade, Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T findByPorId(Class<T> entidade, Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> findListByQueryDinamica(String s) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void executeUpdateQueryDinamica(String s) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void executeUpdateSQLDinamica(String s) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void clearSession() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void evict(Object obj) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public Session getSession() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<?> getListSQLDinamica(String g) throws Exception {
		return null;
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
		return null;
	}

	@Override
	public Query obterQuery(String query) throws Exception {
		return null;
	}

	@Override
	public List<T> findListByQueryDinamica(String query, int iniciaNoRegsitro, int maximoResultado) throws Exception {
		return null;
	}
	
	private void validaTransaction(){
		if (!sessionFactory.getCurrentSession().getTransaction().isActive()){
			sessionFactory.getCurrentSession().beginTransaction();
		}
	}
	
	@SuppressWarnings("unused")
	private void commitProcessoAjax(){
		sessionFactory.getCurrentSession().beginTransaction().commit();
	}

	@SuppressWarnings("unused")
	private void rollbackProcessoAjax(){
		sessionFactory.getCurrentSession().beginTransaction().rollback();
	}
	
	@SuppressWarnings("unused")
	private void validaSessionFactory(){
		if (sessionFactory == null) {
			sessionFactory = HibernateUtil.getSesionFactory();
		}
		validaTransaction();
	}
	
}
