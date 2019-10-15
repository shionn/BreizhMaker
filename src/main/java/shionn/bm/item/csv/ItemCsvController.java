package shionn.bm.item.csv;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.servlet.ModelAndView;

@Controller()
@RequestScope
public class ItemCsvController {

	@RequestMapping(value = "/item/csv", method = RequestMethod.GET)
	public ModelAndView home() {
		return new ModelAndView("home");
	}

}
