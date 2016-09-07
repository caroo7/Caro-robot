package user;

import entities.RobotUser;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDao implements UserRepository {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public RobotUser findByUserName(String username) {
        List<RobotUser> users = new ArrayList<>();

        users = sessionFactory.getCurrentSession()
                .createQuery("from User where username=?")
                .setParameter(0, username)
                .list();

        if (users.size() > 0) {
            return users.get(0);
        } else {
            return null;
        }
    }
}
