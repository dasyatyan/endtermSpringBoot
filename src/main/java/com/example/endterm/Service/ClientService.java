package com.example.endterm.Service;

import com.example.endterm.Model.Client;
import com.example.endterm.Repository.ClientRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final CartService cartService;


    @Autowired
    public ClientService(ClientRepository clientRepository, CartService cartService) {
        this.clientRepository = clientRepository;
        this.cartService = cartService;
    }

    public List<Client> findAllClients() {
        return clientRepository.findAll();
    }

    public Optional<Client> findClientById(Long id) {
        return clientRepository.findById(id);
    }

    public Client saveClient(Client client) {
        Client savedClient = clientRepository.save(client);
        cartService.createCartForClient(savedClient); // Создание корзины после сохранения клиента
        return savedClient;
    }

    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }
    @Transactional
    public Client createAndAssignCartToClient(Client client) {
        // Save the client to the database
        Client savedClient = clientRepository.save(client);
        // Create a new cart and associate it with the saved client
        cartService.createCartForClient(savedClient);
        // Return the saved client
        return savedClient;
    }


}