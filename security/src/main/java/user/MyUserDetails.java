package user;

import entities.RobotUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repositories.UserRepository;

@Service("userDetailsService")
public class MyUserDetails implements UserDetailsService {

    @Autowired
    UserRepository userRepository;


    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        RobotUser user = userRepository.findByUserName(username);

        return buildUserForAuthentication(user);
    }

    private UserDetails buildUserForAuthentication(RobotUser user) {
        return new User(user.getUsername(),user.getPassword(),true,true,true,true,null);
    }
}
