package com.example.vinspector.network;

import com.example.vinspector.constants.Constants;
import com.example.vinspector.model.GetVinDecodeResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface VinApi {

    @GET(Constants.VERSION + "/" + Constants.API_KEY + "/{id}/" + Constants.ID + "/{vin}"  + ".json")
    Call<GetVinDecodeResponse> getVinDecodeInfo(@Path("id") String api_path, @Path("vin") String api_vin);

}
