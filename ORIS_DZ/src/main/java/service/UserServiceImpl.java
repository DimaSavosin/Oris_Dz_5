package service;

import dto.RegisterForm;
import dto.UserDTO;
import models.User;
import org.mindrot.jbcrypt.BCrypt;
import repositories.UserDAO;
import repositories.impl.UserDAOImpl;

public class UserServiceImpl implements UserService {
    private UserDAO userDAO;
    public UserServiceImpl() {
        userDAO = new UserDAOImpl();
    }

    public boolean registerUser(RegisterForm registerForm){
        String email = registerForm.getEmail();
        if(userDAO.isEmailValid(email)){
            return false;
        }
        String hashedPassword = BCrypt.hashpw(registerForm.getPassword(),BCrypt.gensalt());
        User user = User.builder()
                .name(registerForm.getName())
                .email(email)
                .password(hashedPassword)
                .build();
        userDAO.save(user);
        return true;
    }

    public UserDTO loginUser(String email, String password){
        User user = userDAO.getUserByEmail(email);
        if (user!=null && BCrypt.checkpw(password,user.getPassword())){
            return mapToDto(user);
        }

        return null;
    }



    public int getUserIdByEmail(String email){
        return userDAO.getUserIdByEmail(email);
    }

    public UserDTO getUserById(int id){
        User user = userDAO.getUserById(id);
        return mapToDto(user);
    }



    private UserDTO mapToDto(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }

}
