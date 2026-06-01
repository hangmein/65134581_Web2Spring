package thick2.HuynhDucNghia.Service;

import java.math.BigDecimal;

public class ThongKeLoaiHang {
    private final String tenLoai;
    private final int soSanPham;
    private final int tongTon;
    private final BigDecimal giaTriTon;

    public ThongKeLoaiHang(String tenLoai, int soSanPham, int tongTon, BigDecimal giaTriTon) {
        this.tenLoai = tenLoai;
        this.soSanPham = soSanPham;
        this.tongTon = tongTon;
        this.giaTriTon = giaTriTon;
    }
    public String getTenLoai() { return tenLoai; }
    public int getSoSanPham() { return soSanPham; }
    public int getTongTon() { return tongTon; }
    public BigDecimal getGiaTriTon() { return giaTriTon; }
}
