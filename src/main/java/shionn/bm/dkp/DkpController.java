package shionn.bm.dkp;

import java.io.Serializable;
import java.util.Date;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import shionn.bm.db.dao.DkpDao;
import shionn.bm.db.dao.PlayerDao;
import shionn.bm.db.dbo.User;

@Controller
@SessionScope
public class DkpController implements Serializable {
	private static final long serialVersionUID = 4405888703078641064L;

	@Autowired
	private SqlSession session;
	@Autowired
	private User user;

	private DkpOrder order = DkpOrder.clazz;

	@RequestMapping(value = "/dkp", method = RequestMethod.GET)
	public ModelAndView list() {
		return new ModelAndView("dkp").addObject("players",
				session.getMapper(DkpDao.class).readAll(order));
	}

	@RequestMapping(value = "/dkp/{player}", method = RequestMethod.GET)
	public ModelAndView list(@PathVariable("player") int player) {
		return new ModelAndView("dkp-historic").addObject("player",
				session.getMapper(DkpDao.class).readHistoric(player));
	}

	@RequestMapping(value = "/dkp/add/{player}", method = RequestMethod.GET)
	public ModelAndView add(@PathVariable("player") int player) {
		return new ModelAndView("dkp-add")
				.addObject("player", session.getMapper(DkpDao.class).readOne(player))
				.addObject("date", new Date()).addObject("type", "Ajouter");
	}

	@RequestMapping(value = "/dkp/rm/{player}", method = RequestMethod.GET)
	public ModelAndView rm(@PathVariable("player") int player) {
		return new ModelAndView("dkp-add")
				.addObject("player", session.getMapper(DkpDao.class).readOne(player))
				.addObject("date", new Date()).addObject("type", "Retirer");
	}

	@RequestMapping(value = "/dkp/add/{player}", method = RequestMethod.POST)
	public String add(@PathVariable("player") int player, @RequestParam("value") int value,
			@RequestParam("reason") String reason,
			@RequestParam("date") @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm") Date date,
			RedirectAttributes attr) {
		session.getMapper(DkpDao.class).addEntry(player, user.getId(), value, reason, date);
		session.commit();
		attr.addFlashAttribute("message", "Ajout de " + value + " dkp à "
				+ session.getMapper(PlayerDao.class).readOne(player).getName());
		return "redirect:/dkp";
	}

	@RequestMapping(value = "/dkp/rm/{player}", method = RequestMethod.POST)
	public String rm(@PathVariable("player") int player, @RequestParam("value") int value,
			@RequestParam("reason") String reason,
			@RequestParam("date") @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm") Date date,
			RedirectAttributes attr) {
		session.getMapper(DkpDao.class).addEntry(player, user.getId(), -value, reason, date);
		session.commit();
		attr.addFlashAttribute("message", "Retrait de " + value + " dkp à "
				+ session.getMapper(PlayerDao.class).readOne(player).getName());
		return "redirect:/dkp";
	}

}
