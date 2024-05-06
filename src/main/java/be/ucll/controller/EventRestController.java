package be.ucll.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import be.ucll.model.DomainException;
import be.ucll.model.Event;
import be.ucll.model.Sport;
import be.ucll.service.EventService;
import be.ucll.service.ServiceException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/events")
public class EventRestController {

    private EventService eventService;

    public EventRestController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping
    public Event addEvent(@RequestBody @Valid Event event) {
        return eventService.addEvent(event);
    }

    @GetMapping
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }

    @PostMapping("/sport/{eventName}")
    public Sport addSportToEvent(@RequestBody @Valid Sport sport, @PathVariable String eventName) {
        return eventService.addSport(eventName, sport);
    }

    @GetMapping("/sport")
    public List<Event> getEventsWithSportWithMoreThanPlayers(@RequestParam int minNumOfPlayers) {
        return eventService.getEventsWithSportWithMoreThanPlayers(minNumOfPlayers);
    }

    @GetMapping("/kudos/{eventId}/{sportId}")
    public int getKudos(@PathVariable Long eventId, @PathVariable Long sportId) {
        return eventService.getKudos(eventId, sportId);
    }

    @ExceptionHandler(DomainException.class)
    public ResponseEntity<Map<String, String>> handlDomainException(DomainException ex, WebRequest request) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("DomainException: ", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(errorResponse);
    }

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<Map<String, String>> handlServiceException(ServiceException ex, WebRequest request) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("ServiceException: ", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handlMethodArgumentNotValidException(MethodArgumentNotValidException ex,
            WebRequest request) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put(ex.getFieldError().getField(), ex.getFieldError().getDefaultMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(errorResponse);
    }
}
