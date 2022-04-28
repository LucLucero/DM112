package br.inatel.dm112.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import br.inatel.dm112.client.deliveryClient;
import br.inatel.dm112.model.DeliveryResponse.DELIVERY_STATUS;
import br.inatel.dm112.model.DeliveryResponse;
import br.inatel.dm112.model.Order;
import br.inatel.dm112.model.RegisterResponse;
import br.inatel.dm112.model.RegisterResponse.REGISTER_STATUS;



@Service
public class DeliveryService {

	
	@Autowired
	private deliveryClient clientDelivery;

	/**
	 * Lógica de processo de entrega
	 * (1) consulta o pedido pelo número
	 * (2) atualiza o status do pedido
	 * (3) retorna sucesso
	 *
	 * @param orderNumber
	 * @param orderStatus
	 * @return
	 */
	
	public  DeliveryResponse processDelivery(int orderNumber, int orderStatus) {
		
		if (orderNumber < 0) {
			return DeliveryResponse.createErrorStatus(orderNumber, DELIVERY_STATUS.NULL_VALUES);
		}
		Order order;
		try {
			order = clientDelivery.retrieveOrder(orderNumber); //(1) consulta o pedido pelo número
		} catch(Exception e ) {
			System.out.println("Order " + orderNumber + " not found.");
			return DeliveryResponse.createErrorStatus(orderNumber, DELIVERY_STATUS.ORDER_NOT_FOUND);
		}
		
		if(order.getStatus() != Order.STATUS.ON_THE_WAY.ordinal()) { 
			System.out.println("Invalid order status: " + orderNumber + ": " + order.getStatus());
			return DeliveryResponse.createErrorStatus(orderNumber, DELIVERY_STATUS.WRONG_ORDER_STATUS);
		}

		order.setStatus(Order.STATUS.ON_THE_WAY.ordinal()); //entrega a caminho
		
		try {
			clientDelivery.registerDelivery(order); //(2) atualiza o status do pedido
		} catch(Exception e ) {
			System.out.println("Erro no serviço de pedido: update");
			return DeliveryResponse.createErrorStatus(orderNumber, DELIVERY_STATUS.ORDER_ERROR);
		}
		return new DeliveryResponse(DELIVERY_STATUS.OK.ordinal(), orderNumber); //(3) retorna sucesso
	}


	/**
	 * Lógica de confirmação de entrega
	 * (1) consulta o pedido pelo número
	 * (2) coleta dados de entrega
	 * (3) confirma o entrega
	 * (4) atualiza o status do pedido
	 * (5) responde Ok
	 * 
	 * @param orderNumber
	 * @param orderDate
	 * @param orderHour
	 * @return
	 */
	public RegisterResponse registerDelivery(int orderNumber, Date orderDate, Date orderHour, int orderStatus) {
		
		if (orderNumber < 0) {
			return RegisterResponse.createErrorStatus(orderNumber, orderDate, orderHour, REGISTER_STATUS.NULL_VALUES);
		}
		
		Order order = clientDelivery.retrieveOrder(orderNumber); //(1) consulta o pedido pelo número

		if(order == null) { 
			System.out.println("Erro no serviço de pedido: order is null.");
			return RegisterResponse.createErrorStatus(orderNumber, orderDate, orderHour, REGISTER_STATUS.ORDER_NOT_FOUND);
		}
		order.setOrderDate(new Date()); //(2) Coleta de dados
		order.setOrderHour(new Date());
		order.setStatus(Order.STATUS.DELIVERED.ordinal()); //(3) confirma a entrega
		try {
			clientDelivery.registerDelivery(order); //(4) atualiza o status do pedido
		} catch(Exception e ) {
			System.out.println("Erro no serviço de pedido: update");
			return RegisterResponse.createErrorStatus(orderNumber, orderDate, orderHour, REGISTER_STATUS.ORDER_ERROR);
		}
		System.out.println("Sucesso ao confirmar a entrega: orderNumber: " + orderNumber );
		return new RegisterResponse(orderNumber, orderDate, orderHour, REGISTER_STATUS.OK.ordinal()); //(4) responde Ok
	}

}