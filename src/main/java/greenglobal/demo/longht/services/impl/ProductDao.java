package greenglobal.demo.longht.services.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import greenglobal.demo.longht.entity.Product;

@Repository
public class ProductDao {
	@PersistenceContext
	private EntityManager em;
	
	public List<Product> findAll(){
		return em.createQuery("FROM Product",Product.class).getResultList();
	}
	@Transactional
	public void saveOrUpdate(Product product) {
		Product s = new Product();
		s.setCategory(product.getCategory());
		s.setDescription(product.getDescription());
		s.setId(product.getId());
		s.setName(product.getName());
		s.setPrice(product.getPrice());
		s.setDate(product.getDate());
		if(s.getId()!=null) {
			em.merge(s);
		}else {
			em.persist(s);
		}
	}
	@Transactional
	public void delete(Product product) {
		em.createQuery("DELETE FROM Product p WHERE p.id ="+product.getId()).executeUpdate();
		
		
	}
	public List<Product> findLimit(int limit,int offset){
		TypedQuery<Product> query = em.createQuery("SELECT p FROM Product p ORDER BY p.id DESC",Product.class);
		query.setFirstResult(limit);
		query.setMaxResults(offset);
		return query.getResultList();
		
	}
	public long getCount() {
		TypedQuery<Long> query = em.createQuery("SELECT COUNT(c) FROM Product c", Long.class);
		long count = query.getSingleResult();
		return count;
	}
	public boolean findName(String name) {
		return false;
	}
	public List<Product> findKey(String key,int limit,int offset){
		TypedQuery<Product> query = em.createQuery("SELECT c FROM Product c WHERE c.name LIKE '%"+key+"%'",Product.class);
		query.setFirstResult(limit);
		query.setMaxResults(offset);
		return query.getResultList();
	}
	public long getCountSearch(String key) {
		TypedQuery<Long> query = em.createQuery("SELECT COUNT(c) FROM Product c WHERE c.name LIKE '%"+key+"%'", Long.class);
		long count = query.getSingleResult();
		System.out.println(count);
		return count;
	}
}
