package uz.pdp.appwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.entity.Client;
import uz.pdp.appwarehouse.entity.Warehouse;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.repository.ClientRepository;
import uz.pdp.appwarehouse.repository.WarehouseRepository;

import java.util.List;
import java.util.Optional;

@Service
public class WarehouseService {

    @Autowired
    WarehouseRepository warehouseRepository;

    public Result addWarehouseService(Warehouse warehouse) {
        Warehouse newWarehouse = new Warehouse();
        newWarehouse.setName(warehouse.getName());
        warehouseRepository.save(newWarehouse);
        return new Result("Warehouse successfully added", true);
    }

    public List<Warehouse> getWarehouseListService() {
        List<Warehouse> warehouseList = warehouseRepository.findAll();
        return warehouseList;
    }

    public Warehouse getWarehouseByIdService(Integer id) {
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(id);
        if (!optionalWarehouse.isPresent())
            return new Warehouse();
        Warehouse warehouse = optionalWarehouse.get();
        return warehouse;
    }

    public Result deleteWarehouseService(Integer id) {
        warehouseRepository.deleteById(id);
        return new Result("Warehouse successfully deleted", true);
    }

    public Result updateWarehouseService(Integer id, Warehouse warehouse) {
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(id);
        if (!optionalWarehouse.isPresent())
            return new Result("Warehouse not found", false);
        Warehouse editWarehouse = optionalWarehouse.get();
        editWarehouse.setName(warehouse.getName());
        warehouseRepository.save(editWarehouse);
        return new Result("Warehouse successfully updated", true);
    }
}
