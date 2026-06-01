package thick2.HuynhDucNghia.HomeController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import jakarta.servlet.http.HttpSession;

import thick2.HuynhDucNghia.Service.HangHoaService;

@Controller
public class ControllerHome {
    
    @Autowired 
    private HangHoaService hangHoaService;

    // ---- Trang chu ----
    @GetMapping("/")
    public String home(HttpSession session, Model model) {
        // 1. Lấy dữ liệu thật của Hàng hóa
        int soHangHoa = hangHoaService.getAll().size();
        model.addAttribute("soHangHoa", soHangHoa);
        
        // 2. Truyền số 0 giả định cho Hóa đơn để tránh lỗi giao diện
        model.addAttribute("soHoaDon", 0);
        model.addAttribute("doanhThu", 0); 
        
        return "index";
    }
}