package com.example.yoshi.sudoku;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.yoshi.sudoku.ProblemFactory.EasyProblemFactory;
import com.example.yoshi.sudoku.ProblemFactory.HardProblemFactory;
import com.example.yoshi.sudoku.ProblemFactory.MediumProblemFactory;
import com.example.yoshi.sudoku.ProblemFactory.ProblemFactory;
import com.example.yoshi.sudoku.SudokuViewer.SudokuViewerDialogFragment;
import com.example.yoshi.sudoku.SudokuViewer.SudokuUI.ColorOfUI;

public class ProblemSelectActivity extends AppCompatActivity {
    // 問題選択画面のアクティビティ
    public static final String KEY_DIFFICULTY = "org.example.sudoku.difficulty";
    int difficulty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        difficulty = intent.getIntExtra(KEY_DIFFICULTY, -1);
        setContentView(R.layout.activity_problem_select);
    }

    private ProblemFactory getProblemFactory(){
        switch (difficulty) {
            case MainActivity.EASY:
                return new EasyProblemFactory();
            case MainActivity.MEDIUM:
                return new MediumProblemFactory();
            case MainActivity.HARD:
                return new HardProblemFactory();
        }
        return new EasyProblemFactory();
    }

    public void onClick(View view) {
        ProblemFactory factory = getProblemFactory(); // 問題を難易度別に生成するファクトリーの取得
        if(factory==null)
            Log.d("FACTORY", "Factory is empty");


        String problem = new String(); // 問題は長さが81の文字列で表現されている
        switch (view.getId()) {
            // 押されたボタンによって、異なる問題を生成する
            case R.id.problem1:
                problem = factory.getProblemString(0);
                break;
            case R.id.problem2:
                problem = factory.getProblemString(1);
                break;
            case R.id.problem3:
                problem = factory.getProblemString(2);
                break;
            case R.id.problem4:
                problem = factory.getProblemString(3);
                break;
            case R.id.problem5:
                problem = factory.getProblemString(4);
                break;
            case R.id.problem6:
                problem = factory.getProblemString(5);
                break;
            case R.id.problem7:
                problem = factory.getProblemString(6);
                break;
            case R.id.problem8:
                problem = factory.getProblemString(7);
                break;
            case R.id.problem9:
                problem = factory.getProblemString(8);
                break;
            case R.id.problem10:
                problem = factory.getProblemString(9);
                break;
        }
        // 数独の表の色を設定する
        setColor();
        // 選択した問題を Dialog で表示する。その問題でいいならプレイ画面へ遷移する
        SudokuViewerDialogFragment.setProblem(problem);
        SudokuViewerDialogFragment dialog = new SudokuViewerDialogFragment();
        dialog.show(getSupportFragmentManager(), "Problem");
    }

    public void setColor(){
        ColorOfUI colorOfUI = new ColorOfUI();
        colorOfUI.add("black", getResources().getColor(R.color.black));
        colorOfUI.add("normal_cell", getResources().getColor(R.color.normal_cell));
        colorOfUI.add("selected_cell", getResources().getColor(R.color.selected_cell));
        colorOfUI.add("background", getResources().getColor(R.color.background));
        colorOfUI.add("hilite", getResources().getColor(R.color.hilite));
        colorOfUI.add("dark", getResources().getColor(R.color.dark));
        colorOfUI.add("light", getResources().getColor(R.color.light));
        colorOfUI.add("foreground", getResources().getColor(R.color.foreground));
        colorOfUI.add("hint0", getResources().getColor(R.color.hint0));
        colorOfUI.add("hint1", getResources().getColor(R.color.hint1));
        colorOfUI.add("hint2", getResources().getColor(R.color.hint2));
        SudokuInfo.setColorOfUI(colorOfUI);
    }
}
