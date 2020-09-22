package apk.put.green4go.classes;
import android.content.Context;
import android.net.Uri;
import android.net.http.HttpResponseCache;
import android.os.AsyncTask;
import android.os.Debug;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static java.util.Objects.isNull;

public class API {
    private Context appContext;

    String apiUser = "";
    String apiPassword = "";
    String apiUrl;
    String apiToken;
    String apiTokenExpirationDate;
    ProgressBar progressBar;
    TextView errorsText;

    public API(Context context, ProgressBar progressBar, TextView errorsText) {
        this.appContext = context;
        this.apiUser = "";
        this.apiPassword = "";
        this.apiUrl = "http://185.243.54.180/api";
        this.apiToken = "";
        this.apiTokenExpirationDate = "";
        this.progressBar = progressBar;
        this.errorsText = errorsText;
    }

    public ArrayList<String> callAPI(final String url, final String method, final HashMap<String, String> parameters, final boolean isTokenRequired, final boolean isOutputting) {
        URL httpbinEndpoint = null;
        try {
            httpbinEndpoint = new URL(apiUrl + url);
            HttpURLConnection apiConnection = (HttpURLConnection) httpbinEndpoint.openConnection();
            if(isTokenRequired){
                apiConnection.setRequestProperty("X-API-Key", Global.apiToken);
            }
            apiConnection.setRequestMethod(method);

            apiConnection.setDoInput(true);
            apiConnection.setDoOutput(isOutputting);
            apiConnection.setUseCaches(false);
            if(!parameters.isEmpty()) {
                Uri.Builder builder = new Uri.Builder();
                for (Map.Entry map : parameters.entrySet()) {
                    builder.appendQueryParameter((String) map.getKey(), (String) map.getValue());
                }
                String query = builder.build().getEncodedQuery();
                OutputStream os = apiConnection.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, StandardCharsets.UTF_8));
                writer.write(query);
                writer.flush();
                writer.close();
                os.close();
            }
            apiConnection.connect();
            String responseCode = String.valueOf(apiConnection.getResponseCode());
            Log.d("STEP", responseCode);
            StringBuffer sb = new StringBuffer();
            InputStream is = null;
            is = new BufferedInputStream(apiConnection.getInputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String inputLine = "";
            while ((inputLine = br.readLine()) != null) {
                sb.append(inputLine);
            }
            String responseBody = sb.toString();
            ArrayList<String> response = new ArrayList<String>();
            response.add(responseCode);
            response.add(responseBody);
            return response;

        } catch (IOException e) {
            e.printStackTrace();
            ArrayList<String> response = new ArrayList<String>();
            response.add("500");
            response.add(e.getMessage());
            return response;
        }
    }

    private void checkTokenExpirationDate(){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date expirationDate = new Date();
        try {
            expirationDate = formatter.parse(apiTokenExpirationDate);
        } catch (ParseException e) {
            Log.d("ERROR", Objects.requireNonNull(e.getMessage()));
        }
        Date currentDate = new Date();
        if(!(Objects.requireNonNull(expirationDate).compareTo(currentDate) > 0)) {
            generateToken();
        }
    }

    public String getApiUser() {
        return apiUser;
    }

    public void setApiUser(String apiUser) {
        this.apiUser = apiUser;
    }

    public String getApiPassword() {
        return apiPassword;
    }

    public void setApiPassword(String apiPassword) {
        this.apiPassword = apiPassword;
    }

    private void generateToken() {
        progressBar.setVisibility(View.VISIBLE);
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                HashMap<String, String> parameters = new HashMap<String, String>();
                parameters.put("username", apiUser);
                parameters.put("password", apiPassword);
                /*
                try (JsonReader jsonReader = callAPI("/auth/login", "POST", parameters)) {
                    jsonReader.beginObject();
                    while (jsonReader.hasNext()) {
                        String key = jsonReader.nextName();
                        switch (key) {
                            case "token":
                                apiToken = jsonReader.nextString();
                                break;
                            case "expirationDate":
                                apiTokenExpirationDate = jsonReader.nextString();
                                break;
                        }
                    }
                    progressBar.setVisibility(View.GONE);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                */
            }
        });
    }
}
