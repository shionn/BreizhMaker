package shionn.bm.crawler;

import java.io.IOException;

import org.assertj.core.util.Arrays;
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
	public void testRogueBis() throws IOException {
		for (String item : Arrays.array("13404", "12587", "9479", "11925")) {
			new WowHeadClassicCrawler().item(item);
		}
	}

}
