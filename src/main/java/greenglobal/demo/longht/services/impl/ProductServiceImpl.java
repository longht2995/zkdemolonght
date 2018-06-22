package greenglobal.demo.longht.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import greenglobal.demo.longht.entity.Product;
import greenglobal.demo.longht.services.ProductService;
@Service("productService")
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ProductServiceImpl implements ProductService{
	@Autowired
	private ProductDao productDao;
	public List<Product> findAll() {
		// TODO Auto-generated method stub
		return productDao.findAll();
	}

	public void saveOrUpdate(Product product) {
		// TODO Auto-generated method stub
		System.out.println("Serviceimpl:"+product.getDescription());
		productDao.saveOrUpdate(product);
		
	}

	public void delete(Product product) {
		// TODO Auto-generated method stub
		productDao.delete(product);
	}

	public List<Product> findLimit(int limit,int offset) {
		// TODO Auto-generated method stub
		return productDao.findLimit(limit,offset);
	}

	public long getCount() {
		// TODO Auto-generated method stub
		return productDao.getCount();
	}

	public boolean checkName(String name) {
		// TODO Auto-generated method stub
		return false;
	}

	public List<Product> find(String key,int limit,int offset) {
		// TODO Auto-generated method stub
		return productDao.findKey(key,limit, offset);
	}

	public long getCountSearch(String key) {
		// TODO Auto-generated method stub
		return productDao.getCountSearch(key);
	}
	
}
