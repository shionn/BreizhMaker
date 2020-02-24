package shionn.bm.db.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import shionn.bm.db.dbo.Player;
import shionn.bm.db.dbo.PlayerClass;
import shionn.bm.db.dbo.PlayerRank;

public interface PlayerDao {

	@Insert("INSERT INTO player(name,class, rank) VALUES (#{name},#{class},#{rank})")
	void create(@Param("name") String name, @Param("class") PlayerClass clazz,
			@Param("rank") PlayerRank rank);

	@Select("SELECT * FROM player WHERE id = #{id}")
	@Results({ @Result(column = "class", property = "clazz") })
	Player readOne(int id);
}
