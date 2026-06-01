package thick2.HuynhDucNghia.HomeController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
import thick2.HuynhDucNghia.Model.HoaDon;
import thick2.HuynhDucNghia.Model.NguoiDung;
import thick2.HuynhDucNghia.Service.HangHoaService;
import thick2.HuynhDucNghia.Service.HoaDonService;

@Controller
@RequestMapping("/hoadon")
public class HoaDonController {

    @Autowired private HoaDonService hoaDonService;
    @Autowired private HangHoaService hangHoaService;

    private boolean chuaDangNhap(HttpSession s) { return s.getAttribute("user") == null; }

    @GetMapping
    public String list(HttpSession session, Model model) {
        if (chuaDangNhap(session)) return "redirect:/login";
        model.addAttribute("danhSach", hoaDonService.getAll());
        return "hoadon/list";
    }

    @GetMapping("/them")
    public String themForm(HttpSession session, Model model) {
        if (chuaDangNhap(session)) return "redirect:/login";
        model.addAttribute("dsHangHoa", hangHoaService.getAll());
        return "hoadon/form";
    }

    @PostMapping("/luu")
    public String luu(@RequestParam(required = false) String tenKhachHang,
                      @RequestParam(required = false) String soDienThoai,
                      @RequestParam(name = "maHangHoa", required = false) List<Integer> maHangHoa,
                      @RequestParam(name = "soLuong", required = false) List<Integer> soLuong,
                      HttpSession session, RedirectAttributes ra) {
        NguoiDung user = (NguoiDung) session.getAttribute("user");
        if (user == null) return "redirect:/login";
        try {
            HoaDon hd = hoaDonService.lapHoaDon(user, tenKhachHang, soDienThoai, maHangHoa, soLuong);
            ra.addFlashAttribute("thanhCong", "Lap hoa don thanh cong!");
            return "redirect:/hoadon/" + hd.getMaHoaDon();
        } catch (IllegalArgumentException ex) {
            ra.addFlashAttribute("loi", ex.getMessage());
            return "redirect:/hoadon/them";
        }
    }

    @GetMapping("/{id}")
    public String chiTiet(@PathVariable Integer id, HttpSession session, Model model) {
        if (chuaDangNhap(session)) return "redirect:/login";
        HoaDon hd = hoaDonService.getById(id);
        if (hd == null) return "redirect:/hoadon";
        model.addAttribute("hoaDon", hd);
        return "hoadon/detail";
    }

    @GetMapping("/xoa/{id}")
    public String xoa(@PathVariable Integer id) {
        hoaDonService.xoaHoaDon(id);
        return "redirect:/hoadon";
    }
}
