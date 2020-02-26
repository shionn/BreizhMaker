package shionn.bm.raid;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import shionn.bm.db.dao.RaidDao;
import shionn.bm.db.dbo.Raid;
import shionn.bm.db.dbo.RaidEntry;
import shionn.bm.db.dbo.User;
import shionn.bm.dkp.DkpOrder;

@Controller
@SessionScope
public class RaidController implements Serializable {
	private static final long serialVersionUID = -6557111787828849090L;

	private static final int BOSS_DKP = 2;
	private static final int RAID_OFF = 10;
	private static final int IN_TIME = 5;
	private static final long MINUTE_15 = 15 * 60 * 1000;
	@Autowired
	private SqlSession session;
	@Autowired
	private User user;

	private DkpOrder order = DkpOrder.clazz;

	@RequestMapping(value = "/raid", method = RequestMethod.GET)
	public ModelAndView list() {
		RaidDao dao = session.getMapper(RaidDao.class);
		List<Raid> raids = dao.listRunnings();
		for (Raid raid : raids) {
			raid.setPlayers(dao.listRunningPlayer(raid.getId(), order));
		}
		return new ModelAndView("raid").addObject("runnings",
				raids);
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

	@RequestMapping(value = "/raid/sort/{order}", method = RequestMethod.GET)
	public String orderBy(@PathVariable("order") DkpOrder order) {
		this.order = order;
		return "redirect:/raid";
	}

}
