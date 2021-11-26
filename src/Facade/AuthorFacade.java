
package Facade;

import Classes.Author;
import Tools.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class AuthorFacade extends AbstractFacade<Author>{
    
    private EntityManager em;

    public AuthorFacade(Class<Author> entityClass) {
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