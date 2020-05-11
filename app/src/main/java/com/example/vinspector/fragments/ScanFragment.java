package com.example.vinspector.fragments;


import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.vinspector.R;
import com.example.vinspector.constants.Constants;
import com.example.vinspector.dialogs.EnterVinDialog;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;

import java.io.IOException;

import com.example.vinspector.dialogs.ScanDialog;

/**
 * A simple {@link Fragment} subclass.
 */
public class ScanFragment extends Fragment {

    TextView tvScan;
    SurfaceView surfaceView;
    CameraSource cameraSource;
    final int RequestCameraPermissionID = 1001;
    Context context;
    Activity activity;
    String tvScanString = "";
    Button dialogBtn;
    Button typeBtn;


    public ScanFragment(Context context, Activity activity) {
        this.context = context;
        this.activity = activity;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_scan, container, false);

        tvScan = rootView.findViewById(R.id.tvScan);

        dialogBtn = rootView.findViewById(R.id.dialogBtn);

        typeBtn = rootView.findViewById(R.id.typeBtn);

        typeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EnterVinDialog enterVinDialog = new EnterVinDialog(new EnterVinDialog.OkDialogListener() {
                    @Override
                    public void okButton() {

                        FragmentManager fm = getFragmentManager();
                        if (fm != null) {
                            fm.beginTransaction()
                                    .replace(R.id.fragment_container, new MyVinFragment())
                                    .commit();
                        }

                    }
                });

                enterVinDialog.show(getFragmentManager(), "ok");

            }
        });


        surfaceView = (SurfaceView) rootView.findViewById(R.id.surfaceView);

        TextRecognizer textRecognizer = new TextRecognizer.Builder(getContext()).build();


        cameraSource = new CameraSource.Builder(getContext(), textRecognizer)
                .setFacing(CameraSource.CAMERA_FACING_BACK)
                .setRequestedPreviewSize(1920, 1080)
                .setRequestedFps(2.0f)
                .setAutoFocusEnabled(true)
                .build();

        surfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder surfaceHolder) {
                try {
                    if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(activity,
                                new String[]{Manifest.permission.CAMERA},
                                RequestCameraPermissionID);
                    }
                    cameraSource.start(surfaceView.getHolder());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

            }
        });

        textRecognizer.setProcessor(new Detector.Processor<TextBlock>() {
            @Override
            public void release() {

            }

            @Override
            public void receiveDetections(Detector.Detections<TextBlock> detections) {

                final SparseArray<TextBlock> items = detections.getDetectedItems();
                if (items.size() != 0) {
                    tvScan.post(new Runnable() {
                        @Override
                        public void run() {
                            final StringBuilder stringBuilder = new StringBuilder();
                            for (int i = 0; i < items.size(); ++i) {
                                TextBlock item = items.valueAt(i);
                                stringBuilder.append(item.getValue());
                                stringBuilder.append("\n");
                            }

                            tvScanString = stringBuilder.toString();
                            tvScan.setText(tvScanString);



                            dialogBtn.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {




                                    ScanDialog scanDialog = new ScanDialog(tvScanString, new ScanDialog.DialogListener() {


                                        @Override
                                        public void yesButton() {
                                            Constants.setVIN(tvScanString);
                                            FragmentManager fm = getFragmentManager();
                                            if (fm != null) {
                                                fm.beginTransaction()
                                                        .replace(R.id.fragment_container, new MyVinFragment())
                                                        .commit();
                                            }
                                        }

                                        @Override
                                        public void noButton() {

                                            EnterVinDialog enterVinDialog = new EnterVinDialog(new EnterVinDialog.OkDialogListener() {
                                                @Override
                                                public void okButton() {

                                                    FragmentManager fm = getFragmentManager();
                                                    if (fm != null) {
                                                        fm.beginTransaction()
                                                                .replace(R.id.fragment_container, new MyVinFragment())
                                                                .commit();
                                                    }

                                                }
                                            });

                                            enterVinDialog.show(getFragmentManager(), "ok");
                                        }
                                    });


                                    scanDialog.show(getFragmentManager(), "nesto");


                                }
                            });


                        }
                    });
                }
            }
        });


        return rootView;
    }


}