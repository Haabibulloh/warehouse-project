package uz.pdp.appwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.entity.Client;
import uz.pdp.appwarehouse.entity.User;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.payload.UserDto;
import uz.pdp.appwarehouse.service.ClientService;
import uz.pdp.appwarehouse.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping
    public Result addUserController(@RequestBody UserDto userDto) {
        Result result = userService.addUserService(userDto);
        return result;
    }

    @GetMapping
    public List<User> getClientList() {
        List<User> userList = userService.getUserListService();
        return userList;
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Integer id) {
        User user = userService.getUserByIdService(id);
        return user;
    }

    @DeleteMapping("/{id}")
    public Result deleteUser(@PathVariable Integer id) {
        Result result = userService.deleteUserService(id);
        return result;
    }

    @PutMapping("/{id}")
    public Result updateUser(@RequestBody UserDto userDto, @PathVariable Integer id) {
        Result result = userService.updateUserService(userDto, id);
        return result;
    }
}
