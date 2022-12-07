package com.jackyzeng.batch.repository;

import com.example.demo.model.DBModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataRepository extends JpaRepository<DBModel, Integer> {

}
