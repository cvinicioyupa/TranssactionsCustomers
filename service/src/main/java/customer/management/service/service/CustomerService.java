package customer.management.service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import customer.management.service.model.Cliente;
import customer.management.service.model.Persona;
import customer.management.service.repository.ClienteRepository;
import customer.management.service.repository.PersonaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PersonaRepository personaRepository;

    public List<Persona> getAllPersonsWithClients() {
        // Fetch all persons; the client data is lazily loaded due to JPA relationships
        return personaRepository.findAll();
    }

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> findById(int id) {
        return clienteRepository.findById(id);
    }

    public Cliente save(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public void deleteById(int id) {
        clienteRepository.deleteById(id);
    }

    public Cliente updateCliente(int id, Cliente clienteDetails) {
        Optional<Cliente> clienteOptional = clienteRepository.findById(id);
        if (clienteOptional.isPresent()) {
            Cliente cliente = clienteOptional.get();
            Persona persona = cliente.getPersona();

            // Actualizar los datos de Persona
            persona.setNombre(clienteDetails.getPersona().getNombre());
            persona.setGenero(clienteDetails.getPersona().getGenero());
            persona.setDireccion(clienteDetails.getPersona().getDireccion());
            persona.setTelefono(clienteDetails.getPersona().getTelefono());
            personaRepository.save(persona);

            // Actualizar los datos de Cliente
            cliente.setContraseña(clienteDetails.getContraseña());
            cliente.setEstado(clienteDetails.isEstado());
            return clienteRepository.save(cliente);
        } else {
            return null; // O lanzar una excepción si el cliente no existe
        }
    }
}