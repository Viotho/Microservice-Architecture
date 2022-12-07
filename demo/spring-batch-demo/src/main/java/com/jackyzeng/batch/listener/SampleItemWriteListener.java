package com.jackyzeng.batch.listener;

import com.example.demo.model.DBModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ItemWriteListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class SampleItemWriteListener implements ItemWriteListener<List<DBModel>> {

    @Override
    public void beforeWrite(List<? extends List<DBModel>> list) {
        log.info("----------Before Write----------");
    }

    @Override
    public void afterWrite(List<? extends List<DBModel>> list) {
        log.info("----------After Write----------");
    }

    @Override
    public void onWriteError(Exception e, List<? extends List<DBModel>> list) {
        log.error("Write Error: " + e.getMessage());
    }
}
