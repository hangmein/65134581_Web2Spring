package thick2.HuynhDucNghia.Service;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import thick2.HuynhDucNghia.Model.ChiTietHoaDon;
import thick2.HuynhDucNghia.Model.HangHoa;
import thick2.HuynhDucNghia.Model.HoaDon;
import thick2.HuynhDucNghia.Model.NguoiDung;
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

    @Transactional
    public HoaDon lapHoaDon(NguoiDung nv, String tenKH, String sdt,
                            List<Integer> dsMaHH, List<Integer> dsSL) {
        if (dsMaHH == null || dsMaHH.isEmpty())
            throw new IllegalArgumentException("Phieu phai co it nhat 1 san pham");

        Map<Integer, Integer> gom = new LinkedHashMap<>();
        for (int i = 0; i < dsMaHH.size(); i++) {
            Integer mh = dsMaHH.get(i);
            Integer sl = (dsSL != null && i < dsSL.size()) ? dsSL.get(i) : null;
            if (mh == null || sl == null || sl <= 0) continue;
            gom.merge(mh, sl, Integer::sum);
        }
        if (gom.isEmpty())
            throw new IllegalArgumentException("Phieu chua co san pham hop le");

        for (Map.Entry<Integer, Integer> e : gom.entrySet()) {
            HangHoa hh = hangHoaRepo.findById(e.getKey())
                    .orElseThrow(() -> new IllegalArgumentException("Khong tim thay hang hoa"));
            if (hh.getSoLuongTon() < e.getValue())
                throw new IllegalArgumentException(
                        "Khong du ton kho: " + hh.getTenHangHoa() + " (con " + hh.getSoLuongTon() + ")");
        }

        HoaDon hd = new HoaDon();
        hd.setNguoiDung(nv);
        hd.setTenKhachHang(tenKH);
        hd.setSoDienThoai(sdt);
        hd.setTongTien(BigDecimal.ZERO);
        hoaDonRepo.save(hd);

        BigDecimal tong = BigDecimal.ZERO;
        for (Map.Entry<Integer, Integer> e : gom.entrySet()) {
            HangHoa hh = hangHoaRepo.findById(e.getKey()).get();
            int sl = e.getValue();

            ChiTietHoaDon ct = new ChiTietHoaDon();
            ct.setHoaDon(hd);
            ct.setHangHoa(hh);
            ct.setSoLuong(sl);
            ct.setDonGia(hh.getDonGia());
            chiTietRepo.save(ct);

            hh.setSoLuongTon(hh.getSoLuongTon() - sl);
            hangHoaRepo.save(hh);

            tong = tong.add(hh.getDonGia().multiply(BigDecimal.valueOf(sl)));
        }
        hd.setTongTien(tong);
        hoaDonRepo.save(hd);
        return hd;
    }
    @Transactional
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
