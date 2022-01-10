
package facade;

import entitys.Reader;
import tools.Singleton;
import javax.persistence.EntityManager;

public class ReaderFacade extends AbstractFacade<Reader>{
    
    private EntityManager em;

    public ReaderFacade() {
        super(Reader.class);
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
