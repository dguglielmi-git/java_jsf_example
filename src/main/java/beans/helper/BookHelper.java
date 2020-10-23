package beans.helper;

import beans.dao.BookDao;
import beans.model.Book;
import beans.util.EntityManagerProvider;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import javax.persistence.EntityManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@RequestScoped
@Named
public class BookHelper {

    EntityManager em = EntityManagerProvider.getInstance().getEntityManager();
    Logger log = LogManager.getRootLogger();

    public List<Book> getBooks() {
        BookDao bookDao = new BookDao(em);
        List<Book> books = new ArrayList<>();
        books = bookDao.getAll();

        return books;
    }
    
    public Book getById(Long id) {
        BookDao bookDao = new BookDao(em);
        Book book = bookDao.getById(id);
        return book;
    }
    
    public List<SelectItem> getSelectItems() {
        List<SelectItem> selectItems = new ArrayList<>();
        List<Book> books = this.getBooks();

        for (Book book : books) {
            SelectItem selectItem = new SelectItem(book.getId(), book.getTitle());
            selectItems.add(selectItem);
        }
        return selectItems;
    }
}
