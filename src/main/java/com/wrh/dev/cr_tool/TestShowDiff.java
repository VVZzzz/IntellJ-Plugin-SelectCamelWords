package com.wrh.dev.cr_tool;

public class TestShowDiff {
    // 声明一个 int 类型的成员变量
    private int myIntVariable;

    // 无参构造函数
    public TestShowDiff() {
        this.myIntVariable = 0; // 默认初始化为 0
    }

    // 带参构造函数
    public TestShowDiff(int value) {
        this.myIntVariable = value;
    }

    // Getter 方法
    public int getMyIntVariable() {
        return myIntVariable;
    }

    // Setter 方法
    public void setMyIntVariable(int value) {
        this.myIntVariable = value;
    }

    // 示例方法：计算平方
    public int calculateSquare() {
        return myIntVariable * myIntVariable;
    }

    public static void main(String[] args) {
        // 创建对象并测试
        TestShowDiff obj = new TestShowDiff(5);
        System.out.println("初始值: " + obj.getMyIntVariable());
        obj.setMyIntVariable(10);
        System.out.println("平方值: " + obj.calculateSquare());
    }
}
