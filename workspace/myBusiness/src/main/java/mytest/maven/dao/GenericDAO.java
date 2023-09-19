package mytest.maven.dao;

import java.util.List;

import mytest.maven.mapper.RowMapper;

public interface GenericDAO<T> {
	<T> List<T> query(String sql, RowMapper<T> rowMapper, Object... params);

	Long insert(String sql, Object... params);

	void update(String sql, Object... params);
}
