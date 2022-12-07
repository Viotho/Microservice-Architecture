package com.jackyzeng.batch.listener;

import com.example.demo.model.DBModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ItemReadListener;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Could also use @BeforeRead/@AfterRead/@OnReadError
 */

@Slf4j
@Component
public class SampleItemReadListener implements ItemReadListener<List<DBModel>> {

    @Override
    public void afterRead(List<DBModel> DBModelList) {
        log.info("----------After Read----------");
    }

    @Override
    public void beforeRead() {
        log.info("----------Before Read----------");
    }

    @Override
    public void onReadError(Exception e) {
        log.error("On Read Error:" + e.getMessage());
    }
}
