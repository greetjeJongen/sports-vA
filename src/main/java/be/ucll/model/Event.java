package be.ucll.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Event {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @NotBlank(message = "Event name is required.")
    private String name;
    @NotNull(message = "StartDate is required")
    private LocalDate startDate;
    @NotNull(message = "EndDate is required")
    private LocalDate endDate;

    @ManyToMany
    @JoinTable(name = "event_sport", joinColumns = @JoinColumn(name = "event_id"), inverseJoinColumns = @JoinColumn(name = "sport_id"))
    private List<Sport> sports;

    protected Event() {
    }

    public Event(String name, LocalDate startDate, LocalDate endDate) {
        setName(name);
        setStartDate(startDate);
        setEndDate(endDate);

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        if (endDate != null && endDate.isBefore(startDate)) {
            throw new DomainException("Event endDate must be after the event startDate");
        }
        this.endDate = endDate;
    }

    public void addSport(Sport sport) {
        this.sports.add(sport);

    }

    public List<Sport> getSports() {
        return this.sports;
    }

    public Long getId() {
        return this.id;
    }

}
