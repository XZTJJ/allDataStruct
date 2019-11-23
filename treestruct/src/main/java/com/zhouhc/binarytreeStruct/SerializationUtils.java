package com.zhouhc.binarytreeStruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @ClassName: Seriobj
 * @Description: TODO
 * @Author: zhouhc
 * @CreateDate: 2019/11/9 14:20
 */
public class SerializationUtils {

    private static Logger LOG = LoggerFactory.getLogger(SerializationUtils.class);

    //序列化
    public static byte[] objToByte(Object obj) {
        byte[] serializationByte = null;
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(obj);
            objectOutputStream.close();
            byteArrayOutputStream.close();
            serializationByte = byteArrayOutputStream.toByteArray();
        } catch (Exception e) {
            LOG.error("序列化失败", e);
        }
        return serializationByte;
    }


    //反序列化
    public static Object byteToObject(byte[] serializtionByte) {
        Object obj = null;
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(serializtionByte);
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            obj = objectInputStream.readObject();
        } catch (Exception e) {
            LOG.error("反序列化失败", e);
        }

        return obj;
    }
}
