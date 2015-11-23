package edu.esprit.shop.presentation.mbeans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import edu.esprit.shop.persistence.Product;
import edu.esprit.shop.services.CatalogServiceLocal;

@ManagedBean( name = "productsMB" )
@ViewScoped
public class ProductsCrudBean {
	
	@EJB
	private CatalogServiceLocal catalogServiceLocal;
	
	
	private List<Product> products;
	private Product product;
	private Boolean formDisplayed;
	
	public ProductsCrudBean() {
	}
	
	@PostConstruct
	public void init(){
		product = new Product();
		products = catalogServiceLocal.findAllProducts();
		formDisplayed = false;
	}
	
	public void doSaveProduct(){
		catalogServiceLocal.saveProduct(product);
		products = catalogServiceLocal.findAllProducts();
		formDisplayed = false;
	}
	
	public void doNew(){
		formDisplayed = true;
		product = new Product();
	}
	
	public void doSelect(Product product){
		formDisplayed = true;
		this.product = product;
	}
	
	public void doDeleteProduct(){
		catalogServiceLocal.removeProduct(product);
		products = catalogServiceLocal.findAllProducts();
		formDisplayed = false;
	}
	
	public void doCancel(){
		formDisplayed = false;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Boolean getFormDisplayed() {
		return formDisplayed;
	}

	public void setFormDisplayed(Boolean formDisplayed) {
		this.formDisplayed = formDisplayed;
	}
	
	
	
	
	

}
