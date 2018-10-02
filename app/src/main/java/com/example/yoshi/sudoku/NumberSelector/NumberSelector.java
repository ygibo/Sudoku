package com.example.yoshi.sudoku.NumberSelector;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;

import com.example.yoshi.sudoku.SudokuViewer.SudokuUI.ColorOfUI;
import com.example.yoshi.sudoku.SudokuPlayTable.SudokuPlayUI;

// 数字やコマンド入力用 UI
public class NumberSelector {
    // 数値入力用ボタン
    private class NumberButton {
        private float left, right; // 数字入力用のボタンの位置とサイズ
        private float top, bottom;
        private boolean downState; // ボタンが押されているかどうか
        private String symbol = new String(); // 数字かコマンド（＜、＞）を表す

        public NumberButton(int x, int y, float w, float h, int num) {
            set(x, y, w, h, num);
        }

        public NumberButton(int x, int y, float w, float h, String c) {
            set(x, y, w, h, c);
        }

        public NumberButton() {
        }

        private void setSizeAndState(float l, float t, float r, float b) {
            left = l;
            top = t;
            right = r;
            bottom = b;
            downState = false;
        }

        public void set(float l, float t, float r, float b, int num) {
            setSizeAndState(l, t, r, b);
            symbol = Integer.toString(num);
        }

        public void set(float l, float t, float r, float b, String c) {
            setSizeAndState(l, t, r, b);
            symbol = c;
        }

        // 座標 (x, y) がボタンの領域に含まれるか
        public boolean isIn(int x, int y) {
            if (left < x && x < right && top < y && y < bottom)
                return true;
            return false;
        }

        // ボタンを押す
        public void push() {
            downState = true;
        }

        // ボタンを離す
        public void unPush() {
            downState = false;
        }

        // ボタンの内容が数字か？
        public boolean isNumber() {
            return Character.isDigit(symbol.charAt(0));
        }

        // ボタンの数字を取り出す
        public int getNumber() {
            return Integer.parseInt(symbol);
        }

        // ボタンのコマンドを取り出す
        public String getSymbol() {
            return symbol;
        }

        public void setRect(Rect rect) {
            rect.set((int) left, (int) top, (int) right, (int) bottom);
        }

        // ボタンが押されているか?
        public boolean isDown() {
            return downState;
        }

        public float getWidth() {
            return right - left;
        }

        public float getHeight() {
            return bottom - top;
        }

        // ボタンの描画
        public void draw(Canvas canvas, ColorOfUI color) {
            // ボタンの外枠の表示
            drawCellRect(canvas, color);
            // ボタンの内容の表示
            drawSymbol(canvas, color);
        }

        // ボタンの外枠の描画
        private void drawCellRect(Canvas canvas, ColorOfUI color) {
            Paint dark = new Paint();
            dark.setColor(color.get("dark"));

            Paint hilite = new Paint();
            hilite.setColor(color.get("hilite"));
            Paint light = new Paint();
            light.setColor(color.get("light"));

            if (isDown()) { // ボタンが押されているときは塗りつぶし
                dark.setStyle(Paint.Style.FILL);
            } else {
                dark.setStyle(Paint.Style.STROKE);
            }
            canvas.drawRect(left, top, right, bottom, dark);
        }

        // ボタンの内容の表示
        private void drawSymbol(Canvas canvas, ColorOfUI color) {
            Paint foreground = new Paint();
            foreground.setColor(color.get("foreground"));
            foreground.setStyle(Paint.Style.FILL);
            foreground.setTextSize((bottom - top) * 0.75f);
            foreground.setTextAlign(Paint.Align.CENTER);

            Paint.FontMetrics fm = foreground.getFontMetrics();
            float x = (right - left) / 2;
            float y = (bottom - top) / 2 - (fm.ascent + fm.descent) / 2;
            //Log.d("TEST", "x := " + x + " y := " + y);

            //int number = numberSelector.getNumberAtButton(row, colmn);
            canvas.drawText(symbol, left + x, top + y, foreground);
        }
    }

    // 数字、コマンド入力イベントを受け取るリスナー
    // 他のクラスはこのリスナーを実装し、このクラスへ設定することで、数字、コマンドの入力を受け取る
    public interface OnClickListener {
        public void onClick(int number);
    }

    public static final int Row = 3, Colmn = 3; // 数字は3x3で配置される
    private NumberButton buttons[] = new NumberButton[Row * Colmn + 3];
    private int selectorLeft, selectorRight; // 数値入力用 UI のサイズと位置
    private int selectorTop, selectorBottom;
    private int selectRow, selectColmn; // 選択された行、列

    public static int PUSH_BACK_BUTTON = -1, PUSH_NEXT_BUTTON = -2; // コマンド戻る、次へを表す値

    private OnClickListener onClickListener = new OnClickListener() {
        @Override
        public void onClick(int number) {
        }
    };

