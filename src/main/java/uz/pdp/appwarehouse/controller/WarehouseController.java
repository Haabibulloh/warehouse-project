package uz.pdp.appwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.entity.Client;
import uz.pdp.appwarehouse.entity.Warehouse;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.service.ClientService;
import uz.pdp.appwarehouse.service.WarehouseService;

import java.util.List;

@RestController
@RequestMapping("/warehouse")
public class WarehouseController {
    @Autowired
    WarehouseService warehouseService;

    @PostMapping
    public Result addWarehouseController(@RequestBody Warehouse warehouse) {
        Result result = warehouseService.addWarehouseService(warehouse);
        return result;
    }

    @GetMapping
    public List<Warehouse> getWarehouseList() {
        List<Warehouse> warehouseList = warehouseService.getWarehouseListService();
        return warehouseList;
    }

    @GetMapping("/{id}")
    public Warehouse getWarehouseById(@PathVariable Integer id) {
        Warehouse warehouseById = warehouseService.getWarehouseByIdService(id);
        return warehouseById;
    }

    @DeleteMapping("/{id}")
    public Result deleteWarehouse(@PathVariable Integer id) {
        Result result = warehouseService.deleteWarehouseService(id);
        return result;
    }

    @PutMapping("/{id}")
    public Result updateWarehouse(@RequestBody Warehouse warehouse, @PathVariable Integer id) {
        Result result = warehouseService.updateWarehouseService(id, warehouse);
        return result;
    }
}
