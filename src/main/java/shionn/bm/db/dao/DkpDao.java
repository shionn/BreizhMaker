package shionn.bm.db.dao;

import java.util.List;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import shionn.bm.db.dbo.Player;

public interface DkpDao {

	@Select("SELECT * FROM dkp ")
	@Results({ @Result(column = "class", property = "clazz") })
	List<Player> readAll();

	@Select("SELECT * FROM dkp WHERE id = #{player}")
	Player readOne(int player);

}
