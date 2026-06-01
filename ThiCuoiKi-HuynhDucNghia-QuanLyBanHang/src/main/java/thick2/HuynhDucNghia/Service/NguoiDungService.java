package thick2.HuynhDucNghia.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import thick2.HuynhDucNghia.Model.NguoiDung;
import thick2.HuynhDucNghia.Repo.NguoiDungRepository;

@Service
public class NguoiDungService {

    @Autowired
    private NguoiDungRepository repo;
    
    public NguoiDung dangNhap(String tenDangNhap, String matKhau) {
        return repo.findByTenDangNhapAndMatKhau(tenDangNhap, matKhau);
    }
}
