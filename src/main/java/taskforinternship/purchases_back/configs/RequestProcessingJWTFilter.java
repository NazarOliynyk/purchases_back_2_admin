package taskforinternship.purchases_back.configs;

import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Collections;

public class RequestProcessingJWTFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        Authentication authentication = null;

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        // and checks if token exists in header Authorization
        String token = httpServletRequest.getHeader("Authorization");
        // if present
        if (token != null) {
            // parse it and retrieve body subject from
            String user = Jwts.parser()
                    .setSigningKey("yes".getBytes())
                    .parseClaimsJws(token.replace("Bearer", ""))
                    .getBody()
                    .getSubject();
            //after parsing of token an Authentication object is created
            authentication = new UsernamePasswordAuthenticationToken(Collections.emptyList(), user, null);

            // it did not work
            //authentication = new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
        }
        // and sets it to global security context
        SecurityContextHolder.getContext()
                .setAuthentication(authentication);
        chain.doFilter(request, response);
    }
}
