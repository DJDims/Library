
package facade;

import entitys.History;
import tools.Singleton;
import javax.persistence.EntityManager;

public class HistoryFacade extends AbstractFacade<History>{
    
    private EntityManager em;

    public HistoryFacade() {
        super(History.class);
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
