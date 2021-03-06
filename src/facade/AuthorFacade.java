
package facade;

import entitys.Author;
import tools.Singleton;
import javax.persistence.EntityManager;

public class AuthorFacade extends AbstractFacade<Author>{
    
    private EntityManager em;

    public AuthorFacade() {
        super(Author.class);
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