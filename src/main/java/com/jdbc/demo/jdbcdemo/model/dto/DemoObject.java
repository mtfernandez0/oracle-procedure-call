package com.jdbc.demo.jdbcdemo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DemoObject {

    private String username;
    private Long userId;                // user_id
    private LocalDateTime creationDate; // creation_date
}
