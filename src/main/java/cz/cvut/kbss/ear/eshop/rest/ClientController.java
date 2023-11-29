package cz.cvut.kbss.ear.eshop.rest;

import cz.cvut.kbss.ear.eshop.model.*;
import cz.cvut.kbss.ear.eshop.service.ClientService;
import cz.cvut.kbss.ear.eshop.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public ResponseEntity<List<Client>> getAllClients() {
        return ResponseEntity.ok(clientService.findAll());
    }
    @PostMapping
    public ResponseEntity<Client> insertClient(@RequestBody Client client) {
        clientService.persist(client);
        return ResponseEntity.ok(client);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateReservation(@PathVariable int id, @RequestBody Client client) {
        if (clientService.find(id) == null) {
            return ResponseEntity.notFound().build();
        }
        client.setID(id);
        clientService.update(client);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable int id) {
        Client client = clientService.find(id);
        if (client == null) {
            return ResponseEntity.notFound().build();
        }
        clientService.remove(client);
        return ResponseEntity.noContent().build();
    }

}
