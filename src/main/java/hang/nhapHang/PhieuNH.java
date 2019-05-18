package hang.nhapHang;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.Date;
@ManagedBean(name="phieuNH1")
@ViewScoped
public class PhieuNH implements Serializable {
    private int MaPN;
    private String nguoiNhap, nguoiNhan, maSP, tenSP;
    private Date ngayNhap;
    private int soLuong;
    private static long sumSLN;

    public static long getSumSLN() {
        return sumSLN;
    }

    public static void setSumSLN(long sumSLN) {
        PhieuNH.sumSLN += sumSLN;
    }

    public int getMaPN() {
        return MaPN;
    }

    public void setMaPN(int maPN) {
        MaPN = maPN;
    }

    public String getNguoiNhap() {
        return nguoiNhap;
    }

    public void setNguoiNhap(String nguoiNhap) {
        this.nguoiNhap = nguoiNhap;
    }

    public String getNguoiNhan() {
        return nguoiNhan;
    }

    public void setNguoiNhan(String nguoiNhan) {
        this.nguoiNhan = nguoiNhan;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public Date getNgayNhap() {
        return ngayNhap;
    }

    public void setNgayNhap(Date ngayNhap) {
        this.ngayNhap = ngayNhap;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
}
