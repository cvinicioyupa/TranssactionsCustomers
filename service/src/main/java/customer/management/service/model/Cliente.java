package customer.management.service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;


@Entity
@Table(name = "Cliente")
public class Cliente {
    @Id
    private int identificacion;
    private String contraseña;
    private boolean estado;

    @OneToOne
    @JoinColumn(name = "identificacion", foreignKey = @ForeignKey(name = "FK_Cliente_Persona"))
    private Persona persona;

    // Constructor
    public Cliente() {}

    public Cliente(int identificacion, String contraseña, boolean estado, Persona persona) {
        this.identificacion = identificacion;
        this.contraseña = contraseña;
        this.estado = estado;
        this.persona = persona;
    }

    // Getters y Setters
    public int getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(int identificacion) {
        this.identificacion = identificacion;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }
}