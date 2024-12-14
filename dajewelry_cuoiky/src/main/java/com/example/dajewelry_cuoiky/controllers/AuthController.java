package com.example.dajewelry_cuoiky.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;
import com.example.dajewelry_cuoiky.Entity.User;
import com.example.dajewelry_cuoiky.dto.UserDto;
import com.example.dajewelry_cuoiky.Service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuthController {
    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/home")
    public String home() {
        return "home";  // Trả về trang home cho người dùng đã đăng nhập
    }

    // Hiển thị trang đăng ký
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("user", userDto);
        return "jewelry/register";
    }

    // Xử lý đăng ký tài khoản
    @PostMapping("/register/save")
    public String registerUser(@Valid @ModelAttribute("user") UserDto userDto, BindingResult result, Model model) {
        // Kiểm tra email đã tồn tại chưa
        User existingUser = userService.findUserByEmail(userDto.getEmail());

        if (existingUser != null) {
            result.rejectValue("email", null, "Email này đã được đăng ký.");
        }

        // Nếu có lỗi trong form, hiển thị lại trang đăng ký
        if (result.hasErrors()) {
            return "jewelry/register";
        }

        // Lưu người dùng mới
        userService.saveUser(userDto);

        // Chuyển hướng về trang đăng nhập sau khi đăng ký thành công
        return "redirect:/login?success";
    }

    // Hiển thị danh sách người dùng (chỉ dành cho admin)
    @GetMapping("/users")
    public String users(Model model) {
        List<UserDto> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "jewelry/users";
    }

    // Hiển thị trang đăng nhập
    @GetMapping("/login")
    public String login() {
        return "jewelry/login";
    }
}
