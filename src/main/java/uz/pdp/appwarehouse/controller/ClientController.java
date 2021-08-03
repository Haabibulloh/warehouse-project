package uz.pdp.appwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.entity.Category;
import uz.pdp.appwarehouse.entity.Client;
import uz.pdp.appwarehouse.payload.CategoryDto;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.service.CategoryService;
import uz.pdp.appwarehouse.service.ClientService;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {
    @Autowired
    ClientService clientService;

    @PostMapping
    public Result addClientController(@RequestBody Client client) {
        Result result = clientService.addClientService(client);
        return result;
    }

    @GetMapping
    public List<Client> getClientList() {
        List<Client> clientList = clientService.getClientListService();
        return clientList;
    }

    @GetMapping("/{id}")
    public Client getClientById(@PathVariable Integer id) {
        Client client = clientService.getClientByIdService(id);
        return client;
    }

    @DeleteMapping("/{id}")
    public Result deleteClient(@PathVariable Integer id) {
        Result result = clientService.deleteClientService(id);
        return result;
    }

    @PutMapping("/{id}")
    public Result updateClient(@RequestBody Client client, @PathVariable Integer id) {
        Result result = clientService.updateClientService(id, client);
        return result;
    }
}
