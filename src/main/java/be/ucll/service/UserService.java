package be.ucll.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.ucll.model.User;
import be.ucll.repository.UserRepository;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    public User addUser(User userData) {
        User existingUser = userRepository.getUserByEmail(userData.getEmail());
        if (existingUser != null) {
            throw new ServiceException("User already exists.");
        }
        userRepository.addUser(userData);
        return userData;
    }

    // TODO updateUser toevoegen
    public User updateUser(String email, User userData) {
        User existingUser = userRepository.getUserByEmail(email);
        if (existingUser == null) {
            throw new ServiceException("User does not exist.");
        }
        existingUser.setName(userData.getName());
        existingUser.setAge(userData.getAge());
        existingUser.setEmail(userData.getEmail());
        existingUser.setAddress(userData.getAddress());
        userRepository.updateUser(userData);
        return userData;
    }

    public void deleteUser(String email) {
        User existingUser = userRepository.getUserByEmail(email);
        if (existingUser == null) {
            throw new ServiceException("User does not exist.");
        }
        userRepository.deleteUser(existingUser);
    }

}