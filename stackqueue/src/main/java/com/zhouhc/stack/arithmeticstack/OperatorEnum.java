package com.zhouhc.stack.arithmeticstack;

/**
 * @ClassName: OperatorEnum
 * @Description: TODO
 * @Author: zhouhc
 * @CreateDate: 2019/9/2 23:23
 */
public enum OperatorEnum {

    PLUS("+", 1), MINUS("-", 1), MUL("*", 2), DIVI("/", 2), LBRACKETS("(", -1), RBRACKETS(")", 0);

    private String name;
    private int index;

    OperatorEnum(String name, int index) {
        this.name = name;
        this.index = index;
    }

    //获取元素
    public OperatorEnum getItem(int index) {
        for (OperatorEnum o : OperatorEnum.values()) {
            if (o.getIndex() == index)
                return o;
        }
        return null;
    }

    //setter&&getter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    //修改一下啊显示方法


    @Override
    public String toString() {
        return name;
    }
}
