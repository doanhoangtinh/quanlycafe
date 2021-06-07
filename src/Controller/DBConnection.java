/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Ban;
import Model.ChiTietHD;
import Model.ChucVu;
import Model.DanhMuc;
import Model.HoaDon;
import Model.Mon;
import Model.NhanVien;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author ASUS
 */
public class DBConnection {

    PreparedStatement preparedStatement;
    Statement statement;
    ResultSet rs;
    String SQL;

    //Chuc vu
    public void addChucVu(ChucVu cv) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/cafedb";
            String user = "mysql";
            String password = "mysql";
            Connection connection = DriverManager.getConnection(url, user, password);
            SQL = "insert into ChucVu values (?,?)";
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setNString(1, cv.getMaChucVu());
            preparedStatement.setNString(2, cv.getTenChucVu());
            preparedStatement.executeUpdate();
            connection.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void editChucVu(ChucVu cv, String primarykey) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/cafedb";
            String user = "mysql";
            String password = "mysql";
            Connection connection = DriverManager.getConnection(url, user, password);
            SQL = "update ChucVu set MaChucVu = ?, TenChucVu = ? where MaChucVu  = ?";
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setNString(1, cv.getMaChucVu());
            preparedStatement.setNString(2, cv.getTenChucVu());
            preparedStatement.setNString(3, primarykey);
            preparedStatement.executeUpdate();
            connection.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void deleteChucVu(ChucVu cv) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/cafedb";
            String user = "mysql";
            String password = "mysql";
            Connection connection = DriverManager.getConnection(url, user, password);
            SQL = "delete from ChucVu where MaChucVu = ?";
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setNString(1, cv.getMaChucVu());
            preparedStatement.executeUpdate();
            connection.close();
        } catch (Exception ex) {
        }
    }

    public ArrayList<ChucVu> getDSChucVu() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/cafedb";
            String user = "mysql";
            String password = "mysql";
            Connection connection = DriverManager.getConnection(url, user, password);
            ArrayList<ChucVu> chucVuList = new ArrayList<>();
            statement = connection.createStatement();
            SQL = "select MaChucVu,TenChucVu from ChucVu";
            rs = statement.executeQuery(SQL);
            while (rs.next()) {
                String mCV = rs.getNString("MaChucVu");
                String tenCV = rs.getNString("TenChucVu");
                ChucVu chucVu = new ChucVu(mCV, tenCV);
                chucVuList.add(chucVu);
            }
            connection.close();
            return chucVuList;
        } catch (Exception ex) {
            return null;
        }
    }

    //Nhan vien
    public void addNhanVien(NhanVien nv) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/cafedb";
            String user = "mysql";
            String password = "mysql";
            Connection connection = DriverManager.getConnection(url, user, password);
            SQL = "insert into NhanVien values (?,?,?,?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setNString(1, nv.getMaNhanVien());
            preparedStatement.setNString(2, nv.getTenNhanVien());
            preparedStatement.setNString(3, nv.getTaiKhoan());
            preparedStatement.setNString(4, nv.getMatKhau());
            preparedStatement.setNString(5, nv.getChucVu().getMaChucVu());
            preparedStatement.setNString(6, nv.getDiaChi());
            preparedStatement.setNString(7, nv.getCmnd());
            preparedStatement.setNString(8, nv.getSdt());
            preparedStatement.executeUpdate();
            connection.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void editNhanVien(NhanVien nv, String primarykey) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/cafedb";
            String user = "mysql";
            String password = "mysql";
            Connection connection = DriverManager.getConnection(url, user, password);
            SQL = "update NhanVien set MaNhanVien = ?, TenNhanVien = ?, TaiKhoan = ?, MatKhau = ?,"
                    + " MaChucVu = ?, DiaChi = ? , CMND = ?, SDT = ? where MaNhanVien  = ?";
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setNString(1, nv.getMaNhanVien());
            preparedStatement.setNString(2, nv.getTenNhanVien());
            preparedStatement.setNString(3, nv.getTaiKhoan());
            preparedStatement.setNString(4, nv.getMatKhau());
            preparedStatement.setNString(5, nv.getChucVu().getMaChucVu());
            preparedStatement.setNString(6, nv.getDiaChi());
            preparedStatement.setNString(7, nv.getCmnd());
            preparedStatement.setNString(8, nv.getSdt());
            preparedStatement.setNString(9, primarykey);
            preparedStatement.executeUpdate();
            connection.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void deleteNhanVien(NhanVien nv) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/cafedb";
            String user = "mysql";
            String password = "mysql";
            Connection connection = DriverManager.getConnection(url, user, password);
            String SQL = "delete from NhanVien where MaNhanVien = ?";
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setNString(1, nv.getMaNhanVien());
            preparedStatement.executeUpdate();
            connection.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public ArrayList<NhanVien> getDSNhanVien() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/cafedb";
            String user = "mysql";
            String password = "mysql";
            Connection connection = DriverManager.getConnection(url, user, password);
            ArrayList<ChucVu> chucVuList = getDSChucVu();
            ChucVu cv = null;

            ArrayList<NhanVien> nhanVien = new ArrayList<>();
            statement = connection.createStatement();
            SQL = "select * from NhanVien";
            rs = statement.executeQuery(SQL);
            while (rs.next()) {
                String maNV = rs.getNString("MaNhanVien");
                String tenNV = rs.getNString("TenNhanVien");
                String taiKhoan = rs.getNString("TaiKhoan");
                String matKhau = rs.getNString("MatKhau");
                String maCV = rs.getNString("MaChucVu");
                String diaChi = rs.getNString("DiaChi");
                String cmnd = rs.getNString("CMND");
                String sdt = rs.getNString("SDT");
                for (int i = 0; i < chucVuList.size(); i++) {
                    if (maCV.equals(chucVuList.get(i).getMaChucVu())) {
                        cv = new ChucVu(chucVuList.get(i).getMaChucVu(), chucVuList.get(i).getTenChucVu());
                    }
                }
                NhanVien nv = new NhanVien();
                nv.setMaNhanVien(maNV);
                nv.setTenNhanVien(tenNV);
                nv.setTaiKhoan(taiKhoan);
                nv.setMatKhau(matKhau);
                nv.setChucVu(cv);
                nv.setDiaChi(diaChi);
                nv.setCmnd(cmnd);
                nv.setSdt(sdt);
                nhanVien.add(nv);
            }
            connection.close();
            return nhanVien;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    //Ban
    public void addBan(Ban ban) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/cafedb";
            String user = "mysql";
            String password = "mysql";
            Connection connection = DriverManager.getConnection(url, user, password);
            SQL = "insert into Ban values (?,?,?,?)";
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setNString(1, ban.getMaBan());
            preparedStatement.setInt(2, ban.getSoLuongCho());
            preparedStatement.setNString(3, ban.getViTri());
            preparedStatement.setNString(4, ban.getTrangThai());
            preparedStatement.executeUpdate();
            connection.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void editBan(Ban ban, String primarykey) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/cafedb";
            String user = "mysql";
            String password = "mysql";
            Connection connection = DriverManager.getConnection(url, user, password);
            SQL = "update Ban set MaBan = ?, SoLuongCho = ?, ViTri = ?, TrangThai = ? where MaBan  = ?";
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setNString(1, ban.getMaBan());
            preparedStatement.setInt(2, ban.getSoLuongCho());
            preparedStatement.setNString(3, ban.getViTri());
            preparedStatement.setNString(4, ban.getTrangThai());
            preparedStatement.setNString(5, primarykey);
            preparedStatement.executeUpdate();
            connection.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void deleteBan(Ban ban) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/cafedb";
            String user = "mysql";
            String password = "mysql";
            Connection connection = DriverManager.getConnection(url, user, password);
            SQL = "delete from Ban where MaBan = ?";
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setNString(1, ban.getMaBan());
            preparedStatement.executeUpdate();
            connection.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public ArrayList<Ban> getDSBan() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/cafedb";
            String user = "mysql";
            String password = "mysql";
            Connection connection = DriverManager.getConnection(url, user, password);
            ArrayList<Ban> banList = new ArrayList<>();
            statement = connection.createStatement();
            SQL = "select * from Ban";
            rs = statement.executeQuery(SQL);
            while (rs.next()) {
                String maBan = rs.getNString("MaBan");
                int soLuong = rs.getInt("SoLuongCho");
                String viTri = rs.getNString("ViTri");
                String trangThai = rs.getNString("TrangThai");
                Ban ban = new Ban(maBan, soLuong, viTri, trangThai);
                banList.add(ban);
            }
            connection.close();
            return banList;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    //DanhMuc
    public void addDanhMuc(DanhMuc danhMuc) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/cafedb";
            String user = "mysql";
            String password = "mysql";
            Connection connection = DriverManager.getConnection(url, user, password);
            SQL = "insert into DanhMuc values (?,?)";
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setNString(1, danhMuc.getMaDanhMuc());
            preparedStatement.setNString(2, danhMuc.getTenDanhMuc());
            preparedStatement.executeUpdate();
            connection.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void editDanhMuc(DanhMuc danhMuc, String primarykey) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/cafedb";
            String user = "mysql";
            String password = "mysql";
            Connection connection = DriverManager.getConnection(url, user, password);
            SQL = "update DanhMuc set MaDanhMuc = ?, TenDanhMuc = ? where MaDanhMuc  = ?";
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setNString(1, danhMuc.getMaDanhMuc());
            preparedStatement.setNString(2, danhMuc.getTenDanhMuc());
            preparedStatement.setNString(3, primarykey);
            preparedStatement.executeUpdate();
            connection.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void deleteDanhMuc(DanhMuc danhMuc) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/cafedb";
            String user = "mysql";
            String password = "mysql";
            Connection connection = DriverManager.getConnection(url, user, password);
            SQL = "delete from DanhMuc where MaDanhMuc = ?";
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setNString(1, danhMuc.getMaDanhMuc());
            preparedStatement.executeUpdate();
            connection.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public ArrayList<DanhMuc> getDSDanhMuc() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/cafedb";
            String user = "mysql";
            String password = "mysql";
            Connection connection = DriverManager.getConnection(url, user, password);
            ArrayList<DanhMuc> danhMucList = new ArrayList<>();
            statement = connection.createStatement();
            String queryString = "select * from DanhMuc";
            rs = statement.executeQuery(queryString);
            while (rs.next()) {
                String maDM = rs.getNString("MaDanhMuc");
                String tenDM = rs.getNString("TenDanhMuc");
                DanhMuc danhMuc = new DanhMuc(maDM, tenDM);
                danhMucList.add(danhMuc);
            }
            connection.close();
            return danhMucList;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    //Mon
    public void addMon(Mon mon) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/cafedb";
            String user = "mysql";
            String password = "mysql";
            Connection connection = DriverManager.getConnection(url, user, password);
            SQL = "insert into Mon values (?,?,?,?)";
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setNString(1, mon.getMaMon());
            preparedStatement.setNString(2, mon.getTenMon());
            preparedStatement.setNString(3, mon.getDanhMuc().getMaDanhMuc());
            preparedStatement.setFloat(4, mon.getGiamon());
            preparedStatement.executeUpdate();
            connection.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void editMon(Mon mon, String primaryKey) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/cafedb";
            String user = "mysql";
            String password = "mysql";
            Connection connection = DriverManager.getConnection(url, user, password);
            SQL = "update Mon set MaMon = ?, TenMon = ?, MaDanhMuc = ?, GiaMon = ? where MaMon  = ?";
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setNString(1, mon.getMaMon());
            preparedStatement.setNString(2, mon.getTenMon());
            preparedStatement.setNString(3, mon.getDanhMuc().getMaDanhMuc());
            preparedStatement.setFloat(4, mon.getGiamon());
            preparedStatement.setNString(5, primaryKey);
            preparedStatement.executeUpdate();
            connection.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void deleteMon(String maMon) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/cafedb";
            String user = "mysql";
            String password = "mysql";
            Connection connection = DriverManager.getConnection(url, user, password);
            SQL = "delete from Mon where MaMon = ?";
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setNString(1, maMon);
            preparedStatement.executeUpdate();
            connection.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public ArrayList<Mon> getDSMon() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/cafedb";
            String user = "mysql";
            String password = "mysql";
            Connection connection = DriverManager.getConnection(url, user, password);
            ArrayList<DanhMuc> danhMucList = getDSDanhMuc();
            DanhMuc danhMuc = null;
            ArrayList<Mon> monList = new ArrayList<>();
            statement = connection.createStatement();
            String queryString = "select * from Mon";
            rs = statement.executeQuery(queryString);
            while (rs.next()) {
                String maMon = rs.getNString("MaMon");
                String tenMon = rs.getNString("TenMon");
                String maDanhMuc = rs.getNString("MaDanhMuc");
                float gia = rs.getFloat("GiaMon");
                for (int i = 0; i < danhMucList.size(); i++) {
                    if (maDanhMuc.equals(danhMucList.get(i).getMaDanhMuc())) {
                        danhMuc = new DanhMuc(danhMucList.get(i).getMaDanhMuc(), danhMucList.get(i).getTenDanhMuc());
                    }
                }
                Mon m = new Mon(maMon, tenMon, danhMuc, gia);
                monList.add(m);
            }
            connection.close();
            return monList;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public ArrayList<Mon> getDSMon(String maDanhMuc) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/cafedb";
            String user = "mysql";
            String password = "mysql";
            Connection connection = DriverManager.getConnection(url, user, password);
            ArrayList<DanhMuc> danhMucList = getDSDanhMuc();
            DanhMuc danhMuc = null;
            ArrayList<Mon> monList = new ArrayList<>();
            preparedStatement = connection.prepareStatement("select * from Mon as a, DanhMuc as b where a.MaDanhMuc = b.MaDanhMuc and b.MaDanhMuc = ?");
            preparedStatement.setNString(1, maDanhMuc);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String maMon = rs.getNString("MaMon");
                String tenMon = rs.getNString("TenMon");
                float gia = rs.getFloat("GiaMon");
                for (int i = 0; i < danhMucList.size(); i++) {
                    if (maDanhMuc.equals(danhMucList.get(i).getMaDanhMuc())) {
                        danhMuc = new DanhMuc(danhMucList.get(i).getMaDanhMuc(), danhMucList.get(i).getTenDanhMuc());
                    }
                }
                Mon mon = new Mon(maMon, tenMon, danhMuc, gia);
                monList.add(mon);
            }
            connection.close();
            return monList;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public ArrayList<ChiTietHD> getChiTietHD(String maHoaDon) {
        try {
            ArrayList<Mon> monList = getDSMon();
            ArrayList<ChiTietHD> chiTietList = new ArrayList<>();
            Mon mon = null;
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/cafedb";
            String user = "mysql";
            String password = "mysql";
            Connection connection = DriverManager.getConnection(url, user, password);
            SQL = "select a.MaHoaDon,b.TenMon,a.SoLuongMon,b.GiaMon from ChiTietHoaDon as a, Mon as b where a.MaMon = b.MaMon and a.MaHoaDon = ?";
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, maHoaDon);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String tenMon = rs.getNString("TenMon");
                for (int i = 0; i < monList.size(); i++) {
                    if (tenMon.equals(monList.get(i).getTenMon())) {
                        mon = new Mon(monList.get(i).getMaMon(), monList.get(i).getTenMon(), monList.get(i).getDanhMuc(), monList.get(i).getGiamon());
                    }
                }
                int soLuong = rs.getInt("SoLuongMon");
                float giaMon = rs.getFloat("GiaMon");
                float thanhTien = soLuong * giaMon;
                ChiTietHD chiTietHD = new ChiTietHD(maHoaDon, mon, soLuong, thanhTien);
                chiTietList.add(chiTietHD);
            }
            connection.close();
            return chiTietList;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public ArrayList<HoaDon> sapXepGiam(ArrayList<HoaDon> arrayList, int size) {
        HoaDon[] hd = new HoaDon[size];
        ArrayList<HoaDon> hoaDonList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            hd[i] = new HoaDon(arrayList.get(i).getMaHoaDon(), arrayList.get(i).getMaBan(), arrayList.get(i).getNhanVien(),
                    arrayList.get(i).getNgayTaoHD(), arrayList.get(i).getTrangThai(), arrayList.get(i).getTongTien(), arrayList.get(i).getGiamGia(),
                    arrayList.get(i).getPhaiTra(), arrayList.get(i).getGhiChu());
        }
        for (int j = 0; j < size - 2; j++) {
            for (int k = size - 1; k >=j+1; k--) {
                if (hd[k].getPhaiTra() > hd[k-1].getPhaiTra()) {
                    HoaDon h = hd[k];
                    hd[k] = hd[k-1];
                    hd[k-1] = h;
                }
            }
        }
        for (int i = 0; i < size; i++) {
            hoaDonList.add(hd[i]);
        }
        return hoaDonList;
    }

    public ArrayList<HoaDon> sapXepTang(ArrayList<HoaDon> arrayList, int size) {
        HoaDon[] hd = new HoaDon[size];
        ArrayList<HoaDon> hoaDonList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            hd[i] = new HoaDon(arrayList.get(i).getMaHoaDon(), arrayList.get(i).getMaBan(), arrayList.get(i).getNhanVien(),
                    arrayList.get(i).getNgayTaoHD(), arrayList.get(i).getTrangThai(), arrayList.get(i).getTongTien(), arrayList.get(i).getGiamGia(),
                    arrayList.get(i).getPhaiTra(), arrayList.get(i).getGhiChu());
        }
        for (int j = 0; j < size - 2; j++) {
            for (int k = size - 1; k >= j+1; k--) {
                if (hd[k].getPhaiTra() < hd[k-1].getPhaiTra()) {
                    HoaDon h = hd[k];
                    hd[k] = hd[k-1];
                    hd[k-1] = h;
                }
            }
        }
        for (int i = 0; i < size; i++) {
            hoaDonList.add(hd[i]);
        }
        return hoaDonList;
    }

    public ArrayList<HoaDon> getDSHoaDonNgayHienTai() {
        try {
            ArrayList<HoaDon> hoaDonList = new ArrayList<>();
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/cafedb";
            String user = "mysql";
            String password = "mysql";
            Connection connection = DriverManager.getConnection(url, user, password);
            SQL = "select a.MaHoaDon,a.MaBan,b.MaNhanVien,b.TenNhanVien,a.NgayTaoHD,a.TongTien,a.TrangThaiHD,a.GiamGia,a.PhaiTra, a.GhiChu from HoaDon as a, NhanVien as b \n"
                    + "where a.MaNhanVien = b.MaNhanVien\n"
                    + "and Cast(a.NgayTaoHD as Date) = ?";
            preparedStatement = connection.prepareStatement(SQL);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            Date date = new Date();
            preparedStatement.setString(1, sdf.format(date));
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String maHoaDon = rs.getNString("MaHoaDon");
                String maBan = rs.getNString("MaBan");
                String tenNhanVien = rs.getNString("TenNhanVien");
                String maNhanVien = rs.getNString("MaNhanVien");
                NhanVien nv = new NhanVien();
                nv.setMaNhanVien(maNhanVien);
                nv.setTenNhanVien(tenNhanVien);
                Date ngayTaoHD = rs.getTimestamp("NgayTaoHD");
                String trangThai = rs.getNString("TrangThaiHD");
                float tongTien = rs.getFloat("TongTien");
                int giamGia = rs.getInt("GiamGia");
                float phaiTra = rs.getFloat("PhaiTra");
                String ghiChu = rs.getNString("GhiChu");
                HoaDon hd = new HoaDon(maHoaDon, maBan, nv, ngayTaoHD, trangThai, tongTien, giamGia, phaiTra, ghiChu);
                hoaDonList.add(hd);
            }
            connection.close();
            return hoaDonList;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public ArrayList<HoaDon> getDSHDTheoNgay(String ngayBD, String ngayKT) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/cafedb";
            String user = "mysql";
            String password = "mysql";
            Connection connection = DriverManager.getConnection(url, user, password);
            ArrayList<HoaDon> hoaDonList = new ArrayList<>();
            SQL = "select a.MaHoaDon,a.MaBan,b.MaNhanVien,b.TenNhanVien,a.NgayTaoHD,a.TongTien,a.TrangThaiHD,a.GiamGia,a.PhaiTra, a.GhiChu from HoaDon as a, NhanVien as b \n"
                    + "where a.MaNhanVien = b.MaNhanVien\n"
                    + "and Cast(a.NgayTaoHD as Date) BETWEEN CAST(? as Date) AND CAST(? as Date)";
            //+ "and (a.NgayTaoHD >= ? and a.NgayTaoHD <= ?)";
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, ngayBD);
            preparedStatement.setString(2, ngayKT);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String maHoaDon = rs.getNString("MaHoaDon");
                String maBan = rs.getNString("MaBan");
                String tenNhanVien = rs.getNString("TenNhanVien");
                String maNhanVien = rs.getNString("MaNhanVien");
                NhanVien nv = new NhanVien();
                nv.setMaNhanVien(maNhanVien);
                nv.setTenNhanVien(tenNhanVien);
                Date ngayTaoHD = rs.getTimestamp("NgayTaoHD");
                String trangThai = rs.getNString("TrangThaiHD");
                float tongTien = rs.getFloat("TongTien");
                int giamGia = rs.getInt("GiamGia");
                float phaiTra = rs.getFloat("PhaiTra");
                String ghiChu = rs.getNString("GhiChu");
                HoaDon hd = new HoaDon(maHoaDon, maBan, nv, ngayTaoHD, trangThai, tongTien, giamGia, phaiTra, ghiChu);
                hoaDonList.add(hd);
            }
            connection.close();
            return hoaDonList;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public int getSoLuongHoaDon() {
        int soLuong = 0;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/cafedb";
            String user = "mysql";
            String password = "mysql";
            Connection connection = DriverManager.getConnection(url, user, password);
            SQL = "select Count(MaHoaDon) from HoaDon";
            statement = connection.createStatement();
            rs = statement.executeQuery(SQL);
            rs.next();
            soLuong = rs.getInt(1);
            connection.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return soLuong;
    }

    public String getMaHoaDonTheoBan(String maBan) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/cafedb";
            String user = "mysql";
            String password = "mysql";
            Connection connection = DriverManager.getConnection(url, user, password);
            SQL = "select * from HoaDon as a, Ban as b\n"
                    + "where a.MaBan = b.MaBan \n"
                    + "and a.TrangThaiHD = 'Chưa thanh toán'\n"
                    + "and a.MaBan = ?";
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, maBan);
            rs = preparedStatement.executeQuery();
            rs.next();
            String mhd = rs.getNString("MaHoaDon");
            connection.close();
            return mhd;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public void insertHoaDon(String maHD, String maBan, String maNV, String ngayTao, String trangThai, float tongTien, int giamGia, float phaiTra) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/cafedb";
            String user = "mysql";
            String password = "mysql";
            Connection connection = DriverManager.getConnection(url, user, password);
            SQL = "insert into HoaDon values (?,?,?,?,?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setNString(1, maHD);
            preparedStatement.setNString(2, maBan);
            preparedStatement.setNString(3, maNV);
            preparedStatement.setNString(4, ngayTao);
            preparedStatement.setNString(5, trangThai);
            preparedStatement.setFloat(6, tongTien);
            preparedStatement.setInt(7, giamGia);
            preparedStatement.setFloat(8, phaiTra);
            preparedStatement.setNString(9, "");
            preparedStatement.executeUpdate();
            connection.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void insertChiTietHoaDon(String maHD, String maMon, int soLuong) {
        try {
            boolean check = false;
            ArrayList<ChiTietHD> arr = getChiTietHD(maHD);
            for (int i = 0; i < arr.size(); i++) {
                if (maMon.equals(arr.get(i).getMon().getMaMon())) {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    String url = "jdbc:mysql://localhost:3306/cafedb";
                    String user = "mysql";
                    String password = "mysql";
                    Connection connection = DriverManager.getConnection(url, user, password);
                    SQL = "update ChiTietHoaDon set SoLuongMon = ? where MaMon = ?";
                    int soLuongCu = arr.get(i).getSoLuong();
                    preparedStatement = connection.prepareStatement(SQL);
                    preparedStatement.setInt(1, soLuong+soLuongCu);
                    preparedStatement.setNString(2, maMon);
                    preparedStatement.executeUpdate();
                    connection.close();
                    check = true;
                }
            }
            if (check == false) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                String url = "jdbc:mysql://localhost:3306/cafedb";
                String user = "mysql";
                String password = "mysql";
                Connection connection = DriverManager.getConnection(url, user, password);
                SQL = "insert into ChiTietHoaDon values (?,?,?)";
                preparedStatement = connection.prepareStatement(SQL);
                preparedStatement.setNString(1, maHD);
                preparedStatement.setNString(2, maMon);
                preparedStatement.setInt(3, soLuong);
                preparedStatement.executeUpdate();
                connection.close();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void updateTrangThaiBan(String primaryKey, String trangThai) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/cafedb";
            String user = "mysql";
            String password = "mysql";
            Connection connection = DriverManager.getConnection(url, user, password);
            SQL = "update Ban set TrangThai = ? where MaBan  = ?";
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setNString(1, trangThai);
            preparedStatement.setNString(2, primaryKey);
            preparedStatement.executeUpdate();
            connection.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void updateHoaDon(String maHD, String trangThai, float tongTien, int giamGia, float phaiTra, String ghiChu) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/cafedb";
            String user = "mysql";
            String password = "mysql";
            Connection connection = DriverManager.getConnection(url, user, password);
            SQL = "update HoaDon set TrangThaiHD = ?, TongTien = ?, GiamGia = ?, PhaiTra = ?, GhiChu = ? where MaHoaDon  = ?";
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setNString(1, "Đã thanh toán");
            preparedStatement.setFloat(2, tongTien);
            preparedStatement.setInt(3, giamGia);
            preparedStatement.setFloat(4, phaiTra);
            preparedStatement.setNString(5, ghiChu);
            preparedStatement.setNString(6, maHD);
            preparedStatement.executeUpdate();
            connection.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void updateChuThich(String maHD, String ghiChu) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/cafedb";
            String user = "mysql";
            String password = "mysql";
            Connection connection = DriverManager.getConnection(url, user, password);
            SQL = "update HoaDon set GhiChu = ? where MaHoaDon  = ?";
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setNString(1, ghiChu);
            preparedStatement.setNString(2, maHD);
            preparedStatement.executeUpdate();
            connection.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void deleteChiTietHD(String maHoaDon, String maMon) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/cafedb";
            String user = "mysql";
            String password = "mysql";
            Connection connection = DriverManager.getConnection(url, user, password);
            SQL = "delete from ChiTietHoaDon where MaHoaDon = ? and MaMon = ?";
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setNString(1, maHoaDon);
            preparedStatement.setNString(2, maMon);
            preparedStatement.executeUpdate();
            connection.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public ArrayList<HoaDon> getDSTatCaHoaDon() {
        try {
            ArrayList<HoaDon> hoaDonList = new ArrayList<>();
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/cafedb";
            String user = "mysql";
            String password = "mysql";
            Connection connection = DriverManager.getConnection(url, user, password);
            SQL = "select a.MaHoaDon,a.MaBan,b.MaNhanVien,b.TenNhanVien,a.NgayTaoHD,a.TongTien,a.TrangThaiHD,a.GiamGia,a.PhaiTra,a.GhiChu from HoaDon as a, NhanVien as b \n"
                    + "where a.MaNhanVien = b.MaNhanVien";
            preparedStatement = connection.prepareStatement(SQL);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String maHoaDon = rs.getNString("MaHoaDon");
                String maBan = rs.getNString("MaBan");
                String tenNhanVien = rs.getNString("TenNhanVien");
                String maNhanVien = rs.getNString("MaNhanVien");
                NhanVien nv = new NhanVien();
                nv.setMaNhanVien(maNhanVien);
                nv.setTenNhanVien(tenNhanVien);
                Date ngayTaoHD = rs.getTimestamp("NgayTaoHD");
                String trangThai = rs.getNString("TrangThaiHD");
                float tongTien = rs.getFloat("TongTien");
                int giamGia = rs.getInt("GiamGia");
                float phaiTra = rs.getFloat("PhaiTra");
                String ghiChu = rs.getNString("GhiChu");
                HoaDon hd = new HoaDon(maHoaDon, maBan, nv, ngayTaoHD, trangThai, tongTien, giamGia, phaiTra, ghiChu);
                hoaDonList.add(hd);
            }
            connection.close();
            return hoaDonList;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public HoaDon getHoaDon(String maHD) {
        try {
            HoaDon hd = null;
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/cafedb";
            String user = "mysql";
            String password = "mysql";
            Connection connection = DriverManager.getConnection(url, user, password);
            SQL = "select * from HoaDon as a, NhanVien as b where a.MaNhanVien = b.MaNhanVien and a.MaHoaDon = ?";
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setNString(1, maHD);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String maHoaDon = rs.getNString("MaHoaDon");
                String maBan = rs.getNString("MaBan");
                String maNhanVien = rs.getNString("MaNhanVien");
                String tenNhanVien = rs.getNString("TenNhanVien");
                NhanVien nv = new NhanVien();
                nv.setMaNhanVien(maNhanVien);
                nv.setTenNhanVien(tenNhanVien);
                Date ngayTaoHD = rs.getTimestamp("NgayTaoHD");
                String trangThai = rs.getNString("TrangThaiHD");
                float tongTien = rs.getFloat("TongTien");
                int giamGia = rs.getInt("GiamGia");
                float phaiTra = rs.getFloat("PhaiTra");
                String ghiChu = rs.getNString("GhiChu");
                hd = new HoaDon(maHoaDon, maBan, nv, ngayTaoHD, trangThai, tongTien, giamGia, phaiTra, ghiChu);
            }
            connection.close();
            return hd;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
