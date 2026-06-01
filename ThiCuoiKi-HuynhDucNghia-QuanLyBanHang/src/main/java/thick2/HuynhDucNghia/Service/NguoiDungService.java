package thick2.HuynhDucNghia.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import thick2.HuynhDucNghia.Model.NguoiDung;
import thick2.HuynhDucNghia.Repo.NguoiDungRepository;
import java.util.List;

@Service
public class NguoiDungService {

    @Autowired
    private NguoiDungRepository repo;
   
    public NguoiDung dangNhap(String tenDangNhap, String matKhau) {
        return repo.findByTenDangNhapAndMatKhau(tenDangNhap, matKhau);
    }
    public List<NguoiDung> getAll() { return repo.findAll(); }
    public NguoiDung getById(Integer id) { return repo.findById(id).orElse(null); }
    public void save(NguoiDung nd) { repo.save(nd); }
    public void delete(Integer id) { repo.deleteById(id); }

    public List<NguoiDung> search(String keyword) {
        if (keyword != null && !keyword.trim().isEmpty()) {
            String k = keyword.trim();
            return repo.findByHoTenContainingIgnoreCaseOrTenDangNhapContainingIgnoreCase(k, k);
        }
        return repo.findAll();
    }
}