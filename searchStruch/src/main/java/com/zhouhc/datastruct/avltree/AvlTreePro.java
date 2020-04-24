package com.zhouhc.datastruct.avltree;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static com.zhouhc.datastruct.avltree.AVLEnum.*;

//avl树的修改版本
public class AvlTreePro {
    //日志文件
    private static Logger LOG = LoggerFactory.getLogger(AvlTreePro.class);
    //全局变量，用于标识树的高度是否发生变化
    private boolean treeHightChange = false;

    //构造函数
    public AvlTreePro() {
    }

    //右旋转,根结点和左孩子平衡因子相同,只是需要选装一次,返回新的根结点
    //isNeedChangeBf 表示是是否需要修改平衡因子，一次旋转的是需要修改平衡因子的
    private AvlNode RightRotate(AvlNode oldRoot, boolean isNeedChangeBf) {
        //获取 左孩子
        AvlNode leftChild = oldRoot.lchild;
        //首先将左孩子的右孩子放到根结点的左孩子上去
        oldRoot.lchild = leftChild.rchild;
        //然后将根结点放到左孩子的右孩子上面去
        leftChild.rchild = oldRoot;
        //表示修改结点的平衡因子结点信息
        if (isNeedChangeBf)
            onlyOnceRotate(leftChild, false);
        return leftChild;
    }

    //左旋转操作，根结点和右孩子平衡因子相同，只是需要选装一次,返回新的根结点
    //isNeedChangeBf 表示是是否需要修改平衡因子，一次旋转的是需要修改平衡因子的
    private AvlNode LeftRotate(AvlNode oldRoot, boolean isNeedChangeBf) {
        //获取右孩子
        AvlNode rightChild = oldRoot.rchild;
        //首先将右孩子的左孩子放到根结点的右孩子位置上
        oldRoot.rchild = rightChild.lchild;
        //然后是将根结点放到右孩子的左孩子上面
        rightChild.lchild = oldRoot;
        if (isNeedChangeBf)
            onlyOnceRotate(rightChild, true);
        return rightChild;
    }

    //左右旋转，根结点和右孩子的结点的符号不一样，需要旋转两次,
    //可以直接用左旋转和右旋转,中途不需要修改平衡因子，旋转完成才修改
    private AvlNode LeftRightRotate(AvlNode oldRoot) {
        //获取原始状态下面的新根的平衡因子
        AVLEnum originalNewRootBf = oldRoot.lchild.rchild.bf;
        //先左旋转,作用只是符号统一，不需要修改平衡因子
        oldRoot.lchild = LeftRotate(oldRoot.lchild, false);
        //然后右旋转,纠正树的平衡，和只是旋转一次的纠正平衡方法不一致
        AvlNode newRoot = RightRotate(oldRoot, false);
        //纠正平衡,平衡因子
        onlyTwiceRotate(newRoot, originalNewRootBf);
        return newRoot;
    }

    //右左旋转，根结点和原来的结点符号不一致，需要旋转两次
    //可以直接用左旋转和右旋转,中途不需要修改平衡因子，旋转完成才修改
    private AvlNode RightLeftRotate(AvlNode oldRoot) {
        //获取原始状态下面的新根的平衡因子
        AVLEnum originalNewRootBf = oldRoot.rchild.lchild.bf;
        //先右旋转,作用只是符号统一，不需要修改平衡因子
        oldRoot.rchild = RightRotate(oldRoot.rchild, false);
        //然后左旋转,纠正树的平衡，和只是旋转一次的纠正平衡方法不一致
        AvlNode newRoot = LeftRotate(oldRoot, false);
        //纠正平衡,平衡因子
        onlyTwiceRotate(newRoot, originalNewRootBf);
        return newRoot;
    }

    //左等旋转(这种情况存在删除树的情况下)
    //oldRoot旧根结点
    private AvlNode LeftEHRotate(AvlNode oldRoot) {
        //发现直接可以调用对应的方法
        oldRoot = RightRotate(oldRoot, false);
        //进行平衡因子的调整
        delOnceRotate(oldRoot, false);
        return oldRoot;
    }

    //右等旋转(这种情况存在删除树的情况下)
    //oldRoot旧根结点
    private AvlNode RightEHRotate(AvlNode oldRoot) {
        //发现直接可以调用对应的方法
        oldRoot = LeftRotate(oldRoot, false);
        //进行平衡因子的调整
        delOnceRotate(oldRoot, true);
        return oldRoot;
    }

