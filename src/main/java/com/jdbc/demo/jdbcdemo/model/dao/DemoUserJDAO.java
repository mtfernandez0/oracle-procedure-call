package com.jdbc.demo.jdbcdemo.model.dao;

import com.jdbc.demo.jdbcdemo.model.entity.DemoUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DemoUserJDAO extends JpaRepository<DemoUser, Long> {
}
