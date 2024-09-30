package com.ecom.demo.controller.admin;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.demo.dto.FAQDto;
import com.ecom.demo.dto.ProductDto;
import com.ecom.demo.services.admin.adminProduct.AdminProductService;
import com.ecom.demo.services.admin.faq.FAQService;

//import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/admin")
//@RequiredArgsConstructor
public class AdminProductController 
{
	private final AdminProductService adminProductService;
	
	private final FAQService faqService;
	
	public AdminProductController(AdminProductService adminProductService, FAQService faqService) 
	{
		super();
		this.adminProductService = adminProductService;
		this.faqService = faqService;
	}

	@PostMapping("/product")
	public ResponseEntity<ProductDto> appProduct(@ModelAttribute ProductDto productDto) throws IOException
	{
		ProductDto productDto1 = adminProductService.addProduct(productDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(productDto1);
	}
	
	@GetMapping("/products")
	public ResponseEntity<List<ProductDto>> getAllProducts()
	{
		List<ProductDto> productDtos = adminProductService.getAllProducts();
		return ResponseEntity.ok(productDtos);
	}
	
	@GetMapping("/search/{name}")
	public ResponseEntity<List<ProductDto>> getAllProductsByName(@PathVariable String name)
	{
		List<ProductDto> productDtos = adminProductService.getAllProductsByName(name);
		return ResponseEntity.ok(productDtos);
	}
	
	@DeleteMapping("/product/{productId}")
	public ResponseEntity<Void> deleteProduct(@PathVariable int productId)
	{
		boolean deleted = adminProductService.deleteProduct(productId);
		
		if(deleted)
		{
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping("/faq/{productid}")
	public ResponseEntity<FAQDto> postFAQ(@PathVariable int productId, @RequestBody FAQDto faqDto)
	{
		return ResponseEntity.status(HttpStatus.CREATED).body(faqService.postFAQ(productId, faqDto));
	}
	
	@GetMapping("/product/{productid}")
	public ResponseEntity<ProductDto> getProductById(@PathVariable int productId)
	{
		ProductDto productDto = adminProductService.getProductById(productId);
		
		if(productDto != null)
			return ResponseEntity.ok(productDto);
		else
			return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/product/{productid}")
	public ResponseEntity<ProductDto> updateProduct(@PathVariable int productId, @ModelAttribute ProductDto productDto) throws IOException
	{
		ProductDto updateProduct = adminProductService.updateProduct(productId, productDto); 
		
		if(updateProduct != null)
			return ResponseEntity.ok(updateProduct);
		else
			return ResponseEntity.notFound().build();
	}
	
	
}
