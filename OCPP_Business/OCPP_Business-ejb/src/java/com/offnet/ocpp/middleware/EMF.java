/*
 * Decompiled with CFR 0_115.
 * 
 * Could not load the following classes:
 *  javax.ejb.Singleton
 *  javax.ejb.Startup
 *  javax.persistence.EntityManager
 *  javax.persistence.EntityManagerFactory
 *  javax.persistence.Persistence
 */
package com.offnet.ocpp.middleware;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Singleton
@Startup
public class EMF {
    private static EntityManagerFactory emf;

    @PostConstruct
    private void startup() {
        emf = Persistence.createEntityManagerFactory((String)"Subscriber");
    }

    @PreDestroy
    private void shutdown() {
        emf.close();
    }

    public static EntityManager createEntityManager() {
        if (emf == null) {
            throw new IllegalStateException("Context is not initialized yet.");
        }
        return emf.createEntityManager();
    }
}