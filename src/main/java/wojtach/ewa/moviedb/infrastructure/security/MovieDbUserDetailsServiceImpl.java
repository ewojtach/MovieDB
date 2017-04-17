package wojtach.ewa.moviedb.infrastructure.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import wojtach.ewa.moviedb.user.domain.UserAccountDto;
import wojtach.ewa.moviedb.user.domain.UserAccountFacade;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * Created by ewa on 17.04.2017.
 */
@Service
class MovieDbUserDetailsServiceImpl implements UserDetailsService{

    @Autowired
    UserAccountFacade userService;

    @Override
    public UserDetails loadUserByUsername(final String s) throws UsernameNotFoundException {

        final UserAccountDto user = Optional.ofNullable(userService.getUserAccountByNameWithPassword(s))
                .orElseThrow(()->new UsernameNotFoundException(s));

        return new UserDetails() {

            private static final long serialVersionUID = 2059202961588104658L;

            @Override
            public boolean isEnabled() {
                return true;
            }

            @Override
            public boolean isCredentialsNonExpired() {
                return true;
            }

            @Override
            public boolean isAccountNonLocked() {
                return true;
            }

            @Override
            public boolean isAccountNonExpired() {
                return true;
            }

            @Override
            public String getUsername() {
                return user.getName();
            }

            @Override
            public String getPassword() {
                return user.getPassword();
            }

            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                List<SimpleGrantedAuthority> auths = new java.util.ArrayList<SimpleGrantedAuthority>();
                auths.add(new SimpleGrantedAuthority("Admin"));
                return auths;
            }
        };
    }
}
