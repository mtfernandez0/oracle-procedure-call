package com.jdbc.demo.jdbcdemo.service;

import com.jdbc.demo.jdbcdemo.model.dto.DemoRecord;

import java.util.List;

public interface DemoService {

    List<DemoRecord> callDemoProcedure();
}
