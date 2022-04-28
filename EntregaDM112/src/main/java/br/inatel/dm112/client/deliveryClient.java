package br.inatel.dm112.client;



import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import br.inatel.dm112.model.Order;
import reactor.core.publisher.Mono;

@Service
public class deliveryClient {

	@Value("${delivery.rest.url}")
	private String restURL;
	
	private final String endpoint = "/delivery";

	
	public void processDelivery(Order order) {

		String url = restURL + endpoint;
		System.out.println("URL: " + url);
		
		WebClient.create(url)
		        .post()
		        .contentType(MediaType.APPLICATION_JSON)
		        .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
		        .body(Mono.just(order), Order.class)
		        .accept(MediaType.APPLICATION_JSON)
		        .retrieve();
		        //.log()

		System.out.println("Sucesso ao processar o pedido: " + order.getNumber());
	}
	
	
	
	public Order retrieveOrder(int orderNumber) {
		String url = restURL + endpoint + "/" + orderNumber;
		System.out.println("URL: " + url);
		
		return WebClient.create(url)
		        .get()
		        .retrieve()
		        .bodyToMono(Order.class)
		        .block();
	}
	
	
	public void registerDelivery (Order order) {

		String url = restURL + endpoint + "/" + order.getNumber();
		System.out.println("URL: " + url);
		
		WebClient.create(url)
		        .put()
		        .contentType(MediaType.APPLICATION_JSON)
		        .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
		        .body(Mono.just(order), Order.class)
		        .accept(MediaType.APPLICATION_JSON)
		        .retrieve();

		System.out.println("Sucesso no update pedido: " + order.getNumber());
	}
	
	public String getEndpoint() {
		return endpoint;
	}
	
	public void setRestURL(String restURL) {
		this.restURL = restURL;
	}
}
