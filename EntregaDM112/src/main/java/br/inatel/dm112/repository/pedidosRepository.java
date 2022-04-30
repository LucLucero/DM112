package br.inatel.dm112.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.inatel.dm112.model.DeliveryResponse;
import br.inatel.dm112.model.Order;


public interface pedidosRepository extends JpaRepository<Order, Long> {

	Order findById(long id);

	DeliveryResponse findByOrderNumber(int orderNumber);
		
}
