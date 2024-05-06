package be.ucll.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import be.ucll.model.Event;

public interface EventRepository extends JpaRepository<Event, Long> {

    Event findByNameContainingIgnoreCase(String name);

    List<Event> findBySports_NumOfPlayersGreaterThan(int number);

}
