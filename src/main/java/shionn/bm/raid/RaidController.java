package shionn.bm.raid;

import java.util.Date;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import shionn.bm.db.dao.RaidDao;
import shionn.bm.db.dbo.Raid;
import shionn.bm.db.dbo.RaidEntry;
import shionn.bm.db.dbo.User;

@Controller
public class RaidController {

	private static final int BOSS_DKP = 2;
	private static final int RAID_OFF = 10;
	private static final int IN_TIME = 5;
	private static final long MINUTE_15 = 15 * 60 * 1000;
	@Autowired
	private SqlSession session;
	@Autowired
	private User user;

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
		session.getMapper(RaidDao.class).create(name, start, end);
		session.commit();
		return "redirect:/raid";
	}

	@RequestMapping(value = "/raid/update", method = RequestMethod.POST)
	public String updateRaid(@ModelAttribute("raid") Raid raid, RedirectAttributes attr) {
		RaidDao dao = session.getMapper(RaidDao.class);
		if (!raid.isRunning()) {
			int dkp = computeDkp(raid);
			for (RaidEntry e : raid.getPlayers()) {
				dao.addDkpEntry(e.getPlayer().getId(), raid.getId(), user.getId(), raid.getName(),
						dkp);
			}
		}
		dao.update(raid);
		session.commit();
		return "redirect:/raid";
	}

	private int computeDkp(Raid raid) {
		int dkpTime = (int) ((raid.getEnd().getTime() - raid.getStart().getTime()) / MINUTE_15);
		return IN_TIME + RAID_OFF + dkpTime + raid.getBoss() * BOSS_DKP;
	}

	@RequestMapping(value = "/raid/edit/member/{id}", method = RequestMethod.GET)
	public ModelAndView editRaidMember(@PathVariable("id") int id) {
		RaidDao dao = session.getMapper(RaidDao.class);
		Raid raid = dao.read(id);
		raid.setPlayers(dao.readPlayers(id));
		return new ModelAndView("raid-member").addObject("raid", raid);
	}

	@RequestMapping(value = "/raid/edit/member/{id}", method = RequestMethod.POST)
	public String editRaidMember(@PathVariable("id") int id, @ModelAttribute("raid") Raid raid) {
		RaidDao dao = session.getMapper(RaidDao.class);
		dao.removeAllMember(id);
		for (RaidEntry e : raid.getPlayers()) {
			if (e.isMember()) {
				dao.addMember(id, e.getPlayer().getId());
			}
		}
		session.commit();
		return "redirect:/raid";
	}

}
