package uz.pdp.appwarehouse.payload;

import lombok.Data;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

@Data
public class UserDto {
    private String firstName;
    private String lastName;
    private  String phoneNumber;
    private String code;
    private String password;
    private List<Integer> warehouseListId;
}
