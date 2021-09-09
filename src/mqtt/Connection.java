package mqtt;

import java.util.ArrayList;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
//import org.eclipse.paho.client.mqttv3.MqttSecurityException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import banco.ConnectionBD;
import cluster.Sensor;

public class Connection {

	public String host;
	public int Port;
	public boolean Connected;
	public MqttClient client;

	public ArrayList<Sensor> sensors;

	public String getHost() {
		return host;
	}
	public int getPort() {
		return Port;
	}
	public boolean isConected() {
		return Connected;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public void setPort(int port) {
		Port = port;
	}
	public void setConected(boolean connected) {
		Connected = connected;
	}

	public Connection() {
		sensors = new ArrayList<Sensor>();
	}

	public String Uri(){
		String uri = "tcp://" + host + ":" + Port;
		return uri;
	}

	public boolean Connect(){
		Connected = true;
		String uri= this.Uri();
		try {
			client = new MqttClient(uri, MqttClient.generateClientId(), new MemoryPersistence());
		} catch (MqttException e) {
			Connected = false;
		}
	    return Connected;
	}

	public boolean Disconnect(){
		Connected = false;
		try {
			client.disconnect();
		} catch (MqttException e) {
			return Connected;
		}
		return Connected;
	}

	public void Subscriber(String Topic, ConnectionBD connbd) throws MqttException{
	    if (this.Connect()){
	    	MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);
            connOpts.setAutomaticReconnect(true);
	    	client.setCallback( new MqttCallBack(this, connbd));
		    client.connect(connOpts);
		    client.subscribe(Topic,1);
	    }
	}

	public void Publisher(String Topic, String Mensagem){
		if (this.Connect()){
		    try {
				client.connect();
				MqttMessage message = new MqttMessage();
			    message.setPayload(Mensagem.getBytes());
			    message.setQos(1);
			    client.publish(Topic, message);
			    client.disconnect();
			} catch (MqttException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public boolean Compare(String sensor, Sensor sensorlist) {
		// TODO Auto-generated method stub
		if (sensor.equals(sensorlist.getNome())){
			return true;
		}
		return false;
	}

	public boolean ValCommand(String Comando){
		switch(Comando.toLowerCase()){
		case "command":
			return true;
		}

		return false;
	}

}