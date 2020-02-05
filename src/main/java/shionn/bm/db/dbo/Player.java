package shionn.bm.db.dbo;

public class Player {

	private int id;
	private String name;
	private PlayerClass clazz;
	private int dkp;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public PlayerClass getClazz() {
		return clazz;
	}

	public void setClazz(PlayerClass clazz) {
		this.clazz = clazz;
	}

	public int getDkp() {
		return dkp;
	}

	public void setDkp(int dkp) {
		this.dkp = dkp;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
