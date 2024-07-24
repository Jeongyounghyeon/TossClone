package com.tossclone.app.controller;

import com.tossclone.app.dto.UserJoinDTO;
import com.tossclone.app.dto.UserUpdateDTO;
import com.tossclone.app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
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
                new UserJoinDTO(
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
    public String signup(@ModelAttribute UserJoinDTO userJoinDTO, Model model) {
        model.addAttribute("user", userJoinDTO);

        if (userService.isUserIdDuplicate(userJoinDTO.userId())) {
            model.addAttribute("duplicatedId", true);
            return "user/signup";
        }

        if (userService.isEmailDuplicate(userJoinDTO.email())) {
            model.addAttribute("duplicatedEmail", true);
            return "user/signup";
        }

        userService.saveUser(userJoinDTO);

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

        UserJoinDTO userJoinDTO = userService.findUserByUserId(userId).get();

        model.addAttribute("user", userJoinDTO);

        return "user/info";
    }

    @GetMapping("/user/info/update")
    String showUserInfoEditForm(Model model, Authentication authentication) {
        String userId = authentication.getName();

        UserJoinDTO userJoinDTO = userService.findUserByUserId(userId).get();

        model.addAttribute("user", userJoinDTO);

        return "user/update";
    }

    @PostMapping("/user/info/update")
    public String editUserInfo(@ModelAttribute UserUpdateDTO userUpdateDTO, Authentication authentication) {
        String userId = authentication.getName();
        userService.updateUser(userId, userUpdateDTO);

        return "redirect:/";
    }
}
