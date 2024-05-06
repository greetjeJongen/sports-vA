package be.ucll.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import be.ucll.model.User;
import jakarta.annotation.PostConstruct;

@Component
public class DbInitializer {

    private UserRepository userRepository;

    @Autowired
    public DbInitializer(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void initialize() {

        User user1 = new User("John Doe", 25, "john.doe@ucll.be");
        User user2 = new User("Jane Toe", 30, "jane.toe@ucll.be");
        // User user3 = new User("Jack Doe", 5, "jack.doe@ucll.be");
        // User user4 = new User("Sarah Doe", 4, "sarah.doe@ucll.be");
        // User user5 = new User("Birgit Doe", 18, "birgit.doe@ucll.be");

        userRepository.addUser(user1);
        userRepository.addUser(user2);
        // userRepository.addUser(user3);
        // userRepository.addUser(user4);
        // userRepository.addUser(user5);
    }
}
