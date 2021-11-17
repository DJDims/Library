
package Tools;

import Classes.Author;
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
        tx.begin();
            for (int i = 0; i < readersArray.size(); i++) {
                if (readersArray.get(i).getId() == null) {
                    em.persist(readersArray.get(i));
                } else {
                    em.merge(readersArray.get(i));
                }
            }
        tx.commit();
    }

    @Override
    public List<Reader> loadReaders() {
        List<Reader> readersArray = null;
        try {
            readersArray = em.createQuery("SELECT reader FROM Reader reader").getResultList();
        } catch (Exception e) {
            return new ArrayList<>();
        }
        return readersArray;
    }

    @Override
    public void saveHistorys(List<History> historysArray) {
        tx.begin();
            for (int i = 0; i < historysArray.size(); i++) {
                if (historysArray.get(i).getId() == null) {
                    em.persist(historysArray.get(i));
                }
            }
        tx.commit();
    }

    @Override
    public List<History> loadHistorys() {
        List<History> historysArray = null;
        try {
            historysArray = em.createQuery("SELECT history FROM History history").getResultList();
        } catch (Exception e) {
            return new ArrayList<>();
        }
        return historysArray;
    }

    @Override
    public void saveBooks(List<Book> booksArray) {
        tx.begin();
            for (int i = 0; i < booksArray.size(); i++) {
                if (booksArray.get(i).getId() == null) {
                    em.persist(booksArray.get(i));
                } else {
                    em.merge(booksArray.get(i));
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

    @Override
    public void saveAuthors(List<Author> authorsArray) {
        tx.begin();
            for (int i = 0; i < authorsArray.size(); i++) {
                if (authorsArray.get(i).getId() == null) {
                    em.persist(authorsArray.get(i));
                } else {
                    em.merge(authorsArray.get(i));
                }
            }
        tx.commit();
    }

    @Override
    public List<Author> loadAuthors() {
        List<Author> authorsArray = null;
        try {
            authorsArray = em.createQuery("SELECT author FROM Author author").getResultList();
        } catch (Exception e) {
            return new ArrayList<>();
        } 
        return authorsArray;
    }

}
