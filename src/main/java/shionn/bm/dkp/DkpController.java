package shionn.bm.dkp;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import shionn.bm.db.dao.DkpDao;
import shionn.bm.db.dao.PlayerDao;
import shionn.bm.db.dbo.User;

@Controller
public class DkpController {

	@Autowired
	private SqlSession session;
	@Autowired
	private User user;

	@RequestMapping(value = "/dkp", method = RequestMethod.GET)
	public ModelAndView list() {
		return new ModelAndView("dkp").addObject("players",
				session.getMapper(DkpDao.class).readAll());
	}

	@RequestMapping(value = "/dkp/add/{player}", method = RequestMethod.GET)
	public ModelAndView add(@PathVariable("player") int player) {
		return new ModelAndView("dkp-add")
				.addObject("player", session.getMapper(DkpDao.class).readOne(player))
				.addObject("type", "Ajouter");
	}

	@RequestMapping(value = "/dkp/rm/{player}", method = RequestMethod.GET)
	public ModelAndView rm(@PathVariable("player") int player) {
		return new ModelAndView("dkp-add")
				.addObject("player", session.getMapper(DkpDao.class).readOne(player))
				.addObject("type", "Retirer");
	}

	@RequestMapping(value = "/dkp/add/{player}", method = RequestMethod.POST)
	public String add(@PathVariable("player") int player, @RequestParam("value") int value,
			@RequestParam("reason") String reason, RedirectAttributes attr) {
		session.getMapper(DkpDao.class).addEntry(player, user.getId(), value, reason);
		session.commit();
		attr.addFlashAttribute("message", "Ajout de " + value + " dkp à "
				+ session.getMapper(PlayerDao.class).readOne(player).getName());
		return "redirect:/dkp";
	}

	@RequestMapping(value = "/dkp/rm/{player}", method = RequestMethod.POST)
	public String rm(@PathVariable("player") int player, @RequestParam("value") int value,
			@RequestParam("reason") String reason, RedirectAttributes attr) {
		session.getMapper(DkpDao.class).addEntry(player, user.getId(), -value, reason);
		session.commit();
		attr.addFlashAttribute("message", "Retrait de " + value + " dkp à "
				+ session.getMapper(PlayerDao.class).readOne(player).getName());
		return "redirect:/dkp";
	}

}
