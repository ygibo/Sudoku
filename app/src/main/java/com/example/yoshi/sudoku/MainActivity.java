package com.example.yoshi.sudoku;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.yoshi.sudoku.Option.OptionFragment;

public class MainActivity extends AppCompatActivity {
    /* スタート画面を表示するアクティビティ、押されたボタンの種類によって他のモードへ遷移する */
    public static final int EASY = 0;
    public static final int MEDIUM = 1;
    public static final int HARD = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.option:
                // 画面右上のオプション選択ボタンが押されたときに、オプション画面を表示する
                startActivity(new Intent(this, OptionActivity.class));
                return true;
        }
        return false;
    }

    public void onClick(View view){
        switch(view.getId()){
            case R.id.continue_button: // 未実装、前回の終了時のモードの続きから始める
                break;
            case R.id.problem_button:
                // 難易度の表示、ダイアログで難易度を表示する。
                // 戻り値によってその難易度の問題を選択する画面へ遷移する
                new AlertDialog.Builder(this)
                        .setTitle(R.string.difficulty)
                        .setItems(R.array.difficulty, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogInterface, int i) {
                                startGame(i);
                            }
                        }).show();
                break;
            case R.id.create_button:
                // 問題作成モード、過去の作成した問題データか、新規作成するか選択する画面へ遷移
                startCreateMode();
                break;
            case R.id.exit_button:
                // 終了
                finish();
                break;
        }
    }

    void startCreateMode(){
        Intent intent = new Intent(this, SudokuCreateActivity.class);
        startActivity(intent);
    }

    void startGame(int i) {
        // 難易度別の問題選択画面を表示する
        Intent intent = new Intent(this, ProblemSelectActivity.class);
        // インテントで難易度を次のアクティビティに伝える
        switch(i){
            case EASY:
                intent.putExtra(ProblemSelectActivity.KEY_DIFFICULTY, EASY);
                break;
            case MEDIUM:
                intent.putExtra(ProblemSelectActivity.KEY_DIFFICULTY, MEDIUM);
                break;
            case HARD:
                intent.putExtra(ProblemSelectActivity.KEY_DIFFICULTY, HARD);
                break;
        }
        startActivity(intent);
    }
}
