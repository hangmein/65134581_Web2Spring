package thick2.HuynhDucNghia.Repo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import thick2.HuynhDucNghia.Model.HangHoa;

public interface HangHoaRepository extends JpaRepository<HangHoa, Integer> {
    List<HangHoa> findByTenHangHoaContainingIgnoreCase(String ten);
    List<HangHoa> findByLoaiHang_MaLoaiHang(Integer maLoaiHang);
}
