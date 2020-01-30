package com.hello;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.hibernate.ogm.jpa.HibernateOgmPersistence;

import com.hello.model.Bar;
import com.hello.model.DynamicPersistentModel;
import com.hello.model.Foo;
import com.hello.model.HibernateOGMPersistenceUnitInfo;
import com.hello.model.PersistentModel;

/**
 * CDI producer that works around EntityManager initialization.</br>
 * For customization purposes, EMS don't rely on static persistence.xml
 * file.</br>
 * This class aggregates properties from classpath properties file and System
 * properties and generates a Java representation of the persistence.xml
 * file.</br>
 * It's also responsible of the introspection of Entity classes</br>
 */
@ApplicationScoped
public class Producer {

	private static final String PERSISTENCE_PROPERTIES = "persistence.properties";

	private static final String EMS_PERSISTENCE = "ems-persistence";
	private HibernateOgmPersistence ogmPersistence = new HibernateOgmPersistence();
	private EntityManagerFactory factory;
	private PersistentModel persistentModel;
	private Set<Class<?>> persistentClasses;

	public Producer() {
		persistentClasses = new HashSet<Class<?>>();
		persistentClasses.add(Foo.class);
		persistentClasses.add(Bar.class);
	}

	@Produces
	@RequestScoped
	public EntityManager createEntityManager() {
		System.out.println("4 aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		if (factory == null) {
			factory = createEntityManagerFactory();
		}
		return factory.createEntityManager();
	}

	@Produces
	@ApplicationScoped
	public EntityManagerFactory createEntityManagerFactory() {

		System.out.println("3 aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");

		EntityManagerFactory entityManagerFactory = ogmPersistence
				.createContainerEntityManagerFactory(getPersistenceUnitInfo(), getOgmConfig());
		createPersistentModel();
		return entityManagerFactory;
	}

	@Produces
	@Singleton
	public PersistentModel createPersistentModel() {
		System.out.println("2 aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");

		if (persistentModel == null) {
			persistentModel = new DynamicPersistentModel();
			persistentModel.setEntityClasses(new ArrayList<>(persistentClasses));
		}

		return persistentModel;
	}

	private HibernateOGMPersistenceUnitInfo getPersistenceUnitInfo() {
		return new HibernateOGMPersistenceUnitInfo(EMS_PERSISTENCE,
				persistentClasses.stream().map(Class::getCanonicalName).collect(Collectors.toList()));
	}

	/**
	 * Consolidate Hibernate OGM configuration.</br>
	 * First load properties file from classpath and then override them with those
	 * defined in System properties.
	 * 
	 * @return {@link Map} of consolidated properties
	 */
	protected Map<String, String> getOgmConfig() {
		Properties persistenceProperties = loadPersistencePropertiesFile();
		return toMap(persistenceProperties);
	}

	private Properties loadPersistencePropertiesFile() {
		System.out.println("1 aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");

		Properties properties = new Properties();

		try (InputStream in = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream(PERSISTENCE_PROPERTIES)) {
			properties.load(in);
		} catch (IOException e) {
		}

		return properties;
	}

	private Map<String, String> toMap(Properties persistenceProperties) {
		Map<String, String> propertiesMap = new HashMap<>();
		persistenceProperties.forEach((k, v) -> propertiesMap.put((String) k, (String) v));
		return propertiesMap;
	}
}
