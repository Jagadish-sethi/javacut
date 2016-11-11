package com.jagadish.testapi;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class CheckoutService {
	
	
	Order checkOut(Cart cart, Customer customer){
		
		Order order = new Order(cart.itemsWithQuantity,customer);
		//Confirm shipping addres
		order.shippingAddress=verifyShippingAddress(cart.getCustomerId);
		
		//Proceed for payment
		boolean status = makePayment(order.total);
		
		notifyCustomer(customer,order.status);		
		return order;
		
	}
	
	public void makePayment(double sum){
		
		int payment_mode = Enter payment mode;
		
		sum = applyOfferCode(code);
		
		
	}

}

class Order {
	Customer customer;
	Order id;
	Map<Item,Integer> itemsWithQuantity;
	double total;
	Address shippingAddress;
	
	OrderStatus status;
	
	PayMentDetails paymentDetails;
	
	void setStatus(OrderStatus newStatus){
		this.status = newStatus;
		notifyCustomer(customer,this.status);	
	}
}

enum OrderStatus{
	CREATED, PAYMENT_SUCCESS, PAYMENT_FAILED,PACKING, SHIPPING, SHIPPED, DELIVERED, CLOSED, CANCELLED 
}

interface Calculate{
	double calculate();
}

class Cart implements Calculate{
	
	Map<Item,Integer> itemsWithQuantity;
	Set<Offer> offers;
	double total;
	
	
	
	public void add(Item item){
		if(itemsWithQuantity.get(item)==null){
			itemsWithQuantity.put(item, 1);
		}else{
			itemsWithQuantity.put(item,itemsWithQuantity.get(item)+1);
		}
	}

	double getTotal(){
		calculate();
		return total;
	}

	@Override
	public double calculate() {
		double total =0;
		
		for (Entry<Item, Integer> item : itemsWithQuantity.entrySet()) {
			total+=item.getKey().calculate()*item.getValue();
		}
		
		for (Offer offer : offers) {
			total = offer.getOfferedValue(total);
		}
		this.total = total;
		return total;
	}
	
}



class Item implements Calculate{
	String name;
	double price;
	Set<Offer> offers;
	@Override
	public double calculate() {
		// TODO Auto-generated method stub
		for (Offer offer : offers) {
			price = offer.getOfferedValue(price);
		}
		return price;
	}
	
}

class Offer {
	String offerCode;
	Date validTill;
	double discountPercent;
	double discountCut;
	//List<Item> items;
	
	double getOfferedValue(double value){
		double finalValue=value;
		finalValue = finalValue - (discountPercent/100)*value;
		finalValue -=discountCut;
		return finalValue;
	}
	
}
