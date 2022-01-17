
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
        try {
            return (User) em.createQuery("SELECT u FROM User WHERE u.login = :login").setParameter("login", login).getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}