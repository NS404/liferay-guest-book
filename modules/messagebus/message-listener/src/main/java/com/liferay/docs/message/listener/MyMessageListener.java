package com.liferay.docs.message.listener;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageListener;
import org.osgi.service.component.annotations.Component;


@Component(
		property = "destination.name=acme/n8k5_able",  //register listener to destination
	service = MessageListener.class
)
public class MyMessageListener implements MessageListener {

	@Override
	public void receive(Message message) {

		System.out.println("MyMessageListener: Received message payload " + message.getPayload());

	}


	private static final Log _log = LogFactoryUtil.getLog(MyMessageListener.class);


}