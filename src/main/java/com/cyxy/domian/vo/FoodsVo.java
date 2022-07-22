package com.cyxy.domian.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoodsVo {
    private Integer id;
    private String name;
    private Integer tid;
    private String tname;
    private String images;
    private String content;
    private String material;
    private Integer flag;
    private String type;
    private int total;
}
