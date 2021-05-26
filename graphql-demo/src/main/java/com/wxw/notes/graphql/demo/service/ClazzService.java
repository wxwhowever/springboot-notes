package com.wxw.notes.graphql.demo.service;

import com.wxw.notes.graphql.demo.entity.Clazz;
import com.wxw.notes.graphql.demo.entity.Student;
import com.wxw.notes.graphql.demo.entity.Teacher;
import com.wxw.notes.graphql.demo.response.Result;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author wuxiongwei
 * @date 2021/5/25 16:38
 * @Description
 */
@Service
public class ClazzService {

    private List<Clazz> clazzList = new ArrayList<Clazz>(){{
        List<Student> studentList = new ArrayList<Student>(){{
            add(Student.builder().id(UUID.randomUUID().toString()).code("001").name("张三").description("法外狂徒").build());
            add(Student.builder().id(UUID.randomUUID().toString()).code("002").name("李四").description("路人之王").build());
        }};
        add(Clazz.builder()
                .id(UUID.randomUUID().toString())
                .code("001")
                .name("三年二班")
                .description("加里顿第一班")
                .studentList(studentList)
                .teacher(Teacher.builder().code("A001").name("班主任").build())
                .build());
    }};


    public Clazz listByName(String name){
        Optional<Clazz> first = clazzList.stream().filter(clazz -> clazz.getName().equals(name)).findFirst();
        return first.orElse(null);
    }

    public List<Clazz> list(){
        return clazzList;
    }

    public Result add(String code, String name, String description){
        Clazz clazz = new Clazz();
        clazz.setId(UUID.randomUUID().toString());
        clazz.setCode(code);
        clazz.setName(name);
        clazz.setDescription(description);

        clazzList.add(clazz);

        return Result.builder().code(200).msg("success").build();
    }

    public Result del(String id){

        clazzList = clazzList.stream().filter(clazz -> !clazz.getId().equals(id)).collect(Collectors.toList());
        return Result.builder().code(200).msg("success").build();
    }

    public Result edit(String id,String code, String name, String description){
        del(id);
        add(code, name, description);
        return Result.builder().code(200).msg("success").build();
    }

    public Result addByInput(Clazz cla){
        Clazz clazz = new Clazz();
        clazz.setId(UUID.randomUUID().toString());
        clazz.setCode(cla.getCode());
        clazz.setName(cla.getName());
        clazz.setDescription(cla.getDescription());
        clazz.setTeacher(cla.getTeacher());
        clazz.setStudentList(cla.getStudentList());
        clazzList.add(clazz);
        return Result.builder().code(200).msg("success").build();
    }
}
