package greenglobal.demo.longht.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import greenglobal.demo.longht.entity.Category;
import greenglobal.demo.longht.services.CategoryService;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService{
	@Autowired
	private CategoryDao categoryDao;

	public List<Category> findAll() {
		// TODO Auto-generated method stub
		return categoryDao.findAll();
	}
}
