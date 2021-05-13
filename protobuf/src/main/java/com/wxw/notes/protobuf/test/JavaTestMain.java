package com.wxw.notes.protobuf.test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.protobuf.InvalidProtocolBufferException;
import com.wxw.notes.protobuf.entity.User;
import com.wxw.notes.protobuf.proto.RoleProto;
import com.wxw.notes.protobuf.proto.UserProto;
import com.wxw.notes.protobuf.util.ProtoJsonUtil;
import org.springframework.beans.BeanUtils;

import java.util.Arrays;

public class JavaTestMain {

    public static void main(String[] args) {

        //初始化数据
        UserProto.User.Builder user = UserProto.User.newBuilder();
        user.setId(1)
                .setCode("001")
                .setName("张三")
                .build();

        //内部对象
        UserProto.NickName.Builder nickName = UserProto.NickName.newBuilder();
        user.setNickName(nickName.setNickName("昵称").build());

        //简单 list
        user.addStrList("01");
        user.addStrList("02");

        //object list
        RoleProto.Role.Builder role1 = RoleProto.Role.newBuilder();
        role1.setCode("001");
        role1.setName("管理员");

        RoleProto.Role.Builder role2 = RoleProto.Role.newBuilder();
        role2.setCode("002");
        role2.setName("操作员");
        user.addRoleList(role1);
        user.addRoleList(role2);

        //简单 map
        user.putMap("key1", "value1");
        user.putMap("key2", "value2");

        //object map
        UserProto.MapVauleObject.Builder objectMap1 = UserProto.MapVauleObject.newBuilder();
        user.putMapObject("objectMap1", objectMap1.setCode("code1").setName("name1").build());

        UserProto.MapVauleObject.Builder objectMap2 = UserProto.MapVauleObject.newBuilder();
        user.putMapObject("objectMap2", objectMap2.setCode("code2").setName("name2").build());


        //序列化
        UserProto.User build = user.build();
        //转换成字节数组
        byte[] s = build.toByteArray();
        System.out.println("protobuf数据bytes[]:" + Arrays.toString(s));
        System.out.println("protobuf序列化大小: " + s.length);


        UserProto.User user1 = null;
        String jsonObject = null;
        try {
            //反序列化
            user1 = UserProto.User.parseFrom(s);
            //proto 转 json
            jsonObject = ProtoJsonUtil.toJson(user1);
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }

        System.out.println("Json格式化结果:\n" + jsonObject);
        System.out.println("Json格式化数据大小: " + jsonObject.getBytes().length);

        // 复制 proto 对象到 Java 对象 测试,测试下来只能复制基础的属性，内部对象等不可以复制，也就是只有浅拷贝
        User user2 = new User();
        BeanUtils.copyProperties(user1, user2);
        System.out.println("复制后对象：\n " + user2.toString());


        //通过 proto Json 数据转 Java 对象
        Gson GSON = new GsonBuilder().serializeNulls().create();
        User user3 = GSON.fromJson(jsonObject, User.class);
        System.out.println("json 转换之后对象：\n " + user3.toString());

    }

}