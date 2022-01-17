
package facade;

import entitys.User;
import entitys.UserRoles;
import java.util.List;
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
    
    public String getTopRole(User user) {
        try {
            List<String> rolesArray = em.createQuery("SELECT ur.role.roleName FROM UserRoles ur WHERE ur.user = :user").setParameter("user", user).getResultList();
            
            if(rolesArray.contains("ADMINISTRATOR")){
                return "ADMINISTRATOR";
            }else if(rolesArray.contains("MANAGER")){
                return "MANAGER";
            }else if(rolesArray.contains("READER")){
                return "READER";
            }else{
                return "";
            }
        } catch (Exception e) {
            return "";
        }
    }
}