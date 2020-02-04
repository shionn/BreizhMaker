package shionn.bm.db.dao;

import org.apache.ibatis.annotations.Select;

import shionn.bm.db.dbo.User;

public interface AuthDao {

	@Select("SELECT * FROM user WHERE email = #{email}")
	User read(String email);

}
