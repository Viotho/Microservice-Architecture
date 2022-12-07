package com.jackyzeng.batch.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ChunkListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SampleChunkListener implements ChunkListener {

    @Override
    public void beforeChunk(ChunkContext chunkContext) {
        log.info("----------Before Chunk----------");
    }

    @Override
    public void afterChunk(ChunkContext chunkContext) {
        log.info("----------After Chunk----------");
    }

    @Override
    public void afterChunkError(ChunkContext chunkContext) {
        log.info("----------After Chunk Error----------");
    }
}
