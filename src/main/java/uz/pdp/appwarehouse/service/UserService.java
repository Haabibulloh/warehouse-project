package uz.pdp.appwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import uz.pdp.appwarehouse.entity.Client;
import uz.pdp.appwarehouse.entity.Product;
import uz.pdp.appwarehouse.entity.User;
import uz.pdp.appwarehouse.entity.Warehouse;
import uz.pdp.appwarehouse.payload.ProductDto;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.payload.UserDto;
import uz.pdp.appwarehouse.repository.ClientRepository;
import uz.pdp.appwarehouse.repository.UserRepository;
import uz.pdp.appwarehouse.repository.WarehouseRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    WarehouseRepository warehouseRepository;

    public Result addUserService(UserDto userDto) {
        boolean existsByPhoneNumber = userRepository.existsByPhoneNumber(userDto.getPhoneNumber());
        if (existsByPhoneNumber) {
            return new Result("This User already exist", false);
        }

        List<Warehouse> warehouses = warehouseRepository.findAllById(userDto.getWarehouseListId());

        HashSet<Warehouse> warehouseHashSet = new HashSet<>();
        for (Warehouse warehouse : warehouses) {
            warehouseHashSet.add(warehouse);
        }

        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setCode(userDto.getCode());
        user.setPassword(userDto.getPassword());
        user.setWarehouses(warehouseHashSet);
        userRepository.save(user);

        return new Result("User successfully saved", true);
    }

    public List<User> getUserListService() {
        List<User> userList = userRepository.findAll();
        return userList;
    }

    public User getUserByIdService(Integer id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent())
            return new User();
        User user = optionalUser.get();
        return user;
    }

    public Result deleteUserService(Integer id) {
        userRepository.deleteById(id);
        return new Result("User successfully deleted", true);
    }

    public Result updateUserService(UserDto userDto, @PathVariable Integer id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent())
            return new Result("This User doesn't exist", false);

        List<Warehouse> warehouses = warehouseRepository.findAllById(userDto.getWarehouseListId());

        HashSet<Warehouse> warehouseHashSet = new HashSet<>();
        for (Warehouse warehouse : warehouses) {
            warehouseHashSet.add(warehouse);
        }

        User user = optionalUser.get();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setCode(userDto.getCode());
        user.setPassword(userDto.getPassword());
        user.setWarehouses(warehouseHashSet);

        userRepository.save(user);
        return new Result("Product successfully updated", true);
    }
}
