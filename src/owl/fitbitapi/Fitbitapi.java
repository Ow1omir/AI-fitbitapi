package owl.fitbitapi;


import com.google.appinventor.components.runtime.*;
import com.google.appinventor.components.annotations.*;
import com.google.appinventor.components.common.*;
import com.google.appinventor.components.runtime.util.*;
import com.google.appinventor.components.runtime.errors.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;


  public class Fitbitapi extends AndroidNonvisibleComponent {

    private ComponentContainer container;

    public Fitbitapi(ComponentContainer container) {
      super(container.$form());
      this.container = container;
    }

    @SimpleFunction(description = "Makes an asynchronous GET request to the specified URL and returns the response.")
    public void GetProfileJson(final String apiKey) {
      AsynchUtil.runAsynchronously(new Runnable() {
        @Override
        public void run() {
          try {
            String url = "https://api.fitbit.com/1/user/-/profile.json";
            final String response = sendGetRequest(url, apiKey);
            container.$form().runOnUiThread(new Runnable() {
              @Override
              public void run() {
                GotGetRequestResponse(response);
              }
            });
          } catch (IOException e) {
            e.printStackTrace();
            final String errorMessage = e.getMessage();
            container.$form().runOnUiThread(new Runnable() {
              @Override
              public void run() {
                ErrorOccurred(errorMessage);
              }
            });
          }
        }
      });
    }
    @SimpleFunction(description = "Get last heart rate.")
    public void GetHeartRate(final String apiKey) {
      AsynchUtil.runAsynchronously(new Runnable() {
        @Override
        public void run() {
          try {
            String url = "https://api.fitbit.com/1.2/user/-/activities/heart/date/today/1d/1sec.json";
            String response = sendGetRequest(url, apiKey);
            String a = response;
            JSONObject jsonObject = new JSONObject(a);
            JSONArray jsonArray = jsonObject.getJSONObject("activities-heart-intraday").getJSONArray("dataset");
            JSONObject jsonObject1 = jsonArray.getJSONObject(jsonArray.length() - 1);
            response = String.valueOf(jsonObject1.get("value"));

            String finalResponse = response;

            container.$form().runOnUiThread(new Runnable() {
              @Override
              public void run() {
                GotGetHeartRate(finalResponse);
              }
            });
          } catch (IOException e) {
            e.printStackTrace();
            final String errorMessage = e.getMessage();
            container.$form().runOnUiThread(new Runnable() {
              @Override
              public void run() {
                ErrorOccurred(errorMessage);
              }
            });
          } catch (JSONException e) {
          }
        }
      });
    }
    private String sendGetRequest(String url, String apiKey) throws IOException {
      HttpURLConnection connection = null;
      BufferedReader reader = null;
      StringBuilder response = new StringBuilder();


      try {
        URL apiUrl = new URL(url);
        connection = (HttpURLConnection) apiUrl.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Authorization", "Bearer " + apiKey);

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
          InputStream inputStream = connection.getInputStream();
          reader = new BufferedReader(new InputStreamReader(inputStream));

          String line;
          while ((line = reader.readLine()) != null) {
            response.append(line);
          }
        } else {
          throw new IOException("GET request failed with HTTP error code: " + responseCode);
        }
      } finally {
        if (reader != null) {
          reader.close();
        }
        if (connection != null) {
          connection.disconnect();
        }
      }

      return response.toString();
    }

    @SimpleEvent(description = "Event raised when the GET request response is received.")
    public void GotGetRequestResponse(String response) {
      EventDispatcher.dispatchEvent(this, "GotGetRequestResponse", response);
    }
    @SimpleEvent(description = "Event raised when the GET request response is received.")
    public void GotGetHeartRate(String response) {
      EventDispatcher.dispatchEvent(this, "GotGetHeartRate", response);
    }

    @SimpleEvent(description = "Event raised when an error occurs during the GET request.")
    public void ErrorOccurred(String errorMessage) {
      EventDispatcher.dispatchEvent(this, "ErrorOccurred", errorMessage);
    }
  }
