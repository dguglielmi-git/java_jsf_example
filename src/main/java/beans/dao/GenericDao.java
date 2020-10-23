package beans.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import javax.persistence.EntityManager;

public class GenericDao<E, PK> {

    protected EntityManager entityManager;

    public GenericDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @SuppressWarnings("unchecked")
    protected Class<E> getType() {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass()
                .getGenericSuperclass();
        Type[] actualTypeArguments = genericSuperclass.getActualTypeArguments();
        Type type = actualTypeArguments[0];
        return (Class<E>) type;
    }

    public E getById(PK id) {
        return entityManager.find(this.getType(), id);
    }
    
    @SuppressWarnings("unchecked")
    public List<E> getAll() {
        Class<E> type = this.getType();
        return entityManager
                .createQuery("from  " + type.getCanonicalName() + " as t ").getResultList();
    }

}
