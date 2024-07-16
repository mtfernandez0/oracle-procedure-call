package com.jdbc.demo.jdbcdemo.commons;

import com.jdbc.demo.jdbcdemo.model.dto.DemoRecord;
import org.springframework.jdbc.core.SqlReturnType;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DemoRecordSqlReturnType implements SqlReturnType {

    @Override
    public Object getTypeValue(CallableStatement cs, int paramIndex, int sqlType, String typeName) throws SQLException {

        Array array = cs.getArray(paramIndex);
        Object[] structs = (Object[]) array.getArray();
        List<DemoRecord> objects = new ArrayList<>(structs.length);

        for (Object struct : structs) {

            Struct s1 = (Struct) struct;
            objects.add(rowToDemoRecord(s1.getAttributes()));
        }
        return objects;
    }

    private DemoRecord rowToDemoRecord(Object[] attributes) {

        return DemoRecord
                .builder()
                .id(((BigDecimal) attributes[0]).longValue())
                .creationDate(((Timestamp) attributes[1]).toLocalDateTime())
                .customUsername((String) attributes[2])
                .build();
    }
}
