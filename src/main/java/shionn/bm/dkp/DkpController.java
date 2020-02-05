package shionn.bm.dkp;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import shionn.bm.db.dao.DkpDao;

@Controller
public class DkpController {

	@Autowired
	private SqlSession session;

	@RequestMapping(value = "/dkp", method = RequestMethod.GET)
	public ModelAndView list() {
		return new ModelAndView("dkp").addObject("players",
				session.getMapper(DkpDao.class).readAll());
	}

	@RequestMapping(value = "/dkp/add/{player}", method = RequestMethod.GET)
	public ModelAndView add(@PathVariable("player") int player) {
		return new ModelAndView("dkp-add")
				.addObject("player", session.getMapper(DkpDao.class).readOne(player));
	}

	@RequestMapping(value = "/dkp/rm/{player}", method = RequestMethod.GET)
	public ModelAndView rm(@PathVariable("player") int player) {
		return new ModelAndView("dkp-add")
				.addObject("player", session.getMapper(DkpDao.class).readOne(player));
	}

}
