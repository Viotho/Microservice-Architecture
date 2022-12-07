package com.jackyzeng.batch.reader;

import com.example.demo.model.DBModel;
import com.example.demo.repository.DataRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

/**
 * This is reader class which will fetch the Records from DB.
 */

@Slf4j
public class DataItemReader implements ItemReader<List<DBModel>> {

    @Autowired
    private DataRepository dataRepository;

    @Override
    public List<DBModel> read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        log.info("Reader Initiated");
        List<DBModel> modelList = dataRepository.findAll();
        if (!CollectionUtils.isEmpty(modelList)) {
            log.info("Step - FETCHED " + modelList.size() + " RECORDS");
            return modelList;
        } else {
            log.info("No Records Found in this Job Run");
            return Collections.emptyList();
        }
    }
}
