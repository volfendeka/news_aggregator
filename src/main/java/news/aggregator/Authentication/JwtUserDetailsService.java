package news.aggregator.Authentication;

import news.aggregator.Entity.User;
import news.aggregator.Repository.UserRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepositoryCustom jpaUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = jpaUserRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new JwtPrincipalUser(user);
    }
}