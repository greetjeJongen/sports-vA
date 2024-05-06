package be.ucll.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import be.ucll.model.Sport;

public interface SportRepository extends JpaRepository<Sport, Long> {

}
