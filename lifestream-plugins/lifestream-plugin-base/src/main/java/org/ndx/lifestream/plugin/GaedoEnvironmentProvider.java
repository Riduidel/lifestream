package org.ndx.lifestream.plugin;

import java.util.Arrays;

import com.dooapp.gaedo.finders.FinderCrudService;
import com.dooapp.gaedo.finders.Informer;
import com.dooapp.gaedo.finders.collections.CollectionBackedFinderService;
import com.dooapp.gaedo.finders.collections.IdSupportingCollectionBackedFinderService;
import com.dooapp.gaedo.finders.id.AnnotationUtils;
import com.dooapp.gaedo.finders.repository.ServiceRepository;
import com.dooapp.gaedo.finders.repository.SimpleServiceRepository;
import com.dooapp.gaedo.finders.root.BasicFieldInformerLocator;
import com.dooapp.gaedo.finders.root.CumulativeFieldInformerLocator;
import com.dooapp.gaedo.finders.root.ProxyBackedInformerFactory;
import com.dooapp.gaedo.finders.root.ReflectionBackedInformerFactory;
import com.dooapp.gaedo.properties.BeanBackedPropertyProvider;
import com.dooapp.gaedo.properties.Property;
import com.dooapp.gaedo.properties.PropertyProvider;

/**
 * Provide the various elements used to have a valid service repository
 * @author Nicolas
 *
 */
public class GaedoEnvironmentProvider {
	private PropertyProvider propertyProvider;
	private CumulativeFieldInformerLocator locator;
	private ServiceRepository repository;
	private ReflectionBackedInformerFactory reflectiveFactory;
	private ProxyBackedInformerFactory informerFactory;

	/**
	 * Notice that created service repository won't have any service loaded in.
	 * To load services, one will have to add it by himself
	 * @return
	 */
	public ServiceRepository getRepository() {
		if(repository==null) {
			repository = new SimpleServiceRepository();
		}
		return repository;
	}

	private ProxyBackedInformerFactory getInformerFactory() {
		if(informerFactory==null) {
			informerFactory = new ProxyBackedInformerFactory(
				getReflectiveFactory());
		}
		return informerFactory;
	}

	private ReflectionBackedInformerFactory getReflectiveFactory() {
		if(reflectiveFactory==null) {
			reflectiveFactory = new ReflectionBackedInformerFactory(
					getLocator(), getPropertyProvider());
		}
		return reflectiveFactory;
	}
	
	private CumulativeFieldInformerLocator getLocator() {
		if(locator==null) {
			locator = new CumulativeFieldInformerLocator();
			locator.add(new BasicFieldInformerLocator());
//			locator.add(new ServiceBackedFieldLocator(getRepository()));
		}
		return locator;
	}

	private PropertyProvider getPropertyProvider() {
		if(propertyProvider==null) {
			propertyProvider = new BeanBackedPropertyProvider();
		}
		return propertyProvider;
	}

	public <Type, InformerType extends Informer<Type>> FinderCrudService<Type, InformerType> 
		getServiceFor(
			Class<Type> dataClass, Class<InformerType> informerType) {
		if(!getRepository().containsKey(dataClass)) {
			FinderCrudService<Type, InformerType> service = new CollectionBackedFinderService<Type, InformerType>
			(dataClass, informerType, getInformerFactory());
			getRepository().add(service);
		}
		return getRepository().get(dataClass);
	}

	public <Type, InformerType extends Informer<Type>> FinderCrudService<Type, InformerType> 
		getServiceFor(
			Class<Type> dataClass, Class<InformerType> informerType, Class<?>...idClass) {
		Property idProperty = AnnotationUtils.locateIdField(getPropertyProvider(), dataClass, idClass);
		FinderCrudService<Type, InformerType> service = new IdSupportingCollectionBackedFinderService<Type, InformerType>
				(dataClass, informerType, getInformerFactory(), Arrays.asList(idProperty));
		getRepository().add(service);
		return service;
	}
}
