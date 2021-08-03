package uz.pdp.appwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.entity.Client;
import uz.pdp.appwarehouse.entity.Supplier;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.repository.ClientRepository;
import uz.pdp.appwarehouse.repository.SupplierRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {

    @Autowired
    SupplierRepository supplierRepository;

    public Result addSupplierService(Supplier supplier) {
        Supplier newSupplier = new Supplier();
        newSupplier.setName(supplier.getName());
        newSupplier.setPhoneNumber(supplier.getPhoneNumber());
        supplierRepository.save(newSupplier);
        return new Result("Supplier successfully added", true);
    }

    public List<Supplier> getSupplierListService() {
        List<Supplier> suppliers = supplierRepository.findAll();
        return suppliers;
    }

    public Supplier getSupplierByIdService(Integer id) {
        Optional<Supplier> optionalSupplier = supplierRepository.findById(id);
        if (!optionalSupplier.isPresent())
            return new Supplier();
        Supplier supplier = optionalSupplier.get();
        return supplier;
    }

    public Result deleteSupplierService(Integer id) {
        supplierRepository.deleteById(id);
        return new Result("Supplier successfully deleted", true);
    }

    public Result updateSupplierService(Integer id, Supplier supplier) {
        Optional<Supplier> optionalSupplier = supplierRepository.findById(id);
        if (!optionalSupplier.isPresent())
            return new Result("Supplier not found", false);
        Supplier editSupplier = optionalSupplier.get();
        editSupplier.setName(supplier.getName());
        editSupplier.setPhoneNumber(supplier.getPhoneNumber());
        supplierRepository.save(editSupplier);
        return new Result("Supplier successfully updated", true);
    }
}