    //删除情况下的平衡因子的调整
    private void delOnceRotate(AvlNode newRoot, boolean LeftRotate) {
        if (LeftRotate)
            newRoot.bf = LH;
        else
            newRoot.bf = RH;
    }

    //修改平衡因子，新根(newRoot)和原根(oldRoot)是一定的等高的
    //LeftRotate表示是左旋转修改平衡因子还是右旋转修改平衡因子
    private void onlyOnceRotate(AvlNode newRoot, boolean LeftRotate) {
        //不管是左旋转还是右旋转，新根的平衡因子绝对是平衡的
        newRoot.bf = EH;
        //如果是左旋转的话，原根就变成新根的左孩子了,但是它也是平衡的
        if (LeftRotate)
            newRoot.lchild.bf = EH;
        else
            //如果是右旋转的话，原根就变成新根的右孩子了，但是它也是平衡的
            newRoot.rchild.bf = EH;
    }

    //修改平衡因子，新根(newRoot)和原根(oldRoot)是一定的等高的
    //LeftRotate表示是左右旋转修改平衡因子还是右左旋转修改平衡因子
    //原始状态下面的新根的bf值
    private void onlyTwiceRotate(AvlNode newRoot, AVLEnum originalNewRootBf) {
        //通过switch更加容易的判断数据
        switch (originalNewRootBf) {
            //如果原始状态下面的新根的bf值是等高状态，三个值都是登高的
            case EH:
                newRoot.bf = newRoot.lchild.bf = newRoot.rchild.bf = EH;
                break;
            //如果原始状态下面的新根的bf值是左高状态三个值都是左高的，需要根据情况判断
            case LH:
                //新根是可以唯一确定是等高的
                newRoot.bf = EH;
                //如果是左右旋转的话 ，则原根右高，原根的左孩子(也就是新根的左孩子)等高
                //如果是右左旋转的话 ,则原根等高，原根的右孩子(也就是新根的右孩子)右高
                newRoot.rchild.bf = RH;
                newRoot.lchild.bf = EH;
                //注意区分这两种情况，虽然代码相同但是，含义却不一样
                break;
            case RH:
                //新根是可以唯一确定是等高的
                newRoot.bf = EH;
                //如果是左右旋转的话 ，则原根等高，原根的左孩子(也就是新根的左孩子)左高
                //如果是右左旋转的话 ,则原根左高，原根的右孩子(也就是新根的右孩子)等高
                newRoot.rchild.bf = EH;
                newRoot.lchild.bf = LH;
                //注意区分这两种情况，虽然代码相同但是，含义却不一样
                break;
        }

    }

    //对插入的结点进行纠正处理,考虑有必要把修改平衡因子的操作放在旋转函数里面
    private AvlNode insertBalance(AvlNode rootNode, boolean isLeft) {
        //获取平衡因子
        AVLEnum rootBF = rootNode.bf;
        //对树的平衡因子做处理
        switch (rootBF) {
            //如果根结点登高的情况
            case EH:
                if (isLeft)
                    //表示左子树增高了
                    rootNode.bf = LH;
                else
                    //表示右子树增高了
                    rootNode.bf = RH;
                treeHightChange = true;
                break;
            //如果原来是左高的情况
            case LH:
                if (isLeft) {
                    //表示左子树增加，需要纠正树了
                    AVLEnum leftChildbf = rootNode.lchild.bf;
                    if (leftChildbf == LH)
                        //表示左孩子结点符号和根结点的符号一致，只需要一次旋转就好
                        rootNode = RightRotate(rootNode, true);
                    else
                        //表示根结点和左孩子结点符号不一致，需要进行两次旋转
                        rootNode = LeftRightRotate(rootNode);
                } else {
                    //表示是右子树增高了
                    rootNode.bf = EH;
                }
                //上面两种情况树的高度不变
                treeHightChange = false;
                break;
            case RH:
                //如果原来是右高的情况
                if (isLeft) {
                    //表示左子树增高了，根结点高度平衡
                    rootNode.bf = EH;
                } else {
                    //表示还是右子树增高了,就绪奥判断根结点和右孩子结点的符号了
                    AVLEnum rightBF = rootNode.rchild.bf;
                    //符号不一致的情况
                    if (rightBF == LH)
                        //需要两次旋转
                        rootNode = RightLeftRotate(rootNode);
                    else
                        //符号相等的情况
                        rootNode = LeftRotate(rootNode, true);
                }
                //上面两种情况树的高度不变
                treeHightChange = false;
        }
        return rootNode;
    }

    //插入数据函数
    public AvlNode insertNode(AvlNode rootNode, int data) {
        //每次默认初始化树的高度不变
        treeHightChange = false;
        //递归插入数据
        rootNode = recursionInsert(rootNode, data);
        return rootNode;

    }

