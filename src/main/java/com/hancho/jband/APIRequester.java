package com.hancho.jband;

import lombok.NonNull;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;

import static com.hancho.jband.JBAND.ACCESS_TOKEN;

public class APIRequester {
    private final static String USER_AGENT = "Mozilla/5.0";
    private final static String URL = "https://openapi.band.us/";
    private final static String OAUTH2_URL = "https://auth.band.us/oauth2/";

    public APIRequester() {
    }

    public JSONObject getRequest(String api) {
        return this.getRequest(api, "");
    }

    public JSONObject getRequest(String api, @NonNull String parameters) {
        if (ACCESS_TOKEN == null) {
            MainLogger.error("Access Token is null", new NoAccessTokenException());
            return null;
        }
        URL url = null;
        JSONObject resultJsonObj = null;
        try {
            url = new URL(URL + api + "?access_token=" + ACCESS_TOKEN + parameters);

            HttpURLConnection con = null;
            con = (HttpURLConnection) url.openConnection();

            con.setRequestMethod("GET");
            con.setRequestProperty("Accept-Charset", "UTF-8");
            con.setRequestProperty("Accept", "application/json");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String il;
            StringBuilder resultJson = new StringBuilder();
            while ((il = bufferedReader.readLine()) != null) {
                resultJson.append(il);
            }
            bufferedReader.close();
            con.disconnect();

            String resultJsonStr = resultJson.toString();
            JSONParser parser = new JSONParser();
            resultJsonObj = (JSONObject) parser.parse(resultJsonStr);
            return resultJsonObj;
        } catch (ParseException | IOException e) {
            MainLogger.error("An error occurred while request API.", e);
            return null;
        }
    }

    public JSONObject postRequest(String api, @NonNull String parameters) {
        if (ACCESS_TOKEN == null) {
            MainLogger.error("Access Token is null", new NoAccessTokenException());
            return null;
        }
        try {
            URL url = new URL(URL + api);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            String encodedAuth = Base64.getEncoder().encodeToString(ACCESS_TOKEN.getBytes());
            con.setRequestMethod("POST");
            con.setRequestProperty("Authorization", "Basic " + encodedAuth);
            con.setRequestProperty("Accept-Charset", "UTF-8");
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            con.setRequestProperty("Accept", "application/json");
            con.setRequestProperty("User-Agent", USER_AGENT);
            con.setUseCaches(false);
            con.setDoInput(true);
            con.setDoOutput(true);

            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes("access_token=" + ACCESS_TOKEN + parameters);
            wr.flush();
            wr.close();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String il;
            StringBuilder resultJson = new StringBuilder();
            while ((il = bufferedReader.readLine()) != null) {
                resultJson.append(il);
            }
            bufferedReader.close();
            con.disconnect();

            String resultJsonStr = resultJson.toString();
            JSONParser parser = new JSONParser();
            JSONObject resultJsonObj = (JSONObject) parser.parse(resultJsonStr);
            return resultJsonObj;
        } catch (IOException | ParseException e) {
            MainLogger.error("An error occurred while request API.", e);
            return null;
        }
    }

    public enum ErrorType {

        INVALID_PARAMETERS(211),

        INSUFFICIENT_PARAMETERS(212),

        APP_QUOTA_HAS_BEEN_EXCEEDED(1001),

        USER_QUOTA_HAS_BEEN_EXCEEDED(1002),

        COOL_DOWN_TIME_RESTRICTION(1003),

        ONLY_BAND_LEADERS_ARE_ALLOWED_TO_DO_THIS(2142),

        INVALID_RESPONSE(2300),

        INVALID_REQUEST(3000),

        EXCEEDED_CHAREACTER_LIMIT(3001), // ?

        IMAGE_FILE_SIZE_HAS_BEEN_EXCEEDED(3002),

        NUMBER_OF_IMAGE_FILES_HAS_BEEN_EXCEEDED(3003),

        UNAUTHORIZED(10401),

        FORBIDDEN(10403),

        INVALID_MEMBER(60100),

        NOT_MY_FRIEND(60101),

        THIS_USER_IS_NOT_CONNECTED(60103),

        THIS_USER_HAS_ALREADY_BEEN_CONNECTED(60104),

        YOU_ARE_BAND_LEADER_AND_BAND_HAS_MEMBERS(60104),

        THIS_FUNCTION_IS_GRANTED_TO_THE_SPECIFIED_MEMBER(60106),

        YOU_HAVE_ALREADY_JOINED_BAND(60201),

        EXCEEDED_BAND_MAX(60202),

        THIS_BAND_DID_NOT_ALLOW_ACCESS_BY_THE_USER(60204),

        RECEIVING_MESSAGE_IS_BLOCKED(60300),

        ONLY_DESIGNATED_MEMBERS_CAN_WRITE_POST(60400),


        THIS_INVITATION_IS_INVALID(60700),

        IMAGE_URL_IS_INVALID_OR_THE_FORMAT_IS_NOT_SUPPORTED(60800),

        ALBUM_NOT_EXISTS(60801);


        ErrorType(int errorCode) {

        }
    }
}

