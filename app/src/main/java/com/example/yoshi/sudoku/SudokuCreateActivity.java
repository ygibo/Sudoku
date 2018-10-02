package com.example.yoshi.sudoku;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.yoshi.sudoku.NumberSelector.NumberSelector;
import com.example.yoshi.sudoku.NumberSelector.NumberSelectorFragment;
import com.example.yoshi.sudoku.NumberSelector.NumberSelectorView;
import com.example.yoshi.sudoku.SudokuCreateTable.SudokuCreateFragment;
import com.example.yoshi.sudoku.SudokuCreateTable.SudokuCreateView;
import com.example.yoshi.sudoku.SudokuViewer.SudokuUI.ColorOfUI;
import com.example.yoshi.sudoku.SudokuPlayTable.SudokuPlayView;

// 数独作成画面を表示するアクティビティ
public class SudokuCreateActivity extends AppCompatActivity {
    private static boolean createMode = true;
    private SudokuCreateView createView; // 数独作成画面の View

    // 数値選択用の UI のイベントを受け取るリスナー
    public class CreateListener implements NumberSelector.OnClickListener{
        @Override
        public void onClick(int number){
            // コマンドに対応する動作をする
            if(number == NumberSelector.PUSH_BACK_BUTTON)
                createView.back(createMode);
            else if(number == NumberSelector.PUSH_NEXT_BUTTON)
                createView.next(createMode);
            else
                createView.sendNumber(number, createMode);
        }
    }



    @Override
    protected void onStart(){
        super.onStart();
        NumberSelectorView numberSelectorView = findViewById(R.id.number_selector_view);
        numberSelectorView.setListener(new SudokuCreateActivity.CreateListener());
        createView = findViewById(R.id.sudoku_ui);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("Create", "create activity");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sudoku_create);
        // レイアウトに数独作成用の View と 数値入力用の View を設定する
        if (savedInstanceState == null) {
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.add(R.id.sudoku_create_table, new SudokuCreateFragment());
            transaction.add(R.id.sudoku_create_table, new NumberSelectorFragment());
            transaction.commit();
        }
        setColors();
    }


    private void setColors() {
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

    public void onClick(View view){
        // ボタンが押されたとき
        switch(view.getId()){
            case R.id.create_button: // プレイモード、クリエイトモードの切り替え
                modeChange((Button) view);
                break;
            case R.id.save_button: // 未実装、セーブする
        }
    }

    // プレイモード、クリエイトモードの切り替え
    private void modeChange(Button createButton){
        if(createMode){
            createMode = false;
            createButton.setText(R.string.play_mode);
        }else{
            createMode = true;
            createButton.setText(R.string.create_mode);
            createView.reset();
        }
        createButton.invalidate();
    }


}
