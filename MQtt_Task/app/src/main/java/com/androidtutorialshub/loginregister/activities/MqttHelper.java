package com.androidtutorialshub.loginregister.activities;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MqttDefaultFilePersistence;

import java.util.Locale;

public class MqttHelper {
    public MqttClient mqttAndroidClient;
    String Broker_url="m13.cloudmqtt.com";
    String Port="11853";
    String url = String.format(Broker_url,Port);
    final String subscriptionTopic = "ronix/network";

    final String username = "qumrwmme";
    final String password = "oJHjXS7Xi0F9";


}
