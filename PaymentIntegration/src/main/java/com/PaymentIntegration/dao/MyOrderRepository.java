package com.PaymentIntegration.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.PaymentIntegration.entities.MyOrder;

public interface MyOrderRepository extends JpaRepository<MyOrder, Long> {

	public MyOrder findByOrderId( String orderId);
}
