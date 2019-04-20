//package com.orangelabs.RestaurantDemo.dao;
//
//import java.util.List;
//import java.util.Optional;
//
//import javax.persistence.EntityManager;
//import javax.transaction.Transactional;
//
//import org.hibernate.Session;
//import org.hibernate.query.Query;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Example;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.stereotype.Repository;
//
//import com.orangelabs.RestaurantDemo.entity.TableEntity;
//
//@Repository
//public class TableDao implements TableDaoInterface {
//	
//	private EntityManager entityManager;
//	
//	@Autowired
//	public TableDao(EntityManager entityManagerToEnject) {
//		this.entityManager = entityManagerToEnject;
//	}
//	
//	
//	public List<TableEntity> getAllTables(){
//		Session session = entityManager.unwrap(Session.class);
//		
//		Query<TableEntity> query =
//				session.createQuery("from TableEntity", TableEntity.class);
//		
//		List<TableEntity> tables = query.getResultList();
//		
//		return tables;
//	}
//	
//	public void saveTable(TableEntity newTable) {
//		Session session = entityManager.unwrap(Session.class);
//		session.saveOrUpdate(newTable);
//	}
//
//
//	@Override
//	public List<TableEntity> findAll() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//
//	@Override
//	public List<TableEntity> findAll(Sort sort) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//
//	@Override
//	public List<TableEntity> findAllById(Iterable<Integer> ids) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//
//	@Override
//	public <S extends TableEntity> List<S> saveAll(Iterable<S> entities) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//
//	@Override
//	public void flush() {
//		// TODO Auto-generated method stub
//		
//	}
//
//
//	@Override
//	public <S extends TableEntity> S saveAndFlush(S entity) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//
//	@Override
//	public void deleteInBatch(Iterable<TableEntity> entities) {
//		// TODO Auto-generated method stub
//		
//	}
//
//
//	@Override
//	public void deleteAllInBatch() {
//		// TODO Auto-generated method stub
//		
//	}
//
//
//	@Override
//	public TableEntity getOne(Integer id) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//
//	@Override
//	public <S extends TableEntity> List<S> findAll(Example<S> example) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//
//	@Override
//	public <S extends TableEntity> List<S> findAll(Example<S> example, Sort sort) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//
//	@Override
//	public Page<TableEntity> findAll(Pageable pageable) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//
//	@Override
//	public <S extends TableEntity> S save(S entity) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//
//	@Override
//	public Optional<TableEntity> findById(Integer id) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//
//	@Override
//	public boolean existsById(Integer id) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//
//	@Override
//	public long count() {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//
//	@Override
//	public void deleteById(Integer id) {
//		// TODO Auto-generated method stub
//		
//	}
//
//
//	@Override
//	public void delete(TableEntity entity) {
//		// TODO Auto-generated method stub
//		
//	}
//
//
//	@Override
//	public void deleteAll(Iterable<? extends TableEntity> entities) {
//		// TODO Auto-generated method stub
//		
//	}
//
//
//	@Override
//	public void deleteAll() {
//		// TODO Auto-generated method stub
//		
//	}
//
//
//	@Override
//	public <S extends TableEntity> Optional<S> findOne(Example<S> example) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//
//	@Override
//	public <S extends TableEntity> Page<S> findAll(Example<S> example, Pageable pageable) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//
//	@Override
//	public <S extends TableEntity> long count(Example<S> example) {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//
//	@Override
//	public <S extends TableEntity> boolean exists(Example<S> example) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//}
