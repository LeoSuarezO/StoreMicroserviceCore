package edu.tienda.core.controller;

import edu.tienda.core.domain.Client;
import org.springframework.web.bind.annotation.*;

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
    public List<Client> getClients() {
        return clients;
    }

    @GetMapping("/{userName}")
    public Client getClient(@PathVariable String userName) {
        return clients.stream().
                filter(client -> client.getUserName().equalsIgnoreCase(userName)).
                findFirst().orElseThrow();
    }

    @PostMapping
    public Client insertClient(@RequestBody Client client) {
        clients.add(client);
        return client;
    }

    @PutMapping
    public Client modifyClient(@RequestBody Client client) {
        Client clientTemp = clients.stream().filter(client1 -> client1.getUserName().equalsIgnoreCase(client.getUserName())).
                findFirst().orElseThrow();
        clientTemp.setPassword(client.getPassword());
        clientTemp.setName(client.getName());
        return clientTemp;
    }

    @DeleteMapping("/{userName}")
    public void deleteClient(@PathVariable String userName){
        Client clientTemp = clients.stream().
                filter(client -> client.getUserName().equalsIgnoreCase(userName)).
                findFirst().orElseThrow();
        clients.remove(clientTemp);
    }

}
