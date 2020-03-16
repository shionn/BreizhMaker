package shionn.bm.raid;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.servlet.ModelAndView;

import shionn.bm.db.dao.RosterDao;
import shionn.bm.db.dbo.Player;
import shionn.bm.db.dbo.PlayerRank;

@Controller
@SessionScope
public class RosterController implements Serializable {
	private static final long serialVersionUID = 7512237297382825259L;
	@Autowired
	private SqlSession session;

	@RequestMapping(value = "/roster", method = RequestMethod.GET)
	public ModelAndView list() {
		RosterDao dao = session.getMapper(RosterDao.class);
		List<Player> roster1 = dao.list(Arrays.asList(PlayerRank.conquerant, PlayerRank.officier));
		List<Player> roster2 = dao
				.list(Arrays.asList(PlayerRank.membre, PlayerRank.gladiateur, PlayerRank.reroll));
		return new ModelAndView("roster").addObject("rosters", Arrays.asList(roster1, roster2));
	}

}
