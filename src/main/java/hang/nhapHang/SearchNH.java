package hang.nhapHang;

import ConnectDB.ConnectionUtils;
import hang.nhapHang.PhieuNH;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ManagedBean(name="searchPhieuNH")
@RequestScoped
@ViewScoped
public class SearchNH implements Serializable {
    private PhieuNH phieuNH;
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

    public PhieuNH getPhieuNH() {
        return phieuNH;
    }

    public void setPhieuNH(PhieuNH PhieuNH) {
        this.phieuNH = PhieuNH;
    }

//    @PostConstruct
//    public void init(){
//        phieuNH = new PhieuNH();
//    }
    public List setList()throws ClassNotFoundException, SQLException {
        List<PhieuNH> list = new ArrayList();

        Connection connection = ConnectionUtils.getMyConnection();
        Statement statement = connection.createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
        String sql = "Select * from phieunh ";
        boolean check = false;
        if(a != null && !a.isEmpty() && check ==false ){
            sql = sql +"where NGUOINHAN= '+a+'";
            check= true;
        }
        else if(a != null && !a.isEmpty() && check==true){
            sql = sql +" and NGUOINHAN= '+a+'";
        }
        if(b != null && !b.isEmpty() && check ==false  ){
            sql = sql +" where TENSP LIKE '%"+b+"%'";
            check= true;
        }
        else if(b!=null && !b.isEmpty() && check==true){
            sql = sql +" and TENSP LIKE '%"+b+"%'";

        }
        if(c != null && !c.isEmpty() && check ==false  ){
            sql = sql +" where NGUOINHAP LIKE '%"+c+"%'";
            check= true;
        }
        else if(c!=null && !c.isEmpty() && check==true){
            sql = sql +" and NGUOINHAP LIKE '%"+c+"%'";
        }

            sql= sql +" ORDER BY MAPN ASC LIMIT 1000";
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            PhieuNH phieuNH = new PhieuNH();
            phieuNH.setMaPN(rs.getInt(1));
            phieuNH.setNgayNhap(rs.getDate(2));
            phieuNH.setNguoiNhap(rs.getString(3));
            phieuNH.setNguoiNhan(rs.getString(4));
            phieuNH.setMaSP(rs.getString(5));
            phieuNH.setTenSP(rs.getString(6));
            phieuNH.setSoLuong(rs.getInt(7));
            list.add(phieuNH);
        }
//        if (list != null && list.size() >0 ) {
//            for (int j=0; j < list.size(); j++){
//                PhieuNH phieuNH = new PhieuNH();
//                phieuNH = list.get(j);
//            }
//        }

        return list;
    }



}

