package com.jdbc.demo.jdbcdemo.model.dto;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DemoRecord {

    private Long id;
    private LocalDateTime creationDate;
    private String customUsername;
}
