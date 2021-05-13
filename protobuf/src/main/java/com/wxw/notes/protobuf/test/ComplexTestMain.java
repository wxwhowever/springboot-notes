package com.wxw.notes.protobuf.test;

import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.TextFormat;
import com.wxw.notes.protobuf.proto.RoleProto;
import com.wxw.notes.protobuf.proto.UserProto;

import java.util.Arrays;

public class ComplexTestMain {

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
            System.out.println("反序列化：\n" + user1.toString());
            System.out.println("中文反序列化：\n" + printToUnicodeString(user1));

        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }
//        System.out.println("***********************************************");
//        //中文反序列化时会转成八进制，可采用 TextFormat.printToUnicodeString 进行转换
//        System.out.println("直接反序列化：\n" + printToUnicodeString(user1));
    }


    /**
     * 处理反序列化时中文出现的八进制问题（属性值为中文时可能会出现这样的八进制\346\223\215\344\275\234\345\221\230）
     * 可直接使用 protobuf 自带的 TextFormat.printToUnicodeString(message) 方法，但是这个方法过时了，直接从这个方法内部拿出来使用就可以了
     *
     * @param message 转换的 protobuf 对象
     * @return string
     */
    public static String printToUnicodeString(MessageOrBuilder message) {
        return TextFormat.printer().escapingNonAscii(false).printToString(message);
    }

}