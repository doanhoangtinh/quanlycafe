/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Date;

/**
 *
 * @author ASUS
 */
public class HoaDon {

    String maHoaDon;
    String maBan;
    NhanVien nhanVien;
    Date ngayTaoHD;
    String trangThai;
    float tongTien;
    int giamGia;
    float phaiTra;
    String ghiChu;

    public HoaDon(String maHoaDon, String maBan, NhanVien nhanVien, Date ngayTaoHD, String trangThai, float tongTien, int giamGia, float phaiTra, String ghiChu) {
        this.maHoaDon = maHoaDon;
        this.maBan = maBan;
        this.nhanVien = nhanVien;
        this.ngayTaoHD = ngayTaoHD;
        this.trangThai = trangThai;
        this.tongTien = tongTien;
        this.giamGia = giamGia;
        this.phaiTra = phaiTra;
        this.ghiChu = ghiChu;
    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public String getMaBan() {
        return maBan;
    }

    public void setMaBan(String maBan) {
        this.maBan = maBan;
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    public Date getNgayTaoHD() {
        return ngayTaoHD;
    }

    public void setNgayTaoHD(Date ngayTaoHD) {
        this.ngayTaoHD = ngayTaoHD;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public float getTongTien() {
        return tongTien;
    }

    public void setTongTien(float tongTien) {
        this.tongTien = tongTien;
    }

    public int getGiamGia() {
        return giamGia;
    }

    public void setGiamGia(int giamGia) {
        this.giamGia = giamGia;
    }

    public float getPhaiTra() {
        return phaiTra;
    }

    public void setPhaiTra(float phaiTra) {
        this.phaiTra = phaiTra;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

}
