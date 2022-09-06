package com.liferay.docs.message.sender;

import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBus;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component
public class MessageSender  {

	@Activate
	private void _activate() {
		Message message = new Message();

		message.setPayload("N8K5Baker");

		_messageBus.sendMessage("acme/n8k5_able", message);

		System.out.println("message sent ..........");
	}

	@Reference
	private MessageBus _messageBus;

}