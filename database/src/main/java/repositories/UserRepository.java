package repositories;

import entities.User;

public interface UserRepository {

    User findByUserName(String username);

}
