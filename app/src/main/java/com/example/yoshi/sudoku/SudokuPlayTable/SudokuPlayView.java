package com.example.yoshi.sudoku.SudokuPlayTable;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.example.yoshi.sudoku.NumberSelector.NumberSelector;
import com.example.yoshi.sudoku.SudokuLogic.SudokuLogic;
import com.example.yoshi.sudoku.SudokuPlayActivity;
import com.example.yoshi.sudoku.SudokuInfo;

// 数独プレイ用の UI を設定し保持する View
// 入力や描画は SudokuPlayUI に投げられる
public class SudokuPlayView extends View {
    // 数独の表の各マスへアクセスする UI
    // この UI を通して描画や数独表の更新を行う
    SudokuPlayUI tableUI = new SudokuPlayUI();
    SudokuLogic logic = new SudokuLogic(); // 数独の論理的なモデル

    public void setProblem(String sudokuProblem){
        logic = new SudokuLogic(sudokuProblem);
    }

    public SudokuPlayView(Context context){
        this(context, null);

    }

    public SudokuPlayView(Context context, AttributeSet attrs){
        this(context, attrs, 0);
    }

    public SudokuPlayView(Context context, AttributeSet attrs, int defStyleAttr){
        super(context, attrs, defStyleAttr);
        // 問題の設定
        setProblem(SudokuPlayActivity.problem);
        tableUI.setLogic(logic);
        SudokuInfo.setSudokuTableUI(tableUI);

    }

    public void next(){
        tableUI.next();
    }

    public void back(){
        tableUI.back();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh){
        Log.d("TESt", "size change");
        tableUI.setSize(w, h);
    }


    @Override
    protected void onDraw(Canvas canvas){
        tableUI.draw(canvas, SudokuInfo.getColor());
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                tableUI.push((int) event.getX(), (int) event.getY());
                break;
        }
        invalidate();
        return true;
    }

    public void sendNumber(int number){
        tableUI.sendNumber(number);
    }
}
