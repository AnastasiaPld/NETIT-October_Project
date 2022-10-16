package com.example.demoauth.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demoauth.models.Order;
import com.example.demoauth.repository.OrderRepository;
@Service
public class OrderService {

	@Autowired
	OrderRepository orderRepository;

	public Optional<Order> getById(Long id) {

		return orderRepository.findById(id);
	}

	public List<Order> getAllData() {
		List<Order> order = new ArrayList<Order>();
		orderRepository.findAll().forEach(or -> order.add(or));
		return order;
	}

	public void saveOrUpdate(Order order) {
		orderRepository.save(order);
	}

	public void delete(Long id) {
		orderRepository.deleteById(id);
	}

	public Order update(Order order, Long id) {
		return orderRepository.save(order);
	}
}
