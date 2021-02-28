package com.hancho.jband.entities;

import lombok.Getter;
import lombok.Setter;
import org.json.simple.JSONObject;

@Getter
@Setter
public class ResponseInfo<T> {
    protected long resultCode;
    protected T result;

    public ResponseInfo(long resultCode, T result){
        this.setResultCode(resultCode);
        this.setResult(result);
    }

    public ResponseInfo(JSONObject resultJson, T result){
        this((long) resultJson.get("result_code"), result);
    }

    public boolean isSuccess(){
        return this.resultCode == 1;
    }
}
