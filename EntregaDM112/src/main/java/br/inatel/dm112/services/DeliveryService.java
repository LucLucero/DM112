package br.inatel.dm112.services;

import java.util.Date;
import org.springframework.stereotype.Service;
import br.inatel.dm112.model.DeliveryResponse.DELIVERY_STATUS;
import br.inatel.dm112.model.DeliveryResponse;
import br.inatel.dm112.model.Order;
import br.inatel.dm112.model.RegisterResponse;
import br.inatel.dm112.model.RegisterResponse.REGISTER_STATUS;

@Service
public class DeliveryService {
	
	
	private Order clientDelivery;
	

	public  DeliveryResponse processDelivery(int orderNumber, int orderStatus) {
		
		if (orderNumber < 0) {
			return DeliveryResponse.createErrorStatus(orderNumber, DELIVERY_STATUS.NULL_VALUES);
		}
		
		try {
			clientDelivery.retrieveOrder(orderNumber); //(1) consulta o pedido pelo número
		} catch(Exception e ) {
			System.out.println("Order " + orderNumber + " not found.");
			return DeliveryResponse.createErrorStatus(orderNumber, DELIVERY_STATUS.ORDER_NOT_FOUND);
		}
		
		if(clientDelivery.getOrderStatus() != Order.STATUS.ON_THE_WAY.ordinal()) { 
			System.out.println("Invalid order status: " + orderNumber + ": " + clientDelivery.getOrderStatus());
			return DeliveryResponse.createErrorStatus(orderNumber, DELIVERY_STATUS.WRONG_ORDER_STATUS);
		}

		clientDelivery.setOrderStatus(Order.STATUS.ON_THE_WAY.ordinal()); //entrega a caminho
		
		try {
			clientDelivery.registerDelivery(clientDelivery); //(2) atualiza o status do pedido
		} catch(Exception e ) {
			System.out.println("Erro no serviço de pedido: update");
			return DeliveryResponse.createErrorStatus(orderNumber, DELIVERY_STATUS.ORDER_ERROR);
		}
		return new DeliveryResponse(DELIVERY_STATUS.OK.ordinal(), orderNumber); //(3) retorna sucesso
	}


	
	public RegisterResponse registerDelivery(int orderNumber, int orderStatus) {
		
		if (orderNumber < 0) {
			return RegisterResponse.createErrorStatus(orderNumber, REGISTER_STATUS.NULL_VALUES);
		}
		
		clientDelivery.retrieveOrder(orderNumber); //(1) consulta o pedido pelo número

		if(clientDelivery == null) { 
			System.out.println("Erro no serviço de pedido: order is null.");
			return RegisterResponse.createErrorStatus(orderNumber,REGISTER_STATUS.ORDER_NOT_FOUND);
		}
		clientDelivery.setOrderDate(new Date()); //(2) Coleta de dados
		clientDelivery.setOrderHour(new Date());
		clientDelivery.setOrderStatus(Order.STATUS.DELIVERED.ordinal()); //(3) confirma a entrega
		try {
			clientDelivery.registerDelivery(clientDelivery); //(4) atualiza o status do pedido
		} catch(Exception e ) {
			System.out.println("Erro no serviço de pedido: update");
			return RegisterResponse.createErrorStatus(orderNumber, REGISTER_STATUS.ORDER_ERROR);
		}
		System.out.println("Sucesso ao confirmar a entrega: orderNumber: " + orderNumber );
		return new RegisterResponse(orderNumber, REGISTER_STATUS.OK.ordinal()); //(4) responde Ok
	}



	public RegisterResponse registerDelivery(Order order) {
		clientDelivery.setOrderStatus(1);
		return null;
		
	}



	
}
