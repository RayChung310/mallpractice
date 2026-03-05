package com.practice.mallpractice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class OrderQueryParams {

    private Integer userId;
    private Integer limit;
    private Integer offset;

}
