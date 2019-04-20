package com.orangelabs.RestaurantDemo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.orangelabs.RestaurantDemo.entity.TableEntity;

@Repository
public class TableDao {
	
	private EntityManager entityManager;
	
	@Autowired
	public TableDao(EntityManager entityManagerToEnject) {
		this.entityManager = entityManagerToEnject;
	}
	
	
	public List<TableEntity> getAllTables(){
		Session session = entityManager.unwrap(Session.class);
		
		Query<TableEntity> query =
				session.createQuery("from TableEntity", TableEntity.class);
		
		List<TableEntity> tables = query.getResultList();
		
		return tables;
	}
	
	public void saveTable(TableEntity newTable) {
		Session session = entityManager.unwrap(Session.class);
		session.saveOrUpdate(newTable);
	}
}
