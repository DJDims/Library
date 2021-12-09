
package facade;

import classes.Reader;
import tools.Singleton;
import javax.persistence.EntityManager;

public class ReaderFacade extends AbstractFacade<Reader>{
    
    private EntityManager em;

    public ReaderFacade(Class<Reader> entityClass) {
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
