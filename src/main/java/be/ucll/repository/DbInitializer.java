package be.ucll.repository;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import be.ucll.model.Event;
import be.ucll.model.User;
import jakarta.annotation.PostConstruct;

@Component
public class DbInitializer {

    private UserRepository userRepository;
    private EventRepository eventRepository;

    @Autowired
    public DbInitializer(UserRepository userRepository, EventRepository eventRepository) {
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
    }

    @PostConstruct
    public void initialize() {

        User user1 = new User("John Doe", 25, "john.doe@ucll.be", "Naamsesteenweg 29, Heverlee");
        User user2 = new User("Jane Toe", 30, "jane.toe@ucll.be", "Diestsestraat 13, Leuven");
        // User user3 = new User("Jack Doe", 5, "jack.doe@ucll.be");
        // User user4 = new User("Sarah Doe", 4, "sarah.doe@ucll.be");
        // User user5 = new User("Birgit Doe", 18, "birgit.doe@ucll.be");

        userRepository.addUser(user1);
        userRepository.addUser(user2);
        // userRepository.addUser(user3);
        // userRepository.addUser(user4);
        // userRepository.addUser(user5);

        Event event1 = new Event("Sportdag-UCLL", LocalDate.now().minusDays(60), LocalDate.now().minusDays(40));
        Event event2 = new Event("Sportdag-KUL", LocalDate.now().minusDays(2), LocalDate.now().plusDays(20));
        eventRepository.save(event1);
        eventRepository.save(event2);
    }
}
