package br.inatel.dm112.interfaces;

import java.util.Date;

import br.inatel.dm112.model.DeliveryResponse;
import br.inatel.dm112.model.RegisterResponse;

public interface Delivery {

	DeliveryResponse processDelivery(int orderNumber, int orderStatus);

	RegisterResponse registerDelivery(int orderNumber, Date orderDate, Date orderHour, int orderStatus);

}