package thigk2.HuynhDucNghia.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import thigk2.HuynhDucNghia.Model.TheLoaiSanPham;


@Repository
public interface TheLoaiSanPhamRepo extends JpaRepository<TheLoaiSanPham,Integer> {
	
}
