package be.ucll.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

@Entity
public class Sport {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @NotBlank(message = "Sport name is required")
    private String name;

    @Positive(message = "Sport numOfPlayers is less than 1")
    private int numOfPlayers;

    private Boolean teamSport;

    protected Sport() {
    }

    ;

    public Sport(String name, int numOfPlayers, boolean teamSport) {
        setName(name);
        setTeamSport(teamSport);
        setNumOfPlayers(numOfPlayers);

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumOfPlayers() {
        return numOfPlayers;
    }

    public void setNumOfPlayers(int numOfPlayers) {
        if (isTeamSport() && numOfPlayers < 2) {
            throw new DomainException("A sport can only be considered a team sport with 2 or more players.");
        }
        if (!isTeamSport() && numOfPlayers >= 2) {
            throw new DomainException("A sport with 2 or more players is a teamSport. ");
        }
        this.numOfPlayers = numOfPlayers;
    }

    public boolean isTeamSport() {
        return teamSport;
    }

    public void setTeamSport(boolean teamSport) {
        this.teamSport = teamSport;
    }

}
