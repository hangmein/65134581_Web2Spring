package thick2.HuynhDucNghia.HomeController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import thick2.HuynhDucNghia.Model.HangHoa;
import thick2.HuynhDucNghia.Model.HoaDon;
import thick2.HuynhDucNghia.Model.LoaiHang;
import thick2.HuynhDucNghia.Model.NguoiDung;
import thick2.HuynhDucNghia.Service.HangHoaService;
import thick2.HuynhDucNghia.Service.HoaDonService;
import thick2.HuynhDucNghia.Service.LoaiHangService;
import thick2.HuynhDucNghia.Service.NguoiDungService;
import thick2.HuynhDucNghia.Service.ThongKeLoaiHang;

@Controller
public class ControllerHome {

    @Autowired private NguoiDungService nguoiDungService;
    @Autowired private HangHoaService hangHoaService;
    @Autowired private HoaDonService hoaDonService;
    @Autowired private LoaiHangService loaiHangService;

    @GetMapping("/login")
    public String loginForm() { return "login"; }

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

        List<HangHoa> dsHangHoa = hangHoaService.getAll();
        List<HoaDon> dsHoaDon = hoaDonService.getAll();
        List<LoaiHang> dsLoai = loaiHangService.getAll();

        BigDecimal doanhThu = dsHoaDon.stream()
                .map(HoaDon::getTongTien).reduce(BigDecimal.ZERO, BigDecimal::add);
        model.addAttribute("soLoaiHang", dsLoai.size());
        model.addAttribute("soHangHoa", dsHangHoa.size());
        model.addAttribute("soHoaDon", dsHoaDon.size());
        model.addAttribute("doanhThu", doanhThu);
        model.addAttribute("giaTriTon", hangHoaService.giaTriTonKho());

        List<ThongKeLoaiHang> thongKe = new ArrayList<>();
        for (LoaiHang lh : dsLoai) {
            List<HangHoa> trongLoai = dsHangHoa.stream()
                    .filter(h -> h.getLoaiHang() != null
                            && h.getLoaiHang().getMaLoaiHang().equals(lh.getMaLoaiHang()))
                    .toList();
            int tongTon = trongLoai.stream().mapToInt(HangHoa::getSoLuongTon).sum();
            BigDecimal gt = trongLoai.stream()
                    .map(h -> h.getDonGia().multiply(BigDecimal.valueOf(h.getSoLuongTon())))
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            thongKe.add(new ThongKeLoaiHang(lh.getTenLoaiHang(), trongLoai.size(), tongTon, gt));
        }
        model.addAttribute("thongKeLoai", thongKe);
        model.addAttribute("hangSapHet", hangHoaService.hangSapHet(5));

        model.addAttribute("hoaDonGanNhat", hoaDonService.getHoaDonGanNhat());

        return "index";
    }
}