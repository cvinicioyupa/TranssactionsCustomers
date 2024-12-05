
package account.transaction.customer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import account.transaction.customer.model.Cuenta;
import account.transaction.customer.model.Movimientos;
import account.transaction.customer.repository.MovimientosRepository;
import jakarta.transaction.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class MovimientosService {
    private static final Logger logger = LoggerFactory.getLogger(MovimientosService.class);
    @Autowired
    private MovimientosRepository movimientosRepository;

    public List<Movimientos> findAll() {
        return movimientosRepository.findAll();
    }

    public Optional<Movimientos> findById(int id) {
        return movimientosRepository.findById(id);
    }

    public Movimientos save(Movimientos movimientos) {
        return movimientosRepository.save(movimientos);
    }

    public void deleteById(int id) {
        movimientosRepository.deleteById(id);
    }

    /* 
    @Transactional
    public Movimientos createMovimiento(Movimientos movimiento) {
        if (movimiento.getValor() <= 0) {
            logger.warn("El valor del movimiento no puede ser cero: {}", movimiento.getValor());
            throw new IllegalArgumentException("El valor del movimiento debe ser mayor que cero");
        }
        return movimientosRepository.save(movimiento);
    }
        */
/* 
     @Transactional
    public Movimientos createMovimiento(Movimientos movimiento) {
        Cuenta cuenta = movimiento.getCuenta();
        Optional<Movimientos> ultimoMovimientoOpt = movimientosRepository.findTopByCuentaOrderByFechaDesc(cuenta);

        double saldoActual = ultimoMovimientoOpt.map(Movimientos::getSaldo).orElse(cuenta.getSaldoInicial());

        if (movimiento.getTipo().equalsIgnoreCase("debito")) {
            if (movimiento.getValor() <= 0) {
                logger.warn("El valor del movimiento no puede ser menor o igual a cero: {}", movimiento.getValor());
                throw new IllegalArgumentException("El valor del movimiento debe ser mayor que cero");
            }
            if (saldoActual < movimiento.getValor()) {
                logger.warn("Saldo insuficiente para realizar el débito: {}", movimiento.getValor());
                throw new IllegalArgumentException("Saldo insuficiente para realizar el débito");
            }
            saldoActual -= movimiento.getValor();
        } else if (movimiento.getTipo().equalsIgnoreCase("credito")) {
            if (movimiento.getValor() <= 0) {
                logger.warn("El valor del movimiento no puede ser menor o igual a cero: {}", movimiento.getValor());
                throw new IllegalArgumentException("El valor del movimiento debe ser mayor que cero");
            }
            saldoActual += movimiento.getValor();
        } else {
            logger.warn("Tipo de movimiento desconocido: {}", movimiento.getTipo());
            throw new IllegalArgumentException("Tipo de movimiento desconocido");
        }

        movimiento.setSaldo(saldoActual);
        return movimientosRepository.save(movimiento);
    }
        */

    @Transactional
    public Movimientos createMovimiento(Movimientos movimiento) {
        Cuenta cuenta = movimiento.getCuenta();

        // Refrescar el saldo actual obteniendo el último movimiento registrado
        Optional<Movimientos> ultimoMovimientoOpt = movimientosRepository.findTopByCuentaOrderByIdDesc(cuenta);
        double saldoActual = ultimoMovimientoOpt.map(Movimientos::getSaldo).orElse(cuenta.getSaldoInicial());

        try {
            if (movimiento.getTipo().equalsIgnoreCase("debito")) {
                if (movimiento.getValor() <= 0) {
                    throw new IllegalArgumentException("El valor del movimiento debe ser mayor que cero");
                }
                if (saldoActual < movimiento.getValor()) {
                    throw new IllegalArgumentException("Saldo insuficiente para realizar el debito");
                }
                saldoActual -= movimiento.getValor();
            } else if (movimiento.getTipo().equalsIgnoreCase("credito")) {
                if (movimiento.getValor() <= 0) {
                    throw new IllegalArgumentException("El valor del movimiento debe ser mayor que cero");
                }
                saldoActual += movimiento.getValor();
            } else {
                throw new IllegalArgumentException("Tipo de movimiento desconocido");
            }

            movimiento.setSaldo(saldoActual);
            movimiento.setEstado(true); // Movimiento exitoso
            movimiento.setFecha(new Date());
        } catch (IllegalArgumentException e) {
            logger.warn("Error al procesar el movimiento: {}", e.getMessage());
            movimiento.setEstado(false); // Movimiento fallido
            throw e;
        }
        
        return movimientosRepository.save(movimiento);
        
    }


}