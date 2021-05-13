package com.wxw.notes.protobuf.test;

import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.TextFormat;
import com.google.protobuf.util.JsonFormat;
import com.wxw.notes.protobuf.proto.DemoProto;

import java.util.Arrays;

public class SimpleTestMain {

    public static void main(String[] args) {

        //初始化数据
        DemoProto.Demo.Builder demo = DemoProto.Demo.newBuilder();
        demo.setId(1)
                .setCode("001")
                .setName("张三")
                .build();

        //序列化
        DemoProto.Demo build = demo.build();
        //转换成字节数组
        byte[] s = build.toByteArray();
        System.out.println("protobuf数据bytes[]:" + Arrays.toString(s));
        System.out.println("protobuf序列化大小: " + s.length);


        DemoProto.Demo demo1 = null;
        String jsonObject = null;
        try {
            //反序列化
            demo1 = DemoProto.Demo.parseFrom(s);
            //转 json
            jsonObject = JsonFormat.printer().print(demo1);

        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }

        System.out.println("Json格式化结果:\n" + jsonObject);
        System.out.println("Json格式化数据大小: " + jsonObject.getBytes().length);

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