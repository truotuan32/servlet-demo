package mytest.maven.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import mytest.maven.model.ProductModel;

public class ProductMapper implements RowMapper<ProductModel>{
	@Override
	public ProductModel mapRow(ResultSet rs) {
		try {
			ProductModel pm = new ProductModel();
			pm.setId(rs.getLong("id"));
			pm.setName(rs.getString("name"));
			pm.setThumbnail(rs.getString("thumbnail"));
			pm.setShortDescription(rs.getString("shortdescription"));
			pm.setContent(rs.getString("content"));
			pm.setPrice(rs.getLong("price"));
			pm.setCreatedDate(rs.getTimestamp("createddate"));
			return pm;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
