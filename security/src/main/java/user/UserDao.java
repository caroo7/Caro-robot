package user;

import entities.RobotUser;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import repositories.UserRepository;

import java.util.List;

@Repository
public class UserDao implements UserRepository {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public RobotUser findByUserName(String username) {
        List<RobotUser> users;

        users = sessionFactory.getCurrentSession()
                .createQuery("from User where username=?")
                .setParameter(0, username)
                .list();

        if (!users.isEmpty()) {
            return users.get(0);
        } else {
            return null;
        }
    }
}
