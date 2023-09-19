package mytest.maven.service.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.inject.Inject;

import mytest.maven.dao.IProductDAO;
import mytest.maven.model.ProductModel;
import mytest.maven.service.IProductService;

public class ProductService implements IProductService {

	@Inject
	private IProductDAO productDAO;

	@Override
	public List<ProductModel> findAll() {
		return productDAO.findAll();
	}

	@Override
	public ProductModel findOne(Long id) {
		return productDAO.findOne(id);
	}

	@Override
	public ProductModel addProduct(ProductModel productModel) {
		productModel.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		Long id = productDAO.addProduct(productModel);
		return productDAO.findOne(id);
	}

	@Override
	public ProductModel updateProduct(ProductModel productModel) {
		productModel.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		productDAO.updateProduct(productModel);
		return productDAO.findOne(productModel.getId());
	}

	@Override
	public void deleteProduct(Long id) {
		productDAO.deleteProduct(id);
	}
}
