package com.cyxy.domian.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("foods")
public class Foods {
    @TableId
    private Integer id;
    private String name;
    private Integer tid;
    private String images;
    private String content;
    private String material;
    private Integer flag;
    private Integer delFlag;
}
