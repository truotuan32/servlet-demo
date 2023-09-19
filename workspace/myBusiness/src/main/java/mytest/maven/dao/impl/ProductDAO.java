package mytest.maven.dao.impl;

import java.util.List;

import mytest.maven.dao.IProductDAO;
import mytest.maven.mapper.ProductMapper;
import mytest.maven.model.ProductModel;

public class ProductDAO extends AbstractDAO<ProductModel> implements IProductDAO {

	@Override
	public List<ProductModel> findAll() {
		String sql = "Select * from product";
		return query(sql, new ProductMapper());
	}

	@Override
	public ProductModel findOne(Long id) {
		String sql = "Select * from product where id = ?";
		List<ProductModel> prods = query(sql, new ProductMapper(), id);
		return prods.isEmpty() ? null : prods.get(0);
	}

	@Override
	public Long addProduct(ProductModel productModel) {
		StringBuilder sql = new StringBuilder("Insert into product(name, thumbnail, shortdescription,");
		sql.append(" content, price, createddate)");
		sql.append(" Values(?, ?, ?, ?, ?, ?) ");
		return insert(sql.toString(), productModel.getName(), productModel.getThumbnail(),
				productModel.getShortDescription(), productModel.getContent(), 
				productModel.getPrice(), productModel.getCreatedDate());
	}

	@Override
	public void updateProduct(ProductModel productModel) {
		StringBuilder sql = new StringBuilder("Update product");
		sql.append(" Set name = ?,");
		sql.append(" thumbnail = ?,");
		sql.append(" shortdescription = ?,");
		sql.append(" content = ?,");
		sql.append(" price = ?,");
		sql.append(" createddate = ?");
		sql.append(" Where id = ?");
		update(sql.toString(), productModel.getName(), productModel.getThumbnail(), productModel.getShortDescription(),
				productModel.getContent(), productModel.getPrice(), productModel.getCreatedDate(), productModel.getId());
	}

	@Override
	public void deleteProduct(Long id) {
		StringBuilder sql = new StringBuilder("Delete from product");
		sql.append(" Where id = ?");
		update(sql.toString(), id);
	}
}
