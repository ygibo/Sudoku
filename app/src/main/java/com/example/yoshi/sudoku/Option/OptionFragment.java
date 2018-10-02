package com.example.yoshi.sudoku.Option;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import com.example.yoshi.sudoku.R;

// オプション画面を設置するフラグメント
public class OptionFragment extends PreferenceFragment {
    public OptionFragment(){}

    @Override
    public void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        addPreferencesFromResource(R.xml.options);
    }
}
