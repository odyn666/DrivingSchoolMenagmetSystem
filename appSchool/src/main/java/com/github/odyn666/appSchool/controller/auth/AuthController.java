package com.github.odyn666.appSchool.controller.auth;

import com.github.odyn666.appSchool.dto.auth.TrainerRegistrationDto;
import com.github.odyn666.appSchool.service.TrainerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

@Controller
@RequiredArgsConstructor
@RequestMapping
public class AuthController {

    private final TrainerService trainerService;

    @GetMapping("/trainer/register")
    public String registerTrainer(WebRequest request, Model model) {
        TrainerRegistrationDto registrationDto = new TrainerRegistrationDto();
        model.addAttribute("trainer", registrationDto);

//        if (isMatchingPassowrd(registrationDto.getPassword(),registrationDto.getMatchingPassword()))
//        {
//            return "redirect:/trainer/register";
//        }
        return "trainerRegistration";
    }

    @GetMapping
    public String homePage() {
        return "index";
    }

    @PostMapping("/register/save")
    public String registration(@Valid@ModelAttribute("trainer")TrainerRegistrationDto dto
    , BindingResult result
    , Model model)
    {
        return "redirect:/trainer/register?success";
    }

    private Boolean isMatchingPassword(String password, String matchingPassword) {
        return password.equals(matchingPassword);
    }

}