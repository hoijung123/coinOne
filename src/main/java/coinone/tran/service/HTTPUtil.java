package coinone.tran.service;
import org.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class HTTPUtil {

    public static String requestGet(String strUrl, String accept) throws Exception {
        URL url = new URL(strUrl);
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();

        conn.setDoOutput(true);
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", accept);
        conn.setConnectTimeout(5000);
        conn.connect();

        if(conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
//            throw new Exception();
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return response.toString();
    }

    public static String requestPost(String strUrl, Map<String, String> reqProps, String params) throws Exception {
        URL url = new URL(strUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        if(reqProps != null)
            for(String key : reqProps.keySet()) {
                conn.setRequestProperty(key, reqProps.get(key));
            }
        conn.setConnectTimeout(5000);

        params = (params == null ? "" : params);
        DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
        wr.writeBytes(params);
        wr.flush();
        wr.close();

        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        System.out.println(response);
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return response.toString();
    }

    public static String getJSONfromGet(String apiUrl) throws Exception {
        Thread.sleep(200);
        return requestGet(apiUrl, "application/json");
    }

    public static String getJSONfromPost(String apiUrl, Map<String, String> reqProps, String params) throws Exception {
        Thread.sleep(500);
        return (requestPost(apiUrl, reqProps, params));
    }

    public static String paramsBuilder(Map<String, String> map) {
        StringBuilder builder = new StringBuilder();
        for(String key : map.keySet()) {
            builder.append(key);
            builder.append("=");
            builder.append(map.get(key));
            builder.append("&");
        }
        builder.deleteCharAt(builder.length()-1);
        return builder.toString();
    }
}
