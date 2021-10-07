package cluster;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import banco.ConnectionBD;
import mqtt.Connection;

public class Cluster extends Thread {

    //Controle do tempo de analise
	private long inicioCluster = 0;
	private long TRound = 180000; //180 segundos, ou 3 min
    private int QTD_Round = 0; //anota quantos rounds foram realizados
    // Quantidade máxima de seleção de CH que vao ser comparadas
    static private int MAX_VAL_X = 100;//metros //dita o maior valor da coordenada X do campo
    static private int MAX_VAL_Y = 100;//metros //dita o maior valor da coordenada Y do campo
    static private int QTD_MAX_CH = 20;//(int) (QNTD_SENSORES_NA_REDE * PERCENTUAL * 1.2); //Define a quantidade máxima de anuncios de CH que vao ser comparadas
    static private int QTD_MAX_SENSORES = 50; //Quantidade máxima que um cluster head comporta
    //areaTotal/Qntd de clusterheads
    private double rangeCH = (MAX_VAL_X * MAX_VAL_Y) / 2;
    // Controle
    private int NivelBatAlert = 20; //Nivel da Bateria de alerta
    private int NClusterAte = 0; //numero do eventos em que o sensor deve voltar a eleição
    private boolean isCH = false; //define a situação do sensor na rede
    private String id_CH = null; //Variável que guarda o id do sensor que é Cluster Head
    private String[] id_SensorFolha = new String[QTD_MAX_SENSORES]; //Array que guarda os ids dos sensores
    // Controle Lists
    public ArrayList<Sensor> sensors;
    public ArrayList<ArrayList<Sensor>> sensors_;
    public ArrayList<Sensor> group;
    public ArrayList<Sensor> base;
    public ArrayList<Sensor> clusters;
    // Iniciando o processo de seleção do CH baseado no artigo https://ieeexplore.ieee.org/document/8587930
    // Exemplo Thread http://www.inf.ufes.br/~pdcosta/ensino/2008-1-sistemas-operacionais/Slides/Aula17-1slide.pdf
    // Exemplo Thread https://www.caelum.com.br/apostila-java-orientacao-objetos/apendice-programacao-concorrente-e-threads#e-as-classes-anonimas
    public void run (ConnectionBD connbd) throws SQLException {
        Date date = new Date(0);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") ;
        String currentDateTime = format.format(date);
        group = new ArrayList<Sensor>();
        sensors_ = new ArrayList<ArrayList<Sensor>>();
        sensors = connbd.listSenorsWithDate(currentDateTime,sensors);
    	inicioCluster = System.currentTimeMillis();
    	base =  new ArrayList<Sensor>();
    	for (Sensor sensor : sensors) { // monto uma lista das base stations
    		if (sensor.getType().equals("bs")){
    			base.add(sensor);
    		}
    	}
    	//Executando tarefa
        while (true) {
        	for (Sensor sensor : sensors) { //carrego os sensores do banco
    			if (sensor.getBaterylevel() > 0){ // faz sempre que os sensores da lista tiverem bateria
    				for (Sensor sennext : sensors){ // comparo com sensores da lista
    					if(group.size() < QTD_MAX_SENSORES){ // verifico o tamanho do grupo
    						double distance = Distancia(sensor.lgx1,sensor.lgy1,sennext.lgx1,sennext.lgy1);
    						if (distance < sensor.radius && distance < sennext.radius){
    							group.add(sennext);
    						}
    					}
    				}
    				sensors_.add(group); //adiciono o grupo a lista de grupos
    			}
    			//fazer a lista de clusters

    			//enviar msg mqtt para os sensores saberem quem são seus clusters e a trilha de comunicação
    			//pretendo usar distancia para passar o proximo
                else {//Carga = 0
                	InicializarVariavis(connbd,sensors);
                }
                while (System.currentTimeMillis() < (inicioCluster + (TRound * (QTD_Round + 1)))) {
                }
                QTD_Round++;
    		}

        }
    }

    private void InicializarVariavis(ConnectionBD connbd, ArrayList<Sensor> sensors2) throws SQLException {
        Date date = new Date(0);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") ;
        String currentDateTime = format.format(date);
    	sensors = connbd.listSenorsWithDate(currentDateTime,sensors2);
    	id_CH = null;
        for (int i = 0; i < QTD_MAX_SENSORES; i++) {
        	id_SensorFolha[i] = null;
        }

    }

	public double Distancia(double x1, double y1, double x2, double y2){
		double distancia = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
		return distancia;
	}
    // Comunicar direto do cluster head

    // Verificar se o sensor entrou em mais de um cluster, não permitir
}
