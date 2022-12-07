package com.jackyzeng.database.handler;

import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.IOException;
import java.util.List;

public class ListTypeHandler extends JacksonTypeHandler {

    public ListTypeHandler(Class<?> type) {
        super(type);
    }

    @Override
    protected Object parse(String json) {
        try {
            return JacksonTypeHandler.getObjectMapper().readValue(json, new TypeReference<List<?>>() {
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
