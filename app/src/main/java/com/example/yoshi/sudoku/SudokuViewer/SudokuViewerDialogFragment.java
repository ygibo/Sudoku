package com.example.yoshi.sudoku.SudokuViewer;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import com.example.yoshi.sudoku.SudokuPlayActivity;
import com.example.yoshi.sudoku.SudokuViewer.SudokuViewerView;

public class SudokuViewerDialogFragment extends DialogFragment {
    private static String problem = new String();

    public static void setProblem(String sudokuProblem) {
        problem = sudokuProblem;
    }

    @Override
    public Dialog onCreateDialog(Bundle saveInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        SudokuViewerView viewerView = new SudokuViewerView(getActivity());
        viewerView.setProblem(problem);
        builder.setView(viewerView);

        builder.setMessage("setting")
                .setNegativeButton("Close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
        builder.setPositiveButton("Open", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                startGame();
            }
        });
        return builder.create();
    }

    public void startGame(){
        Intent intent = new Intent(getActivity(), SudokuPlayActivity.class);
        intent.putExtra("Problem", problem);
        startActivity(intent);
    }
}
