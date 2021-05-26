package com.wxw.notes.graphql.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wuxiongwei
 * @date 2021/5/26 9:01
 * @Description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Teacher {
    private String code;
    private String name;
    private String description;
}
