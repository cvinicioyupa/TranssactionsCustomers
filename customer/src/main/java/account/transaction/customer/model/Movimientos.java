
package account.transaction.customer.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "Movimientos")
public class Movimientos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Date fecha;
    private String tipo;
    private double valor;
    private double saldo;
    private boolean estado;

    @ManyToOne
    @JoinColumn(name = "cuentaNumero", foreignKey = @ForeignKey(name = "FK_Movimientos_Cuenta"))
    private Cuenta cuenta;

    // Constructor
    public Movimientos() {}

    public Movimientos(int id, Date fecha, String tipo, double valor, double saldo, boolean estado, Cuenta cuenta) {
        this.id = id;
        this.fecha = fecha;
        this.tipo = tipo;
        this.valor = valor;
        this.saldo = saldo;
        this.estado = estado;
        this.cuenta = cuenta;
    }

    

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }
}