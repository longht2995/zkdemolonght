package greenglobal.demo.longht.services;

import java.util.List;

import greenglobal.demo.longht.entity.Product;

public interface ProductService {
	public List<Product> findAll();
	public void saveOrUpdate(Product product);
	public void delete(Product product);
	public List<Product> findLimit(int limit,int offset);
	public long getCount();
	public boolean checkName(String name);
	public List<Product> find(String key,int limit,int offset);
	public long getCountSearch(String key);
}
