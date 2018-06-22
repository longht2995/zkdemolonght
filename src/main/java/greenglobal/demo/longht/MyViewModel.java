package greenglobal.demo.longht;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

import greenglobal.demo.longht.entity.Category;
import greenglobal.demo.longht.entity.Product;
import greenglobal.demo.longht.services.CategoryService;
import greenglobal.demo.longht.services.ProductService;



@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class MyViewModel {
	@WireVariable
	private ProductService productService;
	@WireVariable
	private CategoryService categoryService;
	private List<Product> listProduct;
	private List<Category> listCategory;
	private long sizeList;
	private long sizeListSeacrh;
	private int activePage = 0;
	private int activePageSearch = 0;
	private int pageSize =5;
	private Product product= new Product();
	private String key;
	private List<Product> listSearch;
	@Init
	public void init() {
		listProduct = new ArrayList<Product>();
		listCategory = new ArrayList<Category>();
		listSearch = new ArrayList<Product>();
		getListProduct();
		getListCategory();
		sizeList = productService.getCount();
	}

	public List<Product> getListProduct() {
		listProduct = new ArrayList<Product>();
		listProduct = productService.findLimit(activePage*pageSize,pageSize);
		
		return listProduct;
	}

	@Command
	public void showWindow(@BindingParam("product")Product product) {
		Map<String, Object> map = new HashMap<String,Object>();
		if(product.getId()!=null) {
			map.put("product", product);
		}else {
			map.put("product", new Product());
		}
		map.put("category", listCategory);
		Window window = (Window) Executions.createComponents("saveOrUpdate.zul", null, map);
		window.doModal();
	}

	public void setListProduct(List<Product> listProduct) {
		this.listProduct = listProduct;
	}
	public List<Category> getListCategory() {
		listCategory = categoryService.findAll();
		
		return listCategory;
	}
	public void setListCategory(List<Category> listCategory) {
		this.listCategory = listCategory;
	}
	@NotifyChange("sizeList")
	public long getSizeList() {
		sizeList = productService.getCount();
		System.out.println("SIZE GET:"+sizeList);
		return sizeList;
	}
	@NotifyChange("sizeList")
	public void setSizeList(long sizeList) {
		sizeList = productService.getCount();
		System.out.println("SIZE SET:"+sizeList);
		this.sizeList = sizeList;
	}
	
	public long getSizeListSeacrh() {
		return sizeListSeacrh;
	}

	public void setSizeListSeacrh(long sizeListSeacrh) {
		this.sizeListSeacrh = sizeListSeacrh;
	}

	public int getActivePage() {
		return activePage;
	}
	public void setActivePage(int activePage) {
		BindUtils.postGlobalCommand(null, null, "refesh", null);
		this.activePage = activePage;
	}
	public int getPageSize() {
		return pageSize;
	}
	@NotifyChange("*")
	public void setPageSize(int pageSize) {
		activePage = 0;
		this.pageSize = pageSize;
		BindUtils.postGlobalCommand(null, null, "refesh", null);
	}
	
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}	
	


	public List<Product> getListSearch() {
		sizeListSeacrh = productService.getCountSearch(key);
		
		return listSearch;
	}
	
	public void setListSearch(List<Product> listSearch) {
		this.listSearch = listSearch;
	}
	
	public int getActivePageSearch() {
		return activePageSearch;
	}
	@NotifyChange("*")
	public void setActivePageSearch(int activePageSearch) {
		this.activePageSearch = activePageSearch;
		BindUtils.postGlobalCommand(null, null, "refeshSearch", null);
	}

	@Command
	@NotifyChange({"listSearch","sizeListSeacrh","key"})
	public void searchKey(@BindingParam("key")String key) {
		this.key = key;
		if(key==null || key.equals("") || key=="") {
			listSearch = null;
			sizeListSeacrh = 0;
			activePageSearch = 0;
			return;
		}
		sizeListSeacrh = productService.getCountSearch(key);
		
		listSearch = productService.find(key,activePageSearch*pageSize,pageSize);
		
	}
	@Command
	@NotifyChange("sizeList")
	public void deleteProduct(@BindingParam("product") final Product product) {
		 Messagebox.show("Are you sure to execute Load?", "Execute?", Messagebox.YES | Messagebox.NO, 
			        Messagebox.QUESTION, new org.zkoss.zk.ui.event.EventListener<Event>() {
			            public void onEvent(final Event evt) throws InterruptedException {
			                if (Messagebox.ON_YES.equals(evt.getName())) {
			                	productService.delete(product);
			                	Clients.showNotification("Delete success", "info", null, "top_center", 3000);
			                	BindUtils.postNotifyChange(null, null, this, "setSizeList");
			                	BindUtils.postGlobalCommand(null, null, "refesh", null);
			                	BindUtils.postGlobalCommand(null, null, "refeshSize", null);
			                } else {
			                    // Code if no clicked
			                }
			            }
			        }
			    );
	}
	@GlobalCommand
	@NotifyChange("sizeList")
	public void refeshSize() {
		sizeList = productService.getCount();
		System.out.println("ReFEsh SIZE:"+ sizeList);
	}
	@GlobalCommand
	@NotifyChange("listSearch")
	public void refeshSearch() {
		if(key==null || key.equals("") || key=="") {
			listSearch = null;
			sizeListSeacrh = 0;
			activePageSearch = 0;
			return;
		}
		
		listSearch = productService.find(key,activePageSearch*pageSize,pageSize);
	}
	@GlobalCommand
	@NotifyChange("listProduct")
	public void refesh() {
		getListProduct();
	}
	

}
