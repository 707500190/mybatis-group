package com.dragon.entity;


import lombok.Data;

import java.util.Date;

/**
 * @author sunshilong
 * @version 1.0
 * @date 2023/3/20
 */
@Data
public class Content {

    private Integer id;

    private String account;

    private String title;

    private Integer version;

    private Date timestamp;
}
