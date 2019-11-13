package com.xin.entity;

import lombok.Data;

/**
 * 
 *  
 * @author creator mafh 2019/11/13 18:15
 * @author updater
 *
 * @version 1.0.0
 */
@Data
public class User {
    private Integer id;

    private String username;

    private String password;

    private Integer age;
}