package uz.pdp.appwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.entity.Client;
import uz.pdp.appwarehouse.entity.Supplier;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.service.ClientService;
import uz.pdp.appwarehouse.service.SupplierService;

import java.util.List;

@RestController
@RequestMapping("/supplier")
public class SupplierController {
    @Autowired
    SupplierService supplierService;

    @PostMapping
    public Result addSupplierController(@RequestBody Supplier supplier) {
        Result result = supplierService.addSupplierService(supplier);
        return result;
    }

    @GetMapping
    public List<Supplier> getSupplierList() {
        List<Supplier> supplierList = supplierService.getSupplierListService();
        return supplierList;
    }

    @GetMapping("/{id}")
    public Supplier getSupplierById(@PathVariable Integer id) {
        Supplier supplier = supplierService.getSupplierByIdService(id);
        return supplier;
    }

    @DeleteMapping("/{id}")
    public Result deleteSupplier(@PathVariable Integer id) {
        Result result = supplierService.deleteSupplierService(id);
        return result;
    }

    @PutMapping("/{id}")
    public Result updateSupplier(@RequestBody Supplier supplier, @PathVariable Integer id) {
        Result result = supplierService.updateSupplierService(id, supplier);
        return result;
    }
}
