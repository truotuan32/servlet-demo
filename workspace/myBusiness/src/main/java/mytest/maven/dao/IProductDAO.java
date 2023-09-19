package mytest.maven.dao;

import java.util.List;

import mytest.maven.model.ProductModel;

public interface IProductDAO extends GenericDAO<ProductModel> {
	List<ProductModel> findAll();

	ProductModel findOne(Long id);

	Long addProduct(ProductModel productModel);

	void updateProduct(ProductModel productModel);

	void deleteProduct(Long id);
}
