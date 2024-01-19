package edu.tienda.core.controller;

import edu.tienda.core.domain.Client;
import edu.tienda.core.excceptions.BadRequestException;
import edu.tienda.core.excceptions.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientRestController {

    private final List<Client> clients = new ArrayList<>(Arrays.asList(
            new Client("leoso", "12345", "leo"),
            new Client("kath1", "12345", "kathe"),
            new Client("sebscam", "12345", "sebas"))
    );

    @GetMapping
    public ResponseEntity<?> getClients() {
        return ResponseEntity.ok(clients);
    }

    @GetMapping("/{userName}")
    public ResponseEntity<?> getClient(@PathVariable String userName) {
        if(userName.length() <=3){
            throw new BadRequestException("El nombre de usuario debe contener mas de 3 caracteres.");
        }
        return clients.stream().
                filter(client -> client.getUserName().equalsIgnoreCase(userName)).
                findFirst().
                map(ResponseEntity::ok).
                orElseThrow(() -> new ResourceNotFoundException("Cliente "+userName+" no encontrado"));
    }

    @PostMapping
    public ResponseEntity<?> insertClient(@RequestBody Client client) {
        clients.add(client);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{username}")
                .buildAndExpand(client.getUserName())
                .toUri();
        return ResponseEntity.created(location).body(client);
    }

    @PutMapping
    public ResponseEntity<?> modifyClient(@RequestBody Client client) {
        Client clientTemp = clients.stream().filter(client1 -> client1.getUserName().equalsIgnoreCase(client.getUserName())).
                findFirst().orElseThrow();
        clientTemp.setPassword(client.getPassword());
        clientTemp.setName(client.getName());
        return ResponseEntity.ok(clientTemp);
    }

    @DeleteMapping("/{userName}")
    public ResponseEntity deleteClient(@PathVariable String userName){
        Client clientTemp = clients.stream().
                filter(client -> client.getUserName().equalsIgnoreCase(userName)).
                findFirst().orElseThrow();
        clients.remove(clientTemp);
        return ResponseEntity.noContent().build();
    }

}
