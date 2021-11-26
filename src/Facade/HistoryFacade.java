
package Facade;

import Classes.History;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class HistoryFacade extends AbstractFacade<History>{
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("LibraryPU");
    private EntityManager em = emf.createEntityManager();
    private EntityTransaction tx = em.getTransaction();

    public HistoryFacade(Class<History> entityClass) {
        super(entityClass);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
