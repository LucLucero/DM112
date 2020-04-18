package br.inatel.dm112.client;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import br.inatel.dm112.model.Order;
import br.inatel.dm112.model.OrderResponse;
import reactor.core.publisher.Mono;

public class OrderRestClient {

	// local
	private String restURL = "http://localhost:8080/PedidoDM112/api/";
	//GAE:
	//private String restURL = "http://exemplodm112.appspot.com/rest/";

	/**
	 * createOrder
	 * @param order
	 * @return
	 */
	public OrderResponse createOrder(Order order) {

		OrderResponse orderResponse = WebClient.create(restURL + "order")
		        .post()
		        .contentType(MediaType.APPLICATION_JSON)
		        .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
		        .body(Mono.just(order), OrderResponse.class)
		        .accept(MediaType.APPLICATION_JSON)
		        .retrieve()
		        .bodyToFlux(OrderResponse.class)
		        .log().blockFirst();

		System.out.println("Resultado do createOrder: " + orderResponse);

		return orderResponse;
	}
	

	/**
	 * getItems
	 * @param cpf
	 * @return
	 */
	public List<Order> getItems(String cpf) {
		return WebClient.create(restURL + "orders/" + cpf)
		        .get()
		        .retrieve()
		        .bodyToFlux(Order.class)
		        .collectList()
		        .log()
		        .block();
	}
	
	/**
	 * retrieveOrder
	 * @param orderNumber
	 * @return
	 */
	public Order retrieveOrder(int orderNumber) {
		return WebClient.create(restURL + "order/" + orderNumber)
		        .get()
		        .retrieve()
		        .bodyToFlux(Order.class)
		        .log()
		        .blockFirst();
	}
	
	/**
	 * updateOrder
	 * @param order
	 * @return
	 */
	public OrderResponse updateOrder(Order order) {

		OrderResponse orderResponse = WebClient.create(restURL + "order")
		        .put()
		        .contentType(MediaType.APPLICATION_JSON)
		        .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
		        .body(Mono.just(order), OrderResponse.class)
		        .accept(MediaType.APPLICATION_JSON)
		        .retrieve()
		        .bodyToFlux(OrderResponse.class)
		        .log().blockFirst();

		System.out.println("Resultado do updateOrder: " + orderResponse);

		return orderResponse;
	}
}