package thick2.HuynhDucNghia.Service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import thick2.HuynhDucNghia.Model.ChiTietHoaDon;
import thick2.HuynhDucNghia.Model.HangHoa;
import thick2.HuynhDucNghia.Model.HoaDon;
import thick2.HuynhDucNghia.Repo.ChiTietHoaDonRepository;
import thick2.HuynhDucNghia.Repo.HangHoaRepository;
import thick2.HuynhDucNghia.Repo.HoaDonRepository;

@Service
public class HoaDonService {

    @Autowired private HoaDonRepository hoaDonRepo;
    @Autowired private ChiTietHoaDonRepository chiTietRepo;
    @Autowired private HangHoaRepository hangHoaRepo;

    public List<HoaDon> getAll() { return hoaDonRepo.findAll(); }
    public HoaDon getById(Integer id) { return hoaDonRepo.findById(id).orElse(null); }

    public HoaDon taoHoaDon(HoaDon hd) {
        hd.setTongTien(BigDecimal.ZERO);
        return hoaDonRepo.save(hd);
    }

    public void xoaHoaDon(Integer id) {
        HoaDon hd = getById(id);
        if (hd == null) return;
        for (ChiTietHoaDon ct : hd.getChiTiet()) {
            HangHoa hh = ct.getHangHoa();
            hh.setSoLuongTon(hh.getSoLuongTon() + ct.getSoLuong());
            hangHoaRepo.save(hh);
        }
        hoaDonRepo.deleteById(id);
    }

    @Transactional
    public String themDong(Integer maHoaDon, Integer maHangHoa, int soLuong) {
        HoaDon hd = getById(maHoaDon);
        HangHoa hh = hangHoaRepo.findById(maHangHoa).orElse(null);
        if (hd == null || hh == null) return "Khong tim thay hoa don hoac hang hoa";
        if (soLuong <= 0) return "So luong phai lon hon 0";
        if (hh.getSoLuongTon() < soLuong) return "Khong du hang trong kho (con " + hh.getSoLuongTon() + ")";

        ChiTietHoaDon ct = new ChiTietHoaDon();
        ct.setHoaDon(hd);
        ct.setHangHoa(hh);
        ct.setSoLuong(soLuong);
        ct.setDonGia(hh.getDonGia());
        chiTietRepo.save(ct);
        hd.getChiTiet().add(ct);  

        hh.setSoLuongTon(hh.getSoLuongTon() - soLuong);
        hangHoaRepo.save(hh);
        capNhatTongTien(hd);
        return null; 
    }

    @Transactional
    public void xoaDong(Integer maChiTiet) {
        ChiTietHoaDon ct = chiTietRepo.findById(maChiTiet).orElse(null);
        if (ct == null) return;
        HangHoa hh = ct.getHangHoa();
        hh.setSoLuongTon(hh.getSoLuongTon() + ct.getSoLuong());
        hangHoaRepo.save(hh);

        HoaDon hd = ct.getHoaDon();
        chiTietRepo.delete(ct);
        hd.getChiTiet().removeIf(c -> c.getMaChiTiet().equals(maChiTiet));
        capNhatTongTien(hd);
    }

    private void capNhatTongTien(HoaDon hd) {
        BigDecimal tong = BigDecimal.ZERO;
        for (ChiTietHoaDon c : hd.getChiTiet()) {
            tong = tong.add(c.getThanhTien());
        }
        hd.setTongTien(tong);
        hoaDonRepo.save(hd);
    }
}
