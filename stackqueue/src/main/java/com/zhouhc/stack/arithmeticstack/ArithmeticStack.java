package com.zhouhc.stack.arithmeticstack;


import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    private static Logger LOG = LoggerFactory.getLogger(ArithmeticStack.class);

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

    //进行最后的结果运算，这个是根据中缀表达式的队列来算的
    //每次取都是取队列的队头元素，放入栈中，遇到数据就入栈，遇到
    //操作符就出栈两个队列头元素进行计算，计算结果作为栈顶元素
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

    //将中缀表达式转成后缀表达式，需要借助一个队列或者(list，程序使用的是list)存储输出后缀表达式
    // 一个栈，用于存储操作符，以及弹出操作符构建后缀表达式
    /*
     *  规则:
     *    如果是操作数的书，直接放到list中，如果是操作符的，用当前操作符和栈顶操作符比较，如果
     * 当前操作符优先级大于栈顶操作符的优先级，入栈成为新的栈顶(ps:左括号的优先级是最低的，如果当前元素是左括号，直接入栈不比较)，如果
     *  当前操作符优先级小于等于栈顶操作符的优先级，栈顶元素出去，一直到当前操作符优先级大于栈顶操作符的优先级
     * ，当前元素成为新的栈顶，有个例外，遇到右括号，栈顶出去，直到遇到第一个左括号位置。
     *
     */
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
                        //左括号直接入栈.不需要考虑优先级或者顺序问题
                        resultExpression.push(LBRACKETS);
                        break;
                    case RBRACKETS:
                        OrderOperation(RBRACKETS);
                        break;
                }
            }
        }
        //最后把resultExpression中的元素都加到subfix中,这个时候后缀表达式就已经弄好了
        //同时清空操作符栈，用于后缀表达式的计算
        while (!resultExpression.empty()) {
            //因为正确的表达式，这个时候是不可能有左括号的存在的，有的话证明表达式是错误的，抛出异常。
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

    //操作符的顺序吗，比较操作符，当前操作符该怎么入栈
    private void OrderOperation(OperatorEnum o) {
        OperatorEnum top = null;
        //为空直接放进去
        if (resultExpression.empty()) {
            resultExpression.push(o);
        } else if (o == RBRACKETS) {
            //对于右括号的，一直出栈，知道遇到左括号
            top = (OperatorEnum) resultExpression.peek();
            //resultExpression.size() > 0 是为了防止空指针错误，防止表达式少了一个左括号情况
            while (top != LBRACKETS && resultExpression.size() > 0) {
                subfix.add(top);
                resultExpression.pop();
                //因为还有左括号的情况的，不需要是否为空
                top = (OperatorEnum) resultExpression.peek();
            }
            //这种情况是，缺少一个左括号的情况
            if (resultExpression.empty())
                throw new RuntimeException("括号匹配不正确");
            //弹出左括号
            resultExpression.pop();
        } else {
            //其他情况处理，正常比较操作符的优先级，以及进栈的股则
            top = (OperatorEnum) resultExpression.peek();
            //只要栈顶元素符号的优先级大于等于当前符号的优先级，一律弹出，
            //resultExpression.size() > 0 作用就是，当栈只剩下一个元素，并且优先级比当前元素大或者相等，
            //出栈后，控制循环结束，不然会出现没有栈没有元素了，还resultExpression.pop();操作
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

    //将表达式的转成数组类型，除了表达式开头的数字可以有= ,- ,其余的
    //符号均用于确定一个操作数，操作符将操作数分开
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
                //处理连续为操作符的情况，*(，有括号的情况
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
        //处理表达式的最后一个操作数，表达式最后一位不能是=符号
        temp.add(Double.valueOf(num));
        return temp.toArray();
    }

    //判断是否是运算符
    private boolean isNum(String s) {
        return s.matches("\\d") || s.equals(".");
    }


}
