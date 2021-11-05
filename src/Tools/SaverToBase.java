
package Tools;

import Classes.Book;
import Classes.History;
import Classes.Reader;
import Interface.Keeping;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class SaverToBase implements Keeping{

    @Override
    public void saveReaders(List<Reader> readersArray) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Reader> loadReaders() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void saveHistorys(List<History> historysArray) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<History> loadHistorys() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void saveBooks(List<Book> booksArray) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("LibraryPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
            for (int i = 0; i < booksArray.size(); i++) {
                if (booksArray.get(i).getId() == null) {
                    em.persist(booksArray.get(i));
                }
            }
        tx.commit();
    }

    @Override
    public List<Book> loadBooks() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
