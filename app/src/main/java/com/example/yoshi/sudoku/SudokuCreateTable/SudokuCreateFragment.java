package com.example.yoshi.sudoku.SudokuCreateTable;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yoshi.sudoku.R;

public class SudokuCreateFragment extends Fragment{
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle saveInstanceState){
        Log.d("CreateFrag", "Create Fragmen ----------");
        super.onCreateView(inflater, container, saveInstanceState);
        return inflater.inflate(R.layout.fragment_sudoku_create,
                container, false);
    }
}
