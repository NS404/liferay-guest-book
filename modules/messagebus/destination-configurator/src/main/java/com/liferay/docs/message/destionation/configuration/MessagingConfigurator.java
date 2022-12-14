package com.liferay.docs.message.destionation.configuration;


import com.liferay.portal.kernel.messaging.Destination;
import com.liferay.portal.kernel.messaging.DestinationConfiguration;
import com.liferay.portal.kernel.messaging.DestinationFactory;
import com.liferay.portal.kernel.util.MapUtil;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

@Component
public class MessagingConfigurator {

	@Activate
	private void _activate(BundleContext bundleContext) {

		System.out.println("destination configured......");

		Destination destination = _destinationFactory.createDestination(
				DestinationConfiguration.createSerialDestinationConfiguration("acme/n8k5_able"));

		_serviceRegistration = bundleContext.registerService(
				Destination.class, destination,
				MapUtil.singletonDictionary("destination.name", destination.getName()));
	}

	@Deactivate
	private void _deactivate() {
		if (_serviceRegistration != null) {
			_serviceRegistration.unregister();
		}
	}

	@Reference
	private DestinationFactory _destinationFactory;

	private ServiceRegistration<Destination> _serviceRegistration;

}