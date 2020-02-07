package com.hello;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import javax.persistence.SharedCacheMode;
import javax.persistence.ValidationMode;
import javax.persistence.spi.ClassTransformer;
import javax.persistence.spi.PersistenceUnitInfo;
import javax.persistence.spi.PersistenceUnitTransactionType;
import javax.sql.DataSource;

import org.hibernate.ogm.jpa.HibernateOgmPersistence;

public class HibernateOGMPersistenceUnitInfo implements PersistenceUnitInfo {

    private List<String> classes = new ArrayList<>();
    
    private PersistenceUnitTransactionType transactionType;
    private String name;
    private Properties properties;
    
    public HibernateOGMPersistenceUnitInfo(String name) {
        this.name = name;
        this.transactionType = PersistenceUnitTransactionType.JTA;
    }

    public HibernateOGMPersistenceUnitInfo(String name, PersistenceUnitTransactionType transactionType) {
        this.name = name;
        this.transactionType = transactionType;
    }    
    
    public HibernateOGMPersistenceUnitInfo(String name, List<String> entityClasses) {
        this(name);
        this.transactionType = PersistenceUnitTransactionType.JTA;
        addClasses(entityClasses);
    }

    @Override
    public String getPersistenceUnitName() {
        return name;
    }

    @Override
    public String getPersistenceProviderClassName() {
        return HibernateOgmPersistence.class.getName();
    }

    @Override
    public PersistenceUnitTransactionType getTransactionType() {
        return transactionType;
    }

    @Override
    public DataSource getJtaDataSource() {
        return null;
    }

    @Override
    public DataSource getNonJtaDataSource() {
        return null;
    }

    @Override
    public List<String> getMappingFileNames() {
        return Collections.<String>emptyList();
    }

    @Override
    public List<URL> getJarFileUrls() {
        return Collections.<URL>emptyList();
    }

    @Override
    public URL getPersistenceUnitRootUrl() {
        return null;
    }

    @Override
    public List<String> getManagedClassNames() {
        return classes;
    }

    public void addClass(String className) {
        this.classes.add(className);
    }
    
    public void addClasses(List<String> classNames) {
        this.classes.addAll(classNames);
    }
    
    @Override
    public boolean excludeUnlistedClasses() {
        return false;
    }

    @Override
    public SharedCacheMode getSharedCacheMode() {
        return null;
    }

    @Override
    public ValidationMode getValidationMode() {
        return null;
    }

    @Override
    public Properties getProperties() {
        return this.properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    @Override
    public String getPersistenceXMLSchemaVersion() {
        return null;
    }

    @Override
    public ClassLoader getClassLoader() {
        return null;
    }

    @Override
    public void addTransformer(ClassTransformer paramClassTransformer) {
    	// Do nothing
    }

    @Override
    public ClassLoader getNewTempClassLoader() {
        return null;
    }
}
