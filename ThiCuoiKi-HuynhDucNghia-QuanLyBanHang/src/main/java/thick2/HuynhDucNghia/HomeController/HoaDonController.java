package thick2.HuynhDucNghia.HomeController;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import jakarta.servlet.http.HttpServletResponse;
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
    
    @GetMapping("/pdf/{id}")
    public void xuatPDF(@PathVariable Integer id, HttpServletResponse response, HttpSession session) throws Exception {
        if (chuaDangNhap(session)) return;
        
        HoaDon hd = hoaDonService.getById(id);
        if (hd == null) return;

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=HoaDon_" + id + ".pdf");

        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();

        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20);
        Paragraph title = new Paragraph("HOA DON MUA HANG - ELECTROSTORE", fontTitle);
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);
        document.add(new Paragraph(" "));

        document.add(new Paragraph("Ma hoa don: HD" + hd.getMaHoaDon()));
        document.add(new Paragraph("Khach hang: " + (hd.getTenKhachHang() != null ? hd.getTenKhachHang() : "Khach le")));
        document.add(new Paragraph("So dien thoai: " + (hd.getSoDienThoai() != null ? hd.getSoDienThoai() : "Khong co")));
        document.add(new Paragraph("Ngay lap: " + hd.getNgayLap().toString()));
        document.add(new Paragraph("Nhan vien: " + (hd.getNguoiDung() != null ? hd.getNguoiDung().getHoTen() : "He thong")));
        document.add(new Paragraph(" "));

        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100);
        table.setSpacingBefore(10f);
        
        table.addCell("Ten san pham");
        table.addCell("Don gia (VND)");
        table.addCell("So luong");
        table.addCell("Thanh tien (VND)");

        for (var ct : hd.getChiTiet()) {
            table.addCell(ct.getHangHoa().getTenHangHoa());
            table.addCell(String.format("%,.0f", ct.getDonGia()));
            table.addCell(String.valueOf(ct.getSoLuong()));
            table.addCell(String.format("%,.0f", ct.getThanhTien()));
        }
        document.add(table);

        document.add(new Paragraph(" "));
        Font fontTotal = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16);
        Paragraph total = new Paragraph("TONG CONG: " + String.format("%,.0f", hd.getTongTien()) + " VND", fontTotal);
        total.setAlignment(Element.ALIGN_RIGHT);
        document.add(total);
        
        document.add(new Paragraph(" "));
        document.add(new Paragraph(" "));

        PdfPTable signTable = new PdfPTable(2);
        signTable.setWidthPercentage(100);

        PdfPCell cellBuyer = new PdfPCell();
        cellBuyer.setBorder(0); // Ẩn viền
        cellBuyer.setHorizontalAlignment(Element.ALIGN_CENTER);
        Paragraph pBuyer = new Paragraph("Nguoi mua hang\n(Ky, ghi ro ho ten)", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12));
        pBuyer.setAlignment(Element.ALIGN_CENTER);
        cellBuyer.addElement(pBuyer);
        Paragraph spaceBuyer = new Paragraph("\n\n\n\n" + (hd.getTenKhachHang() != null ? hd.getTenKhachHang() : ""));
        spaceBuyer.setAlignment(Element.ALIGN_CENTER);
        cellBuyer.addElement(spaceBuyer);

        PdfPCell cellSeller = new PdfPCell();
        cellSeller.setBorder(0); 
        cellSeller.setHorizontalAlignment(Element.ALIGN_CENTER);
        Paragraph pSeller = new Paragraph("Nhan vien lap phieu\n(Ky, ghi ro ho ten)", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12));
        pSeller.setAlignment(Element.ALIGN_CENTER);
        cellSeller.addElement(pSeller);
        String tenNV = hd.getNguoiDung() != null ? hd.getNguoiDung().getHoTen() : "He thong";
        Paragraph spaceSeller = new Paragraph("\n\n\n\n" + tenNV);
        spaceSeller.setAlignment(Element.ALIGN_CENTER);
        cellSeller.addElement(spaceSeller);

        signTable.addCell(cellBuyer);
        signTable.addCell(cellSeller);

        document.add(signTable);

        document.close();
    }
    
    
}
