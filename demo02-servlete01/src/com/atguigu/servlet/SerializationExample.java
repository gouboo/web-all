package com.atguigu.servlet;

/**
 * ClassName: SerializationExample
 * Package: com.atguigu.servlet
 * Description: 序列化案例
 *
 * @Author: bushG
 * @Create: 2024/6/21 13:40
 * @Version: 1.0
 */

import java.io.*;

public class SerializationExample {
    public static void main(String[] args) {
        try {
            // 创建对象
            MySerializableObject obj = new MySerializableObject("Hello, World!");

            // 序列化对象
            FileOutputStream fos = new FileOutputStream("object.data");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(obj);
            oos.close();
            fos.close();

            // 反序列化对象
            FileInputStream fis = new FileInputStream("object.data");
            ObjectInputStream ois = new ObjectInputStream(fis);
            MySerializableObject deserializedObj = (MySerializableObject) ois.readObject();
            ois.close();
            fis.close();

            // 打印反序列化后的对象
            System.out.println(deserializedObj);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

class MySerializableObject implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String message;

    public MySerializableObject(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
