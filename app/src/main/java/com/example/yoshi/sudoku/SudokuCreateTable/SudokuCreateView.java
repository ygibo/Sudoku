package com.example.yoshi.sudoku.SudokuCreateTable;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.example.yoshi.sudoku.SudokuInfo;
import com.example.yoshi.sudoku.SudokuLogic.SudokuLogic;
import com.example.yoshi.sudoku.SudokuPlayTable.SudokuPlayUI;

// 数独作成用 UI を生成、管理する View
public class SudokuCreateView extends View {
    public static String resetProblem = new String(
            "000000000000000000000000000" +
                    "000000000000000000000000000" +
                    "000000000000000000000000000");

    SudokuCreateUI createUI = new SudokuCreateUI(); // 数独作成用の UI
    SudokuLogic logic = new SudokuLogic(resetProblem); // 数独の論理モデル

    public SudokuCreateView(Context context) {
        this(context, null);
    }

    public SudokuCreateView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SudokuCreateView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Log.d("Create", "create createView *************");
        // 全てのマスが空白の状態に設定するする
        createUI.setLogic(logic);
        SudokuInfo.setSudokuTableUI(createUI);
    }

    // ひとつ前の状態に戻す
    public void back(boolean createMode) {
        createUI.back(createMode);
    }

    // 現在のコマンドを実行する
    public void next(boolean createMode) {
        createUI.next(createMode);
    }

    // サイズの再設定
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        Log.d("TESt", "size change");
        createUI.setSize(w, h);
    }

    // 描画
    @Override
    protected void onDraw(Canvas canvas) {
        createUI.draw(canvas, SudokuInfo.getColor());
    }

    // タッチ入力を UI への入力へ変換し送る
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                createUI.push((int) event.getX(), (int) event.getY());
                break;
        }
        invalidate();
        return true;
    }

    // マスへの入力を UI へ送る
    public void sendNumber(int number, boolean createMode) {
        createUI.sendNumber(number, createMode);
    }

    // UI を初期状態に設定するし、再描画する
    public void reset(){
        logic.reset();
        createUI.reset();
        invalidate();
    }
}
