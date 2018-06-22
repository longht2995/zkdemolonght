package greenglobal.demo.longht;

import java.util.Map;

import org.zkoss.bind.Property;
import org.zkoss.bind.ValidationContext;
import org.zkoss.bind.validator.AbstractValidator;

import greenglobal.demo.longht.entity.Category;

public class ProductValidator extends AbstractValidator{
	
	public void validate(ValidationContext ctx) {
		// TODO Auto-generated method stub
		Map<String,Property> beanProps = ctx.getProperties(ctx.getProperty().getBase());
		if(beanProps.get("name")!= null) {
			validateName(ctx,(String)beanProps.get("name").getValue());
		}
		if(beanProps.get("description")!=null) {
			validateDescription(ctx,(String)beanProps.get("description").getValue());
			
		}
		if(beanProps.get("price")!=null) {
			validatePrice(ctx,(Double)beanProps.get("price").getValue());
			
		}
		if(beanProps.get("category")!=null) {
			validateCategory(ctx,(Category)beanProps.get("category").getValue());
			
		}
		
		
	}
	private void validateName(ValidationContext ctx, String name) {
        if(name == null || name.length() <= 6 || name.equals("")) {
        	this.addInvalidMessage(ctx, "name", "Please enter name");
        	
        	
        }
    }
	private void validateDescription(ValidationContext ctx, String description) {
        if(description == null || description.length() <= 6 || description.equals("")) {
            this.addInvalidMessage(ctx, "description", "Please enter description");
        }
    }
	private void validatePrice(ValidationContext ctx, Double price) {
        if(price == null || price <1) {
            this.addInvalidMessage(ctx, "price", "Please enter price");
        }
    }
	private void validateCategory(ValidationContext ctx, Category category) {
        if(category == null || category.getId()==0 || category.getName() == null || category.getName().equals("")) {
            this.addInvalidMessage(ctx, "category", "Please enter category");
        }
    }
}
