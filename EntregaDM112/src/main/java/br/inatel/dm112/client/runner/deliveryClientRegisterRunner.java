package br.inatel.dm112.client.runner;


import br.inatel.dm112.client.deliveryClient;
import br.inatel.dm112.model.Order;

public class deliveryClientRegisterRunner {

	public static void main(String[] args) {
		deliveryClient client = new deliveryClient();
		client.setRestURL(ClientUtil.getDeliveryRestURL());

		int orderNumber = 1;

		Order orderToUpdate = client.retrieveOrder(orderNumber);
		if (orderToUpdate == null) {
			System.out.println("Order not found: " + 1);
			return;
		}

		// update the values for the order
		orderToUpdate.setStatus(0);

		client.registerDelivery(orderToUpdate);
	}
}
