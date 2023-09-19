package mytest.maven.service;

import java.util.List;

import mytest.maven.model.ProductModel;

public interface IProductService {
	List<ProductModel> findAll();

	ProductModel findOne(Long id);

	ProductModel addProduct(ProductModel productModel);

	ProductModel updateProduct(ProductModel productModel);

	void deleteProduct(Long id);
}
