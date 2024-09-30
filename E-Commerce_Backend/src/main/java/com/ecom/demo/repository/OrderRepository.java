package com.ecom.demo.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecom.demo.entity.Order;
import com.ecom.demo.enums.OrderStatus;


@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>
{
	Order findByUserIdAndOrderStatus(int userId, OrderStatus orderStatus);
	
	List<Order> findAllByOrderStatusIn(List<OrderStatus> orderStatusList);
	
	List<Order> findByUserIdAndOrderStatusIn(int userId, List<OrderStatus> orderStatus);
	
	Optional<Order> findByTrackingId(UUID trackingId);
	
	List<Order> findByDateBetweenAndOrderStatus(Date startOfMonth, Date endOfMonth, OrderStatus status);
	
	int countByOrderStatus(OrderStatus status);
}
