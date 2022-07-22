package com.cyxy.domian.vo;

import com.cyxy.domian.entity.Foods;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearechVo<T> {
    private T rows;
    private long total;
    private int pageSize;
    private int currentPage;
}
