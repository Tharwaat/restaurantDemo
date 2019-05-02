package com.orangelabs.RestaurantDemo.dao;

import java.sql.Date;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.orangelabs.RestaurantDemo.entity.TableEntity;

public interface TableDaoInterface extends JpaRepository<TableEntity, Integer> {
	@Query(value="SELECT * FROM restaurant_table AS a "
				 + "LEFT JOIN reservation AS b ON a.id = b.table_id WHERE b.table_id IS NULL "
				 + "UNION "
				 + "SELECT * FROM restaurant_table AS a "
				 + "INNER JOIN reservation AS b ON a.id = b.table_id WHERE  b.date !=  ?1", 
				nativeQuery = true)
	public Set<TableEntity> getAvailableTables(Date availableDate);
}
