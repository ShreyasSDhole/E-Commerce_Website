package com.ecom.demo.services.customer;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ecom.demo.dto.ProductDetailDto;
import com.ecom.demo.dto.ProductDto;
import com.ecom.demo.entity.FAQ;
import com.ecom.demo.entity.Product;
import com.ecom.demo.entity.Review;
import com.ecom.demo.repository.FAQRepository;
import com.ecom.demo.repository.ProductRepository;
import com.ecom.demo.repository.ReviewRepository;

//import lombok.RequiredArgsConstructor;

@Service
//@RequiredArgsConstructor
public class CustomerProductServiceImpl implements CustomerProductService
{
	
	private final ProductRepository productRepository;
	
	private final FAQRepository faqRepository;

	private final ReviewRepository reviewRepository;

	public CustomerProductServiceImpl(ProductRepository productRepository, FAQRepository faqRepository,
			ReviewRepository reviewRepository) {
		super();
		this.productRepository = productRepository;
		this.faqRepository = faqRepository;
		this.reviewRepository = reviewRepository;
	}

	public List<ProductDto> getAllProducts()
	{
		List<Product> products = productRepository.findAll();
		return products.stream().map(Product::getDto).collect(Collectors.toList());
	}

	@Override
	public List<ProductDto> searchProductByTitle(String name) {
		List<Product> products = productRepository.findAllByNameContaining(name);
		return products.stream().map(Product::getDto).collect(Collectors.toList());
	}

	public ProductDetailDto getProductDetailbyId(int productId)
	{
		Optional<Product> optionalProduct = productRepository.findById(productId);
		
		if(optionalProduct.isPresent())
		{
			List<FAQ> faqlist = faqRepository.findAllByProductId(productId);
			List<Review> reviewlist = reviewRepository.findAllByProductId(productId);
			
			ProductDetailDto productDetailDto = new ProductDetailDto();
			productDetailDto.setProductDto(optionalProduct.get().getDto());
			productDetailDto.setFaqDtoList(faqlist.stream().map(FAQ::getFAQDto).collect(Collectors.toList()));
			productDetailDto.setReviewDtoList(reviewlist.stream().map(Review::getDto).collect(Collectors.toList()));
			
			return productDetailDto;
		}
		return null;
	}
}






