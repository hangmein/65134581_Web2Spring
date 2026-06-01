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


}
