package com.hancho.jband;

public class Jband {
    public Jband(){

    }

    public static JbandClient createClient(String accessToken){
        return new JbandClient(accessToken);
    }

    private static String requestAuthCode(){
        return "";
    }

    private static String requestToken(){
        return "";
    }

}
