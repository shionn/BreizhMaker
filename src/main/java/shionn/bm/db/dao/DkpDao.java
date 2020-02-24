package shionn.bm.db.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import shionn.bm.db.dbo.DkpEntry;
import shionn.bm.db.dbo.Player;
import shionn.bm.dkp.DkpOrder;

public interface DkpDao {

	@Select("SELECT * FROM dkp ORDER BY ${order.sql}")
	@Results({ @Result(column = "class", property = "clazz") })
	List<Player> readAll(@Param("order") DkpOrder order);

	@Select("SELECT * FROM dkp WHERE id = #{player}")
	@Results({ @Result(column = "class", property = "clazz") })
	Player readOne(int player);

	@Select("SELECT * FROM dkp WHERE id = #{player}")
	@Results({ @Result(column = "class", property = "clazz"),
			@Result(column = "id", property = "historic", many = @Many(select = "readHistoricDetail")) })
	Player readHistoric(int player);

	@Select("SELECT d.reason, d.value, d.date, u.name AS author " //
			+ "FROM `dkp-entry` AS d " //
			+ "LEFT JOIN user AS u ON d.user = u.id " //
			+ "WHERE player = #{id} ORDER BY date DESC")
	List<DkpEntry> readHistoricDetail(int id);

	@Insert("INSERT INTO `dkp-entry` (player, user, value, reason, `value-type`, date) "
			+ "VALUES (#{player}, #{user}, #{value}, #{reason}, 'amount', #{date})")
	int addEntry(@Param("player") int player, @Param("user") int user, @Param("value") int value,
			@Param("reason") String reason, @Param("date") Date date);

	@Insert("INSERT INTO `dkp-entry` (player, user, value, reason, `value-type`, `value-percent`) "
			+ "VALUES (#{player}, #{author}, #{amount}, #{reason}, 'percent', #{percent})")
	int addPercentEntry(@Param("player") int player, @Param("author") int author,
			@Param("amount") int amount, @Param("reason") String reason,
			@Param("percent") int percent);

}
