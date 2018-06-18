package com.androidtutorialshub.loginregister.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.androidtutorialshub.loginregister.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttToken;


public class After_Login extends AppCompatActivity  {
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;

    String url ="http://ronixtech.com/ronix_services/task/srv.php";
    // String Broker_url_withHost="m13.cloudmqtt.com:11853";
    String Broker_url_withHost="tcp://m13.cloudmqtt.com:11853";
    final    String topic = "ronix/network";
    final String username = "qumrwmme";
    final String password = "oJHjXS7Xi0F9";
    MqttAndroidClient client;
    MqttToken mqttToken;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after__login);
        sendAndRequestResponse();

        final String clientId = MqttClient.generateClientId();
        client =new MqttAndroidClient(After_Login.this, Broker_url_withHost,clientId);
        MqttConnectOptions options = new MqttConnectOptions();
        options.setMqttVersion(MqttConnectOptions.MQTT_VERSION_3_1);
        byte[] payload = "some payload".getBytes();
        options.setWill(topic, payload,1,false);
        options.setUserName(username);
        options.setPassword(password.toCharArray());

        try {

            final IMqttToken token = client.connect(options);
            token.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    // We are connected
                    Toast.makeText(After_Login.this, "Connected ", Toast.LENGTH_SHORT).show();
                    Log.d( "OnSuccess","onSuccess");

                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    // Something went wrong e.g. connection timeout or firewall problems

                    Toast.makeText(After_Login.this, "Not Connected", Toast.LENGTH_SHORT).show();

                    Log.d("OnFailure", "onFailure");

                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }

    }





    private void  sendAndRequestResponse() {

        //RequestQueue initialized
        mRequestQueue = Volley.newRequestQueue(this);

        //String Request initialized
        mStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {            @Override
        public void onResponse(String response) {
            GsonBuilder gsonBuilder = new GsonBuilder();
            Gson gson = gsonBuilder.create();
            GsonFormat ssid = null;
            ssid = gson.fromJson(response,GsonFormat.class);
            String fin_ssid=ssid.getSSID().toString();

            Toast.makeText(getApplicationContext(),"SSID :"+fin_ssid, Toast.LENGTH_LONG).show();//display the response on screen

        }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        mRequestQueue.add(mStringRequest);
    }


}
