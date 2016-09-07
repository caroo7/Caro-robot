package repositories;

import entities.RobotUser;

@FunctionalInterface
public interface UserRepository {

    /**
     *
     * @param username provided username
     * @return the user of the application
     */
    RobotUser findByUserName(String username);

}
