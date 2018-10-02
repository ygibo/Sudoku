package com.example.yoshi.sudoku.ProblemFactory;

// 問題生成用のファクトリー
public class ProblemFactory {
    protected int problemNum = 0;
    protected String[] problems;

    public String getProblemString(int num){
        if(num >= problemNum)
            return new String("");
        return problems[num];
    }

    public int getProblemNum(){
        return problemNum;
    }
}
