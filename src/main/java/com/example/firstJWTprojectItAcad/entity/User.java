package com.example.firstJWTprojectItAcad.entity;

import lombok.Data;

import javax.persistence.*;
@Data
@Entity
@Table(name = "user_table")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String userName;
    private String password;
    private String email;
}
