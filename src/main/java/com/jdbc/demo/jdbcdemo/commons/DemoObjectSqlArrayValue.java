package com.jdbc.demo.jdbcdemo.commons;

import com.jdbc.demo.jdbcdemo.model.dto.DemoObject;
import oracle.jdbc.driver.OracleConnection;
import org.springframework.jdbc.core.support.AbstractSqlTypeValue;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Struct;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

public class DemoObjectSqlArrayValue extends AbstractSqlTypeValue {

    private final List<DemoObject> values;
    private static final String T_OBJECT = "DEMO_SCHEMA.T_OBJECT";
    private static final String R_OBJECT = "DEMO_SCHEMA.R_OBJECT";
    public DemoObjectSqlArrayValue(List<DemoObject> values) {
        this.values = values;
    }

    @Override
    protected Object createTypeValue(Connection con, int sqlType, String typeName) throws SQLException {

        Struct[] structs = new Struct[values.size()];
        OracleConnection oracleCon = con.unwrap(OracleConnection.class);

        int i = 0;
        for (DemoObject demoObject : values) {

            structs[i] = oracleCon.createStruct(R_OBJECT, demoObjectMapper(demoObject));
            i++;
        }

        return oracleCon.createOracleArray(T_OBJECT, structs);
    }

    private Object[] demoObjectMapper(DemoObject demoObject) {

        Object[] row = new Object[3];
        row[0] = demoObject.getUserId();
        row[1] = demoObject.getUsername();
        row[2] = demoObject.getCreationDate() == null ? Timestamp.from(Instant.now()) : Timestamp.valueOf(demoObject.getCreationDate());
        return row;
    }
}
