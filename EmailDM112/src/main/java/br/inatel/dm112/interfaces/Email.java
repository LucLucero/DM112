package br.inatel.dm112.interfaces;

import br.inatel.dm112.model.MailRequestData;
import br.inatel.dm112.model.Order;

public interface Email {

	public void notifyProgress(MailRequestData mailData, Order order);

}