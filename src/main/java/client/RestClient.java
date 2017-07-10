package client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RestClient {

    private static final Logger log = LoggerFactory.getLogger(RestClient.class);

    private String baseUrl;
    private HttpURLConnection conn;

    public RestClient(String url) {
        this.baseUrl = url;
    }

    private String getBaseUrl() {
        return this.baseUrl;
    }

    public String get(String id) {

        String restResponse = null;

        try {
            String fullUrl = getBaseUrl() + id;
            URL url = new URL(fullUrl);

            this.conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed HTTP request for : " + fullUrl +
                        " HTTP error code : " + conn.getResponseCode() +
                        " HTTP response : " + conn.getResponseMessage());
            }
            else {
                StringBuilder response = new StringBuilder();

                BufferedReader br = new BufferedReader(new InputStreamReader(
                        (conn.getInputStream())));

                String output;

                while ((output = br.readLine()) != null) {
                    response.append(output);
                }

                restResponse = response.toString();
                log.info("restResponse: {}", restResponse);
            }
        }
        catch (Exception e) {
            log.error("Error while firing up the REST call: {}", e.getMessage());
            disconnect();
        }
        finally {
            disconnect();
        }

        return restResponse;
    }

    private void disconnect() {
        if (conn != null) {
            conn.disconnect();
            conn = null;
        }
    }
}
