package shionn.bm.db.dbo;

import java.util.List;

public class Player {

	private int id;
	private String name;
	private PlayerClass clazz;
	private int dkp;
	private List<DkpEntry> historic;
	private PlayerRank rank;

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

	public List<DkpEntry> getHistoric() {
		return historic;
	}

	public void setHistoric(List<DkpEntry> historic) {
		this.historic = historic;
	}

	public PlayerRank getRank() {
		return rank;
	}

	public void setRank(PlayerRank rank) {
		this.rank = rank;
	}
}
