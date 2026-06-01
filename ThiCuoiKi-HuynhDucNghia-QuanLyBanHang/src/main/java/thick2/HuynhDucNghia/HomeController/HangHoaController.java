package thick2.HuynhDucNghia.HomeController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import thick2.HuynhDucNghia.Model.HangHoa;
import thick2.HuynhDucNghia.Service.HangHoaService;
import thick2.HuynhDucNghia.Service.LoaiHangService;

@Controller
@RequestMapping("/hanghoa")
public class HangHoaController {

    @Autowired private HangHoaService service;
    @Autowired private LoaiHangService loaiHangService;

    private boolean chuaDangNhap(HttpSession s) { return s.getAttribute("user") == null; }

    @GetMapping
    public String list(@RequestParam(required = false) String keyword,
                       HttpSession session, Model model) {
        if (chuaDangNhap(session)) return "redirect:/login";
        model.addAttribute("danhSach", service.search(keyword));
        model.addAttribute("keyword", keyword);
        return "hanghoa/list";
    }

    @GetMapping("/them")
    public String themForm(HttpSession session, Model model) {
        if (chuaDangNhap(session)) return "redirect:/login";
        model.addAttribute("hangHoa", new HangHoa());
        model.addAttribute("dsLoai", loaiHangService.getAll());
        model.addAttribute("tieuDe", "Them hang hoa");
        return "hanghoa/form";
    }

    @GetMapping("/sua/{id}")
    public String suaForm(@PathVariable Integer id, HttpSession session, Model model) {
        if (chuaDangNhap(session)) return "redirect:/login";
        HangHoa hh = service.getById(id);
        if (hh == null) return "redirect:/hanghoa";
        model.addAttribute("hangHoa", hh);
        model.addAttribute("dsLoai", loaiHangService.getAll());
        model.addAttribute("tieuDe", "Sua hang hoa");
        return "hanghoa/form";
    }

    @PostMapping("/luu")
    public String luu(@Valid @ModelAttribute("hangHoa") HangHoa hangHoa,
                      BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("dsLoai", loaiHangService.getAll());
            model.addAttribute("tieuDe", hangHoa.getMaHangHoa() == null ? "Them hang hoa" : "Sua hang hoa");
            return "hanghoa/form";
        }
        service.save(hangHoa);
        return "redirect:/hanghoa";
    }

    @GetMapping("/xoa/{id}")
    public String xoa(@PathVariable Integer id) {
        service.delete(id);
        return "redirect:/hanghoa";
    }
}
