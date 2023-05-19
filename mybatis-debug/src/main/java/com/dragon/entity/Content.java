package com.dragon.entity;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author sunshilong
 * @version 1.0
 * @date 2023/3/20
 */
@Data
//开启二级缓存 实体类必须实现序列化
public class Content implements Serializable {

    private Integer id;

    private String account;

    private String title;

    private Integer version;

    private Date timestamp;
}
