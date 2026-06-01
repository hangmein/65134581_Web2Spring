package thick2.HuynhDucNghia.HomeController;


import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import thick2.HuynhDucNghia.Model.HoaDon;
import thick2.HuynhDucNghia.Model.NguoiDung;
import thick2.HuynhDucNghia.Service.HangHoaService;
import thick2.HuynhDucNghia.Service.HoaDonService;
import thick2.HuynhDucNghia.Service.NguoiDungService;

@Controller
public class ControllerHome {

    @Autowired private NguoiDungService nguoiDungService;
    @Autowired private HangHoaService hangHoaService;
    @Autowired private HoaDonService hoaDonService;

    // ---- Dang nhap ----
    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String tenDangNhap,
                        @RequestParam String matKhau,
                        HttpSession session, Model model) {
        NguoiDung nd = nguoiDungService.dangNhap(tenDangNhap, matKhau);
        if (nd == null) {
            model.addAttribute("loi", "Sai ten dang nhap hoac mat khau");
            return "login";
        }
        session.setAttribute("user", nd);  
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
    @GetMapping("/")
    public String home(HttpSession session, Model model) {
        if (session.getAttribute("user") == null) return "redirect:/login";

        int soHangHoa = hangHoaService.getAll().size();
        var dsHoaDon = hoaDonService.getAll();
        BigDecimal doanhThu = dsHoaDon.stream()
                .map(HoaDon::getTongTien)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        model.addAttribute("soHangHoa", soHangHoa);
        model.addAttribute("soHoaDon", dsHoaDon.size());
        model.addAttribute("doanhThu", doanhThu);
        return "index";
    }
}
