package br.inatel.dm112.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


import br.inatel.dm112.model.DeliveryResponse;
import br.inatel.dm112.model.Order;
import br.inatel.dm112.model.RegisterResponse;
import br.inatel.dm112.repository.pedidosRepository;
import br.inatel.dm112.services.DeliveryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
@Api(value="API REST LOGISTICA")
@CrossOrigin(origins="*")
public class DeliveryRest {

	@Autowired
	DeliveryService service;
	@Autowired
	pedidosRepository pedidosRepo;
	
	@GetMapping("/pedidos") //busca todos pedidos
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value="retorna a lista de todos os pedidos")
	public List<Order> listaPedidos(){
		return pedidosRepo.findAll();
	}


	@GetMapping("/processDelivery/{orderNumber}/{orderStatus}")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value="Processa a entrega")
	public DeliveryResponse processDelivery(@PathVariable("orderNumber") int orderNumber,
			@PathVariable("orderStatus") int orderStatus){
		
		
		service.processDelivery(orderNumber, orderStatus);
		//service.registerDelivery(orderNumber, orderStatus);
		return pedidosRepo.findByOrderNumber(orderNumber);
	}

	@PutMapping("/registerDelivery/{orderNumber}")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value="Registra a entrega")
	public RegisterResponse registerDelivery(@RequestBody Order order){
		
		System.out.println("registerDelivery");
		return service.registerDelivery(order);

}
}