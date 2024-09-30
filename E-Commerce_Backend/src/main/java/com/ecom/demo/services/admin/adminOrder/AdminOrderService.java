package com.ecom.demo.services.admin.adminOrder;

import java.util.List;

import com.ecom.demo.dto.AnalyticsResponse;
import com.ecom.demo.dto.OrderDto;

public interface AdminOrderService 
{
	List<OrderDto> getAllPlacedOrders();
	
	OrderDto changeOrderStatus(int orderId, String status);
	
	AnalyticsResponse calculateAnalytics();
}
