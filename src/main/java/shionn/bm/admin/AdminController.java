package shionn.bm.admin;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import shionn.bm.db.dao.PlayerDao;
import shionn.bm.db.dbo.PlayerClass;

@Controller
public class AdminController {
	@Autowired
	private SqlSession session;

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public ModelAndView admin() {
		return new ModelAndView("admin").addObject("playerclasses", PlayerClass.values());
	}

	@RequestMapping(value = "/admin/create-player", method = RequestMethod.POST)
	public String getCreateUser(@RequestParam("pseudo") String pseudo,
			@RequestParam("class") PlayerClass clazz, RedirectAttributes attr) {
		session.getMapper(PlayerDao.class).create(pseudo, clazz);
		session.commit();
		attr.addFlashAttribute("message", "Personnage crée");
		return "redirect:/admin";
	}

}
