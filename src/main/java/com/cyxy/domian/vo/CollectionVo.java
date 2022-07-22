package com.cyxy.domian.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CollectionVo {
    private int id;
    private Integer fid;
    private String name;
    private String images;
    private String openid;
}
