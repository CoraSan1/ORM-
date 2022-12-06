package Project.config.securityConfig;

import Project.model.Role_Account;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.management.relation.Role;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    @Override
    protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String url = "";
        List<Role_Account> roles = (List<Role_Account>) authentication.getAuthorities();

        Role_Account role = roles.get(0);
        if (role.getName().equals("ROLE_ADMIN")){
            url = "/products";
        }else {
            url = "/user";
        }
        response.sendRedirect(url);
    }
}
