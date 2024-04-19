package com.github.odyn666.appSchool.controller.auth;

import com.github.odyn666.appSchool.controller.TrainerController;
import com.github.odyn666.appSchool.dto.auth.TrainerRegistrationDto;
import com.github.odyn666.appSchool.entity.TrainerEntity;
import com.github.odyn666.appSchool.service.TrainerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
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
@Slf4j
public class AuthController {

    private final TrainerController trainerController;
    private final TrainerService trainerService;

    @GetMapping("/trainer/register")
    public String registerTrainer(Model model) {
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
    public String registration(@Valid @ModelAttribute("trainer") TrainerRegistrationDto dto
            , BindingResult result
            , Model model) {

        try {
            TrainerEntity trainerByEmail = trainerController.getTrainerByEmail(dto.getEmail()).getBody();

            if (trainerByEmail != null && trainerByEmail.getEmail() != null && !trainerByEmail.getEmail().isEmpty()) {
                result.rejectValue("email", null,
                        "There is already an account registered with the same email");
            }
        } catch (Exception e) {
        }

        if (result.hasErrors()) {
            model.addAttribute("trainer", dto);
            log.error(result.getAllErrors().toString());
            return "redirect:/trainer/register?failed";
        }

        trainerController.createTrainer(dto);
        return "redirect:/trainer/register?success";
    }



    private Boolean isMatchingPassword(String password, String matchingPassword) {
        return password.equals(matchingPassword);
    }

}