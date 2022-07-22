package com.cyxy.domian.entity;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("link")
public class Link {
    @TableId
    private Long id;

    
    private String name;
    
    private String logo;
    
    private String description;
    
    private String address;
    
    private String status;
    
    private Integer delFlag;



}
