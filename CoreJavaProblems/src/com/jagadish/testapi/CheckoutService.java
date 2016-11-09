package com.jagadish.testapi;

public class CheckoutService {
	
	
	Order checkOut(Cart cart){
		
		//Confirm shipping addres
		verifyShippingAddress(cart.getCustomerId);
		
		//Proceed for payment
		
		boolean status = makePayment(cart.getTotal())
		
		Order order = new Order(cart,status);		
				
		return order;
		
	}

}

class Cart {
	
	List<Items> items;
	long total
	
	
	long calculateTotal();
	
	add(Item)
	
}
