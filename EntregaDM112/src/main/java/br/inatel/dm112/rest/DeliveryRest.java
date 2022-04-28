package br.inatel.dm112.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import java.util.Date;
import br.inatel.dm112.interfaces.Delivery;
import br.inatel.dm112.model.DeliveryResponse;
import br.inatel.dm112.model.RegisterResponse;
import br.inatel.dm112.services.DeliveryService;

@RestController
@RequestMapping("/api")
public class DeliveryRest implements Delivery {

	@Autowired
	private DeliveryService service;

	@Override
	@GetMapping("/processDelivery/{orderNumber}/{orderStatus}")
	@ResponseStatus(HttpStatus.OK)
	public DeliveryResponse processDelivery(@PathVariable("orderNumber") int orderNumber,
			@PathVariable("orderStatus") int orderStatus){
		
		System.out.println("processDelivery");
		return service.processDelivery(orderNumber, orderStatus);
	}

	@Override
	@GetMapping("/registerDelivery/{orderNumber}/{orderData}/{orderHour}")
	@ResponseStatus(HttpStatus.OK)
	public RegisterResponse registerDelivery(
			@PathVariable("orderNumber") int orderNumber,
			@PathVariable("orderData") Date orderData,
			@PathVariable("orderHour") Date orderHour,
			@PathVariable("orderStatus") int orderStatus)
	{
		
		System.out.println("registerDelivery");
		return service.registerDelivery(orderNumber, orderData, orderHour, orderStatus);
	}

}