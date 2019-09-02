package com.zhouhc.stack.arithmeticstack;


import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import java.util.ArrayList;
import java.util.Stack;

import static com.zhouhc.stack.arithmeticstack.OperatorEnum.*;

/**
 * @ClassName: ArithmeticStack
 * @Description: TODO
 * @Author: zhouhc
 * @CreateDate: 2019/9/1 22:42
 */

//四则运算表达式求值，利用栈的性质来计算
public class ArithmeticStack {

    private static Logger LOG = LogManager.getLogger(ArithmeticStack.class);

    //用于构建后缀表达式
    private ArrayList subfix = new ArrayList();
    //用于计算最后的结果
    private Stack<Object> resultExpression = new Stack<Object>();

    //根据表达式求值
    public Double getResult(String expression) {
        //将表达式转成数组形式
        Object[] allItem = toObjectArray(expression);
        //开始转成后缀表达式
        changeToSubfix(allItem);
        //计算表达式的值
        Double reslut = toOperation();

        return reslut;
    }

    //进行最后的结果运算
    private Double toOperation() {
        //运算的结果放，遇到数字就近栈，遇到操作符就栈顶两个元素相加，并生成一个新的栈顶元素
        for (int i = 0; i < subfix.size(); i++) {
            if (isDouble(subfix.get(i)))
                resultExpression.push(subfix.get(i));
            else {
                OperatorEnum op = (OperatorEnum) subfix.get(i);
                Double first = (Double) resultExpression.pop();
                Double second = (Double) resultExpression.pop();
                //对操作符要进行进出栈的判断
                switch (op) {
                    case PLUS:
                        resultExpression.push(second + first);
                        break;
                    case MINUS:
                        resultExpression.push(second - first);
                        break;
                    case MUL:
                        resultExpression.push(second * first);
                        break;
                    case DIVI:
                        resultExpression.push(second / first);
                        break;
                    default:
                        throw new RuntimeException("表达式错误");
                }
            }
        }

        //转成结果
        return (Double) resultExpression.pop();
    }

    //将中缀表达式转成后缀表达式
    private void changeToSubfix(Object[] allItem) {
        //对每一项进项遍历
        for (int i = 0; i < allItem.length; i++) {
            //是数字就直接存进去
            if (isDouble(allItem[i]))
                subfix.add(allItem[i]);
            else {
                OperatorEnum op = (OperatorEnum) allItem[i];
                //对操作符要进行进出栈的判断
                switch (op) {
                    case PLUS:
                        OrderOperation(PLUS);
                        break;
                    case MINUS:
                        OrderOperation(MINUS);
                        break;
                    case MUL:
                        OrderOperation(MUL);
                        break;
                    case DIVI:
                        OrderOperation(DIVI);
                        break;
                    case LBRACKETS:
                        //左括号直接入栈.不需要考虑其他的
                        resultExpression.push(LBRACKETS);
                        break;
                    case RBRACKETS:
                        OrderOperation(RBRACKETS);
                        break;
                }
            }
        }
        //最后把resultExpression中的元素都加到subfix中,这个时候后缀表达式就已经弄好了
        while (!resultExpression.empty()) {
            if ((OperatorEnum) resultExpression.peek() == LBRACKETS)
                throw new RuntimeException("括号匹配不正确");
            subfix.add(resultExpression.pop());
        }

        //打印一下后缀表达式
        String subfixExpress = "";
        for (int i = 0; i < subfix.size(); i++)
            subfixExpress += subfix.get(i).toString() + " ";

        LOG.info("后缀表达式为:" + subfixExpress);
    }

    //操作符的顺序
    private void OrderOperation(OperatorEnum o) {
        OperatorEnum top = null;
        //为空直接放进去
        if (resultExpression.empty()) {
            resultExpression.push(o);
        } else if (o == RBRACKETS) {
            //对于右括号的，一直出栈，知道遇到左括号
            top = (OperatorEnum) resultExpression.peek();
            while (top != LBRACKETS && resultExpression.size() > 0) {
                subfix.add(top);
                resultExpression.pop();
                //因为还有左括号的情况的，不需要是否为空
                top = (OperatorEnum) resultExpression.peek();
            }
            //判断只有右括号没有左括号的情况
            if (resultExpression.empty())
                throw new RuntimeException("括号匹配不正确");
            //弹出左括号
            resultExpression.pop();
        } else {
            //其他情况处理
            top = (OperatorEnum) resultExpression.peek();
            //只要是o的优先级不大于top的优先级，一律弹出
            while (o.getIndex() <= top.getIndex() && resultExpression.size() > 0) {
                subfix.add(top);
                resultExpression.pop();
                //判断是否栈已经空了
                if (!resultExpression.empty())
                    top = (OperatorEnum) resultExpression.peek();
            }
            //现在把元素加入栈中
            resultExpression.push(o);
        }
    }

    //判断是否是操作数
    private boolean isDouble(Object o) {
        return o.getClass() == Double.class;
    }

    //将表达式的转成数组类型
    private Object[] toObjectArray(String expression) {
        //先转先转成字符串类型
        String[] expressionArray = expression.split("");
        //临时保存数组
        ArrayList temp = new ArrayList();
        //数组每一项
        String num = "";
        //逐一判断
        for (int i = 0; i < expressionArray.length; i++) {
            //处理10以上的数字，和开始为负号和加号的情况
            if (isNum(expressionArray[i]) || (i == 0 && (expressionArray[i].equals("-") || expressionArray[i].equals("+"))))
                num += expressionArray[i];
            else {
                //处理连续为操作符的情况
                if (StringUtils.isNotBlank(num)) {
                    Double aDouble = Double.valueOf(num);
                    num = "";
                    temp.add(aDouble);
                }
                //处理符号的情况
                switch (expressionArray[i]) {
                    case "+":
                        temp.add(PLUS);
                        break;
                    case "-":
                        temp.add(MINUS);
                        break;
                    case "*":
                        temp.add(MUL);
                        break;
                    case "/":
                        temp.add(DIVI);
                        break;
                    case "(":
                        temp.add(LBRACKETS);
                        break;
                    case ")":
                        temp.add(RBRACKETS);
                        break;
                    default:
                        throw new RuntimeException("表达式暂时只支持+-*/()这些运算符");
                }
            }
        }
        //加上左后以为
        temp.add(Double.valueOf(num));
        return temp.toArray();
    }

    //判断是否是运算符
    private boolean isNum(String s) {
        return s.matches("\\d") || s.equals(".");
    }


}
