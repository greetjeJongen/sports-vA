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

                // TODO address field toevoegen aan users
                User user1 = new User("John Doe", 25, "john.doe@ucll.be", "john1234");
                User user2 = new User("Jane Toe", 30, "jane.toe@ucll.be", "jane1234");
                User user3 = new User("Jack Doe", 5, "jack.doe@ucll.be", "jack1234");
                User user4 = new User("Sarah Doe", 4, "sarah.doe@ucll.be", "sarah1234");
                User user5 = new User("Birgit Doe", 18, "birgit.doe@ucll.be", "birgit1234");

                userRepository.addUser(user1);
                userRepository.addUser(user2);
                userRepository.addUser(user3);
                userRepository.addUser(user4);
                userRepository.addUser(user5);
        }
}
