package repositories;

import entities.RobotUser;

public interface UserRepository {

    RobotUser findByUserName(String username);

}
