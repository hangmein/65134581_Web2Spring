package thick2.HuynhDucNghia.HomeController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import jakarta.servlet.http.HttpSession;

@Controller
public class ControllerHome {

    // ---- Trang chu ----
    @GetMapping("/")
    public String home(HttpSession session, Model model) {

        return "index";
    }
}
