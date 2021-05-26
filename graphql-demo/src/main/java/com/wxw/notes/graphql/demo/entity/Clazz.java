package com.wxw.notes.graphql.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author wuxiongwei
 * @date 2021/5/25 16:38
 * @Description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Clazz {
    private String id;
    private String code;
    private String name;
    private String description;
    private Teacher teacher;
    private List<Student> studentList;
}
