package shionn.bm.dkp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DkpController {

	@RequestMapping(value = "/dkp", method = RequestMethod.GET)
	public ModelAndView list() {
		return new ModelAndView("dkp");
	}

}
