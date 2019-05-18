package hang.xuatHang;

import ConnectDB.ConnectionUtils;
import hang.xuatHang.PhieuXH;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ManagedBean(name="searchPhieuXH")
@RequestScoped
@ViewScoped
public class SearchXH implements Serializable {
    private PhieuXH phieuXH;
    public PhieuXH getPhieuXH() {
        return phieuXH;
    }
    private String a,b,c;

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public void setPhieuXH(PhieuXH PhieuXH) {
        this.phieuXH = PhieuXH;
    }

//    @PostConstruct
//    public void init(){
//        phieuXH = new PhieuXH();
//    }
    public List setList()throws ClassNotFoundException, SQLException {
        List<PhieuXH> list = new ArrayList();
//        String a = phieuXH.getNguoiXuat();
//        String b = phieuXH.getTenSP();
//        String c = phieuXH.getNguoiNhan();

        Connection connection = ConnectionUtils.getMyConnection();
        Statement statement = connection.createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
        // Tạo đối tượng Statement.
        // Statement statement = connection.createStatement();
        String sql = "Select * from phieuXH ";
        boolean check = false;
        if(a != null && !a.isEmpty() && check ==false  ){
            sql = sql +"where NGUOIXUAT= '+a+'";
            check= true;
        }
        else if(a!=null && !a.isEmpty() && check==true){
            sql = sql +" and NGUOIXUAT= '+a+'";
        }
        if(b != null && !b.isEmpty() && check ==false  ){
            sql = sql +" where TENSP LIKE '%"+b+"%'";
            check= true;
        }
        else if(b!=null && !b.isEmpty() && check==true){
            sql = sql +" and TENSP LIKE '%"+b+"%'";

        }
        if(c != null && !c.isEmpty() && check ==false  ){
            sql = sql +" where NGUOINHAN LIKE '%"+c+"%'";
            check= true;
        }
        else if(c!=null && !c.isEmpty() && check==true){
            sql = sql +" and NGUOINHAN LIKE '%"+c+"%'";
        }

        sql= sql +" ORDER BY MAPX ASC LIMIT 1000";

        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            PhieuXH phieuXH = new PhieuXH();
            phieuXH.setMaPX(rs.getInt(1));
            phieuXH.setNgayXuat(rs.getDate(2));
            phieuXH.setNguoiXuat(rs.getString(3));
            phieuXH.setNguoiNhan(rs.getString(4));
            phieuXH.setMaSP(rs.getString(5));
            phieuXH.setTenSP(rs.getString(6));
            phieuXH.setSoLuong(rs.getInt(7));
            list.add(phieuXH);
        }
//        if (list != null && list.size() >0 ) {
//            for (int j=0; j < list.size(); j++){
//                PhieuXH phieuXH = new PhieuXH();
//                phieuXH = list.get(j);
//            }
//        }

        return list;
    }



}