    //递归插入函数
    private AvlNode recursionInsert(AvlNode rootNode, int data) {
        //用于标记是左子树还是右子树增高了
        boolean isLeft = true;
        //为空的话直接插入数据
        if (rootNode == null) {
            //直接插入数据
            rootNode = new AvlNode(data, null, null, EH);
            //标记树长高了
            treeHightChange = true;
            return rootNode;
        }
        //父结点的数据域
        int parentData = rootNode.data;
        //如果存在该值，直接返回就行了
        if (parentData == data)
            return rootNode;
        //决定是插入左边还是右边
        if (parentData > data) {
            rootNode.lchild = recursionInsert(rootNode.lchild, data);
            //标记是左边插入
            isLeft = true;
        } else {
            rootNode.rchild = recursionInsert(rootNode.rchild, data);
            //标记是右边插入
            isLeft = false;
        }
        //到这里表示插入已经完成了,如果树右长高的话，就需要调整平衡和平衡因子了
        if (treeHightChange)
            rootNode = insertBalance(rootNode, isLeft);
        //插入完成
        return rootNode;
    }

    //删除结点操作
    /**
     * @rootNode 根结点
     * @key 要删除的关键字
     */
    public AvlNode delAvlNode(AvlNode rootNode, int key) {
        //每次默认初始化树的高度不变
        treeHightChange = false;
        rootNode = recusionDelAvlNode(rootNode, key);
        return rootNode;
    }

    //删除操作的递归方法
    /**
     * @rootNode 根结点
     * @key 要删除的关键字
     */
    private AvlNode recusionDelAvlNode(AvlNode rootNode, int key) {
        //用于标记是左子树还是右子树增高了
        boolean isLeft = true;
        if (rootNode == null)
            return null;
        //获取获取对应的数据
        int rootData = rootNode.data;
        //递归决定对应找到对应的数据
        if (rootData > key) {
            rootNode.lchild = recusionDelAvlNode(rootNode.lchild, key);
            isLeft = true;
        }
        if (rootData < key) {
            rootNode.rchild = recusionDelAvlNode(rootNode.rchild, key);
            isLeft = false;
        }
        //删除的详细c操作
        if (rootData == key) {
            rootNode = delNodeDetail(rootNode);
            return rootNode;
        }

        //纠正树的平衡处理
        if (treeHightChange)
            rootNode = delBalance(rootNode, isLeft);

        //然后才返回结果操作
        return rootNode;
    }

    //删除的详细操作
    private AvlNode delNodeDetail(AvlNode delNode) {
        //处理叶子结点的情况,树高度发生改变了
        if (delNode.rchild == null && delNode.lchild == null) {
            //树高度发生了变化
            treeHightChange = true;
            return null;
        }
        //寻找前序,
        if (delNode.lchild != null)
            delNode = recusionPreOrPostNode(delNode, delNode, delNode.lchild, true);
            //寻找后继
        else
            delNode = recusionPreOrPostNode(delNode, delNode, delNode.rchild, false);
        //返回删除后的结点
        return delNode;
    }

    //递归寻找前驱或者后继的过程
    private AvlNode recusionPreOrPostNode(AvlNode delNode, AvlNode parentNode, AvlNode preORPostNode, boolean isPre) {
        //说明找的前驱
        if (isPre) {
            //多加这一步的操作，是为了解决删除前驱，java删除前驱需要有父结点
            if (preORPostNode.rchild == null) {
                //说明preORPostNode就是前驱了，修改对应的值
                delNode.data = preORPostNode.data;
                //处理delNode的前驱是 delNode直接左孩子的情况
                if (delNode == parentNode) {
                    //通过父结点删除前驱结点,使用前驱左孩子替代前驱，因为非常有可能前驱是还有左孩子的
                    parentNode.lchild = preORPostNode.lchild;
                    //只有一层是，左孩子就是前驱
                    isPre = false;
                }
                else
                    //通过父结点删除前驱结点,使用前驱左孩子替代前驱，因为非常有可能前驱是还有左孩子的
                    parentNode.rchild = preORPostNode.lchild;
                //树高度发生了变化
                treeHightChange = true;
                //返回结点，最后一步不需要纠偏
                //return parentNode;
            } else
                //表明还没有找到前驱
                parentNode = recusionPreOrPostNode(delNode, preORPostNode, preORPostNode.rchild, true);
        }
        //说明找的后继
        else {
            //多加这一步的操作，是为了解决删除后继，java删除后继需要有父结点
            if (preORPostNode.lchild == null) {
                //说明preORPostNode就是后继了，修改对应的值
                delNode.data = preORPostNode.data;
                //处理delNode的后继是 delNode直接右孩子的情况
                if (delNode == parentNode) {
                    //通过父结点删除后继结点,使用后继右孩子替代后继，因为非常有可能后继是还有右孩子的
                    parentNode.rchild = preORPostNode.rchild;
                    //只有一层是，右孩子就是是驱
                    isPre = false;
                }
                else
                    parentNode.lchild = preORPostNode.rchild;
                //树高度发生了变化
                treeHightChange = true;
                //返回结点，最后一步不需要纠偏
                //return parentNode;
            } else
                //表明还没有找到后继
                preORPostNode = recusionPreOrPostNode(delNode, preORPostNode, preORPostNode.lchild, false);
        }
        //这个时候需要纠偏操作,只有数据额高度发生了变化才执行纠偏操作
        if (treeHightChange)
            parentNode = delBalance(parentNode, !isPre);

        //返回数据
        return parentNode;
    }

