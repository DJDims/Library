
package facade;

import entitys.Book;
import tools.Singleton;
import javax.persistence.EntityManager;

public class BookFacade extends AbstractFacade<Book>{
    
    private EntityManager em;
    

    public BookFacade() {
        super(Book.class);
        init();
    }
    
    private void init(){
        Singleton singleton = Singleton.getInstance();
        em = singleton.getEntityManager();
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
