package com.example.vinspector.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vinspector.R;
import com.example.vinspector.adapters.VinAdapter;
import com.example.vinspector.constants.Constants;
import com.example.vinspector.model.GetVinDecodeResponse;
import com.example.vinspector.model.VinDetailInfo;
import com.example.vinspector.network.RetrofitClient;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.vinspector.constants.Constants.API_KEY;
import static com.example.vinspector.constants.Constants.ID;
import static com.example.vinspector.constants.Constants.SECRET_KEY;
import static com.example.vinspector.constants.Constants.VIN;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyVinFragment extends Fragment {


    public MyVinFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_my_vin, container, false);

        final RecyclerView recyclerView = rootView.findViewById(R.id.rvMyScan);

        LinearLayoutManager llManager = new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(llManager);

        String vin_path = Constants.getVIN();

        Constants.CONTROLLSUM = setControllsum();



        RetrofitClient.getVinApi().getVinDecodeInfo(Constants.CONTROLLSUM, vin_path).enqueue(new Callback<GetVinDecodeResponse>() {
            @Override
            public void onResponse(Call<GetVinDecodeResponse> call, Response<GetVinDecodeResponse> response) {

                List<VinDetailInfo> decode = response.body().getDecode();

                List<VinDetailInfo> incorect = new ArrayList<>();

                incorect.add(new VinDetailInfo("INCORECT", "VIN"));

                VinAdapter adapter;

                if (decode != null){

                    adapter = new VinAdapter(decode);
                }else {
                    adapter = new VinAdapter(incorect);
                }


                recyclerView.setAdapter(adapter);


            }

            @Override
            public void onFailure(Call<GetVinDecodeResponse> call, Throwable t) {

            }
        });




        return rootView;
    }


    private static String setControllsum(){

        String controlSum = null;
        try {
            controlSum = sha1(VIN + "|" + ID + "|" + API_KEY + "|" + SECRET_KEY).substring(0, 10);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return controlSum;
    }


    static String sha1(String input) throws NoSuchAlgorithmException {
        MessageDigest mDigest = MessageDigest.getInstance("SHA1");
        byte[] result = mDigest.digest(input.getBytes());
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < result.length; i++) {
            sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
        }

        return sb.toString();
    }


}
