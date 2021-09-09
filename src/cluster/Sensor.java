package cluster;

public class Sensor {
	public String nome;
	public String Command;
	public String Type;

	public int id;
	public int idSensor;
	public int baterypercent;
	public int round;
	public int head;
	public int cluster_members;
	public int head_count;

	public double baterylevel;
	public double bateryini;
	public double baterycons;
	public double deltanext;
	public double Xi;
	public double Vi;
	public double Xf;
	public double Vf;
	public double lgx1;
	public double lgy1;
	public double lgx2;
	public double lgy2;
	public double radius;

	public Sensor() {
		super();
	}

	public String getNome() {
		return nome;
	}
	public int getIdSensor() {
		return idSensor;
	}
	public int getId() {
		return id;
	}
	public double getBaterylevel() {
		return baterylevel;
	}
	public double getBateryini() {
		return bateryini;
	}
	public double getBaterycons() {
		return baterycons;
	}
	public int getBaterypercent() {
		return baterypercent;
	}
	public String getCommand() {
		return Command;
	}

	public double getDeltanext() {
		return deltanext;
	}

	public double getXi() {
		return Xi;
	}

	public double getVi() {
		return Vi;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setIdSensor(int idSensor) {
		this.idSensor = idSensor;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setBaterylevel(double baterylevel) {
		this.baterylevel = baterylevel;
	}
	public void setBateryini(double bateryini) {
		this.bateryini = bateryini;
	}
	public void setBaterycons(double baterycons) {
		this.baterycons = baterycons;
	}
	public void setBaterypercent(int baterypercent) {
		this.baterypercent = baterypercent;
	}
	public void setCommand(String command) {
		Command = command;
	}

	public void setDeltanext(double deltanext) {
		this.deltanext = deltanext;
	}

	public void setXi(double xi) {
		Xi = xi;
	}

	public void setVi(double vi) {
		Vi = vi;
	}

	public double getXf() {
		return Xf;
	}

	public void setXf(double xf) {
		Xf = xf;
	}

	public double getVf() {
		return Vf;
	}

	public void setVf(double vf) {
		Vf = vf;
	}

	public double getLgx1() {
		return lgx1;
	}

	public void setLgx1(double lgx1) {
		this.lgx1 = lgx1;
	}

	public double getLgy1() {
		return lgy1;
	}

	public void setLgy1(double lgy1) {
		this.lgy1 = lgy1;
	}

	public double getLgx2() {
		return lgx2;
	}

	public void setLgx2(double lgx2) {
		this.lgx2 = lgx2;
	}

	public double getLgy2() {
		return lgy2;
	}

	public void setLgy2(double lgy2) {
		this.lgy2 = lgy2;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}

	public int getRound() {
		return round;
	}

	public void setRound(int round) {
		this.round = round;
	}

	public int getHead() {
		return head;
	}

	public void setHead(int head) {
		this.head = head;
	}

	public int getCluster_members() {
		return cluster_members;
	}

	public void setCluster_members(int cluster_members) {
		this.cluster_members = cluster_members;
	}

	public int getHead_count() {
		return head_count;
	}

	public void setHead_count(int head_count) {
		this.head_count = head_count;
	}

	public double Distancia(double x1, double y1, double x2, double y2){
		double distancia = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
		return distancia;
	}

}
