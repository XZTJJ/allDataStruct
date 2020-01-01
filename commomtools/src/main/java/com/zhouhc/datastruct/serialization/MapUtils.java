package com.zhouhc.datastruct.serialization;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: MapUtils
 * @Description: TODO
 * @Author: zhouhc
 * @CreateDate: 2019/12/26 22:52
 */

//Map的的Utils
public class MapUtils {

    //存储数据用,图存储用到了
    public static Map getMapData(Map result, String key, Integer value) {
        //创建map对象
        if (result == null)
            result = new HashMap<String, Integer>();
        //添加元素
        result.put(key, value);
        return result;
    }
}
