package com.cyxy.domian.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("collection")
public class Collection {
    @TableId
    private int id;
    private Integer fid;
//    private String name;
//    private String images;
    private String openid;
    private Integer delFlag;
}
