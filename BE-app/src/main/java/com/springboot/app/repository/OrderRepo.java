package com.springboot.app.repository;

//import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

//import com.springboot.app.model.OrderDetails;
import com.springboot.app.model.Orders;

@Repository
public interface OrderRepo extends JpaRepository<Orders, Integer> {
//
//	@Query("SELECT od.orderDetailsCollection FROM Orders od where od.id= :id")
//	public List<OrderDetails> findAllyIdOrder(Integer id);
//	public Orders findByIdOrder(Integer id);
//	public List<Orders> findAll();
}
