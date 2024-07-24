package com.tossclone.app.controller;

import com.tossclone.app.dto.UserDTO;
import com.tossclone.app.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
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

    @GetMapping("/user/info")
    String showUserInfo(Model model, Authentication authentication) {
        String userId = authentication.getName();

        UserDTO userDTO = userService.findUserByUserId(userId).get();

        model.addAttribute("user", userDTO);

        return "user/info";
    }

    @GetMapping("/user/info/update")
    String showUserInfoEditForm(Model model, Authentication authentication) {
        String userId = authentication.getName();

        UserDTO userDTO = userService.findUserByUserId(userId).get();

        model.addAttribute("user", userDTO);

        return "user/update";
    }

    @PostMapping("/user/info/update")
    public String editUserInfo(@ModelAttribute UserDTO userDTO, Authentication authentication) {
        String userId = authentication.getName();
        userService.updateUser(userId, userDTO);

        return "redirect:/";
    }
}
