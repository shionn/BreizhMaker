package shionn.bm.crawler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class WowHeadClassicCrawler {

	public Item item(String id) throws IOException {
		Document document = Jsoup.connect("https://classic.wowhead.com/item=" + id).get();
		Element root = document.select("h1.heading-size-1 + noscript").first();
		List<ItemStat> stats = new ArrayList<>();
		stats.add(
				new ItemStat(ItemStatType.slot, root.select("table").first().select("table td").first().text()));
		for (Element e : root.select("table td span")) {
			stats.add(parseSpan(e));
		}
		for (Element e : root.select("table").first().select("th")) {
			stats.add(parseSpan(e));
		}
		Item item = new Item();
		item.setStats(stats);
		return item;
	}

	private ItemStat parseSpan(Element e) {
		if (e.html().contains("<!--scstart4:2-->")) {
			return new ItemStat(ItemStatType.type, e.text());
		}
		if (e.html().contains("<!--ilvl-->")) {
			return new ItemStat(ItemStatType.ilvl, e.text().replaceAll("Item Level ", ""));
		}
		if (e.html().contains("<!--amr-->")) {
			return new ItemStat(ItemStatType.armor, e.text().replaceAll(" Armor", ""));
		}
		if (e.html().contains("<!--dmg-->")) {
			return new ItemStat(ItemStatType.damage, e.text().replaceAll(" Damage", ""));
		}
		if (e.html().contains("<!--spd-->")) {
			return new ItemStat(ItemStatType.speed, e.text().replaceAll("Speed ", ""));
		}
		if (e.html().contains("<!--stat3-->")) {
			return new ItemStat(ItemStatType.agility, e.text().replaceAll(" Agility", ""));
		}
		if (e.html().contains("<!--stat4-->")) {
			return new ItemStat(ItemStatType.strenght, e.text().replaceAll(" Strength", ""));
		}
		if (e.html().contains("<!--stat5-->")) {
			return new ItemStat(ItemStatType.intellect, e.text().replaceAll(" Intellect", ""));
		}
		if (e.html().contains("<!--stat6-->")) {
			return new ItemStat(ItemStatType.spirit, e.text().replaceAll(" Spirit", ""));
		}
		if (e.html().contains("<!--stat7-->")) {
			return new ItemStat(ItemStatType.stamina, e.text().replaceAll(" Stamina", ""));
		}
		if (e.text().contains("Equip: Increases damage and healing done by magical spells and effects by up to")) {
			return new ItemStat(ItemStatType.spellpower, extract(e.text(), " (\\d+)\\.", 1));
		}
		if (e.text().matches("Equip: \\+\\d+ Attack Power\\.")) {
			return new ItemStat(ItemStatType.attackpower, extract(e.text(), " (\\+\\d+) ", 1));
		}
		if (e.text().matches("Equip: Improves your chance to get a critical strike by \\d+%\\.")) {
			return new ItemStat(ItemStatType.attackcrit, extract(e.text(), " (\\d+)%\\.", 1));
		}
		if (e.text().matches("Equip: Improves your chance to hit by \\d+%\\.")) {
			return new ItemStat(ItemStatType.attackhit, extract(e.text(), " (\\d+)%\\.", 1));
		}
		return new ItemStat(ItemStatType.unknown, e.html());
	}

	private String extract(String text, String regex, int group) {
		Matcher m = Pattern.compile(regex).matcher(text);
		if (m.find())
			return m.group(group);
		return "Not found in : " + text;
	}

}
