package com.codetally.service;

import com.codetally.configuration.GithubConfiguration;
import com.codetally.model.github.User;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

/**
 * Created by greg on 25/06/17.
 */
@Service
public class MeService {
    private static final Logger logger = LoggerFactory.getLogger(MeService.class);

    public String validateMe(String token) {
        HttpURLConnection urlconnection = null;
        try {
            urlconnection = (HttpURLConnection) new URL(GithubConfiguration.access_token_request_url + token).openConnection();
            urlconnection.setRequestMethod("HEAD");
            urlconnection.connect();
            int responsecode = urlconnection.getResponseCode();
            if (responsecode == HttpURLConnection.HTTP_OK) {
                urlconnection.disconnect();
                urlconnection = (HttpURLConnection) new URL(GithubConfiguration.access_token_request_url + token).openConnection();
                urlconnection.setRequestMethod("GET");
                urlconnection.connect();
                Gson gson = new Gson();
                User user = gson.fromJson(new InputStreamReader(urlconnection.getInputStream(), StandardCharsets.UTF_8), User.class);
                return gson.toJson(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public String handleCallback(String code) {

        try {
            String urlParameters = GithubConfiguration.client_id
                    + "=" + GithubConfiguration.clientid
                    + "&" + GithubConfiguration.client_secret
                    + "=" + GithubConfiguration.clientsecret
                    + "&" + GithubConfiguration.code
                    + "=" + code;
            String request = GithubConfiguration.access_token_url;
            URL url = new URL(request);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setInstanceFollowRedirects(false);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("charset", "utf-8");
            conn.setRequestProperty("accept", "application/json");
            conn.setUseCaches(false);
            DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.flush();
            wr.close();
            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8), JsonObject.class);
            return jsonObject.get(GithubConfiguration.access_token).getAsString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public String createCookie(String token) {
        DateFormat df = new SimpleDateFormat("EEE, dd-MMM-yyyy HH:mm:ss zzz");
        df.setTimeZone(TimeZone.getTimeZone("GMT"));

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MINUTE, 20);
        String cookieExpiry = df.format(cal.getTime());
        return "X-AUTH-TOKEN=" + token + "; path=/; expires=" + cookieExpiry;

    }
}
