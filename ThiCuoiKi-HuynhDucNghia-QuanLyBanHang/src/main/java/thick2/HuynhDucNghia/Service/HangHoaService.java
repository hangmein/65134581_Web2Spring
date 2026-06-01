package thick2.HuynhDucNghia.Service;


import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import thick2.HuynhDucNghia.Model.HangHoa;
import thick2.HuynhDucNghia.Repo.HangHoaRepository;

@Service
public class HangHoaService {

    @Autowired private HangHoaRepository repo;

    public List<HangHoa> getAll() { return repo.findAll(); }
    public HangHoa getById(Integer id) { return repo.findById(id).orElse(null); }
    public void save(HangHoa hh) { repo.save(hh); }
    public void delete(Integer id) { repo.deleteById(id); }

    public List<HangHoa> search(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) return getAll();
        return repo.findByTenHangHoaContainingIgnoreCase(keyword.trim());
    }

    public List<HangHoa> loc(String keyword, Integer maLoaiHang) {
        boolean coKey = keyword != null && !keyword.trim().isEmpty();
        boolean coLoai = maLoaiHang != null;
        if (coKey && coLoai) {
            return repo.findByTenHangHoaContainingIgnoreCase(keyword.trim()).stream()
                    .filter(h -> h.getLoaiHang() != null
                            && h.getLoaiHang().getMaLoaiHang().equals(maLoaiHang))
                    .toList();
        }
        if (coKey) return repo.findByTenHangHoaContainingIgnoreCase(keyword.trim());
        if (coLoai) return repo.findByLoaiHang_MaLoaiHang(maLoaiHang);
        return getAll();
    }

    public List<HangHoa> hangSapHet(int nguong) {
        return getAll().stream().filter(h -> h.getSoLuongTon() <= nguong).toList();
    }

    public BigDecimal giaTriTonKho() {
        return getAll().stream()
                .map(h -> h.getDonGia().multiply(BigDecimal.valueOf(h.getSoLuongTon())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
