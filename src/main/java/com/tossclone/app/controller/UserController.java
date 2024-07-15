package com.tossclone.app.controller;

import com.tossclone.app.dto.UserAccountDTO;
import com.tossclone.app.repository.UserAccountRepository;
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
    private final UserAccountRepository userAccountRepository;

    @GetMapping("/signup")
    public String showSignupForm(Model model) {
        model.addAttribute("user",
                new UserAccountDTO(
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
    public String signup(@ModelAttribute UserAccountDTO userAccountDTO, Model model) {
        model.addAttribute("user", userAccountDTO);

        if (userService.isUserIdDuplicate(userAccountDTO.userId())) {
            model.addAttribute("duplicatedId", true);
            return "user/signup";
        }

        if (userService.isEmailDuplicate(userAccountDTO.email())) {
            model.addAttribute("duplicatedEmail", true);
            return "user/signup";
        }

        userService.saveUser(userAccountDTO);

        return "redirect:/";
    }
}
