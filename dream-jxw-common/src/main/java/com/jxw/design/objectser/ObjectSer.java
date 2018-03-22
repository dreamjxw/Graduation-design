package com.jxw.design.objectser;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author Xingwu.Jia [xingwuj@tujia.com]
 * @date 2018/1/10 16:43
 */
public class ObjectSer {

    /**
     * 对象序列化
     */
    public static byte[] ObjectToByte(Object obj) {
        byte[] bytes = null;
        ByteArrayOutputStream bo;
        ObjectOutputStream oo;
        try {
            bo = new ByteArrayOutputStream();
            oo = new ObjectOutputStream(bo);
            oo.writeObject(obj);
            bytes = bo.toByteArray();
            bo.close();
            oo.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bytes;
    }

    /**
     * 反序列化
     */
    public static Object ByteToObject(byte[] bytes) {
        Object object = null;
        ByteArrayInputStream bais;
        ObjectInputStream ois;
        try {
            bais = new ByteArrayInputStream(bytes);
            ois = new ObjectInputStream(bais);
            object = ois.readObject();
            bais.close();
            ois.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return object;
    }
}
