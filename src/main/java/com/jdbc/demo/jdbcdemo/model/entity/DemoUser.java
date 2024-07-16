package com.jdbc.demo.jdbcdemo.model.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "DEMO_USER")
public class DemoUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DEMO_USER_ID")
    private Long id;

    private String username;

    private String pass;

    private LocalDateTime creationDate;
}
