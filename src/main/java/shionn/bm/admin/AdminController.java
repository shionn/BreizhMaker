package shionn.bm.admin;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import shionn.bm.db.dao.DkpDao;
import shionn.bm.db.dao.PlayerDao;
import shionn.bm.db.dbo.Player;
import shionn.bm.db.dbo.PlayerClass;
import shionn.bm.db.dbo.PlayerRank;
import shionn.bm.db.dbo.User;
import shionn.bm.dkp.DkpOrder;

@Controller
public class AdminController {
	@Autowired
	private SqlSession session;
	@Autowired
	private User user;

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public ModelAndView admin() {
		return new ModelAndView("admin").addObject("playerclasses", PlayerClass.values())
				.addObject("playerranks", PlayerRank.values());
	}

	@RequestMapping(value = "/admin/create-player", method = RequestMethod.POST)
	public String getCreateUser(@RequestParam("pseudo") String pseudo,
			@RequestParam("class") PlayerClass clazz, @RequestParam("rank") PlayerRank rank,
			RedirectAttributes attr) {
		session.getMapper(PlayerDao.class).create(pseudo, clazz, rank);
		session.commit();
		attr.addFlashAttribute("message", "Personnage crée");
		return "redirect:/admin";
	}

	@RequestMapping(value = "/admin/depreciation", method = RequestMethod.POST)
	public String dkpDepreciation(@RequestParam("value") int value) {
		DkpDao dao = session.getMapper(DkpDao.class);
		for (Player player : dao.readAll(DkpOrder.name)) {
			int amount = player.getDkp() * value / 100;
			dao.addPercentEntry(player.getId(), user.getId(), -amount, "Dépréciation", value);
		}
		session.commit();
		return "redirect:/dkp";
	}

}
