package com.jdbc.demo.jdbcdemo.service.impl;

import com.jdbc.demo.jdbcdemo.commons.DemoObjectSqlArrayValue;
import com.jdbc.demo.jdbcdemo.commons.DemoRecordSqlReturnType;
import com.jdbc.demo.jdbcdemo.model.dto.DemoObject;
import com.jdbc.demo.jdbcdemo.model.dto.DemoRecord;
import com.jdbc.demo.jdbcdemo.service.DemoService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import oracle.jdbc.OracleTypes;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class DemoServiceImpl implements DemoService {

    private final JdbcTemplate jdbcTemplate;
    private static final String PROCEDURE_NAME = "demo_procedure";
    private static final String SCHEMA_NAME = "DEMO_SCHEMA";
    private static final String CATALOG_NAME = "DEMO_PKG";
    private static final String ORACLE_TYPE_IN = SCHEMA_NAME.concat(".").concat("T_OBJECT");
    private static final String ORACLE_TYPE_OUT = SCHEMA_NAME.concat(".").concat(CATALOG_NAME).concat(".").concat("T_RECORD");

    @Override
    public List<DemoRecord> callDemoProcedure() {

        List<DemoObject> objects = new ArrayList<>();

        for (int i = 0; i < 50000; i++)
            objects.add(new DemoObject(UUID.randomUUID().toString().substring(0, 25), (long) i, LocalDateTime.now()));

        MapSqlParameterSource params = new MapSqlParameterSource().addValue("p_list", new DemoObjectSqlArrayValue(objects));

        try {

            List<DemoRecord> result = (List<DemoRecord>) buildCall().executeFunction(List.class, params);

//            List<DemoRecord> records = (List<DemoRecord>) result.get("p_out");

            log.info(String.valueOf(result.size()));

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

    private SimpleJdbcCall buildCall() {

        return new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName(SCHEMA_NAME)
                .withCatalogName(CATALOG_NAME)
                .withProcedureName(PROCEDURE_NAME)
                .withoutProcedureColumnMetaDataAccess()
                .declareParameters(
                        new SqlParameter("p_list", OracleTypes.ARRAY, ORACLE_TYPE_IN),
                        new SqlOutParameter("p_out", OracleTypes.ARRAY, ORACLE_TYPE_OUT, new DemoRecordSqlReturnType())
                );
    }
}
