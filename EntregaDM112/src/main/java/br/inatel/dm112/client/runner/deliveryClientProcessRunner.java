package br.inatel.dm112.client.runner;

import java.util.Date;


import br.inatel.dm112.client.deliveryClient;
import br.inatel.dm112.model.Order;

public class deliveryClientProcessRunner {

	public static void main(String[] args) {
		deliveryClient client = new deliveryClient();
		client.setRestURL(ClientUtil.getDeliveryRestURL());

		String cpf = "111.111.111-11";
		
		Order order = new Order();
		order.setCpf(cpf);
		order.setOrderDate(new Date());
		order.setStatus(1);
		
		client.processDelivery(order);
	}
}
