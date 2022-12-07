package com.jackyzeng.batch.listener;

import com.example.demo.model.DBModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ItemProcessListener;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Could also use @BeforeProcess/@AfterProcess/@OnProcessError
 */

@Slf4j
@Component
public class SampleItemProcessListener implements ItemProcessListener<List<DBModel>, List<DBModel>> {

    @Override
    public void beforeProcess(List<DBModel> DBModels) {
        log.info("----------Before Process----------");
    }

    @Override
    public void afterProcess(List<DBModel> DBModels, List<DBModel> DBModels2) {
        log.info("----------After Process----------");
    }

    @Override
    public void onProcessError(List<DBModel> DBModels, Exception e) {
        log.error("On Process Error: " + e.getMessage());
    }
}
