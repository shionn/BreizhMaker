package shionn.bm.db.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import shionn.bm.db.dbo.PlayerClass;

public interface PlayerDao {

	@Insert("INSERT INTO player(name,class) VALUES (#{name},#{class})")
	void create(@Param("name") String name, @Param("class") PlayerClass clazz);
}