    public NumberSelector(int left, int top, int right, int bottom) {
        set(left, top, right, bottom);
    }

    public NumberSelector() {
    }

    // リスナーの設定
    public void setOnClickListner(OnClickListener listener) {

        onClickListener = listener;
    }

    public void set(int left, int top, int right, int bottom) {
        selectorLeft = left;
        selectorTop = top;
        selectorRight = right;
        selectorBottom = bottom;
        Log.d("NumSelArea", "Area (" + left + "," + top + "), (" + right + "," + bottom + ")");
        Log.d("TEXT", "left =" + left + "top =" + top + "left =" + left + "bottom =" + bottom);
        // ボタンの設定
        setButtons(left, top, right, bottom);
    }

    // 数字入力用とコマンド用ボタンの設定
    private void setButtons(int left, int top, int right, int bottom) {
        int width = (right - left) / 3;
        int height = (bottom - top) / 4;
        Log.d("TEST", "width and height " + width + " " + height);
        // 3x3の数字入力用ボタンの設定
        for (int i = 0; i < Row; ++i) {
            for (int j = 0; j < Colmn; ++j) {
                buttons[i * Colmn + j] = new NumberButton();
                buttons[i * Colmn + j].set(j * width + left, i * height + top,
                        (j + 1) * width + left, (i + 1) * height + top, i * Colmn + j + 1);
            }
        }

        // コマンド用ボタンの設定

        // スペースは数字０の入力を表す
        buttons[9] = new NumberButton();
        buttons[9].set(0, top + height * 3, width, top + height * 4, " ");
        // 戻るコマンド
        buttons[10] = new NumberButton();
        buttons[10].set(width, top + height * 3, 2 * width, top + height * 4, "<");
        // 次へのコマンド
        buttons[11] = new NumberButton();
        buttons[11].set(width * 2, top + height * 3, 3 * width, top + height * 4, ">");
    }


    public int getNumberAtButton(int row, int colmn) {
        return buttons[row * Colmn + colmn].getNumber();
    }

    public boolean isDownButtonAt(int row, int colmn) {
        return buttons[row * Colmn + colmn].isDown();
    }

    public void getButtonRect(int row, int colmn, Rect rect) {
        buttons[row * Colmn + colmn].setRect(rect);
    }

    public boolean isIn(int x, int y) {
        return (selectorLeft < x && x < selectorRight &&
                selectorTop < y && y < selectorBottom);
    }

    // ボタンを押す
    public void push(int x, int y) {
        Log.d("TEST", "push ");
        for (int i = 0; i < buttons.length; ++i) {
            // どのボタンが押されたか探す
            if (buttons[i].isIn(x, y)) {
                selectRow = (int) (i / 3);
                selectColmn = i % 3;
                Log.d("Select", "button " + selectRow + ", " + selectColmn);
                buttons[i].push();
                return;
            }
        }
    }

    public void unPush(int x, int y) {
        Log.d("UnPush", "unpush" + selectRow + ", " + selectColmn);
        //　前に押された同じボタンが離されたか調べる
        if (buttons[selectRow * Colmn + selectColmn].isIn(x, y)) {
            // 同じボタンなら、リスナーを通して数字、コマンドを送る
            Log.d("TEST", "Push Button " + buttons[selectRow * Colmn + selectColmn].getSymbol());
            if (buttons[selectRow * Colmn + selectColmn].isNumber())
                onClickListener.onClick(buttons[selectRow * Colmn + selectColmn].getNumber());
            else if (buttons[selectRow * Colmn + selectColmn].getSymbol().equals(" ")) {
                onClickListener.onClick(0);
            } else if (buttons[selectRow * Colmn + selectColmn].getSymbol().equals("<")) {
                onClickListener.onClick(PUSH_BACK_BUTTON);
            } else if (buttons[selectRow * Colmn + selectColmn].getSymbol().equals(">")) {
                onClickListener.onClick(PUSH_NEXT_BUTTON);
            }
        }
        buttons[selectRow * Colmn + selectColmn].unPush();
    }

    public float getButtonWidth(int row, int colmn) {
        return buttons[row * Colmn + colmn].getWidth();
    }

    public float getButtonHeight(int row, int colmn) {
        return buttons[row * Colmn + colmn].getHeight();
    }

    public void setSelectedRect(Rect rect) {
        buttons[selectRow * Colmn + selectColmn].setRect(rect);
    }

    public void draw(Canvas canvas, ColorOfUI color) {
        // ボタン領域の背景を描画
        Paint background = new Paint();
        background.setColor(color.get("background"));
        canvas.drawRect(selectorLeft, selectorTop,
                selectorRight, selectorBottom, background);

        // 各ボタンの描画
        for (int i = 0; i < buttons.length; ++i) {
            buttons[i].draw(canvas, color);
        }
    }
}
