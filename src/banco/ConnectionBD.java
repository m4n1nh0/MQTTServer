package banco;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import cluster.Sensor;

public class ConnectionBD {
	private static String SGBD;
    private static String usuario;
    private static String chave;
    private static String servidor;
    private static String SID;

    private static boolean status;

    private static Connection conBD;

    /**
     *Início da classe de conexão
     * @param SGBD Oracle, MySQL, OBDC e SQLite
     * @param PC IP o localhost
     * @param usuario Usuario com permissoes no BD
     * @param cheve Password do usuario
     * @param SID em oracle ORADAM2 e MySQL para o banco de dados usado (no restante vazio)
     */
    public ConnectionBD() {
    	SGBD = "";
    	servidor = "";
    	SID = "";
    	usuario = "";
    	chave = "";
    }

    public ConnectionBD(String _SGBD, String _servidor, String _SID, String _usuario, String _chave) {
    	SGBD = _SGBD;
    	servidor = _servidor;
    	SID = _SID;
    	usuario = _usuario;
    	chave = _chave;
    }

    //Método de Conexão
    public static Connection getConnectionBD() {

        try {
            switch (SGBD) {
                case "MySQL":
                    Class.forName("com.mysql.jdbc.Driver");
                    conBD = DriverManager.getConnection("jdbc:mysql://" + servidor + "/" + SID, usuario, chave);
                    break;
                case "Oracle":
                    Class.forName("oracle.jdbc.driver.OracleDriver");
                    conBD = DriverManager.getConnection("jdbc:oracle:thin:@" + servidor + ":1521:" + SID, usuario, chave);
                    break;
                case "OBDC": //Não é necessário passar mais dados para ele, tome cuidado com MYSQL_ODBC
                    Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
                    conBD = DriverManager.getConnection("jdbc:odbc:MYSQL_ODBC");
                    break;
                case "SQLite": //Os atributos PC, usuário e senha não são usados
                    Class.forName("org.sqlite.JDBC");
                    conBD = DriverManager.getConnection("jdbc:sqlite:" + SID);
                    break;
            }

            if (conBD != null) {
                status = true;
            }else{
            	status = false;
            }

        } catch (ClassNotFoundException e) {  //Driver não encontrado
        	status = false;
            return null;
        } catch (SQLException e) {//Não conseguindo se conectar ao banco
        	status = false;
            return null;
        }
        return conBD;

    }

    //Método que retorna o status da sua conexão
    public static boolean statusConection() {
        return status;
    }

    //Método que fecha sua conexão
    public boolean FecharConexao() {
        try {
        	ConnectionBD.getConnectionBD().close();
        	status = false;
            return true;
        } catch (SQLException e) {
        	status = true;
            return false;
        }
    }

    //Método que reinicia sua conexão
    public Connection ReiniciarConexao() {
        FecharConexao();
        return ConnectionBD.getConnectionBD();
    }

  //Método que grava o sensro no banco
    public void createSensor(Sensor aSensor) throws SQLException {
        PreparedStatement stmt = ConnectionBD.getConnectionBD().prepareStatement(
        		"insert into Sensors (idSensor, nome, Command, Type, baterypercent, baterycons, bateryini, baterylevel, deltanext, lgx1, lgx2, lgy1, lgy2, Vf, Vi, Xf, Xi, Data) "
        				   + "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)'",
        		Statement.RETURN_GENERATED_KEYS);

        stmt.setInt(1,aSensor.idSensor);
        stmt.setString(2,aSensor.nome);
        stmt.setString(3,aSensor.Command);
        stmt.setString(4,aSensor.Type);
        stmt.setInt(5,aSensor.baterypercent);
        stmt.setDouble(6,aSensor.baterycons);
        stmt.setDouble(7,aSensor.bateryini);
        stmt.setDouble(8,aSensor.baterylevel);
        stmt.setDouble(9,aSensor.deltanext);
        stmt.setDouble(10,aSensor.lgx1);
        stmt.setDouble(11,aSensor.lgx2);
        stmt.setDouble(12,aSensor.lgy1);
        stmt.setDouble(13,aSensor.lgy2);
        stmt.setDouble(14,aSensor.Vf);
        stmt.setDouble(15,aSensor.Vi);
        stmt.setDouble(16,aSensor.Xf);
        stmt.setDouble(17,aSensor.Xi);

        Date date = new Date(0);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") ;
        String currentDateTime = format.format(date);

        stmt.setString(18,currentDateTime);

        stmt.executeUpdate();

        ResultSet rs = stmt.getGeneratedKeys();

        if (rs.next()) {
        	aSensor.id = rs.getInt(1);
    	}

    }

    public List<Sensor> listSenorsWithDate(Date date) throws SQLException {
        PreparedStatement stmt = ConnectionBD.getConnectionBD().prepareStatement("SELECT * FROM Customers where date = '?'");
        stmt.setString(1,date.toString());

        ResultSet rs = stmt.executeQuery();

        List<Sensor> matches = new ArrayList<Sensor>();
        while ( rs.next() ) {
        	Sensor listed = new Sensor();
            listed.id = rs.getInt("id");
            listed.idSensor = rs.getInt("idSensor");
            listed.baterypercent = rs.getInt("baterypercent");
            listed.nome = rs.getString("nome");
            listed.Command = rs.getString("Command");
            listed.Type = rs.getString("Type");

            listed.baterycons = rs.getDouble("baterycons");
            listed.bateryini = rs.getDouble("bateryini");
            listed.baterylevel = rs.getDouble("baterylevel");
            listed.deltanext = rs.getDouble("deltanext");
            listed.lgx1 = rs.getDouble("lgx1");
            listed.lgx2 = rs.getDouble("lgx2");
            listed.lgy1 = rs.getDouble("lgy1");
            listed.lgy2 = rs.getDouble("lgy2");
            listed.Vf = rs.getDouble("Vf");
            listed.Vi = rs.getDouble("Vi");
            listed.Xf = rs.getDouble("Xf");
            listed.Xi = rs.getDouble("Xi");

            if (!matches.contains(listed)){
            	matches.add(listed);
            }
        }

        return matches;
    }

    public Sensor RetSensor(String sensor){

    	return null;
    }

}
