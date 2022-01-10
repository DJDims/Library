
package facade;

import entitys.UserRoles;
import tools.Singleton;
import javax.persistence.EntityManager;

public class UserRolesFacade extends AbstractFacade<UserRoles>{
    
    private EntityManager em;

    public UserRolesFacade() {
        super(UserRoles.class);
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