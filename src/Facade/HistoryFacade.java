
package Facade;

import Classes.History;
import Tools.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class HistoryFacade extends AbstractFacade<History>{
    
    private EntityManager em;

    public HistoryFacade(Class<History> entityClass) {
        super(entityClass);
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
