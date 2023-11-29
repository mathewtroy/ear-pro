package cz.cvut.kbss.ear.eshop.rest;

import cz.cvut.kbss.ear.eshop.model.Book;
import cz.cvut.kbss.ear.eshop.model.Client;
import cz.cvut.kbss.ear.eshop.model.Server;
import cz.cvut.kbss.ear.eshop.service.ServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/server")
public class ServerController {

    private final ServerService serverService;

    @Autowired
    public ServerController(ServerService serverService) {
        this.serverService = serverService;
    }
    @GetMapping
    public ResponseEntity<List<Server>> getAllBooks() {
        return ResponseEntity.ok(serverService.findAll());
    }

    @PostMapping
    public ResponseEntity<Server> insertServer(@RequestBody Server server) {
        serverService.persist(server);
        return ResponseEntity.ok(server);
    }
}
