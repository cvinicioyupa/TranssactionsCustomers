
package customer.management.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import customer.management.service.model.Cliente;



@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
