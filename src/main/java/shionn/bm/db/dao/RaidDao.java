package shionn.bm.db.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import shionn.bm.db.dbo.Raid;
import shionn.bm.db.dbo.RaidEntry;

public interface RaidDao {

	@Select("SELECT * FROM raid WHERE running IS true")
	@Results({ @Result(column = "id", property = "id"),
			@Result(column = "id", property = "players", many = @Many(select = "listRunningPlayer")) })
	public List<Raid> listRunnings();

	@Select("SELECT * " //
			+ "FROM dkp AS p " //
			+ "INNER JOIN `raid-entry`AS e ON e.player = p.id AND e.raid = #{raid} "
			+ "ORDER BY class, dkp DESC")
	@Results({ @Result(column = "id", property = "player.id"),
			@Result(column = "name", property = "player.name"),
			@Result(column = "rank", property = "player.rank"),
			@Result(column = "class", property = "player.clazz"),
			@Result(column = "dkp", property = "player.dkp"),
			@Result(column = "id", property = "player.id") })
	public List<RaidEntry> listRunningPlayer(@Param("raid") int raid);

	@Insert("INSERT INTO raid (name, start, end, running, boss) VALUES (#{name}, #{start}, #{end}, true, 0)")
	public void create(@Param("name") String name, @Param("start") Date start,
			@Param("end") Date end);

	@Update("UPDATE raid SET name = #{name}, " //
			+ "start = #{start}, end = #{end}, " //
			+ "running = #{running}, boss = #{boss} " //
			+ "WHERE id = #{id} ")
	public void update(Raid raid);

	@Select("SELECT * FROM raid WHERE id = #{id}")
	public Raid read(int id);

	@Select("SELECT p.id, p.name, p.class, p.rank, r.raid AS member " //
			+ "FROM       player      AS p " //
			+ "LEFT JOIN `raid-entry` AS r ON r.player = p.id AND r.raid = #{raid} " //
			+ "ORDER by name")
	@Results({ @Result(column = "id", property = "player.id"),
			@Result(column = "name", property = "player.name"),
			@Result(column = "class", property = "player.clazz"),
			@Result(column = "rank", property = "player.rank") })
	public List<RaidEntry> readPlayers(@Param("raid") int raid);

	@Delete("DELETE FROM `raid-entry` WHERE raid = #{raid}")
	public int removeAllMember(@Param("raid") int raid);

	@Insert("INSERT INTO `raid-entry` (raid, player) VALUES (#{raid}, #{player})")
	public int addMember(@Param("raid") int raid, @Param("player") int player);

	@Insert("INSERT INTO `dkp-entry` (player, raid, user, reason, `value-type`, value) "
			+ "VALUE (#{player}, #{raid}, #{author}, #{reason}, 'amount', #{dkp} )")
	public int addDkpEntry(@Param("player") int player, @Param("raid") int raid,
			@Param("author") int author, @Param("reason") String reason,
			@Param("dkp") int dkp);
}
