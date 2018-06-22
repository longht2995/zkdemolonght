package greenglobal.demo.longht.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.EventListener;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.zkoss.bind.BindContext;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.image.AImage;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

import greenglobal.demo.longht.MyViewModel;
import greenglobal.demo.longht.services.ProductService;

@Entity
@Table(name="product")
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class Product{
	@Transient
	@WireVariable
	private ProductService productService;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column(name="name",nullable=false)
	private String name;
	@Column(name="price",nullable=false)
	private float price;
	@Column(name="description",nullable=false)
	private String description;
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateupdate;
	@ManyToOne
	private Category category;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	
	public Date getDate() {
		return date;
	}
	
	public Date getDateupdate() {
		return dateupdate;
	}
	public void setDateupdate(Date dateupdate) {
		this.dateupdate = dateupdate;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	@PreUpdate
	public void setDateupdate() {
		this.dateupdate = new Date();
	}
	@PrePersist
	public void setDate() {
		this.date = new Date();
	}
	public Product(Long id, String name, float price, String description, Category category) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.description = description;
		this.category = category;
	}
	
	public Product() {
		
	}
	@Transient
	private List<AImage> myImage = new ArrayList<AImage>();
	
	
	public List<AImage> getMyImage() {
		return myImage;
	}
	public void setMyImage(List<AImage> myImage) {
		this.myImage = myImage;
	}
	@Transient
	private int i = 0;
	@Command
	@NotifyChange("myImage")
	public void onImageUpload(@ContextParam(ContextType.BIND_CONTEXT) BindContext ctx,@BindingParam("index")int index) {
		UploadEvent upEvent = null;
		Object objUploadEvent = ctx.getTriggerEvent();
		if(objUploadEvent != null &&(objUploadEvent instanceof UploadEvent)) {
			upEvent = (UploadEvent) objUploadEvent;
		}
		if(upEvent !=null) {
			for(org.zkoss.util.media.Media media : upEvent.getMedias()) {
				int lengthofImage = media.getByteData().length;
				if(media instanceof org.zkoss.image.Image) {
					if(lengthofImage > 800*1024) {
						return;
					}else {
						if(index==-1) {
							if(i<5) {
								myImage.add((AImage) media);
								i++;
							}
						}else {
							myImage.set(index,(AImage) media);
						}
					}
				}
			}
		}
		
	}
	@Command
	@NotifyChange("myImage")
	public void dropImage(@BindingParam("vitri")int index) {
		myImage.remove(index);
		i--;
	}
	@Command
	public void save(@BindingParam("product")Product product,@BindingParam("wdn") Window wdn) {
		String message;
		if(product.getId()!=null) {
			message = "Save success";
		}else {
			message = "Add success";
		}
		BindUtils.postNotifyChange(null, null, MyViewModel.class, "sizeList");
		productService.saveOrUpdate(product);
		BindUtils.postGlobalCommand(null, null, "refesh", null);
		Clients.showNotification(message, "info", null, "top_center", 3000);
		wdn.detach();
	}

}
