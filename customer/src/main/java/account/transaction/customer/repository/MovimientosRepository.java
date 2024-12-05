package account.transaction.customer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import account.transaction.customer.model.Cuenta;
import account.transaction.customer.model.Movimientos;


@Repository
public interface MovimientosRepository extends JpaRepository<Movimientos, Integer> {

    Optional<Movimientos> findTopByCuentaOrderByIdDesc(Cuenta cuenta);

}
