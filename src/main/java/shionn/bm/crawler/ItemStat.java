package shionn.bm.crawler;

public class ItemStat {

	private String value;
	private ItemStatType type;

	public ItemStat(ItemStatType type, String value) {
		this.type = type;
		this.value = value;
	}

	@Override
	public String toString() {
		return type + " " + value;
	}

	public ItemStatType getType() {
		return type;
	}

	public String getValue() {
		return value;
	}

}
