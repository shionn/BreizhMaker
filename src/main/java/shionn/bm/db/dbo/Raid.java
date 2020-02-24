package shionn.bm.db.dbo;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

public class Raid {
	private int id;
	private String name;
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	private Date start;
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	private Date end;
	private boolean running;
	private int boss;
	private List<RaidEntry> players;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public int getBoss() {
		return boss;
	}

	public void setBoss(int boss) {
		this.boss = boss;
	}

	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	public List<RaidEntry> getPlayers() {
		return players;
	}

	public void setPlayers(List<RaidEntry> players) {
		this.players = players;
	}
}
