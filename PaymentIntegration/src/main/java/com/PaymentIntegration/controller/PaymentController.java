package com.PaymentIntegration.controller;

import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.PaymentIntegration.services.OrderService;
import com.razorpay.*;

@Controller
@RequestMapping("/user")
public class PaymentController {

	@Autowired
	OrderService orderService;
	//Creating order for payment
	@PostMapping("/create_order")
	@ResponseBody
	public String createOrder(@RequestBody Map<String, Object> data) throws RazorpayException {
		System.out.println(data);
		int amt=Integer.parseInt(data.get("amount").toString());
		
		
		String order = orderService.createOrder(amt);
		System.out.println(order);
		return order.toString();
	}
	
	@PostMapping("/update_order")
	public ResponseEntity<?> updateOrder(@RequestBody Map<String,Object> data){
		//update payment status in database
		 orderService.updateOrder(data.get("order_id").toString(), data.get("payment_id").toString(), data.get("status").toString());
		return ResponseEntity.ok(Map.of("msg","updated"));
	}
}
