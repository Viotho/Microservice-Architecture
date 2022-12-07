package com.jackyzeng.database.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.jackyzeng.database.config.MybatisPlusAutoFillProperties;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class DateMetaObjectHandler implements MetaObjectHandler {

    @Autowired
    private MybatisPlusAutoFillProperties autoFillProperties;

    /**
     * 是否开启了插入填充
     */
    @Override
    public boolean openInsertFill() {
        return autoFillProperties.getEnableInsertFill();
    }

    /**
     * 是否开启了更新填充
     */
    @Override
    public boolean openUpdateFill() {
        return autoFillProperties.getEnableUpdateFill();
    }

    /**
     * 插入填充，字段为空自动填充
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        Object createTime = getFieldValByName(autoFillProperties.getCreateTimeField(), metaObject);
        Object updateTime = getFieldValByName(autoFillProperties.getUpdateTimeField(), metaObject);
        if (createTime == null || updateTime == null) {
            Date date = new Date();
            if (createTime == null) {
                setFieldValByName(autoFillProperties.getCreateTimeField(), date, metaObject);
            }
            if (updateTime == null) {
                setFieldValByName(autoFillProperties.getUpdateTimeField(), date, metaObject);
            }
        }
    }

    /**
     * 更新填充
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        setFieldValByName(autoFillProperties.getUpdateTimeField(), new Date(), metaObject);
    }
}