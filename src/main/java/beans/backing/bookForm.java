package beans.backing;

import beans.helper.BookHelper;
import beans.model.Book;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import javax.inject.Named;

@RequestScoped
@Named
public class bookForm {

    @Inject
    private Book book;

    @Inject
    private BookHelper bookHelper;

    public String submit() {
        return "list";
    }

    public void bookListener(ValueChangeEvent valueChangeEvent) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        UIViewRoot uiViewRoot = facesContext.getViewRoot();
        Long idBookSelected = (Long) valueChangeEvent.getNewValue();
        this.book = bookHelper.getById(idBookSelected);
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public BookHelper getBookHelper() {
        return bookHelper;
    }

    public void setBookHelper(BookHelper bookHelper) {
        this.bookHelper = bookHelper;
    }
}
