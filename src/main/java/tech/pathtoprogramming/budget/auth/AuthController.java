package tech.pathtoprogramming.budget.auth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import tech.pathtoprogramming.budget.model.ExchangeRequest;

import javax.servlet.http.HttpServletResponse;

@RestController
@Slf4j
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/")
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index.html");
        return modelAndView;
    }

    @GetMapping("/create-link-token")
    public String link() throws Exception {
        return authService.getLinkToken();
    }

    @PostMapping("/exchange-token")
    public void access(HttpServletResponse response, @RequestBody ExchangeRequest exchangeRequest, Model model) throws Exception {
        String accessToken = authService.getAccessToken(exchangeRequest.publicToken());
        model.addAttribute("accessToken", accessToken);
        response.sendRedirect("/transactions");
    }

}
