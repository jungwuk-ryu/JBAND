package com.hancho.jband;

import com.hancho.jband.entities.Band;
import com.hancho.jband.entities.ResponseInfo;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class JbandClient {
    protected String accessToken;
    protected String clientId;      //TODO
    protected BandApi bandApi;

    public JbandClient(String accessToken){
        this.setAccessToken(accessToken);
        this.bandApi = new BandApi(this);
    }

    public ResponseInfo<ArrayList<Band>> getBandList() {
        return bandApi.getBandList();
    }
}
