
package facade;

import entitys.Role;
import tools.Singleton;
import javax.persistence.EntityManager;

public class RoleFacade extends AbstractFacade<Role>{
    
    private EntityManager em;

    public RoleFacade() {
        super(Role.class);
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