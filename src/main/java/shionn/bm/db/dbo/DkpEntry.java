package shionn.bm.db.dbo;

import java.util.Date;

public class DkpEntry {
	private String reason;
	private int value;
	private int valuePercent;
	private Date date;
	private String author;
	private Player player;
	private int id;

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Player getPlayer() {
		return player;
	}

	public void setValuePercent(int valuePercent) {
		this.valuePercent = valuePercent;
	}

	public int getValuePercent() {
		return valuePercent;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
