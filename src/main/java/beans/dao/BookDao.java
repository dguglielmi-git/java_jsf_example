package beans.dao;

import beans.model.Book;
import javax.persistence.EntityManager;

public class BookDao extends GenericDao<Book, Long>{
    
    public BookDao(EntityManager entityManager) {
        super(entityManager);
    }
    
}
