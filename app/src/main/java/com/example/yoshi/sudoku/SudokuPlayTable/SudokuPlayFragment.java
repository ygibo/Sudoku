package com.example.yoshi.sudoku.SudokuPlayTable;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yoshi.sudoku.R;

public class SudokuPlayFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater,
                                   ViewGroup container,
                                   Bundle saveInstanceState){
        super.onCreateView(inflater, container, saveInstanceState);
        return inflater.inflate(R.layout.fragment_sudoku_play,
                container, false);
    }
}
