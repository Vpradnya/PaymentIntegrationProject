package com.PaymentIntegration.services;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PaymentIntegration.dao.MyOrderRepository;
import com.PaymentIntegration.entities.MyOrder;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

@Service
public class OrderService {
	
	@Autowired
	MyOrderRepository myOrderRepo;

	public String createOrder(int amt) throws RazorpayException {
		
        RazorpayClient client=new RazorpayClient("rzp_test_u6ST3cIeyQauIq", "IXZLePrEP8DmGte4gdcgOd7H");
		
		JSONObject ob = new JSONObject();
		ob.put("amount", amt*100);
		ob.put("currency", "INR");
		ob.put("receipt", "txn_234561");
		
		Order order = client.Orders.create(ob);
		System.out.println(order.toString());
		//Save Order in database
		
		MyOrder myOrder = new MyOrder();
		myOrder.setAmount(order.get("amount"));
		myOrder.setOrderId(order.get("id"));
		myOrder.setPaymentId(order.get(null));
		myOrder.setStatus("created");
		myOrder.setReceipt(order.get("receipt"));
		this.myOrderRepo.save(myOrder);
		System.out.println("Inside createOrder");
		return order.toString();
	}
	
	public void updateOrder(String order_Id, String payment_id, String status) {
		MyOrder myOrderinfo = this.myOrderRepo.findByOrderId(order_Id);
		myOrderinfo.setPaymentId(payment_id);
		myOrderinfo.setStatus(status);
		this.myOrderRepo.save(myOrderinfo);
		System.out.println("Inside updateOrder");
		
	}
}
