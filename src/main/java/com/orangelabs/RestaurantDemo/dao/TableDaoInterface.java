package com.orangelabs.RestaurantDemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.orangelabs.RestaurantDemo.entity.TableEntity;

public interface TableDaoInterface extends JpaRepository<TableEntity, Integer> {

}
