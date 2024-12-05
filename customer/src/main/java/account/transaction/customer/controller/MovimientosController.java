package account.transaction.customer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import account.transaction.customer.model.Movimientos;
import account.transaction.customer.service.MovimientosService;


import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/movimientos")
public class MovimientosController {
    @Autowired
    private MovimientosService movimientosService;

    @GetMapping
    public List<Movimientos> getAllMovimientos() {
        return movimientosService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movimientos> getMovimientosById(@PathVariable int id) {
        Optional<Movimientos> movimientos = movimientosService.findById(id);
        return movimientos.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

     @PostMapping
    public ResponseEntity<Movimientos> createMovimiento(@RequestBody Movimientos movimientos) {
        try {
            Movimientos savedMovimiento = movimientosService.createMovimiento(movimientos);
            return ResponseEntity.created(URI.create("/movimientos/" + savedMovimiento.getId())).body(savedMovimiento);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
/*
 @PostMapping
    public Movimientos createMovimientos(@RequestBody Movimientos movimientos) {
        return movimientosService.save(movimientos);
    }
 */
   

    @PutMapping("/{id}")
    public ResponseEntity<Movimientos> updateMovimientos(@PathVariable int id, @RequestBody Movimientos movimientosDetails) {
        Optional<Movimientos> movimientos = movimientosService.findById(id);
        if (movimientos.isPresent()) {
            Movimientos updatedMovimientos = movimientos.get();
            updatedMovimientos.setFecha(movimientosDetails.getFecha());
            updatedMovimientos.setTipo(movimientosDetails.getTipo());
            updatedMovimientos.setValor(movimientosDetails.getValor());
            updatedMovimientos.setSaldo(movimientosDetails.getSaldo());
            updatedMovimientos.setEstado(movimientosDetails.isEstado());
            updatedMovimientos.setCuenta(movimientosDetails.getCuenta());
            return ResponseEntity.ok(movimientosService.save(updatedMovimientos));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovimientos(@PathVariable int id) {
        if (movimientosService.findById(id).isPresent()) {
            movimientosService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}