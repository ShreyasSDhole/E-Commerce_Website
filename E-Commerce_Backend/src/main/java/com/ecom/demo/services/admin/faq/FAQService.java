package com.ecom.demo.services.admin.faq;

import com.ecom.demo.dto.FAQDto;

public interface FAQService 
{
	FAQDto postFAQ(int productId, FAQDto faqdto);
}
