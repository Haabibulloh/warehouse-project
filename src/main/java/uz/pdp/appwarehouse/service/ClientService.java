package uz.pdp.appwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.entity.Category;
import uz.pdp.appwarehouse.entity.Client;
import uz.pdp.appwarehouse.payload.CategoryDto;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.repository.CategoryRepository;
import uz.pdp.appwarehouse.repository.ClientRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    ClientRepository clientRepository;

    public Result addClientService(Client client) {
        Client newClient = new Client();
        newClient.setName(client.getName());
        newClient.setPhoneNumber(client.getPhoneNumber());
        clientRepository.save(newClient);
        return new Result("Client successfully added", true);
    }

    public List<Client> getClientListService() {
        List<Client> clients = clientRepository.findAll();
        return clients;
    }

    public Client getClientByIdService(Integer id) {
        Optional<Client> optionalClient = clientRepository.findById(id);
        if (!optionalClient.isPresent())
            return new Client();
        Client client = optionalClient.get();
        return client;
    }

    public Result deleteClientService(Integer id) {
        clientRepository.deleteById(id);
        return new Result("Client successfully deleted", true);
    }

    public Result updateClientService(Integer id, Client client) {
        Optional<Client> optionalClient = clientRepository.findById(id);
        if (!optionalClient.isPresent())
            return new Result("Client not found", false);
        Client editClient = optionalClient.get();
        editClient.setName(client.getName());
        editClient.setPhoneNumber(client.getPhoneNumber());
        clientRepository.save(editClient);
        return new Result("Client successfully updated", true);
    }
}
