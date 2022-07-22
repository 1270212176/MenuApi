package com.cyxy.domian.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsersVo {

    @JsonSerialize(using = ToStringSerializer.class)

    private Long id;
    private String avatarUrl;

    private String nickName;

    private String sex;

    private String phone;
    private String autograph;
    private String username;
    private int total;
}
