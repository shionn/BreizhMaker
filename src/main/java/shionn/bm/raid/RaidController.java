package shionn.bm.raid;

import java.util.Date;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import shionn.bm.db.dao.RaidDao;

@Controller
public class RaidController {

	@Autowired
	private SqlSession session;

	@RequestMapping(value = "/raid", method = RequestMethod.GET)
	public ModelAndView list() {
		return new ModelAndView("raid").addObject("runnings",
				session.getMapper(RaidDao.class).listRunnings());
	}

	@RequestMapping(value = "/raid/add", method = RequestMethod.POST)
	public String createRaid(@RequestParam("name") String name,
			@RequestParam("start") @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm") Date start,
			@RequestParam("end") @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm") Date end,
			RedirectAttributes attr) {
		return "redirect:/raid";
	}

}
