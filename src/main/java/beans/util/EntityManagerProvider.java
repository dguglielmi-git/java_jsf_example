package beans.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerProvider {
    
    private static EntityManagerProvider instance;
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    
    public EntityManagerProvider() {
        entityManagerFactory = Persistence.createEntityManagerFactory("mysql");
    }
    
    public static EntityManagerProvider getInstance() {
        if (instance == null) {
            instance = new EntityManagerProvider();
        }
        return instance;
    }
    
    public EntityManager getEntityManager() {
        if (entityManager == null) {
            entityManager = entityManagerFactory.createEntityManager();
        }
        return entityManager;
    }
    
    public void close(){
        entityManager.close();
        entityManagerFactory.close();
    }
    
}
