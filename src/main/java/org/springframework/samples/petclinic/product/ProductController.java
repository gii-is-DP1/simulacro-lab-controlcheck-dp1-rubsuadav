package org.springframework.samples.petclinic.product;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProductController {
	
	private ProductService productService;
	
	@Autowired
	public ProductController (ProductService productService) {
		this.productService = productService;
	}
	
	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
	
	//AÑADIR EL FORMULARIO DEL LISTADO DE TIPO DE PRODUCTOS
	
	@ModelAttribute("types")
	public List<ProductType> populatePetTypes() {
		return this.productService.getAllProductsTypes();
	}
	
	@GetMapping(value = "/product/create")
	public String processCreationForm(Map<String, Object> model) {
		Product product = new Product();
		model.put("product", product);
		return "products/createOrUpdateProductForm";
	}
	
	@PostMapping(value = "/product/create")
	public String processCreationForm(@Valid Product product, BindingResult result, ModelMap model) {
		if (result.hasErrors()) {
			model.put("product", product);
			return "products/createOrUpdateProductForm";
		}
		else 
			this.productService.save(product);
		
		return "welcome";
	}
    
}
