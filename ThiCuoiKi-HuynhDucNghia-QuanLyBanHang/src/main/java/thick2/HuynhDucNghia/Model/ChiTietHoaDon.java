package thick2.HuynhDucNghia.Model;


import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "ChiTietHoaDon")
public class ChiTietHoaDon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaChiTiet")
    private Integer maChiTiet;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "MaHoaDon", nullable = false)
    private HoaDon hoaDon;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "MaHangHoa", nullable = false)
    private HangHoa hangHoa;

    @Column(name = "SoLuong", nullable = false)
    private Integer soLuong;

    @Column(name = "DonGia", nullable = false, precision = 18, scale = 2)
    private BigDecimal donGia;

    @Transient
    public BigDecimal getThanhTien() {
        if (soLuong == null || donGia == null) return BigDecimal.ZERO;
        return donGia.multiply(BigDecimal.valueOf(soLuong));
    }

    public Integer getMaChiTiet() { return maChiTiet; }
    public void setMaChiTiet(Integer maChiTiet) { this.maChiTiet = maChiTiet; }
    public HoaDon getHoaDon() { return hoaDon; }
    public void setHoaDon(HoaDon hoaDon) { this.hoaDon = hoaDon; }
    public HangHoa getHangHoa() { return hangHoa; }
    public void setHangHoa(HangHoa hangHoa) { this.hangHoa = hangHoa; }
    public Integer getSoLuong() { return soLuong; }
    public void setSoLuong(Integer soLuong) { this.soLuong = soLuong; }
    public BigDecimal getDonGia() { return donGia; }
    public void setDonGia(BigDecimal donGia) { this.donGia = donGia; }
}
