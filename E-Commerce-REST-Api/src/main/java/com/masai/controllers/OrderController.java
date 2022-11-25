package com.masai.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.exceptions.CustomerException;
import com.masai.exceptions.InsufficientQuantity;
import com.masai.exceptions.OrderException;
import com.masai.exceptions.ProductNotFoundException;
import com.masai.models.CartDto;
import com.masai.models.CurrentCustomerSession;
import com.masai.models.Orders;
import com.masai.repositories.CustomerSessionDao;
import com.masai.services.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	private OrderService oService;
	
	@Autowired
	private CustomerSessionDao csDao;
	
	
	
	
	
	@GetMapping("/buyProduct/{customerKey}/{productId}/{quantity}")
	public ResponseEntity<Orders> buyProductByProductIdHandler(@PathVariable("customerKey") String key,@PathVariable("productId") Integer productId,@PathVariable("quantity") Integer quantity) throws CustomerException, ProductNotFoundException, InsufficientQuantity {
		
		CurrentCustomerSession ccs = csDao.findByUuid(key);
		
		if(ccs == null) {
			
			throw new CustomerException("No customer exist with this key");
		}else {
			
			Orders order = oService.buyProductByProductId(key, productId, quantity);
			
			return new ResponseEntity<Orders>(order,HttpStatus.OK);
		}
		
		
	}
	
	
	
	
	
	
	@GetMapping("/{customerKey}")
	public ResponseEntity<CartDto> visitYourCartHandler(@PathVariable("customerKey") String key) throws OrderException,CustomerException{
		
		CurrentCustomerSession css = csDao.findByUuid(key);
		
		if(css == null) {
			
			throw new CustomerException("No customer exist with this key");
		}else {
			
			CartDto cartdto = oService.visitYourCart(key);
			return new ResponseEntity<CartDto>(cartdto,HttpStatus.OK);
		}
		
		
	}
	
	@DeleteMapping("/{customerKey}/{amount}")
	public ResponseEntity<String> payYourAmount(@PathVariable("customerKey") String key,@PathVariable("amount") Double amount) throws OrderException,CustomerException{
		
		CurrentCustomerSession css = csDao.findByUuid(key);
		
		if(css == null) {
			
			throw new CustomerException("No customer exist with this Key");
		}else {
			
			String string = oService.payAmount(key, amount);
			
			return new ResponseEntity<String>(string,HttpStatus.OK);
		}
		
	}
	
	@DeleteMapping("/delete/{customerKey}/{orderId}")
	public ResponseEntity<Orders> deleteProductByOrderIdHandler(@PathVariable("customerKey") String key,@PathVariable("orderId") Integer orderId) throws CustomerException, OrderException {
		
		CurrentCustomerSession css = csDao.findByUuid(key);
		
		
		if(css == null) {
			
			throw new CustomerException("No customer exist with this key");
		}else {
			
			Orders order = oService.deleteProductByOrderId(key, orderId);
			return new ResponseEntity<Orders>(order,HttpStatus.OK);
		}
		
		
	}
	
	


	
	
	
	
	
	
	
	
}
