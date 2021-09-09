package mqtt;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import banco.ConnectionBD;
import cluster.Sensor;
//import janela.Arquivo;

public class MqttCallBack implements MqttCallback {

	private Connection cliente;
	private ConnectionBD clienteBD;

	public MqttCallBack(Connection connection, ConnectionBD connbd) {
		cliente = connection;
		clienteBD = connbd;
	}

	public void connectionLost(Throwable throwable) {
		String str = "Connection to MQTT broker lost!";
		System.out.println(str);
	}

	public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
		String str = "Message received: "+ new String(mqttMessage.getPayload());
		String[] command = new String(mqttMessage.getPayload()).split(";");
		if (cliente.ValCommand(command[0])){
			String Mensagem = "";
			for (int i = 2; i < command.length; i++) {
				Mensagem = Mensagem + command[i];
			}
			cliente.Publisher(command[1], Mensagem);
		}else{
			//Arquivo arquivo =  new Arquivo();
			//arquivo.escreverRet(new String(mqttMessage.getPayload()));
			Sensor sensor = clienteBD.RetSensor(new String(mqttMessage.getPayload()));
			clienteBD.createSensor(sensor);
			System.out.println(str);
		}
	}

	public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

	}

}
