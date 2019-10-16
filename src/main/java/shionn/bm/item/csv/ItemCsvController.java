package shionn.bm.item.csv;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.servlet.ModelAndView;

import shionn.bm.crawler.ItemStatType;
import shionn.bm.crawler.WowHeadClassicCrawler;

@Controller()
@RequestScope
public class ItemCsvController {

	@Autowired
	private WowHeadClassicCrawler crawler;

	@RequestMapping(value = "/item/csv", method = RequestMethod.GET)
	public ModelAndView home() {
		return new ModelAndView("item/csv");
	}

	@RequestMapping(value = "/item/csv", method = RequestMethod.POST)
	public ModelAndView view(@RequestParam("url") String url) throws IOException {
		Matcher matcher = Pattern.compile("item=(\\d+)/").matcher(url);
		DecoratedItem item = null;
		if (matcher.find()) {
			item = new DecoratedItem(crawler.item(matcher.group(1)));
		}
		return new ModelAndView("item/csv").addObject("item", item).addObject("statTypes", ItemStatType.allValues());
	}

}
