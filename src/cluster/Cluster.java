package cluster;

public class Cluster extends Thread {

    //Controle do tempo de analise
    private long TRound = 180000; //180 segundos, ou 3 min
    // Quantidade máxima de seleção de CH que vao ser comparadas
    static private int QTD_SENSORES_REDE = 100; //Define quantos sensores eu vou ter na minha rede
    static private int MAX_VAL_X = 100;//metros //dita o maior valor da coordenada X do campo
    static private int MAX_VAL_Y = 100;//metros //dita o maior valor da coordenada Y do campo
    static private int QTD_MAX_CH = 20;//(int) (QNTD_SENSORES_NA_REDE * PERCENTUAL * 1.2); //Define a quantidade máxima de anuncios de CH que vao ser comparadas
    static private int QTD_MAX_SENSORES = 50; //Quantidade máxima que um cluster head comporta
    //areaTotal/Qntd de clusterheads
    private double rangeCH = (MAX_VAL_X * MAX_VAL_Y) / 2;
    // Controle
    private boolean isCH = false; //define a situação do sensor na rede
    private String id_CH = null; //Variável que guarda o id do sensor que é Cluster Head
    private String[] id_SensorFolha = new String[QTD_MAX_SENSORES]; //Array que guarda os ids dos sensores
    private int qtdRealFolhas = 0; //quantidade de sensores que respondem ao CH, menor que o total suportado de sensores pelo CH
    // Iniciando o processo de seleção do CH baseado no artigo https://ieeexplore.ieee.org/document/8587930
    // Exemplo Thread http://www.inf.ufes.br/~pdcosta/ensino/2008-1-sistemas-operacionais/Slides/Aula17-1slide.pdf
    // Exemplo Thread https://www.caelum.com.br/apostila-java-orientacao-objetos/apendice-programacao-concorrente-e-threads#e-as-classes-anonimas
    public void run () {
        // ...
    }
}
