package com.zhouhc.huffmanTree;

        import java.io.Serializable;

/**
 * @ClassName: HuffManNode
 * @Description: TODO
 * @Author: zhouhc
 * @CreateDate: 2019/11/20 8:21
 */
public class HuffManNode<T> implements Serializable {

    public T data;
    //左孩子
    public HuffManNode lchild;
    //右孩子
    public HuffManNode rchild;
    //左边值0,或者其他值(用于其他树类型，哈夫曼树用不到)
    public int lTag;
    //右边值1,或者其他值(用于其他树类型，哈夫曼树用不到)
    public int rTag;
    //出现的频率，用于比较，构建哈夫曼树
    public long count;

    public HuffManNode() {
    }

    public HuffManNode(T data) {
        this.data = data;
    }

    public HuffManNode(T data, long count) {
        this.data = data;
        this.count = count;
    }
}
