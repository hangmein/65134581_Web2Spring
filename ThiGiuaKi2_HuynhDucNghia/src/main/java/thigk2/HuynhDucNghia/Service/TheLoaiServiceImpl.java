package thigk2.HuynhDucNghia.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import thigk2.HuynhDucNghia.Model.TheLoaiSanPham;
import thigk2.HuynhDucNghia.Repo.TheLoaiSanPhamRepo;

@Service
public class TheLoaiServiceImpl implements TheLoaiService {
    @Autowired
    private TheLoaiSanPhamRepo theLoaiRepository;

    @Override
    public List<TheLoaiSanPham> getAllTheLoai() {
        return theLoaiRepository.findAll();
    }

    @Override
    public TheLoaiSanPham getTheLoaiById(Integer id) {
        return theLoaiRepository.findById(id).orElse(null);
    }
}