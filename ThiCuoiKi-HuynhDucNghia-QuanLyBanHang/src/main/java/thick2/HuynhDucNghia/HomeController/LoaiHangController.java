package thick2.HuynhDucNghia.HomeController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import thick2.HuynhDucNghia.Model.LoaiHang;
import thick2.HuynhDucNghia.Service.LoaiHangService;

@Controller
@RequestMapping("/loaihang")
public class LoaiHangController {

    @Autowired private LoaiHangService service;

    private boolean chuaDangNhap(HttpSession s) { return s.getAttribute("user") == null; }

    @GetMapping
    public String list(HttpSession session, Model model) {
        if (chuaDangNhap(session)) return "redirect:/login";
        model.addAttribute("danhSach", service.getAll());
        return "loaihang/list";
    }

    @GetMapping("/them")
    public String themForm(HttpSession session, Model model) {
        if (chuaDangNhap(session)) return "redirect:/login";
        model.addAttribute("loaiHang", new LoaiHang());
        model.addAttribute("tieuDe", "Them loai hang");
        return "loaihang/form";
    }

    @GetMapping("/sua/{id}")
    public String suaForm(@PathVariable Integer id, HttpSession session, Model model) {
        if (chuaDangNhap(session)) return "redirect:/login";
        LoaiHang lh = service.getById(id);
        if (lh == null) return "redirect:/loaihang";
        model.addAttribute("loaiHang", lh);
        model.addAttribute("tieuDe", "Sua loai hang");
        return "loaihang/form";
    }

    @PostMapping("/luu")
    public String luu(@Valid @ModelAttribute("loaiHang") LoaiHang loaiHang,
                      BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("tieuDe", loaiHang.getMaLoaiHang() == null ? "Them loai hang" : "Sua loai hang");
            return "loaihang/form";
        }
        service.save(loaiHang);
        return "redirect:/loaihang";
    }

    @GetMapping("/xoa/{id}")
    public String xoa(@PathVariable Integer id) {
        service.delete(id);
        return "redirect:/loaihang";
    }
}
