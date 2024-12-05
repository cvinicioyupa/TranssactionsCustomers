package customer.management.service.dto;


import lombok.Getter;
import lombok.Setter;


@Getter
@Setter




public class PersonDTO {
    private Integer identificacion;
    private String nombre;
    private char genero;
    private String direccion;
    private String telefono;
    private ClientDTO client;

    // Getters y setters
}