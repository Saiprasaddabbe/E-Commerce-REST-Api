package com.masai.services;

import java.util.List;

import javax.naming.InsufficientResourcesException;

import com.masai.exceptions.CustomerException;
import com.masai.exceptions.InsufficientQuantity;
import com.masai.exceptions.OrderException;

import com.masai.exceptions.ProductNotFoundException;
import com.masai.models.CartDto;
import com.masai.models.Orders;

public interface OrderService {

	
	public Orders buyProductByProductId(String sessionId,Integer productId,Integer quantity) throws CustomerException,ProductNotFoundException,InsufficientQuantity;
	
	
	
	public CartDto visitYourCart(String customerKey) throws CustomerException,OrderException;
	
	public String payAmount(String customerKey,Double amount) throws CustomerException,OrderException;
	
	public Orders deleteProductByOrderId(String customerKey,Integer orderId) throws CustomerException,OrderException;
	
	
	
}
