package com.jackyzeng.batch.mapper;

import com.example.demo.model.DBModel;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

public class SampleFieldSetMapper implements FieldSetMapper<DBModel> {

    @Override
    public DBModel mapFieldSet(FieldSet fieldSet) throws BindException {
        DBModel customObject = new DBModel();
        customObject.setField1(fieldSet.readString(0));
        customObject.setField2(fieldSet.readString(1));
        return customObject;
    }
}
