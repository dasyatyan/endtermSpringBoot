package com.example.endterm.Controller;

import com.example.endterm.Model.Client;
import com.example.endterm.Service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public List<Client> getAllClients() {
        return clientService.findAllClients();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable Long id) {
        return clientService.findClientById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public String createClient(@ModelAttribute Client client) {
        clientService.saveClient(client);
        return "redirect:/clients/view";
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable Long id, @RequestBody Client clientDetails) {
        return clientService.findClientById(id)
                .map(client -> {
                    client.setName(clientDetails.getName());
                    client.setSurname(clientDetails.getSurname());
                    client.setPhone(clientDetails.getPhone());
                    Client updatedClient = clientService.saveClient(client);
                    return ResponseEntity.ok(updatedClient);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        return clientService.findClientById(id)
                .map(client -> {
                    clientService.deleteClient(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}