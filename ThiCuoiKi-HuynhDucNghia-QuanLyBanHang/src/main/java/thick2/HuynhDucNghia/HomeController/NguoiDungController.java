package thick2.HuynhDucNghia.HomeController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import thick2.HuynhDucNghia.Model.NguoiDung;
import thick2.HuynhDucNghia.Service.NguoiDungService;

@Controller
@RequestMapping("/nhanvien")
public class NguoiDungController {

    @Autowired private NguoiDungService service;

    private boolean chuaDangNhap(HttpSession s) { return s.getAttribute("user") == null; }

    @GetMapping
    public String list(@RequestParam(value = "keyword", required = false) String keyword,
                       HttpSession session, Model model) {
        if (chuaDangNhap(session)) return "redirect:/login";
        
        model.addAttribute("danhSach", service.search(keyword));
        model.addAttribute("keyword", keyword);
        return "nguoidung/list";
    }

    @GetMapping("/them")
    public String themForm(HttpSession session, Model model) {
        if (chuaDangNhap(session)) return "redirect:/login";
        NguoiDung user = (NguoiDung) session.getAttribute("user");
        if (user == null || !"Admin".equals(user.getVaiTro())) {
            return "redirect:/"; 
        }
        model.addAttribute("nhanVien", new NguoiDung());
        model.addAttribute("tieuDe", "Thêm nhân viên mới");
        return "nguoidung/form";
    }

    @GetMapping("/sua/{id}")
    public String suaForm(@PathVariable Integer id, HttpSession session, Model model) {
        if (chuaDangNhap(session)) return "redirect:/login";
        NguoiDung nd = service.getById(id);
        if (nd == null) return "redirect:/nhanvien";
        model.addAttribute("nhanVien", nd);
        model.addAttribute("tieuDe", "Sửa thông tin nhân viên");
        return "nguoidung/form";
    }

    @PostMapping("/luu")
    public String luu(@Valid @ModelAttribute("nhanVien") NguoiDung nd,
                      BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("tieuDe", nd.getMaNguoiDung() == null ? "Thêm nhân viên mới" : "Sửa thông tin nhân viên");
            return "nguoidung/form";
        }
        service.save(nd);
        return "redirect:/nhanvien";
    }

    @GetMapping("/xoa/{id}")
    public String xoa(@PathVariable Integer id) {
        service.delete(id);
        return "redirect:/nhanvien";
    }
}