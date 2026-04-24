package thigk2.HuynhDucNghia.Model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="sanpham")
public class SanPham {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer maSP;

    @Column(nullable = false, length = 255)
    private String tenSP;

    @Column(nullable = false, precision = 15, scale = 2) 
    private BigDecimal soTien;

    @Column(columnDefinition = "TEXT")
    private String moTa;
    @ManyToOne
    @JoinColumn(name = "maTheLoai", nullable = false) 
    @JsonIgnore 
    private TheLoaiSanPham theLoai;

    public SanPham() {}
    
    

    public SanPham(Integer maSP, String tenSP, BigDecimal soTien, String moTa, TheLoaiSanPham theLoai) {
		super();
		this.maSP = maSP;
		this.tenSP = tenSP;
		this.soTien = soTien;
		this.moTa = moTa;
		this.theLoai = theLoai;
	}



	public Integer getMaSP() { return maSP; }
    public void setMaSP(Integer maSP) { this.maSP = maSP; }

    public String getTenSP() { return tenSP; }
    public void setTenSP(String tenSP) { this.tenSP = tenSP; }

    public BigDecimal getSoTien() { return soTien; }
    public void setSoTien(BigDecimal soTien) { this.soTien = soTien; }

    public String getMoTa() { return moTa; }
    public void setMoTa(String moTa) { this.moTa = moTa; }

    public TheLoaiSanPham getTheLoai() { return theLoai; }
    public void setTheLoai(TheLoaiSanPham theLoai) { this.theLoai = theLoai; 
    }
}
