package com.tossclone.app.controller;

import com.tossclone.app.dto.UserDTO;
import com.tossclone.app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/signup")
    public String showSignupForm(Model model) {
        model.addAttribute("user",
                new UserDTO(
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null
                )
        );

        return "user/signup";
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute UserDTO userDTO, Model model) {
        model.addAttribute("user", userDTO);

        if (userService.isUserIdDuplicate(userDTO.userId())) {
            model.addAttribute("duplicatedId", true);
            return "user/signup";
        }

        if (userService.isEmailDuplicate(userDTO.email())) {
            model.addAttribute("duplicatedEmail", true);
            return "user/signup";
        }

        userService.saveUser(userDTO);

        return "redirect:/";
    }

    @GetMapping("/login")
    String showLoginForm() {
        return "user/login";
    }

    @PostMapping("/login")
    String successfulLogin() {
        return "redirect:/";
    }
}
