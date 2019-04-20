package com.orangelabs.RestaurantDemo.dao;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.orangelabs.RestaurantDemo.entity.TableEntity;

public interface TableDaoInterface extends JpaRepository<TableEntity, Integer> {
	@Query(value="SELECT * FROM restaurant_table AS a left join reservation As b ON a.id = b.table_id WHERE b.id IS NULL AND b.date != ?1", 
				nativeQuery = true)
	public List<TableEntity> getAvailableTables(Date availableDate);
}
