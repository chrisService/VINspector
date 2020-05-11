package com.example.vinspector.model;

import java.util.List;

public class GetVinDecodeResponse {

    private List<VinDetailInfo> decode;

    public GetVinDecodeResponse() {
    }

    public GetVinDecodeResponse(List<VinDetailInfo> decode) {
        this.decode = decode;
    }

    public List<VinDetailInfo> getDecode() {
        return decode;
    }

    public void setDecode(List<VinDetailInfo> decode) {
        this.decode = decode;
    }
}
