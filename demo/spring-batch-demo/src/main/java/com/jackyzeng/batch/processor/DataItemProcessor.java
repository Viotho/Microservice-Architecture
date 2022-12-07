package com.jackyzeng.batch.processor;

import com.example.demo.model.DBModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;

import java.util.List;

@Slf4j
public class DataItemProcessor implements ItemProcessor<List<DBModel>, List<DBModel>> {

    @Override
    public List<DBModel> process(List<DBModel> DBModels) throws Exception {
        log.info("Processor Initiated");
        return DBModels;
    }
}
