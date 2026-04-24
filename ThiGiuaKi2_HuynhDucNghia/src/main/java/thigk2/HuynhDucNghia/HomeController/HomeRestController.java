package thigk2.HuynhDucNghia.HomeController;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController; 

import thigk2.HuynhDucNghia.Model.SanPham;
import thigk2.HuynhDucNghia.Model.TheLoaiSanPham;
import thigk2.HuynhDucNghia.Service.TheLoaiService;

@RestController 
@RequestMapping("/api/san-pham") 
public class HomeRestController {

    @Autowired
    private TheLoaiService theLoaiService;
    @GetMapping("/the-loai")
    public List<TheLoaiSanPham> listTheLoai() {
        return theLoaiService.getAllTheLoai();
    }

    @GetMapping("/the-loai/{id}")
    public List<SanPham> listSanPhamByTheLoai(@PathVariable("id") Integer id) {
        TheLoaiSanPham tl = theLoaiService.getTheLoaiById(id);
        if (tl != null) {
            return tl.getDanhSachSanPham();
        }
        return null; 
    }
}