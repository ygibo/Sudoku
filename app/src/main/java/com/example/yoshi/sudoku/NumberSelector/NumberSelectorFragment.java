package com.example.yoshi.sudoku.NumberSelector;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yoshi.sudoku.R;

public class NumberSelectorFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle saveInstanceState){
        return inflater.inflate(R.layout.fragment_number_selector,
                container, false);
    }
}
