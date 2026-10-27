package com.shop;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
public class ProductController {
	
	@Autowired
	private ProductRepository prodrepo;
	
	// Show Product List
	@GetMapping("/product-list")
	public String viewProductList(Model model) {
		List<Products> products = prodrepo.findAll();
		model.addAttribute("prodList", products);
		
		// For adding new product
		model.addAttribute("product", new Products());
		
		return "productList";
	}
	
	// Search Result (Not have been implemented yet)
	@GetMapping("/search-result")
	public String viewSearchResult(Model model) {
		List<Products> products = prodrepo.findAll();
		model.addAttribute("searchedProducts", products);

		return "searchedProduct";
	}
		
	// Show Update Product Form
	@GetMapping("/edit-product/{id}")
	public String showProdUpdateForm(@PathVariable("id") int id, Model model) {
		Products products = prodrepo.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + id));
	    model.addAttribute("selectedProd", products);
	    
		return "updateProduct";
	}
	
	// Update Product
	@PostMapping("/edit-product/{id}")
	public String updateProd(@PathVariable("id") int id, Products products, 
	  BindingResult result, Model model) {		
	    if (result.hasErrors()) {
	    	products.setId(id);
	        return "updateProduct";
	    }
	        
	    prodrepo.save(products);
	    return "redirect:/product-list";
	}
	
	// Delete Product	
	@PostMapping("/delete-product/{id}")
	public String deleteProd(@PathVariable("id") int id, Model model) {
		Products products = prodrepo.findById(id)
	    		.orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + id));
		prodrepo.delete(products);
	    
	    return "redirect:/product-list";
	}
	
	// Upload Product
	@PostMapping("/product-list")
	public String uploadProduct(Products product) {
		prodrepo.save(product);
		
		return "productList";
	}
	
	// Show Product Information Page
	@GetMapping("/info/{id}")
	public String showProdInfo(@PathVariable("id") int id, Model model) {
		Products product = prodrepo.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + id));
	    model.addAttribute("selectedProd", product);
	    
		return "productInfo";
	}

}
