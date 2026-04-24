package thigk2.HuynhDucNghia.Model;

import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name = "theloaisanpham")
public class TheLoaiSanPham {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    @Column(name = "maTheLoai")
    private Integer maTheLoai;

    @Column(name = "tenTheLoai", nullable = false, length = 255)
    private String tenTheLoai;

    @Column(name = "moTa", columnDefinition = "TEXT") 
    private String moTa;

    @OneToMany(mappedBy = "theLoai", cascade = CascadeType.ALL)
    private List<SanPham> danhSachSanPham;

    public TheLoaiSanPham() {}

    public TheLoaiSanPham(Integer maTheLoai, String tenTheLoai, String moTa, List<SanPham> danhSachSanPham) {
        super();
        this.maTheLoai = maTheLoai;
        this.tenTheLoai = tenTheLoai;
        this.moTa = moTa;
        this.danhSachSanPham = danhSachSanPham;
    }

    public Integer getMaTheLoai() { return maTheLoai; }
    public void setMaTheLoai(Integer maTheLoai) { this.maTheLoai = maTheLoai; }

    public String getTenTheLoai() { return tenTheLoai; }
    public void setTenTheLoai(String tenTheLoai) { this.tenTheLoai = tenTheLoai; }

    public String getMoTa() { return moTa; }
    public void setMoTa(String moTa) { this.moTa = moTa; }

    public List<SanPham> getDanhSachSanPham() { return danhSachSanPham; }
    public void setDanhSachSanPham(List<SanPham> danhSachSanPham) { this.danhSachSanPham = danhSachSanPham; }
}