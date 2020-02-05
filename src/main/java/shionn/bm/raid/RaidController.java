package shionn.bm.raid;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RaidController {

	@RequestMapping(value = "/raid", method = RequestMethod.GET)
	public ModelAndView list() {
		return new ModelAndView("raid");
	}

}
