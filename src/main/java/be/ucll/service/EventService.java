package be.ucll.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import be.ucll.model.Event;
import be.ucll.model.Sport;
import be.ucll.repository.EventRepository;
import be.ucll.repository.SportRepository;

@Service
public class EventService {

    private EventRepository eventRepository;
    private SportRepository sportRepository;

    public EventService(EventRepository eventRepository, SportRepository sportRepository) {
        this.eventRepository = eventRepository;
        this.sportRepository = sportRepository;
    }

    public Event addEvent(Event event) {
        if (findByNameContainingIgnoreCase(event.getName()) != null) {
            throw new ServiceException("Event already exists.");

        }
        eventRepository.save(event);
        return event;
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Event findByNameContainingIgnoreCase(String name) {
        return eventRepository.findByNameContainingIgnoreCase(name);
    }

    public Sport addSport(String eventName, Sport sport) {
        Event event = findByNameContainingIgnoreCase(eventName);
        if (event == null) {
            throw new ServiceException("No event with given name");
        }
        event.addSport(sport);
        Sport sportFromDB = sportRepository.save(sport);
        // eventRepository.save(event);
        return sportFromDB;
    }

    public List<Event> getEventsWithSportWithMoreThanPlayers(int minNumOfPlayers) {
        return eventRepository.findBySports_NumOfPlayersGreaterThan(minNumOfPlayers);
    }

    public int getKudos(Long eventId, Long sportId) {
        Event event = eventRepository.findById(eventId).orElse(null);
        Sport sport = sportRepository.findById(sportId).orElse(null);
        if (event.getStartDate().isBefore(LocalDate.now())
                && event.getEndDate().isAfter(LocalDate.now())) {
            return (sport.isTeamSport() ? sport.getNumOfPlayers() * 2 : 5);
        } else {
            throw new ServiceException("No active event");
        }
    }

}
