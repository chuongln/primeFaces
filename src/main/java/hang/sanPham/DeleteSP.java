package hang.sanPham;

import ConnectDB.ConnectionUtils;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@ManagedBean(name="deleleSP")
@ViewScoped
public class DeleteSP implements Serializable {

    @PostConstruct
    public void init(){
        sanPham = new SanPham();
    }
    private SanPham sanPham;
    Search search1;

    public SanPham getSanPham() {
        return sanPham;
    }

    public void setSanPham(SanPham sanPham) {
        this.sanPham = sanPham;
    }

    public void updateHang(SanPham sanPham1) {
        sanPham =sanPham1;
        sanPham.getMaSP();
        sanPham.getSoLuong();
    }
    public void deleteSP()throws ClassNotFoundException, SQLException {
//        int a=12;
//        String b ="aaa";
//        Double c = 1243.0;
        String a= sanPham.getMaSP();
        int b = sanPham.getSoLuong();

        if(b!=0){

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR," ", "Còn hàng trong kho nên không thể xoá sản phẩm này"));
            System.out.println("ok");
            return;
        }
//        String b = hang.getTenHang();
//        Double c = hang.getGia();
        // Lấy ra đối tượng Connection kết nối vào com.mkyong.editor.DB.
        Connection connection = ConnectionUtils.getMyConnection();
        Statement statement = connection.createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
        // Tạo đối tượng Statement.
        // Statement statement = connection.createStatement();

//        String sql = "Select * from DSMH";
        String sql = "DELETE FROM SP where MaSP = '"+a+"' ";
        // Thực thi câu lệnh SQL trả về đối tượng ResultSet.
        boolean rs = statement.execute(sql);

        search1.setList();
    }

}
