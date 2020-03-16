package shionn.bm.db.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import shionn.bm.db.dbo.Player;
import shionn.bm.db.dbo.PlayerRank;

public interface RosterDao {

	@Select("<script>SELECT * FROM player WHERE rank IN "
			+ "<foreach item='r' collection='ranks' open='(' close=')' separator=','>#{r}</foreach> "
			+ "ORDER BY class ASC, name ASC</script>")
	@Results({ @Result(property = "clazz", column = "class") })
	List<Player> list(@Param("ranks") List<PlayerRank> ranks);

}
