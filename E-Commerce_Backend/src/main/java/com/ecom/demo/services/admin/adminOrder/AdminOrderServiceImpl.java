package com.ecom.demo.services.admin.adminOrder;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ecom.demo.dto.AnalyticsResponse;
import com.ecom.demo.dto.OrderDto;
import com.ecom.demo.entity.Order;
import com.ecom.demo.enums.OrderStatus;
import com.ecom.demo.repository.OrderRepository;

//import lombok.RequiredArgsConstructor;

@Service
//@RequiredArgsConstructor
public class AdminOrderServiceImpl implements AdminOrderService
{
	private final OrderRepository orderRepository;

	public AdminOrderServiceImpl(OrderRepository orderRepository) 
	{
		super();
		this.orderRepository = orderRepository;
	}

	public List<OrderDto> getAllPlacedOrders()
	{
		List<Order> orderList = orderRepository.findAllByOrderStatusIn(List.of(OrderStatus.Placed, OrderStatus.Shipped,
									OrderStatus.Delivered));
		
		return orderList.stream().map(Order::getOrderDto).collect(Collectors.toList());
	}
	
	public OrderDto changeOrderStatus(int orderId, String status)
	{
		Optional<Order> optionalOrder = orderRepository.findById(orderId);
		
		if(optionalOrder.isPresent())
		{
			Order order = new Order();
			
			if(Objects.equals(status, "Shipped"))
			{
				order.setOrderStatus(OrderStatus.Shipped);
			}
			else if(Objects.equals(status, "Delivered"))
			{
				order.setOrderStatus(OrderStatus.Delivered);
			}
			return orderRepository.save(order).getOrderDto();
		}
		return null;
	}
	
	private int getTotalOrdersForMonth(int month, int year) 
	{
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month-1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		
		Date startOfMonth = calendar.getTime();
		
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		
		Date endOfMonth = calendar.getTime();
		
		List<Order> orders = orderRepository.findByDateBetweenAndOrderStatus(startOfMonth, endOfMonth, 
								OrderStatus.Delivered);
		
		return 0;
	}
	
	public double getTotalEarningsForMonth(int month, int year)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month-1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		
		Date startOfMonth = calendar.getTime();
		
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		
		Date endOfMonth = calendar.getTime();
		
		List<Order> orders = orderRepository.findByDateBetweenAndOrderStatus(startOfMonth, endOfMonth,
								OrderStatus.Delivered);
		
		double sum = 0.0;
		
		for(Order order:orders)
		{
			sum += order.getAmount();
		}
		return sum;
	}
	
	public AnalyticsResponse calculateAnalytics()
	{
		LocalDate currentDate = LocalDate.now();
		LocalDate previousMonthDate = currentDate.minusMonths(1);
		
		int currentMonthOrders = getTotalOrdersForMonth(currentDate.getMonthValue(), currentDate.getYear());
		int previousMonthOrders = getTotalOrdersForMonth(previousMonthDate.getMonthValue(), 
									previousMonthDate.getYear());
		
		double currentMonthEarnings = getTotalEarningsForMonth(currentDate.getMonthValue(), currentDate.getYear());
		double previousMonthEarnings = getTotalEarningsForMonth(previousMonthDate.getMonthValue(), 
										previousMonthDate.getYear());
		
		int placed = orderRepository.countByOrderStatus(OrderStatus.Placed);
		int shipped = orderRepository.countByOrderStatus(OrderStatus.Shipped);
		int delivered = orderRepository.countByOrderStatus(OrderStatus.Delivered);
		
		return new AnalyticsResponse(placed, shipped, delivered, previousMonthOrders, currentMonthOrders,
							previousMonthEarnings, currentMonthEarnings);
	}

}
