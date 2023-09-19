package mytest.maven.controller.admin.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import mytest.maven.model.ProductModel;
import mytest.maven.service.IProductService;
import mytest.maven.utils.HttpUtil;

@WebServlet(urlPatterns = { "/api-product" })
public class ProductAPI extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Inject
	private IProductService productService;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");

		ObjectMapper mapper = new ObjectMapper();
		List<ProductModel> rs = new ArrayList<ProductModel>();

		rs = productService.findAll();
		String json = mapper.writeValueAsString(rs);
		resp.getWriter().write(json);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		//đọc tiếng việt dùng reader
		//BufferedReader reader = new BufferedReader(new InputStreamReader(req.getInputStream(), "UTF-8"));
		
		ObjectMapper mapper = new ObjectMapper();
		List<ProductModel> rs = new ArrayList<ProductModel>();
		
		//đọc tiếng việt không dùng reader mà dùng req
		ProductModel productModel = HttpUtil.readJson(req).toModel(ProductModel.class);
		productModel = productService.addProduct(productModel);
		mapper.writeValue(resp.getOutputStream(), productModel);
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		
		ObjectMapper mapper = new ObjectMapper();
		
		ProductModel productModel = HttpUtil.readJson(req).toModel(ProductModel.class);
		productModel = productService.updateProduct(productModel);
		mapper.writeValue(resp.getOutputStream(), productModel);
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		
		ProductModel productModel = HttpUtil.readJson(req).toModel(ProductModel.class);
		productService.deleteProduct(productModel.getId());
	}
}
