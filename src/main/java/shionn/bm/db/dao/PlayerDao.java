package shionn.bm.db.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import shionn.bm.db.dbo.Player;
import shionn.bm.db.dbo.PlayerClass;

public interface PlayerDao {

	@Insert("INSERT INTO player(name,class) VALUES (#{name},#{class})")
	void create(@Param("name") String name, @Param("class") PlayerClass clazz);

	@Select("SELECT * FROM player WHERE id = #{id}")
	@Results({ @Result(column = "class", property = "clazz") })
	Player readOne(int id);
}
