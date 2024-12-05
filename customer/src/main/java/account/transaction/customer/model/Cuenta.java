
package account.transaction.customer.model;
import jakarta.persistence.*;



@Entity
@Table(name = "Cuenta")
public class Cuenta {
    @Id
    private int numero;
    private String tipo;
    private double saldoInicial;
    private boolean estado;

    @ManyToOne
    @JoinColumn(name = "clienteIdentificacion", foreignKey = @ForeignKey(name = "FK_Cuenta_Cliente"))
    private Cliente cliente;

      // Constructor por defecto
      public Cuenta() {
        // Asegúrate de que este constructor esté vacío
    }

    // Constructor
    public Cuenta(int numero) {
        this.numero = numero;

    }

    public Cuenta(int numero, String tipo, double saldoInicial, boolean estado, Cliente cliente) {
        this.numero = numero;
        this.tipo = tipo;
        this.saldoInicial = saldoInicial;
        this.estado = estado;
        this.cliente = cliente;
    }

    // Getters y Setters
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getSaldoInicial() {
        return saldoInicial;
    }

    public void setSaldoInicial(double saldoInicial) {
        this.saldoInicial = saldoInicial;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}