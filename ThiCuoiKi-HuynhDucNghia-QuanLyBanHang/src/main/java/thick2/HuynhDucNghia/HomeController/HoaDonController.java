package thick2.HuynhDucNghia.HomeController;

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
        model.addAttribute("hoaDon", new HoaDon());
        return "hoadon/form";
    }

    @PostMapping("/luu")
    public String luu(@ModelAttribute HoaDon hoaDon, HttpSession session) {
        NguoiDung user = (NguoiDung) session.getAttribute("user");
        if (user == null) return "redirect:/login";
        hoaDon.setNguoiDung(user);
        HoaDon saved = hoaDonService.taoHoaDon(hoaDon);
        return "redirect:/hoadon/" + saved.getMaHoaDon();
    }

    @GetMapping("/{id}")
    public String chiTiet(@PathVariable Integer id, HttpSession session, Model model) {
        if (chuaDangNhap(session)) return "redirect:/login";
        HoaDon hd = hoaDonService.getById(id);
        if (hd == null) return "redirect:/hoadon";
        model.addAttribute("hoaDon", hd);
        model.addAttribute("dsHangHoa", hangHoaService.getAll());
        return "hoadon/detail";
    }

    @PostMapping("/{id}/themdong")
    public String themDong(@PathVariable Integer id,
                           @RequestParam Integer maHangHoa,
                           @RequestParam int soLuong,
                           RedirectAttributes ra) {
        String loi = hoaDonService.themDong(id, maHangHoa, soLuong);
        if (loi != null) ra.addFlashAttribute("loi", loi);
        return "redirect:/hoadon/" + id;
    }
    @GetMapping("/{id}/xoadong/{maChiTiet}")
    public String xoaDong(@PathVariable Integer id, @PathVariable Integer maChiTiet) {
        hoaDonService.xoaDong(maChiTiet);
        return "redirect:/hoadon/" + id;
    }

    @GetMapping("/xoa/{id}")
    public String xoa(@PathVariable Integer id) {
        hoaDonService.xoaHoaDon(id);
        return "redirect:/hoadon";
    }
}
