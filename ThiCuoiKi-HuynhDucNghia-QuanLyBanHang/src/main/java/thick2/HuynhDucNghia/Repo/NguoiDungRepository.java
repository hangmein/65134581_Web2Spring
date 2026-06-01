package thick2.HuynhDucNghia.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import thick2.HuynhDucNghia.Model.NguoiDung;

public interface NguoiDungRepository extends JpaRepository<NguoiDung, Integer> {
    NguoiDung findByTenDangNhapAndMatKhau(String tenDangNhap, String matKhau);
    NguoiDung findByTenDangNhap(String tenDangNhap);
}
