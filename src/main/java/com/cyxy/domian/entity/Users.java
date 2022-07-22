package com.cyxy.domian.entity;




import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;



@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("users")
public class Users {
    @TableId


    @JsonSerialize(using = ToStringSerializer.class)

    private Long id;

    
    private String openid;
    
    private String avatarUrl;
    
    private String nickName;
    
    private String sex;
    
    private String phone;
    
    private Date registerdate;
    
    private Date lastlogindate;

    private String autograph;

    private Integer type;

    private String username;
    private String password;
    private Integer delFlag;

    @TableField(exist = false)
    private String code;





}
