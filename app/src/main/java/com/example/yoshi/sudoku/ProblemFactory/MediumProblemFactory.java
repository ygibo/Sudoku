package com.example.yoshi.sudoku.ProblemFactory;

// 普通の難易度の問題
public class MediumProblemFactory extends ProblemFactory{
    public MediumProblemFactory(){
        problemNum = 1;
        problems = new String[problemNum];
        problems[0] = new String(
                "650000070000506000014000005" +
                        "007009000002314700000700800" +
                        "500000630000201000030000097");
    }

    // 他の問題をサポートするように修正の必要
    @Override
    public String getProblemString(int num) {
        return problems[0];
    }
}
