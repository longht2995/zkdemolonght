package greenglobal.demo.longht.entity;

import org.zkoss.zk.ui.select.annotation.WireVariable;

import greenglobal.demo.longht.services.ProductService;

public class ProductPaging {
	int pageSize = 10;
	int activePage = 0;
	@WireVariable
	ProductService productService;
	
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getActivePage() {
		return activePage;
	}
	public void setActivePage(int activePage) {
		this.activePage = activePage;
	}
	
}
