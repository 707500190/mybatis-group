package com.dragon.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author sunshilong
 * @version 1.0
 * @date 2023/5/17
 */
@Data
@AllArgsConstructor
public class Employee {

    private String name;

    private Integer age;

    private Depart dept;
}
