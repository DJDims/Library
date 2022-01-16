
package facade;

import entitys.User;
import tools.Singleton;
import javax.persistence.EntityManager;

public class UserFacade extends AbstractFacade<User>{
    
    private EntityManager em;

    public UserFacade() {
        super(User.class);
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
    
    public User findByLogin(String login){
        throw new UnsupportedOperationException("Not supported yet.");
    }
}