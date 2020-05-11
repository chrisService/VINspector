package com.example.vinspector.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.vinspector.R;

public class ScanDialog extends DialogFragment {

    private TextView tvQuestion;

    private TextView tvDialogVin;

    private String vinNumber;

    private DialogListener listener;

    public ScanDialog(String vinNumber, DialogListener listener) {
        this.vinNumber = vinNumber;

        this.listener = listener;
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.custom_dialog, null);


        tvQuestion = view.findViewById(R.id.isThisYourVIN);

        tvDialogVin = view.findViewById(R.id.tvDialogVIN);


        tvDialogVin.setText(vinNumber);

        Button yesBtn = view.findViewById(R.id.yesBtn);
        yesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                listener.yesButton();
                dismiss();
            }
        });



        Button noBtn = view.findViewById(R.id.noBtn);
        noBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.noButton();
                dismiss();
            }
        });




        builder.setView(view);

        return builder.create();
    }

    public interface DialogListener {
        void yesButton ();
        void noButton();

    }

}
