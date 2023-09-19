package mytest.maven.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import mytest.maven.dao.GenericDAO;
import mytest.maven.mapper.RowMapper;

public class AbstractDAO<T> implements GenericDAO<T> {

	public Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/mybusiness";
			String user = "root";
			String password = "12345678";
			return DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public void setParam(PreparedStatement pstmt, Object... params) {
		try {
			for (int i = 0; i < params.length; i++) {
				Object param = params[i];
				int index = i + 1;
				if (param instanceof Long) {
					pstmt.setLong(index, (Long) param);
				} else if (param instanceof String) {
					pstmt.setString(index, (String) param);
				} else if (param instanceof Integer) {
					pstmt.setInt(index, (Integer) param);
				} else if (param instanceof Double) {
					pstmt.setDouble(index, (Double) param);
				} else if (param instanceof Timestamp) {
					pstmt.setTimestamp(index, (Timestamp) param);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	@Override
	public <T> List<T> query(String sql, RowMapper<T> rowMapper, Object... params) {
		List<T> results = new ArrayList<T>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			setParam(pstmt, params);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				results.add(rowMapper.mapRow(rs));
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return results;
	}

	@Override
	public Long insert(String sql, Object... params) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Long id = null;
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			setParam(pstmt, params);
			pstmt.executeUpdate();
			rs = pstmt.getGeneratedKeys();
			while (rs.next()) {
				id = rs.getLong(1);
			}

			conn.commit();
			conn.setAutoCommit(true);
			rs.close();
			pstmt.close();
			conn.close();
			return id;
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public void update(String sql, Object... params) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql);
			setParam(pstmt, params);
			pstmt.executeUpdate();

			conn.commit();
			conn.setAutoCommit(true);
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
	}

}
