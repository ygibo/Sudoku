package com.example.yoshi.sudoku.NumberSelector;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.example.yoshi.sudoku.R;
import com.example.yoshi.sudoku.SudokuInfo;

//数値入力用 UI を生成、設定、保持し、入力を渡す View
public class NumberSelectorView extends View {
    private NumberSelector selector = new NumberSelector(0, 0, 0, 0);

    public NumberSelectorView(Context context) {
        this(context, null);
    }

    public NumberSelectorView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NumberSelectorView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setListener(NumberSelector.OnClickListener listener) {
        selector.setOnClickListner(listener);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        selector.set(0, 0, w, h);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        selector.draw(canvas, SudokuInfo.getColor());
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("Touch Number Selector", "touch in number selector");
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                touchDown(event.getX(), event.getY());
                break;
            case MotionEvent.ACTION_UP:
                touchUp(event.getX(), event.getY());
        }
        invalidate();
        getRootView().findViewById(R.id.sudoku_ui).invalidate();
        return true;
    }

    private boolean touchDown(float x, float y) {
        Log.d("down in NumSelector", "touch " + ((int) x) + "," + ((int) y));
        selector.push((int) x, (int) y);
        return true;

    }

    private boolean touchUp(float x, float y) {
        Log.d("up in NumSelector", "touch " + ((int) x) + "," + ((int) y));
        selector.unPush((int) x, (int) y);
        return true;
    }
}
