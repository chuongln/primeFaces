package hang.sanPham;

import ConnectDB.ConnectionUtils;
import hang.sanPham.*;

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
import java.text.SimpleDateFormat;
import java.util.Date;
@ManagedBean(name="addSP")
@ViewScoped
public class AddSP implements Serializable {
    Search searchSP;
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

    public void addSP() throws ClassNotFoundException, SQLException {
//        int a=15;
//        String b ="aaa";
//        Double c = 1243.0;
        String gia1;
        String a= sanPham.getMaSP();
        String b = sanPham.getTenSP();
        String c = sanPham.getLoaiSP();
        long d = sanPham.getGia();
        int e= 0;
        String f = sanPham.getHangSX();
        if(a==null && b == null ) {

            return;
        }
        checkLap(a);
        if (checkLap(a)== false) {
            {
//                FacesContext context = FacesContext.getCurrentInstance();

//                context.addMessage(null, new FacesMessage("Error",  "SP Đã tồn tại." ) );
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR," ", "Sản phẩm đã tồn tại. Xin vui lòng kiểm tra lại hoặc thử bàng mã sản phẩm khác"));
                System.out.println("ok");
            }
            return;
        }

        Connection connection = ConnectionUtils.getMyConnection();
        Statement statement = connection.createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
        // Tạo đối tượng Statement.
        // Statement statement = connection.createStatement();

//        String sql = "Select * from DSMH";
        String sql = "Insert INTO SP VALUES('" + a +"' , '"+b+"', '"+c +"', " + d +","+e+",'"+f+"')";
        // Thực thi câu lệnh SQL trả về đối tượng ResultSet.
//        System.out.println(1);
        boolean rs = statement.execute(sql);
//            rs.moveToInsertRow();
//        rs.moveToInsertRow();
//        rs.updateInt(1,hang.getMaHang());
//        rs.updateString(2,hang.getTenHang());
//        rs.updateDouble(3,hang.getMaHang());
//        rs.insertRow();
        searchSP.setList();
    }
    private boolean checkLap(String maSP) throws SQLException, ClassNotFoundException {
        boolean check= true;
        Connection connection = ConnectionUtils.getMyConnection();
        Statement statement = connection.createStatement();
        String sql = "select MASP from SP";
        ResultSet rs = statement.executeQuery(sql);
        while(rs.next()){
            String checkMa = rs.getString(1);
            if (checkMa.equals(maSP) ){
                check = false;
            }
        }
        if(check == true){

        }
        else if(check == false){

        }
        return check;
    }


}
