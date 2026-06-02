package thick2.HuynhDucNghia.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import thick2.HuynhDucNghia.Model.HoaDon;

public interface HoaDonRepository extends JpaRepository<HoaDon, Integer> {
	List<HoaDon> findTop5ByOrderByNgayLapDesc();
}
