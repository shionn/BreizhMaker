package shionn.bm.crawler;


import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.util.Arrays;

import org.junit.Ignore;
import org.junit.Test;

public class WowHeadClassicCrawlerTest {

	@Test
	public void testItemGantDevot() throws IOException {
		new WowHeadClassicCrawler().item("16692");
	}

	@Test
	public void testThunderfury() throws IOException {
		new WowHeadClassicCrawler().item("19019");
	}

	@Test
	public void testThuzadinSash() throws IOException {
		new WowHeadClassicCrawler().item("18740");
	}

	@Test
	@Ignore
	public void testRogueBis() throws IOException {
		for (String item : Arrays.asList("13404", "12587", "9479", "11925")) {
			new WowHeadClassicCrawler().item(item);
		}
	}

	@Test
	public void testCapOfTheScarletSavant() throws IOException {
		Item item = new WowHeadClassicCrawler().item("12752");
		assertStat(item, ItemStatType.spellcrit, "2");
	}

	private void assertStat(Item item, ItemStatType spellcrit, String value) {
		ItemStat itemStat = item.getStats().stream().filter(s-> s.getType() == spellcrit).findFirst().get();
		assertThat(itemStat.getValue()).isEqualTo(value);
	}
}
