package shionn.bm.crawler;

import java.util.List;

public class Item {

	private List<ItemStat> stats;
	private String id;
	private String name = "TODO";

	public void setStats(List<ItemStat> stats) {
		this.stats = stats;
	}

	public List<ItemStat> getStats() {
		return stats;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	@Override
	public String toString() {
		return stats.toString();
	}

	public String getName() {
		return name;
	}

}
