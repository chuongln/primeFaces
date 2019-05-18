package hang.sanPham;

import ConnectDB.ConnectionUtils;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

@ManagedBean(name="editSP")
@ViewScoped
public class EditSP implements Serializable {

    Search search1;
    private SanPham sanPham;

    public SanPham getSanPham() {
        return sanPham;
    }

    public void setSanPham(SanPham sanPham) {
        this.sanPham = sanPham;
    }

        @PostConstruct
    public void init() {
        sanPham = new SanPham();
    }
    public void updateHang(SanPham sanPham1) {
//        hang = this.hang;
//        hang = new Hang();
        sanPham = sanPham1;
        sanPham.getMaSP();
        sanPham.getTenSP();
        sanPham.getLoaiSP();
        sanPham.getGia();
        sanPham.getHangSX();
    }

    public void editSP()throws ClassNotFoundException, SQLException {

        String a = sanPham.getMaSP();
        String b = sanPham.getTenSP();
        String c = sanPham.getLoaiSP();
        long d = sanPham.getGia();
        String e = sanPham.getHangSX();
//        SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd");
//        java.util.Date d = new Date();
//        String date = dt1.format(d);
        // Lấy ra đối tượng Connection kết nối vào com.mkyong.editor.DB.
        Connection connection = ConnectionUtils.getMyConnection();
        Statement statement = connection.createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
        // Tạo đối tượng Statement.
        // Statement statement = connection.createStatement();

//        String sql = "Select * from DSMH";
        String sql1 = "UPDATE SP SET TenSP = '"+b+"' , HangSX = '"+e+"' ,LoaiSP = '"+c+"' ,   Gia = " +d+" where MaSP = '"+a+"' ";
        // Thực thi câu lệnh SQL trả về đối tượng ResultSet.
        boolean rs = statement.execute(sql1);
        search1.setList();

//            rs.moveToInsertRow();
//        rs.moveToInsertRow();
//        rs.updateInt(1,hang.getMaHang());
//        rs.updateString(2,hang.getTenHang());
//        rs.updateDouble(3,hang.getMaHang());
//        rs.insertRow();


    }
}
