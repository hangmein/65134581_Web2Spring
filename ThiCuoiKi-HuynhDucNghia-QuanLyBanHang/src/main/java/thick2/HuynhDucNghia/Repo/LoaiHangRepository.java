package thick2.HuynhDucNghia.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import thick2.HuynhDucNghia.Model.LoaiHang;

public interface LoaiHangRepository extends JpaRepository<LoaiHang, Integer> {
	List<LoaiHang> findByTenLoaiHangContainingIgnoreCase(String keyword);
}
