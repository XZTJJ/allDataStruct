package com.zhouhc.binarytreeStruct;

import java.io.Serializable;

/**
 * @ClassName: BinaryTreeEnum
 * @Description: TODO
 * @Author: zhouhc
 * @CreateDate: 2019/11/18 0:37
 */

//用于标识child是线索还是孩子，Link标识孩子，thred标识线索，除了线索二叉树，其他的不需要
public enum BinaryTreeEnum implements Serializable {
    LINK,THRED
}
