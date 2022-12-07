package com.jackyzeng.batch.listener;

import com.example.demo.model.DBModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.SkipListener;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Could also use @/@/@
 */

@Slf4j
@Component
public class SampleSkipListener implements SkipListener<List<DBModel>, List<DBModel>> {

    @Override
    public void onSkipInRead(Throwable throwable) {
        log.info("----------On Skip Read----------");
    }

    @Override
    public void onSkipInWrite(List<DBModel> DBModels, Throwable throwable) {
        log.info("----------On Skip Write----------");
    }

    @Override
    public void onSkipInProcess(List<DBModel> DBModels, Throwable throwable) {
        log.info("-----------On Skip Process----------");
    }
}
