package com.wxw.notes.graphql.demo.resolver;

import com.wxw.notes.graphql.demo.entity.Teacher;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wuxiongwei
 * @date 2021/5/25 16:37
 * @Description
 */
@Component
public class TeacherResolver implements GraphQLQueryResolver {

    private List<Teacher> teacherList = new ArrayList<Teacher>(){{
        add(Teacher.builder().code("A01").name("张大炮").build());
    }};

    public List<Teacher> listTeacher(){
        return teacherList;
    }

}
