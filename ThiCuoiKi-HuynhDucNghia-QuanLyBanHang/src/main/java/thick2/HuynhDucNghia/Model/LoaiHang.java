package thick2.HuynhDucNghia.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "LoaiHang")
public class LoaiHang {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaLoaiHang")
    private Integer maLoaiHang;

    @NotBlank(message = "Ten loai hang khong duoc de trong")
    @Column(name = "TenLoaiHang", nullable = false, length = 100)
    private String tenLoaiHang;

    @Column(name = "MoTa", length = 200)
    private String moTa;

    public Integer getMaLoaiHang() { return maLoaiHang; }
    public void setMaLoaiHang(Integer maLoaiHang) { this.maLoaiHang = maLoaiHang; }
    public String getTenLoaiHang() { return tenLoaiHang; }
    public void setTenLoaiHang(String tenLoaiHang) { this.tenLoaiHang = tenLoaiHang; }
    public String getMoTa() { return moTa; }
    public void setMoTa(String moTa) { this.moTa = moTa; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LoaiHang)) return false;
        LoaiHang other = (LoaiHang) o;
        return maLoaiHang != null && maLoaiHang.equals(other.maLoaiHang);
    }
    @Override
    public int hashCode() { return maLoaiHang == null ? 0 : maLoaiHang.hashCode(); }

    @Override
    public String toString() { return maLoaiHang == null ? "" : String.valueOf(maLoaiHang); }
}
