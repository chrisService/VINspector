package com.example.vinspector.constants;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Constants {

    public static final String BASE_URL = "https://api.vindecoder.eu/";
    public static final String VERSION = "3.0";
    public static final String API_KEY = "750441402a4d";
    public static final String SECRET_KEY = "398edf1b1e";
    public static final String ID = "decode";

    public static String VIN;

    public static void setVIN(String VIN) {
        Constants.VIN = VIN;
    }

    public static String getVIN() {
        return VIN;
    }

    //        test Vin    "VF1BM0F0529591566"

    public static String CONTROLLSUM;

}
