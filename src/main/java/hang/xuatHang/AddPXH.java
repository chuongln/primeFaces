package hang.xuatHang;

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
import java.text.SimpleDateFormat;
import java.util.Date;
@ManagedBean(name="addPXH")
@ViewScoped
public class AddPXH implements Serializable {
    private PhieuXH phieuXH;
    public PhieuXH getPhieuXH() {
        return phieuXH;
    }

    public void setPhieuXH(PhieuXH PhieuXH) {
        this.phieuXH = PhieuXH;
    }

    @PostConstruct
    public void init(){
        phieuXH = new PhieuXH();
    }

    public void addPXH() throws ClassNotFoundException, SQLException {
        SearchXH searchXH = new SearchXH();
        Date a = new Date();
        String b = phieuXH.getNguoiXuat();
        String c = phieuXH.getNguoiNhan();
        String d = phieuXH.getMaSP();
        String e = phieuXH.getTenSP();
        int f= phieuXH.getSoLuong();
        SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd");

        String date = dt1.format(a);
        if(f==0 && d == null ) {

            return;
        }
        checkLap(d, f);
        if (checkLap(d, f)== true) {
            {

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR," ", "Không tồn tại sản phẩm này hoặc không đủ số lượng hàng trong kho, xin kiểm tra lại"));
                System.out.println("ok");
            }
            return;
        }

        Connection connection = ConnectionUtils.getMyConnection();
        Statement statement = connection.createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
        String sql = "Insert INTO PhieuXH(NGAYXUAT, NGUOIXUAT, NGUOINHAN, MASP,TENSP, SOLUONG) VALUES('"+ date+"' , '"+b+"', '"+c +"','"+d+"' , (select TENSP from SP where MASP='"+d+"')," + f+")";
        String sql1= "UPDATE SP SET SOLUONG = SOLUONG-"+f+" WHERE MASP='"+d+"'" ;
        boolean rs = statement.execute(sql);
        boolean rs1= statement.execute(sql1);
        searchXH.setList();
    }
    private boolean checkLap(String maSP, int soLuong) throws SQLException, ClassNotFoundException {
        boolean check= true;
        Connection connection = ConnectionUtils.getMyConnection();
        Statement statement = connection.createStatement();
        String sql = "select MaSP,soLuong from sp";
        ResultSet rs = statement.executeQuery(sql);
        while(rs.next()){
            String checkMa = rs.getString(1);
            int checkSL = rs.getInt(2);
            if (checkMa.equals(maSP) && soLuong <= checkSL ){
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

