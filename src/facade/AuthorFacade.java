
package facade;

import classes.Author;
import tools.Singleton;
import javax.persistence.EntityManager;

public class AuthorFacade extends AbstractFacade<Author>{
    
    private EntityManager em;

    public AuthorFacade(Class<Author> entityClass) {
        super(entityClass);
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