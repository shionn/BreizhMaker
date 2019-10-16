package shionn.bm.item.csv;

import java.util.List;

import shionn.bm.crawler.Item;
import shionn.bm.crawler.ItemStat;
import shionn.bm.crawler.ItemStatType;

public class DecoratedItem {

	private Item item;

	public DecoratedItem(Item item) {
		this.item = item;
	}

	public String getAsTabs() {
		return item.getId() + ';' + item.getName() + ItemStatType.allValues().stream()
				.map(this::getStatValue).reduce("", (a, b) -> a + ';' + b);
	}

	public String getStatValue(ItemStatType type) {
		return item.getStats().stream().filter(s -> s.getType() == type).map(ItemStat::getValue).findFirst().orElse("");
	}

	public List<ItemStat> getStats() {
		return item.getStats();
	}

	public String getId() {
		return item.getId();
	}

	public String getName() {
		return item.getName();
	}
}
