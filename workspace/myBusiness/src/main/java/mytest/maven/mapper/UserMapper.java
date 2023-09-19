package mytest.maven.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import mytest.maven.model.UserModel;

public class UserMapper implements RowMapper<UserModel> {
	@Override
	public UserModel mapRow(ResultSet rs) {
		try {
			UserModel um = new UserModel();
			um.setId(rs.getLong("id"));
			um.setUserName(rs.getString("username"));
			um.setPassWord(rs.getString("password"));
			um.setFullName(rs.getString("fullname"));
			um.setAddress(rs.getString("address"));
			um.setPhone(rs.getString("phone"));
			um.setCreatedDate(rs.getTimestamp("createddate"));
			return um;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
