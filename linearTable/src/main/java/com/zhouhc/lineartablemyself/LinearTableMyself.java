package com.zhouhc.lineartablemyself;

/**
 * @ClassName: LinearTableMyself
 * @Description: TODO
 * @Author: zhouhc
 * @CreateDate: 2019/6/3 23:50
 */
public class LinearTableMyself<E> {

    //数组的长度
    private int length;

    //头指针
    private InnerLinear headerLinear;

    //尾指针
    private InnerLinear endLinear;

    //construt
    public LinearTableMyself() {
        length =0;
        headerLinear = this.new InnerLinear<E>();
        endLinear =null;
    }

    //数组的长度
    public int getLength(){
        return length;
    }

    //opeation clear
    public void clear(){
        headerLinear.clear();
    }

    //opration add
    public void addElement(E e){
        headerLinear.addElement(e);
    }

    //opertion del
    public int delElement(E e){
       return headerLinear.delElement(e);
    }

    //opertion getall
    public  E[] getAllElement(){
        E[] eArray =  (E[])headerLinear.getAllElement();
        return eArray;
    }


    //定义内部对象
    class InnerLinear<E>{
        //节点数据
        private E dataContent;
        //节点指针
        private InnerLinear nextLinear;

        //construct
        public InnerLinear() {
        }

        //setter getter
        public E getDataContent() {
            return dataContent;
        }

        public void setDataContent(E dataContent) {
            this.dataContent = dataContent;
        }

        public InnerLinear getNextLinear() {
            return nextLinear;
        }

        public void setNextLinear(InnerLinear nextLinear) {
            this.nextLinear = nextLinear;
        }

        //operation操作 clear
        public void clear(){
            length = 0;
            //置空
            headerLinear.setNextLinear(null);
            endLinear = null;
        }

        //add opeartion
        public void addElement(E e){
            //初始化的过程,第一次，为头指针和尾指针都初始化一次
            //数组的长度加1
            if(headerLinear.getNextLinear() == null){
                InnerLinear temp = new InnerLinear();
                temp.setDataContent(e);
                endLinear = temp;
                headerLinear.setNextLinear(temp);
                length++;
            }else{
                //后面都采用尾插法，操作的是endLinear对象
                InnerLinear temp = new InnerLinear();
                temp.setDataContent(e);
                endLinear.setNextLinear(temp);
                endLinear = temp;
                length++;
            }
        }

        //del opertion，返回的索引文件
        public int delElement(E e){
            //没有元素直接返回-1
            if(length == 0)
                return -1;

            //初始化数组
            InnerLinear preLinear = headerLinear;
            InnerLinear currentLinear = headerLinear.getNextLinear();

            //标记
            int i = 0;
            while(currentLinear != null){
                Object dataContent = currentLinear.getDataContent();
                //相等的时候直接返回
                if(dataContent.equals(e)){
                    //舍弃中间的元素
                    preLinear.setNextLinear(currentLinear.getNextLinear());
                    length--;
                    return i;
                }
                i++;
                //开始赋值
                preLinear =currentLinear;
                currentLinear = currentLinear.getNextLinear();
            }
            return -1;
        }

        //获取根元素
        public E[] getAllElement(){
            //为空的情况
            if(length==0)
                return null;

            //强制转换
            E[] tempNew =(E[]) new Object [length];

            InnerLinear tempLinear = headerLinear.getNextLinear();
            int index =0;
            while(tempLinear != null){
                tempNew[index] =(E) tempLinear.getDataContent();
                tempLinear = tempLinear.getNextLinear();
                index++;
            }

            return tempNew;
        }

    }


    public static void main(String[] args){
        System.out.println("hello world");
    }
}
