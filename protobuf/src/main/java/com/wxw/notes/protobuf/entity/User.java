package com.wxw.notes.protobuf.entity;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author wuxiongwei
 * @date 2021/5/13 14:55
 * @Description
 */
@Data
public class User {

    private int id;
    private String code;
    private String name;
    private NickName nickName;
    private List<String> strList;
    private List<Role> roleList;
    private Map<String,String> map;
    private Map<String,MapVauleObject> mapObject;

}
