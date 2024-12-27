package repositories;

import models.User;

import java.util.List;

public interface UserDAO extends CrudDAO<User>{
    boolean isEmailValid(String email);
    int getUserIdByEmail(String email);
    User getUserByEmail(String email);
    User getUserById(int id);
}
