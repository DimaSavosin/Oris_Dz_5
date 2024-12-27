package service;

import dto.RegisterForm;
import dto.UserDTO;

public interface UserService {
    boolean registerUser(RegisterForm registerForm);
    UserDTO loginUser(String email, String password);
    int getUserIdByEmail(String email);
    UserDTO getUserById(int id);

}
