package com.jackyzeng.batch.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "db_table")
public class DBModel {

    @Id
    private Integer id;

    @Column(name = "field1")
    private String field1;

    @Column(name = "field2")
    private String field2;

}
