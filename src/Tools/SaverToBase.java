
package Tools;

import Classes.Book;
import Classes.History;
import Classes.Reader;
import Interface.Keeping;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class SaverToBase implements Keeping{
    
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("LibraryPU");
    private EntityManager em = emf.createEntityManager();
    private EntityTransaction tx = em.getTransaction();

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
        tx.begin();
            for (int i = 0; i < booksArray.size(); i++) {
                if (booksArray.get(i).getId() == null) {
                    for (int j = 0; j < booksArray.get(i).getAuthors().size(); j++) {
                        em.persist(booksArray.get(i).getAuthors().get(j));
                    }
                    em.persist(booksArray.get(i));
                }
            }
        tx.commit();
    }

    @Override
    public List<Book> loadBooks() {
        List<Book> booksArray = null;
        try {
            booksArray = em.createQuery("SELECT book FROM Book book").getResultList();
        } catch (Exception e) {
            return new ArrayList<>();
        } 
        return booksArray;
    }
    
}
