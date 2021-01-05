package com.devsuperior.dsdelivery.services;

import java.util.List;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsuperior.dsdelivery.dto.ProductDTO;
import com.devsuperior.dsdelivery.entities.Product;
import com.devsuperior.dsdelivery.repositories.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository repository;
	
//	Anotacao abaixo evita locks no banco de dados
	@org.springframework.transaction.annotation.Transactional(readOnly = true)
	public List<ProductDTO> findAll() {
		List<Product> list = repository.findAllByOrderByNameAsc();
		
//		Expressa lambda para converter a lista de Products para ProductsDTO(.collect serve para converter
//		novamente em lista
		return list.stream().map(x -> new ProductDTO(x)).
				collect(Collectors.toList());
	}

}
