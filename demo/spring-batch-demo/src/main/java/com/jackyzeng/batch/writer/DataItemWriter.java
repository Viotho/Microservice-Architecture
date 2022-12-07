package com.jackyzeng.batch.writer;

import com.example.demo.model.DBModel;
import com.example.demo.repository.DataRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * This writer class is used to update records In Database
 */

@Slf4j
public class DataItemWriter implements ItemWriter<List<DBModel>> {

    @Autowired
    private DataRepository dataRepository;

    private List<DBModel> consolidatedList;

    @Override
    public void write(List<? extends List<DBModel>> list) throws Exception {

        log.info("Writer Initiated");
        consolidatedList = new ArrayList<>();
        list.forEach(l -> consolidatedList.addAll(l));

        log.info("Step - UPDATE RECORDS STATUS IN DB");
        dataRepository.saveAll(consolidatedList);
    }
}
