package com.example.vinspector.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.vinspector.R;
import com.example.vinspector.constants.Constants;

public class EnterVinDialog extends DialogFragment {

    private OkDialogListener okListener;

    public EnterVinDialog(OkDialogListener okListener) {
        this.okListener = okListener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.enter_vin_dialog, null);

        final EditText etEnterVin = view.findViewById(R.id.etEnterVin);

        Button okBtn = view.findViewById(R.id.okButton);

        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Constants.setVIN(etEnterVin.getText().toString());
                okListener.okButton();
                dismiss();


            }
        });

        builder.setView(view);

        return builder.create();

    }



    public interface OkDialogListener {
        void okButton ();


    }



}
