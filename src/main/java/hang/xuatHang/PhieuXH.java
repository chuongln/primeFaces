package hang.xuatHang;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.Date;
@ManagedBean(name="phieuXH1")
@ViewScoped
public class PhieuXH implements Serializable {
    private int MaPX;
    private String nguoiXuat, nguoiNhan, maSP, tenSP;
    private Date ngayXuat;
    private int soLuong;

    public int getMaPX() {
        return MaPX;
    }

    public void setMaPX(int maPX) {
        MaPX = maPX;
    }

    public String getNguoiXuat() {
        return nguoiXuat;
    }

    public void setNguoiXuat(String nguoiXuat) {
        this.nguoiXuat = nguoiXuat;
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

    public Date getNgayXuat() {
        return ngayXuat;
    }

    public void setNgayXuat(Date ngayXuat) {
        this.ngayXuat = ngayXuat;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
}
