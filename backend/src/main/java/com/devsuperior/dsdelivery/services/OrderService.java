package com.devsuperior.dsdelivery.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsuperior.dsdelivery.dto.OrderDTO;
import com.devsuperior.dsdelivery.entities.Order;
import com.devsuperior.dsdelivery.repositories.OrderRepository;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository repository;
	
//	Anotacao abaixo evita locks no banco de dados
	@org.springframework.transaction.annotation.Transactional(readOnly = true)
	public List<OrderDTO> findAll() {
		List<Order> list = repository.findOrderWithProducts();
		
//		Expressa lambda para converter a lista de Products para ProductsDTO(.collect serve para converter
//		novamente em lista
		return list.stream().map(x -> new OrderDTO(x)).
				collect(Collectors.toList());
	}

}
