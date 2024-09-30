package com.ecom.demo.services.admin.faq;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ecom.demo.dto.FAQDto;
import com.ecom.demo.entity.FAQ;
import com.ecom.demo.entity.Product;
import com.ecom.demo.repository.FAQRepository;
import com.ecom.demo.repository.ProductRepository;

//import lombok.RequiredArgsConstructor;

@Service
//@RequiredArgsConstructor
public class FAQServiceImpl implements FAQService
{
	private final FAQRepository faqRepository;
	
	private final ProductRepository productRepository;

	public FAQServiceImpl(FAQRepository faqRepository, ProductRepository productRepository) 
	{
		super();
		this.faqRepository = faqRepository;
		this.productRepository = productRepository;
	}
	
	public FAQDto postFAQ(int productId, FAQDto faqdto)
	{
		Optional<Product> optionalProduct = productRepository.findById(productId);
		
		if(optionalProduct.isPresent())
		{
			FAQ faq = new FAQ();
			faq.setQuestion(faqdto.getQuestion());
			faq.setAnswer(faqdto.getAnswer());
			faq.setProduct(optionalProduct.get());
			
			return faqRepository.save(faq).getFAQDto();
		}
		return null;
	}
	
	
}
