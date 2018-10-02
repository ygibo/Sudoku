package com.example.yoshi.sudoku;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.yoshi.sudoku.NumberSelector.NumberSelector;
import com.example.yoshi.sudoku.NumberSelector.NumberSelectorFragment;
import com.example.yoshi.sudoku.NumberSelector.NumberSelectorView;
import com.example.yoshi.sudoku.SudokuCreateTable.SudokuCreateView;
import com.example.yoshi.sudoku.SudokuPlayTable.SudokuPlayFragment;
import com.example.yoshi.sudoku.SudokuPlayTable.SudokuPlayView;
import com.example.yoshi.sudoku.SudokuViewer.SudokuUI.ColorOfUI;

// 数独プレイ用アクティビティ
public class SudokuPlayActivity extends AppCompatActivity {
    public static String problem;
    private SudokuPlayView playView;

    // 入力用 UI のイベントリスナー
    public class PlayListener implements NumberSelector.OnClickListener{
        @Override
        public void onClick(int number){
            Log.d("Click in listener", "Click in Listener");
            if(number == NumberSelector.PUSH_BACK_BUTTON)
                playView.back();
            else if(number == NumberSelector.PUSH_NEXT_BUTTON)
                playView.next();
            else
                playView.sendNumber(number);
        }
    }

    @Override
    protected void onStart(){
        super.onStart();
        // 各 View の設定
        NumberSelectorView numberSelectorView = findViewById(R.id.number_selector_view);
        numberSelectorView.setListener(new PlayListener());
        playView = findViewById(R.id.sudoku_ui);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("Play", "create Play activity");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sudoku_play);
        Intent intent = getIntent();
        problem = intent.getStringExtra("Problem");
        // レイアウトへの数独プレイ用 View と入力用 View の設定
        if(savedInstanceState == null){
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.linearLayout, new SudokuPlayFragment());
            transaction.add(R.id.linearLayout, new NumberSelectorFragment());
            transaction.commit();
        }
        setColors();
    }


    private void setColors(){
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
