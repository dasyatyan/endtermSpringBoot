package com.example.endterm.Controller;

import com.example.endterm.Model.Client;
import com.example.endterm.Service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ClientViewController {

    private final ClientService clientService;

    @Autowired
    public ClientViewController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/clients/view")
    public String clientsView(Model model) {
        List<Client> clients = clientService.findAllClients();
        model.addAttribute("clients", clients);
        model.addAttribute("client", new Client());
        return "clients"; // This should match the name of the HTML template
    }
}