package customer.management.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import customer.management.service.model.Persona;



@Repository
public interface PersonaRepository extends JpaRepository<Persona, Integer> {

}
