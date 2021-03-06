package edu.esprit.shop.presentation.converters;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import edu.esprit.shop.persistence.Category;
import edu.esprit.shop.services.CatalogServiceLocal;

@ManagedBean
public class CategoryConverter implements Converter{
	
	@EJB
	private CatalogServiceLocal catalogServiceLocal;

	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		Category eqCategory = null;
		System.out.println(catalogServiceLocal);
		eqCategory = catalogServiceLocal.findCategoryByName(value);
		return eqCategory;
	}

	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		String eqString = null;
		if (value==null || value.equals("")) {
			eqString = "";
		}else{
			eqString = ((Category)value).getName();
		}
		return eqString;
	}

}
