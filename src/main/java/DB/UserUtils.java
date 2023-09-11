package DB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.poly.dao.AccountDAO;
import com.poly.entities.AccountToUserDetails;
import com.poly.model.Account;

public class UserUtils {
	@Autowired AccountDAO aDAO;
    public static UserDetails getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            if (userDetails != null) {
            	return userDetails;
            }
            return null;
        } else {
            System.out.println("No user authenticated.");
            return null;
        }
    }	
}
