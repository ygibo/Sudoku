package com.example.yoshi.sudoku.ProblemFactory;

// イージー問題の生成
public class EasyProblemFactory extends ProblemFactory{
    public EasyProblemFactory(){
        problemNum = 10;
        problems = new String[problemNum];
        problems[0] = new String(
                "360000000004230800000004200" +
                        "070460003820000014500013020" +
                        "001900000007048300000000045");
    }

    // 他の問題をサポートするように修正の必要
    @Override
    public String getProblemString(int num) {
        return problems[0];
    }
}
