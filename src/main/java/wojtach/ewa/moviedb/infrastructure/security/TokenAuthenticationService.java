package wojtach.ewa.moviedb.infrastructure.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

import static java.util.Collections.emptyList;

/**
 * Created by ewa on 16.04.2017.
 */
class TokenAuthenticationService {

    static final long EXPIRATION_TIME = 10; // days
    static final String SECRET = "Secret";
    static final String TOKEN_PREFIX = "Bearer";
    static final String HEADER_STRING = "Authorization";


    static void addAuthentication(final HttpServletResponse res, final String username) {
        final String jwt = Jwts.builder()
                .setSubject(username)
                .setExpiration(Date.from(LocalDateTime.now().plusDays(EXPIRATION_TIME).atZone(ZoneId.systemDefault()).toInstant()))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
        res.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + jwt);
    }

    static Authentication getAuthentication(final HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);
        if (token != null) {
            // parse the token.
            final String user = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .getBody()
                    .getSubject();

            return Optional.ofNullable(user)
                    .map(userRead -> new UsernamePasswordAuthenticationToken(userRead, null, emptyList()) )
                    .orElse(null);
        }
        return null;
    }
}
