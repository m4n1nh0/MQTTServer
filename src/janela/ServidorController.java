package janela;

import java.io.IOException;
import java.io.PrintStream;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;

import banco.ConnectionBD;
import mqtt.Connection;
import org.eclipse.paho.client.mqttv3.MqttException;
//import org.eclipse.paho.client.mqttv3.MqttSecurityException;

public class ServidorController implements Initializable {

	@FXML
	private Label lblMensagem;

	@FXML
	private TextField txtHost;

	@FXML
	private TextField txtPorta;

	@FXML
	private TextField txtTopic;

	@FXML
	public TextArea textConsole;

	@FXML
	public CheckBox cbBanco;

	@FXML
	public TextField txtServerName;

	@FXML
	public TextField txtSID;

	@FXML
	public TextField txtUser;

	@FXML
	public PasswordField pswSenha;

	@FXML
	private void conectar() throws IOException {
		if (this.valCampos()) {
			ConnectionBD connbd = new ConnectionBD("MySQL", "localhost:3311", "wsnufs", "root", "root");
			if (cbBanco.isSelected()){
				connbd = new ConnectionBD("MySQL", txtServerName.getText(), txtSID.getText(), txtUser.getText(), pswSenha.getText());
			}
			Connection conn = new Connection();
			conn.setHost(txtHost.getText());
			conn.setPort(Integer.parseInt(txtPorta.getText()));
			if (conn.Connect()) {
				lblMensagem.setText("Conectado com sucesso!");
				try {
					String _topic = txtTopic.getText();
					conn.Subscriber(_topic,connbd);
					//conn.Subscriber(txtTopic.getText());
					System.out.println("Comunicacao iniciada");
					//Arquivo arq = new Arquivo();
					//arq.criarArquivo(text);
				} catch (MqttException e) {
					// TODO Auto-generated catch block
					System.out.println("Falha na comunicacao");
					// e.printStackTrace();
				}
			} else {
				lblMensagem.setText("Falha na conexão!");
			}
		} else {
			lblMensagem.setText("Campos não preenchidos!");
		}
	}

	@FXML
	private void desconectar() {
		Connection conn = new Connection();
		ConnectionBD connbd = new ConnectionBD("MySQL", "localhost:3311", "wsnufs", "root", "root");
		if (cbBanco.isSelected()){
			connbd = new ConnectionBD("MySQL", txtServerName.getText(), txtSID.getText(), txtUser.getText(), pswSenha.getText());
		}
		conn.setHost(txtHost.getText());
		conn.setPort(Integer.parseInt(txtPorta.getText()));
		if (conn.Connect()){
			conn.Disconnect();
			connbd.FecharConexao();
			textConsole.clear();
			lblMensagem.setText("Desconectado!");
		}
	}

	public ServidorController() {

	}

	private boolean valCampos() {
		if (txtHost.getText().isEmpty() || txtPorta.getText().isEmpty() || txtTopic.getText().isEmpty()) {
			return false;
		}
		return true;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		ServidorConsoleStream mqttOut = new ServidorConsoleStream(textConsole);
		PrintStream texto = new PrintStream(mqttOut, true);
		System.setOut(texto);
	}
}
