package com.zhouhc.datastruct.avltree;

public enum AVLEnum {
    EH("等高", 0), LH("左高", 1), RH("右高", -1);
    private String describe;
    private int bf;

    AVLEnum(String describe, int bf) {
        this.describe = describe;
        this.bf = bf;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public int getBf() {
        return bf;
    }

    public void setBf(int bf) {
        this.bf = bf;
    }

    //找到对应的值
    public static String getName(int bf) {
        for (AVLEnum a : AVLEnum.values()) {
            if (a.getBf() == bf)
                return a.describe;
        }
        return null;
    }
}
