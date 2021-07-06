/**
 * 
 */
 
 const paymentStart=()=>{
	console.log("payment started");
	/* alert("hi") */
	var amount = $("#payment_amount").val();
	if(amount=="" || amount== null){
		/* alert("Amount cannot be null or blank!!"); */
		swal("Failed!", "Amount cannot be null or blank!!", "error");
		return;
	}
	//we will use ajax to send request to server to create order
	$.ajax({
		url:'/user/create_order',
		data:JSON.stringify({amount:amount, info:'order_request'}),
		contentType:'application/json',
		type:'POST',
		dataType:'json',
		success:function(response){
			//invoked when success
			console.log(response);
			if(response.status=='created'){
				// open payment form
				let options={
						key:'rzp_test_u6ST3cIeyQauIq',
						amount:response.amount,
						currency:'INR',
						name:'Customer Donation',
						description:'Donation',
						image:"",
						order_id:response.id,
						handler:function(response){
							console.log(response)
							console.log("Congrats.. Payment Successfull!!")
							 
							//function call to save payment detail on server
							updatePaymentOnServer(response.razorpay_payment_id, response.razorpay_order_id,'paid');
							 			
						},
						"prefill": {
					        "name": "",
					        "email": "",
					        "contact": ""
					    },
					    "notes": {
					        "address": "Donate Us"
					    },
					    "theme": {
					        "color": "#3399cc"
					    }
				};
				let rzp = new Razorpay(options);
				rzp.on("payment.failed", function (response){
					console.log(response.error.code);
			        console.log(response.error.description);
			        console.log(response.error.source);
			        console.log(response.error.step);
			        console.log(response.error.reason);
			        console.log(response.error.metadata.order_id);
			        console.log(response.error.metadata.payment_id);
			       /*  alert("Oops Payment Failed"); */
			        swal("Failed!", "Oops Payment Failed", "error");
			});
				rzp.open();
			}
		},
		error:function(error){
			console.log(error);
			/* alert("Something went wrong!!") */
			swal("Failed!", "Something went wrong", "error");
		}
	})
};

function updatePaymentOnServer(payment_id, order_id, status){
	$.ajax({
		url:"/user/update_order",
		data: JSON.stringify({
			payment_id : payment_id,
			order_id : order_id,
			status : status
		}),
		contentType : "application/json",
		type: "POST",
		dataType:"json",
		success:function(response){
			swal("Good job!", "Congrats Payment Successfull!!", "success");
		},
		error:function(error){
			swal("Failed!", "Payment is successfull, but we didn't get details on server", "error");
		},
	});
}