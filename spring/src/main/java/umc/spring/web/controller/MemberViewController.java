package umc.spring.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import umc.spring.service.UserService.UserCommandService;
import umc.spring.web.dto.UserRequestDTO;

@Controller
@RequiredArgsConstructor
public class MemberViewController {

    private final UserCommandService userCommandService;


    @PostMapping("/users/signup")
    public String joinMember(@ModelAttribute("user") UserRequestDTO.JoinDto request, // 협업시에는 기존 RequestBody 어노테이션을 붙여주시면 됩니다!
                             BindingResult bindingResult,
                             Model model) {
        if (bindingResult.hasErrors()) {
            // 뷰에 데이터 바인딩이 실패할 경우 signup 페이지를 유지합니다.
            return "signup";
        }

        try {
            userCommandService.joinUser(request);
            return "redirect:/login";
        } catch (Exception e) {
            // 회원가입 과정에서 에러가 발생할 경우 에러 메시지를 보내고, signup 페이디를 유지합니다.
            model.addAttribute("error", e.getMessage());
            return "signup";
        }
    }


    @GetMapping("/login")
    public String loginPage() {

        return "login";
    }

    @GetMapping("/signup")
    public String signupPage(Model model) {
        model.addAttribute("user", new UserRequestDTO.JoinDto());
        return "signup";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }
}