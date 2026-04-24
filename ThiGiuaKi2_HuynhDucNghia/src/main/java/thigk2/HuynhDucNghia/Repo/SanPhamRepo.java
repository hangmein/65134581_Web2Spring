package thigk2.HuynhDucNghia.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import thigk2.HuynhDucNghia.Model.SanPham;

@Repository
public interface SanPhamRepo extends JpaRepository<SanPham,Integer> {
	
}
