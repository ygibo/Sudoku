package com.example.yoshi.sudoku.SudokuViewer;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

import com.example.yoshi.sudoku.SudokuLogic.SudokuChecker;
import com.example.yoshi.sudoku.SudokuLogic.SudokuLogic;
import com.example.yoshi.sudoku.SudokuViewer.SudokuUI.ColorOfUI;
import com.example.yoshi.sudoku.SudokuViewer.SudokuUI.SudokuCellUI;

import static com.example.yoshi.sudoku.SudokuInfo.MAX_COLMN;
import static com.example.yoshi.sudoku.SudokuInfo.MAX_ROW;

public class SudokuViewer {
    protected int width, height;
    protected int cellWidth, cellHeight;
    protected float lineStroke = 7f;

    protected SudokuLogic logic = null;
    protected SudokuCellUI[] cellUI = new SudokuCellUI[MAX_ROW * MAX_COLMN];
    protected SudokuChecker checker = new SudokuChecker();

    public void setLogic(SudokuLogic sudokuLogic){
        logic = sudokuLogic;
    }

    public SudokuViewer(){
        initArrayOfCellUI();
    }

    public SudokuViewer(int tableWidth, int tableHeight, SudokuLogic sudokuLogic) {
        initArrayOfCellUI();
        setSize(tableWidth, tableHeight);
        setLogic(sudokuLogic);
    }

    private void initArrayOfCellUI(){
        for(int i=0; i < MAX_ROW; ++i)
            for(int j=0; j < MAX_COLMN; ++j)
                cellUI[i * MAX_COLMN + j] = new SudokuCellUI(i, j);
    }

    public void setSize(int tableWidth, int tableHeight){
        width = tableWidth;
        height = tableHeight;
        cellWidth = width / 9;
        cellHeight = height / 9;

        for(int i=0; i < MAX_ROW; ++i){
            for(int j=0; j < MAX_COLMN; ++j){
                Rect cellRect = new Rect();
                setRect(i, j, cellRect);
                Log.d("SET", "set i, j => index" + i + ", " + j + " " + (i*MAX_ROW+j) + " length " + cellUI.length);
                cellUI[i * MAX_COLMN + j].set(i, j, cellRect);
            }
        }
    }
    public void draw(Canvas canvas, ColorOfUI color){
        drawCell(canvas, color, logic);
        drawLine(canvas, color);
    }

    private void drawLine(Canvas canvas, ColorOfUI color){
        Paint lineColor = new Paint();
        lineColor.setStrokeWidth(lineStroke);
        for(int i=0; i <= MAX_ROW; ++i){
            // 縦線
            if(i % 3 == 0){
                lineColor.setColor(color.get("black"));
            }else{
                lineColor.setColor(color.get("light"));
            }

            canvas.drawLine((float) i * cellWidth, 0,
                    (float) i * cellWidth, (float) height, lineColor);


            // 横線
            if(i % 3 == 0){
                lineColor.setColor(color.get("black"));
            }else{
                lineColor.setColor(color.get("light"));
            }
            canvas.drawLine((float) 0, (float) i * cellHeight,
                    (float) width, (float) i * cellHeight, lineColor);
        }
    }

    protected void drawCell(Canvas canvas, ColorOfUI color, SudokuLogic logic){
        for(int i=0; i < MAX_ROW; ++i){
            for(int j=0; j < MAX_COLMN; ++j){
                cellUI[i * MAX_ROW + j].draw(canvas, color, logic, false, checker);
            }
        }
    }

    public boolean isIn(int x, int y){
        if(0 < x && x < width && 0 < y && y < height)
            return true;
        return false;
    }

    private void setRect(int row, int colmn, Rect rect){
        rect.set(row * cellWidth, colmn * cellHeight,
                row * cellWidth + cellWidth, colmn * cellHeight + cellHeight);
    }

}
