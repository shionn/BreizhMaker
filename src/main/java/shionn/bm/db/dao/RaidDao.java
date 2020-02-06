package shionn.bm.db.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import shionn.bm.db.dbo.Raid;

public interface RaidDao {

	@Select("SELECT * FROM raid WHERE running IS true")
	public List<Raid> listRunnings();
}
