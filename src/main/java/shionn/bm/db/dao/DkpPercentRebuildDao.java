package shionn.bm.db.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import shionn.bm.db.dbo.DkpEntry;

public interface DkpPercentRebuildDao {
	@Select("SELECT * FROM `dkp-entry` WHERE `value-type` = 'percent' ORDER BY `date` ASC")
	@Results({ @Result(column = "player", property = "player.id"),
			@Result(column = "value-percent", property = "valuePercent") })
	List<DkpEntry> readAllPercent();

	@Select("SELECT sum(value) FROM `dkp-entry` WHERE `date` < #{date} AND player = #{player}")
	int sumPlayerEntry(@Param("player") int player, @Param("date") Date date);

	@Update("UPDATE `dkp-entry` SET value = #{value} WHERE id = #{id}")
	int updateEntry(@Param("id") int id, @Param("value") int amount);

}
