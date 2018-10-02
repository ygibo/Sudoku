package com.example.yoshi.sudoku;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.app.FragmentManager;
import com.example.yoshi.sudoku.Option.OptionFragment;

public class OptionActivity extends AppCompatActivity {
    // 未実装、オプション画面を表示するアクティビティ
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);
        if(savedInstanceState == null){
            FragmentManager manager = getFragmentManager();
            manager.beginTransaction().replace(R.id.option_layout, new OptionFragment()).commit();
        }
    }

}
