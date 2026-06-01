package thick2.HuynhDucNghia.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "HangHoa")
public class HangHoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaHangHoa")
    private Integer maHangHoa;

    @NotBlank(message = "Ten hang hoa khong duoc de trong")
    @Column(name = "TenHangHoa", nullable = false, length = 200)
    private String tenHangHoa;

    @NotNull(message = "Phai chon loai hang")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "MaLoaiHang", nullable = false)
    private LoaiHang loaiHang;

    @NotNull(message = "Don gia khong duoc de trong")
    @Min(value = 0, message = "Don gia phai >= 0")
    @Column(name = "DonGia", nullable = false, precision = 18, scale = 2)
    private BigDecimal donGia = BigDecimal.ZERO;

    @NotNull(message = "So luong khong duoc de trong")
    @Min(value = 0, message = "So luong phai >= 0")
    @Column(name = "SoLuongTon", nullable = false)
    private Integer soLuongTon = 0;
    
    @Column(name = "MoTa", length = 300)
    private String moTa;

    public Integer getMaHangHoa() { return maHangHoa; }
    public void setMaHangHoa(Integer maHangHoa) { this.maHangHoa = maHangHoa; }
    public String getTenHangHoa() { return tenHangHoa; }
    public void setTenHangHoa(String tenHangHoa) { this.tenHangHoa = tenHangHoa; }
    public LoaiHang getLoaiHang() { return loaiHang; }
    public void setLoaiHang(LoaiHang loaiHang) { this.loaiHang = loaiHang; }
    public BigDecimal getDonGia() { return donGia; }
    public void setDonGia(BigDecimal donGia) { this.donGia = donGia; }
    public Integer getSoLuongTon() { return soLuongTon; }
    public void setSoLuongTon(Integer soLuongTon) { this.soLuongTon = soLuongTon; }
    public String getMoTa() { return moTa; }
    public void setMoTa(String moTa) { this.moTa = moTa; }
}