    //纠正树的平衡方法处理,删除时用到，需要返回纠偏完成的结点
    private AvlNode delBalance(AvlNode parentNode, boolean isLeft) {
        AVLEnum parentbf = parentNode.bf;
        //纠偏了
        switch (parentbf) {
            //双亲结点为登高的情况，就一种情况
            case EH:
                //表示左子树表矮了
                if (isLeft)
                    parentNode.bf = RH;
                else
                    //右子树变矮了
                    parentNode.bf = LH;
                //树高度不变
                treeHightChange = false;
                break;
            //双亲结点为左高的情况,四种情况
            case LH:
                //这里面基本是要返回修改bf值的
                treeHightChange = true;
                //表示左子树是变矮了
                if (isLeft)
                    parentNode.bf = EH;
                    //表示右子树变矮了，需要根据左孩子的bf的值来分情况判断
                else {
                    //因为左孩子bf会有三种情况,所以分三种情况处理
                    AVLEnum lchildBf = parentNode.lchild.bf;
                    //分情况处理
                    switch (lchildBf) {
                        //处理相等的情况的
                        case EH:
                            //这个时候需要坐等旋转,这种情况下是，不需要沿着原路返回的
                            parentNode = LeftEHRotate(parentNode);
                            treeHightChange = false;
                            break;
                        //表示左孩子和父亲结点一样，需要旋转，并且沿着原路返回
                        case LH:
                            parentNode = RightRotate(parentNode, true);
                            break;
                        //表示左孩子和父亲结点不一样，需要旋转两次，并且沿着原路返回
                        case RH:
                            parentNode = LeftRightRotate(parentNode);
                            break;
                    }
                }
                break;
            //双亲结点为右高的情况,四种情况
            case RH:
                //这里面基本是要返回修改bf值的
                treeHightChange = true;
                //表明较矮的左子树被删除了，需要根据右孩子bf来调整了
                if (isLeft) {
                    //因为右孩子bf会有三种情况,所以分三种情况处理
                    AVLEnum rchildBf = parentNode.rchild.bf;
                    //分情况处理
                    switch (rchildBf) {
                        //处理相等的情况的
                        case EH:
                            //这个时候需要坐等旋转,这种情况下是，不需要沿着原路返回的
                            parentNode = RightEHRotate(parentNode);
                            treeHightChange = false;
                            break;
                        //表示右孩子和父亲结点一样，需要旋转，并且沿着原路返回
                        case LH:
                            parentNode = RightLeftRotate(parentNode);
                            break;
                        //表示右孩子和父亲结点不一样，需要旋转两次，并且沿着原路返回
                        case RH:
                            parentNode = LeftRotate(parentNode, true);
                            break;
                    }
                }
                //较高的右子树被删除了，需要根据右孩子bf来调整了
                else
                    parentNode.bf = EH;
                break;
        }
        //返回结点
        return parentNode;
    }


    //中序遍历,递归方法
    private void recursionMinddleSort(AvlNode avlNode, List<String> dataList) {
        if (avlNode == null)
            return;
        recursionMinddleSort(avlNode.lchild, dataList);
        dataList.add(avlNode.data + "");
        recursionMinddleSort(avlNode.rchild, dataList);
    }

    //中序遍历,
    public String[] minddleSort(AvlNode avlNode) {
        List<String> dataList = new ArrayList();
        recursionMinddleSort(avlNode, dataList);
        String[] sArray = new String[dataList.size()];
        dataList.toArray(sArray);
        return sArray;
    }

}
