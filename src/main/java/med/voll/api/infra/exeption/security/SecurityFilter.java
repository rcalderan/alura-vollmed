package med.voll.api.infra.exeption.security;

import com.auth0.jwt.exceptions.JWTVerificationException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import med.voll.api.domain.user.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository repository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var jwtToken = readJwtToken(request);
        if(jwtToken!=null){
            var subject = tokenService.getSubject(jwtToken);
            var user = repository.findByLogin(subject);

            var authentication = new UsernamePasswordAuthenticationToken(
                    user.getUsername(), null, user.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }



        filterChain.doFilter(request, response);
    }

    private String readJwtToken(HttpServletRequest request) {
        var auth = request.getHeader("Authorization");
        if(auth != null){
            return auth.replace("Bearer ","");
        }
        return null;
    }
}
