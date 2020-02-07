package shionn.bm.db.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import shionn.bm.db.dbo.Raid;

public interface RaidDao {

	@Select("SELECT * FROM raid WHERE running IS true")
	public List<Raid> listRunnings();

	@Insert("INSERT INTO raid (name, start, end) VALUES (#{name}, #{start}, #{end})")
	public void create(@Param("name") String name, @Param("start") Date start,
			@Param("end") Date end);
}
