package thigk2.HuynhDucNghia.Service;

import java.util.List;

import thigk2.HuynhDucNghia.Model.TheLoaiSanPham;

public interface TheLoaiService {
    List<TheLoaiSanPham> getAllTheLoai();
    TheLoaiSanPham getTheLoaiById(Integer id);
}