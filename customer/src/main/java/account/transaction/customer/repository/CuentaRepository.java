
package account.transaction.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import account.transaction.customer.model.Cuenta;
@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, Integer> {

}
