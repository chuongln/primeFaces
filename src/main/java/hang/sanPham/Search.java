package hang.sanPham;

import ConnectDB.ConnectionUtils;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name="searchSanPham")
@RequestScoped
@ViewScoped
public class Search implements Serializable {
    private SanPham sanPham;
    public SanPham getSanPham() {
        return sanPham;
    }

    public void setSanPham(SanPham sanPham) {
        this.sanPham = sanPham;
    }

    @PostConstruct
    public void init(){
        sanPham = new SanPham();
    }
    public List setList()throws ClassNotFoundException, SQLException {
//        int a=5;
        List<SanPham> list = new ArrayList();
        String a = sanPham.getMaSP();
        String b = sanPham.getTenSP();
        double c = sanPham.getGia();
        String d = sanPham.getHangSX();
        String e = sanPham.getLoaiSP();
//        long sum = 0;
//        Date d = hang.getNgayTao();
        // Lấy ra đối tượng Connection kết nối vào com.mkyong.editor.DB.
        Connection connection = ConnectionUtils.getMyConnection();
        Statement statement = connection.createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
        // Tạo đối tượng Statement.
        // Statement statement = connection.createStatement();
        String sql = "Select * from sp ";
        boolean check = false;
        if(a != null && !a.isEmpty() && check ==false  ){
            sql = sql +"where MASP= '"+a+"'";
            check= true;
        }
        else if(a!=null && !a.isEmpty() && check==true){
            sql = sql +" and MASP= '"+a+"'";
        }
        if(b != null && !b.isEmpty() && check ==false  ){
            sql = sql +" where TENSP LIKE '%"+b+"%'";
            check= true;
        }
        else if(b!=null && !b.isEmpty() && check==true){
            sql = sql +" and TENSP LIKE '%"+b+"%'";

        }
        if(c != 0.0  && check ==false  ){
            sql = sql +" where Gia= "+c+"";
            check= true;
        }
        else if(c!=0.0 && check==true){
            sql = sql +" and Gia= "+c+"";
        }
        if(d != null && !d.isEmpty() && check ==false  ){
            sql = sql +" where HANGSX LIKE '%"+d+"%'";
            check= true;
        }
        else if(d!=null && !d.isEmpty() && check==true){
            sql = sql +" and HANGSX LIKE '%"+d+"%'";

        }
        if(e != null && !e.isEmpty() && check ==false  ){
            sql = sql +" where LOAISP LIKE '%"+e+"%'";
            check= true;
        }
        else if(e!=null && !e.isEmpty() && check==true){
            sql = sql +" and LOAISP LIKE '%"+e+"%'";

        }
            sql= sql +" ORDER BY MASP ASC LIMIT 1000";
//        String sql = "Insert INTO DSMH(MaHang, TenHang, Gia) VALUES(" + a +" , '"+b+"', "+c +")";
        // Thực thi câu lệnh SQL trả về đối tượng ResultSet.
//
//        System.out.println("------------------");
//        System.out.println("Mã Hàng:" + rs.getInt(1));
//        System.out.println("Tên Hàng:" + rs.getString);
//        System.out.println("Giá:" + Gia);
//        System.out.println("Ngày Tạo:" + NgayTao);
//        System.out.println("Ngày Cập Nhật:" + NgayCapNhat);
        ResultSet rs = statement.executeQuery(sql);
        long sumAll = 0;
        while (rs.next()) {
            SanPham sanPham = new SanPham();
            sanPham.setMaSP(rs.getString(1));
            sanPham.setTenSP(rs.getString(2));
            sanPham.setLoaiSP(rs.getString(3));
            sanPham.setGia(rs.getLong(4));
            sanPham.setSoLuong(rs.getInt(5));
            sanPham.setHangSX(rs.getString(6));
            sanPham.setSum(sanPham.getGia()*sanPham.getSoLuong());
            sumAll+=sanPham.getSum();
            list.add(sanPham);
        }
        sanPham.setSumAll(sumAll);
        if (list != null && list.size() >0 ) {
            for (int j=0; j < list.size(); j++){
                SanPham sanPham = new SanPham();
                sanPham = list.get(j);
            }
        }

        return list;
    }



}

