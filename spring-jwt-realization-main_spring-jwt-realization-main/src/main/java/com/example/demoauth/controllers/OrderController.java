package com.example.demoauth.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demoauth.models.Order;
import com.example.demoauth.pojo.MessageResponse;
import com.example.demoauth.service.OrderService;



@RestController
@RequestMapping("/api/employee/data")
@CrossOrigin(origins = "*", maxAge = 3600)
public class OrderController {

	@Autowired
	OrderService orderService;

	@GetMapping("/all/orders")
	public List<Order> getAllemployeesAssignment(Model model) {
		List<Order> all_orders = orderService.getAllData();
		model.addAttribute("listAssignment", all_orders);
		return all_orders;
	}

	@DeleteMapping(value = "/{id}/order")
	public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
		orderService.delete(id);
		return ResponseEntity.ok(new MessageResponse("Order is deleted"));

	}

	@GetMapping(value = "/{id}/order")
	// @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	public Optional<Order> getAssignmentById(@PathVariable(name = "id") Long id) {
		Optional<Order> order = orderService.getById(id);
		return order;
	}

	@PostMapping(value = "/edit/order")
	// @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	public Order addData(@RequestBody Order order) {
		orderService.saveOrUpdate(order);
		return order;
	}

	@ExceptionHandler(RuntimeException.class)
	@PatchMapping("/order/{id}")
	public ResponseEntity<?> updateAssignment(BindingResult result, @PathVariable(value = "id") Long id,
			 @RequestBody Order request, Model model) throws ResourceNotFoundException {
		Order order = orderService.getById(id).orElseThrow(() -> new RuntimeException("Assignment not found: " + id));

		boolean notExist = orderService.getById(id).get() != null;
		if (!notExist) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: assignment not exist!"));
		}

		if (request.getDate() != null) {
			order.setDate(request.getDate());
		}
			
		if (request.getOrderStatus() != null) {
			order.setOrderStatus(request.getOrderStatus());
		}

		if (request.getQuantity() != null) {
			order.setQuantity(request.getQuantity());
		}
		final Order updateOrder = orderService.update(order, id);

		return ResponseEntity.ok(new MessageResponse("Order is updated!" + updateOrder.toString()));

	}

}
