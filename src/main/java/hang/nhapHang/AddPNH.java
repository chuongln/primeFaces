package hang.nhapHang;


import ConnectDB.ConnectionUtils;
import hang.nhapHang.*;
import hang.sanPham.Search;

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

@ManagedBean(name="addPNH")
@ViewScoped
public class AddPNH implements Serializable {
    private PhieuNH phieuNH;
    public PhieuNH getPhieuNH() {
        return phieuNH;
    }

    public void setPhieuNH(PhieuNH PhieuNH) {
        this.phieuNH = PhieuNH;
    }

    @PostConstruct
    public void init(){
        phieuNH = new PhieuNH();
    }

    public void addPNH() throws ClassNotFoundException, SQLException {
        SearchNH searchNH = new SearchNH();
        Date a = new Date();
        String b = phieuNH.getNguoiNhap();
        String c = phieuNH.getNguoiNhan();
        String d = phieuNH.getMaSP();
        int f= phieuNH.getSoLuong();
        SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd");

        String date = dt1.format(a);
        if(f==0 && d == null ) {

            return;
        }
        checkLap(d);
        if (checkLap(d)== true) {
            {

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR," ", "Chưa tồn tại sản phẩm này. Bạn phải thêm mới sản phẩm này"));
                System.out.println("ok");
            }
            return;
        }

        Connection connection = ConnectionUtils.getMyConnection();
        Statement statement = connection.createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
        String sql = "Insert INTO PHIEUNH(NGAYNHAP, NGUOINHAP, NGUOINHAN, MASP,TENSP, SOLUONG) VALUES('"+ date+"' , '"+b+"', '"+c +"', '"+d+"', (select TENSP from SP where MASP='"+d+"'), " + f+")";
        String sql1= "UPDATE SP SET SOLUONG = SOLUONG+"+f+" WHERE MASP='"+d+"'" ;
        boolean rs = statement.execute(sql);
        boolean rs1= statement.execute(sql1);
        searchNH.setList();
    }
    private boolean checkLap(String maSP) throws SQLException, ClassNotFoundException {
        boolean check= true;
        Connection connection = ConnectionUtils.getMyConnection();
        Statement statement = connection.createStatement();
        String sql = "select MaSP from sp";
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
