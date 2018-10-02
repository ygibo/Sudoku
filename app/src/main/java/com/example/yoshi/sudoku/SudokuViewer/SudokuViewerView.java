package com.example.yoshi.sudoku.SudokuViewer;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.yoshi.sudoku.SudokuInfo;
import com.example.yoshi.sudoku.SudokuLogic.SudokuLogic;

public class SudokuViewerView extends View {
    SudokuViewer viewer = new SudokuViewer();
    SudokuLogic logic = new SudokuLogic();

    public void setProblem(String problem){
        Log.d("problem", problem);
        logic = new SudokuLogic(problem);
        viewer.setLogic(logic);

    }

    public SudokuViewerView(Context context){
        this(context, null);

    }

    public SudokuViewerView(Context context, AttributeSet attrs){
        this(context, attrs, 0);
    }

    public SudokuViewerView(Context context, AttributeSet attrs, int defStyleAttr){
        super(context, attrs, defStyleAttr);
        viewer.setLogic(logic);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh){
        Log.d("TESt", "size change");
        viewer.setSize(w, h);
    }


    @Override
    protected void onDraw(Canvas canvas){
        viewer.draw(canvas, SudokuInfo.getColor());
    }
}
