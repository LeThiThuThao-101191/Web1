package com.example.dajewelry_cuoiky.Service;
import com.example.dajewelry_cuoiky.dto.UserDto;
import com.example.dajewelry_cuoiky.Entity.User;
import java.util.List;
public interface UserService {
    void saveUser(UserDto userDto);
    User findUserByEmail(String email);
    List<UserDto> findAllUsers();

}
