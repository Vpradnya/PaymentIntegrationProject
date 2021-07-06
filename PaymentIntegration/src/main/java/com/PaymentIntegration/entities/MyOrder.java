package com.PaymentIntegration.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="orders")
public class MyOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long myOrderId;
	
	private String orderId;
	private int amount;
	private String receipt;
	private String status;
	private String paymentId;
	public long getMyOrderId() {
		return myOrderId;
	}
	public void setMyOrderId(long myOrderId) {
		this.myOrderId = myOrderId;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getReceipt() {
		return receipt;
	}
	public void setReceipt(String receipt) {
		this.receipt = receipt;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}
	
	
	
}
