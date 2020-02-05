package shionn.bm.db.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import shionn.bm.db.dbo.Player;

public interface DkpDao {

	@Select("SELECT * FROM dkp ")
	@Results({ @Result(column = "class", property = "clazz") })
	List<Player> readAll();

	@Select("SELECT * FROM dkp WHERE id = #{player}")
	@Results({ @Result(column = "class", property = "clazz") })
	Player readOne(int player);

	@Insert("INSERT INTO `dkp-entry` (player, user, value, reason) VALUES (#{player}, #{user}, #{value}, #{reason})")
	int addEntry(@Param("player") int player, @Param("user") int user, @Param("value") int value,
			@Param("reason") String reason);

}
