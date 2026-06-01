package thick2.HuynhDucNghia.HomeController;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {


    // ----  Dashboard ----
    @GetMapping("/")
    public String home(HttpSession session, Model model) {

        return "index";
    }
}
