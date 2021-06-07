/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.DBConnection;
import Model.ChiTietHD;
import Model.HoaDon;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ASUS
 */
public class ThongKe extends javax.swing.JPanel {
    int status1 = 0;
    int status2 = 0;
    /**
     * Creates new form ThongKe
     */
    public ThongKe() {
        initComponents();
        loadHoaDonNgayHomNay();
        loadTatCaHoaDon();
    }

    void loadHoaDonNgayHomNay() {
        float tongTien = 0;
        String[] title = {"MÃ HÓA ĐƠN", "MÃ BÀN", "TÊN NHÂN VIÊN", "NGÀY TẠO HÓA ĐƠN", "TỔNG TIỀN", "TRẠNG THÁI", "GIẢM GIÁ", "PHẢI TRẢ"};
        DefaultTableModel defaultTableModel = new DefaultTableModel(title, 0);
        DBConnection dBConnection = new DBConnection();
        ArrayList<HoaDon> hoaDonList = dBConnection.getDSHoaDonNgayHienTai();
        for (int i = 0; i < hoaDonList.size(); i++) {
            Vector vector = new Vector();
            vector.add(hoaDonList.get(i).getMaHoaDon());
            vector.add(hoaDonList.get(i).getMaBan());
            vector.add(hoaDonList.get(i).getNhanVien().getTenNhanVien());
            vector.add(hoaDonList.get(i).getNgayTaoHD());
            vector.add(hoaDonList.get(i).getTongTien());
            vector.add(hoaDonList.get(i).getTrangThai());
            vector.add(hoaDonList.get(i).getGiamGia());
            vector.add(hoaDonList.get(i).getPhaiTra());
            defaultTableModel.addRow(vector);
            tongTien += hoaDonList.get(i).getPhaiTra();
        }
        tableHDNgayHienTai.setRowHeight(35);
        tableHDNgayHienTai.setModel(defaultTableModel);
        txtTongHDNgayHomNay.setText(hoaDonList.size() + "");
        txtTongTienNgayHomNay.setText(tongTien + "");
    }

    void loadTatCaHoaDon() {
        float tongTien = 0;
        String[] title = {"MÃ HÓA ĐƠN", "MÃ BÀN", "TÊN NHÂN VIÊN", "NGÀY TẠO HÓA ĐƠN", "TỔNG TIỀN", "TRẠNG THÁI", "GIẢM GIÁ", "PHẢI TRẢ"};
        DefaultTableModel defaultTableModel = new DefaultTableModel(title, 0);
        DBConnection dBConnection = new DBConnection();
        ArrayList<HoaDon> hoaDonList = dBConnection.getDSTatCaHoaDon();
        for (int i = 0; i < hoaDonList.size(); i++) {
            Vector vector = new Vector();
            vector.add(hoaDonList.get(i).getMaHoaDon());
            vector.add(hoaDonList.get(i).getMaBan());
            vector.add(hoaDonList.get(i).getNhanVien().getTenNhanVien());
            vector.add(hoaDonList.get(i).getNgayTaoHD());
            vector.add(hoaDonList.get(i).getTongTien());
            vector.add(hoaDonList.get(i).getTrangThai());
            vector.add(hoaDonList.get(i).getGiamGia());
            vector.add(hoaDonList.get(i).getPhaiTra());
            defaultTableModel.addRow(vector);
            tongTien += hoaDonList.get(i).getPhaiTra();
        }
        tableHDTheoNgay.setRowHeight(35);
        tableHDTheoNgay.setModel(defaultTableModel);
        txtTongSoHD.setText(hoaDonList.size() + "");
        txtTongTien.setText(tongTien + "");
    }

    void loadHoaDonTheoNgay(String ngayBD, String ngayKT) {
        float tongTien = 0;
        String[] title = {"MÃ HÓA ĐƠN", "MÃ BÀN", "TÊN NHÂN VIÊN", "NGÀY TẠO HÓA ĐƠN", "TỔNG TIỀN", "TRẠNG THÁI", "GIẢM GIÁ", "PHẢI TRẢ"};
        DefaultTableModel defaultTableModel = new DefaultTableModel(title, 0);
        DBConnection dBConnection = new DBConnection();
        ArrayList<HoaDon> hoaDonList = dBConnection.getDSHDTheoNgay(ngayBD, ngayKT);
        for (int i = 0; i < hoaDonList.size(); i++) {
            Vector vector = new Vector();
            vector.add(hoaDonList.get(i).getMaHoaDon());
            vector.add(hoaDonList.get(i).getMaBan());
            vector.add(hoaDonList.get(i).getNhanVien().getTenNhanVien());
            vector.add(hoaDonList.get(i).getNgayTaoHD());
            vector.add(hoaDonList.get(i).getTongTien());
            vector.add(hoaDonList.get(i).getTrangThai());
            vector.add(hoaDonList.get(i).getGiamGia());
            vector.add(hoaDonList.get(i).getPhaiTra());
            defaultTableModel.addRow(vector);
            tongTien += hoaDonList.get(i).getPhaiTra();
        }
        tableHDTheoNgay.setRowHeight(35);
        tableHDTheoNgay.setModel(defaultTableModel);
        txtTongSoHD.setText(hoaDonList.size() + "");
        txtTongTien.setText(tongTien + "");
    }

    void loadChiTietHoaDon(String maHoaDon) {
        String[] title = {"MÃ HÓA ĐƠN", "TÊN MÓN", "SỐ LƯỢNG", "ĐƠN GIÁ", "THÀNH TIỀN"};
        DefaultTableModel defaultTableModel = new DefaultTableModel(title, 0);
        DBConnection dBConnection = new DBConnection();
        ArrayList<ChiTietHD> chiTietHDList = dBConnection.getChiTietHD(maHoaDon);
        for (int i = 0; i < chiTietHDList.size(); i++) {
            Vector vector = new Vector();
            vector.add(chiTietHDList.get(i).getMaHD());
            vector.add(chiTietHDList.get(i).getMon().getTenMon());
            vector.add(chiTietHDList.get(i).getSoLuong());
            vector.add(chiTietHDList.get(i).getMon().getGiamon());
            vector.add(chiTietHDList.get(i).getThanhTien());
            defaultTableModel.addRow(vector);
        }
        tableChiTietHD.setRowHeight(35);
        tableChiTietHD.setModel(defaultTableModel);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableHDNgayHienTai = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtTongHDNgayHomNay = new javax.swing.JTextField();
        txtTongTienNgayHomNay = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        btnXemChiTietHD1 = new javax.swing.JButton();
        btnSapXep1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableHDTheoNgay = new javax.swing.JTable();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        btnXemHDTheoNgay = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtTongSoHD = new javax.swing.JTextField();
        txtTongTien = new javax.swing.JTextField();
        btnXemChiTietHD2 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        btnSapXep2 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableChiTietHD = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        tableHDNgayHienTai.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tableHDNgayHienTai.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tableHDNgayHienTai);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 0, 51));
        jLabel1.setText("THÔNG TIN HÓA ĐƠN TRONG NGÀY HÔM NAY");

        jLabel2.setFont(new java.awt.Font("Tahoma", 2, 17)); // NOI18N
        jLabel2.setText("Tổng số hóa đơn bán ra trong ngày hôm nay");

        jLabel3.setFont(new java.awt.Font("Tahoma", 2, 17)); // NOI18N
        jLabel3.setText("Tổng tiền hóa đơn hôm nay");

        txtTongHDNgayHomNay.setEditable(false);
        txtTongHDNgayHomNay.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtTongHDNgayHomNay.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txtTongTienNgayHomNay.setEditable(false);
        txtTongTienNgayHomNay.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtTongTienNgayHomNay.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel4.setFont(new java.awt.Font("Tahoma", 2, 17)); // NOI18N
        jLabel4.setText("Xem thông tin chi tiết hóa đơn");

        btnXemChiTietHD1.setBackground(new java.awt.Color(255, 255, 255));
        btnXemChiTietHD1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnXemChiTietHD1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Search.png"))); // NOI18N
        btnXemChiTietHD1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXemChiTietHD1ActionPerformed(evt);
            }
        });

        btnSapXep1.setBackground(new java.awt.Color(255, 255, 255));
        btnSapXep1.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        btnSapXep1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/swap.png"))); // NOI18N
        btnSapXep1.setText("Sắp xếp");
        btnSapXep1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSapXep1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(186, 186, 186)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(209, 209, 209))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnXemChiTietHD1)
                        .addGap(18, 18, 18)
                        .addComponent(btnSapXep1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTongHDNgayHomNay, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTongTienNgayHomNay))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTongHDNgayHomNay, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtTongTienNgayHomNay, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnXemChiTietHD1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSapXep1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        tableHDTheoNgay.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tableHDTheoNgay.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tableHDTheoNgay);

        jDateChooser1.setDate(new Date("2020/01/01"));
        jDateChooser1.setDateFormatString("yyyy/MM/dd");
        jDateChooser1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 0, 51));
        jLabel5.setText("Xem doanh thu từ ngày");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 0, 51));
        jLabel6.setText("đến ngày");

        jDateChooser2.setDate(new Date());
        jDateChooser2.setDateFormatString("yyyy/MM/dd");
        jDateChooser2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        btnXemHDTheoNgay.setBackground(new java.awt.Color(255, 255, 255));
        btnXemHDTheoNgay.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnXemHDTheoNgay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Search.png"))); // NOI18N
        btnXemHDTheoNgay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXemHDTheoNgayActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 2, 17)); // NOI18N
        jLabel8.setText("Tổng số hóa đơn");

        jLabel9.setFont(new java.awt.Font("Tahoma", 2, 17)); // NOI18N
        jLabel9.setText("Tổng tiền");

        txtTongSoHD.setEditable(false);
        txtTongSoHD.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtTongSoHD.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txtTongTien.setEditable(false);
        txtTongTien.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtTongTien.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        btnXemChiTietHD2.setBackground(new java.awt.Color(255, 255, 255));
        btnXemChiTietHD2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnXemChiTietHD2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Search.png"))); // NOI18N
        btnXemChiTietHD2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXemChiTietHD2ActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 2, 17)); // NOI18N
        jLabel10.setText("Xem thông tin chi tiết hóa đơn");

        btnSapXep2.setBackground(new java.awt.Color(255, 255, 255));
        btnSapXep2.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        btnSapXep2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/swap.png"))); // NOI18N
        btnSapXep2.setText("Sắp xếp");
        btnSapXep2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSapXep2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtTongSoHD, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnXemChiTietHD2)
                                .addGap(18, 18, 18)
                                .addComponent(btnSapXep2)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnXemHDTheoNgay)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnXemHDTheoNgay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jDateChooser2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTongSoHD, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                        .addComponent(btnXemChiTietHD2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(btnSapXep2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        tableChiTietHD.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tableChiTietHD.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "MÃ HÓA ĐƠN", "TÊN MÓN", "SỐ LƯỢNG", "ĐƠN GIÁ", "THÀNH TIỀN"
            }
        ));
        jScrollPane3.setViewportView(tableChiTietHD);

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 0, 51));
        jLabel7.setText("THÔNG TIN CHI TIẾT HÓA ĐƠN");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(619, 619, 619)
                .addComponent(jLabel7)
                .addContainerGap(680, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 1280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(141, 141, 141))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addGap(27, 27, 27)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 267, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(13, 13, 13)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnXemChiTietHD1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXemChiTietHD1ActionPerformed
        // TODO add your handling code here:
        try {
            loadChiTietHoaDon(tableHDNgayHienTai.getValueAt(tableHDNgayHienTai.getSelectedRow(), 0).toString());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn hàng bạn muốn xem");
        }
    }//GEN-LAST:event_btnXemChiTietHD1ActionPerformed

    private void btnXemHDTheoNgayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXemHDTheoNgayActionPerformed
        // TODO add your handling code here:
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            loadHoaDonTheoNgay(dateFormat.format(jDateChooser1.getDate()), dateFormat.format(jDateChooser2.getDate()));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn ngày bạn muốn xem");
        }
    }//GEN-LAST:event_btnXemHDTheoNgayActionPerformed

    private void btnXemChiTietHD2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXemChiTietHD2ActionPerformed
        // TODO add your handling code here:
        try {
            loadChiTietHoaDon(tableHDTheoNgay.getValueAt(tableHDTheoNgay.getSelectedRow(), 0).toString());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn hàng bạn muốn xem");
        }
    }//GEN-LAST:event_btnXemChiTietHD2ActionPerformed

    private void btnSapXep1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSapXep1ActionPerformed
        // TODO add your handling code here:       
        if (status1 == 0) {
            float tongTien = 0;
            String[] title = {"MÃ HÓA ĐƠN", "MÃ BÀN", "TÊN NHÂN VIÊN", "NGÀY TẠO HÓA ĐƠN", "TỔNG TIỀN", "TRẠNG THÁI", "GIẢM GIÁ", "PHẢI TRẢ"};
            DefaultTableModel defaultTableModel = new DefaultTableModel(title, 0);
            DBConnection dBConnection = new DBConnection();
            ArrayList<HoaDon> hoaDon = dBConnection.getDSHoaDonNgayHienTai();
            ArrayList<HoaDon> hoaDonList = dBConnection.sapXepTang(hoaDon, hoaDon.size());
            for (int i = 0; i < hoaDonList.size(); i++) {
                Vector vector = new Vector();
                vector.add(hoaDonList.get(i).getMaHoaDon());
                vector.add(hoaDonList.get(i).getMaBan());
                vector.add(hoaDonList.get(i).getNhanVien().getTenNhanVien());
                vector.add(hoaDonList.get(i).getNgayTaoHD());
                vector.add(hoaDonList.get(i).getTongTien());
                vector.add(hoaDonList.get(i).getTrangThai());
                vector.add(hoaDonList.get(i).getGiamGia());
                vector.add(hoaDonList.get(i).getPhaiTra());
                defaultTableModel.addRow(vector);
                tongTien += hoaDonList.get(i).getPhaiTra();
            }
            tableHDNgayHienTai.setRowHeight(35);
            tableHDNgayHienTai.setModel(defaultTableModel);
            txtTongHDNgayHomNay.setText(hoaDonList.size() + "");
            txtTongTienNgayHomNay.setText(tongTien + "");
            status1 = 1;

        } else {
            float tongTien = 0;
            String[] title = {"MÃ HÓA ĐƠN", "MÃ BÀN", "TÊN NHÂN VIÊN", "NGÀY TẠO HÓA ĐƠN", "TỔNG TIỀN", "TRẠNG THÁI", "GIẢM GIÁ", "PHẢI TRẢ"};
            DefaultTableModel defaultTableModel = new DefaultTableModel(title, 0);
            DBConnection dBConnection = new DBConnection();
            ArrayList<HoaDon> hoaDon = dBConnection.getDSHoaDonNgayHienTai();
            ArrayList<HoaDon> hoaDonList = dBConnection.sapXepGiam(hoaDon, hoaDon.size());
            for (int i = 0; i < hoaDonList.size(); i++) {
                Vector vector = new Vector();
                vector.add(hoaDonList.get(i).getMaHoaDon());
                vector.add(hoaDonList.get(i).getMaBan());
                vector.add(hoaDonList.get(i).getNhanVien().getTenNhanVien());
                vector.add(hoaDonList.get(i).getNgayTaoHD());
                vector.add(hoaDonList.get(i).getTongTien());
                vector.add(hoaDonList.get(i).getTrangThai());
                vector.add(hoaDonList.get(i).getGiamGia());
                vector.add(hoaDonList.get(i).getPhaiTra());
                defaultTableModel.addRow(vector);
                tongTien += hoaDonList.get(i).getPhaiTra();
            }
            tableHDNgayHienTai.setRowHeight(35);
            tableHDNgayHienTai.setModel(defaultTableModel);
            txtTongHDNgayHomNay.setText(hoaDonList.size() + "");
            txtTongTienNgayHomNay.setText(tongTien + "");
            status1 = 0;
        }
    }//GEN-LAST:event_btnSapXep1ActionPerformed

    private void btnSapXep2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSapXep2ActionPerformed
        // TODO add your handling code here:
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String ngayBD = dateFormat.format(jDateChooser1.getDate());
        String ngayKT = dateFormat.format(jDateChooser2.getDate());
        if (status2 == 0) {
            float tongTien = 0;
            String[] title = {"MÃ HÓA ĐƠN", "MÃ BÀN", "TÊN NHÂN VIÊN", "NGÀY TẠO HÓA ĐƠN", "TỔNG TIỀN", "TRẠNG THÁI", "GIẢM GIÁ", "PHẢI TRẢ"};
            DefaultTableModel defaultTableModel = new DefaultTableModel(title, 0);
            DBConnection dBConnection = new DBConnection();
            ArrayList<HoaDon> hoaDon = dBConnection.getDSHDTheoNgay(ngayBD, ngayKT);
            ArrayList<HoaDon> hoaDonList = dBConnection.sapXepGiam(hoaDon, hoaDon.size());
            for (int i = 0; i < hoaDonList.size(); i++) {
                Vector vector = new Vector();
                vector.add(hoaDonList.get(i).getMaHoaDon());
                vector.add(hoaDonList.get(i).getMaBan());
                vector.add(hoaDonList.get(i).getNhanVien().getTenNhanVien());
                vector.add(hoaDonList.get(i).getNgayTaoHD());
                vector.add(hoaDonList.get(i).getTongTien());
                vector.add(hoaDonList.get(i).getTrangThai());
                vector.add(hoaDonList.get(i).getGiamGia());
                vector.add(hoaDonList.get(i).getPhaiTra());
                defaultTableModel.addRow(vector);
                tongTien += hoaDonList.get(i).getPhaiTra();
            }
            tableHDTheoNgay.setRowHeight(35);
            tableHDTheoNgay.setModel(defaultTableModel);
            txtTongSoHD.setText(hoaDonList.size() + "");
            txtTongTien.setText(tongTien + "");
            status2 = 1;

        } else {
            float tongTien = 0;
            String[] title = {"MÃ HÓA ĐƠN", "MÃ BÀN", "TÊN NHÂN VIÊN", "NGÀY TẠO HÓA ĐƠN", "TỔNG TIỀN", "TRẠNG THÁI", "GIẢM GIÁ", "PHẢI TRẢ"};
            DefaultTableModel defaultTableModel = new DefaultTableModel(title, 0);
            DBConnection dBConnection = new DBConnection();
            ArrayList<HoaDon> hoaDon = dBConnection.getDSHDTheoNgay(ngayBD, ngayKT);
            ArrayList<HoaDon> hoaDonList = dBConnection.sapXepTang(hoaDon, hoaDon.size());
            for (int i = 0; i < hoaDonList.size(); i++) {
                Vector vector = new Vector();
                vector.add(hoaDonList.get(i).getMaHoaDon());
                vector.add(hoaDonList.get(i).getMaBan());
                vector.add(hoaDonList.get(i).getNhanVien().getTenNhanVien());
                vector.add(hoaDonList.get(i).getNgayTaoHD());
                vector.add(hoaDonList.get(i).getTongTien());
                vector.add(hoaDonList.get(i).getTrangThai());
                vector.add(hoaDonList.get(i).getGiamGia());
                vector.add(hoaDonList.get(i).getPhaiTra());
                defaultTableModel.addRow(vector);
                tongTien += hoaDonList.get(i).getPhaiTra();
            }
            tableHDTheoNgay.setRowHeight(35);
            tableHDTheoNgay.setModel(defaultTableModel);
            txtTongSoHD.setText(hoaDonList.size() + "");
            txtTongTien.setText(tongTien + "");
            status2 = 0;
        }

    }//GEN-LAST:event_btnSapXep2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSapXep1;
    private javax.swing.JButton btnSapXep2;
    private javax.swing.JButton btnXemChiTietHD1;
    private javax.swing.JButton btnXemChiTietHD2;
    private javax.swing.JButton btnXemHDTheoNgay;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tableChiTietHD;
    private javax.swing.JTable tableHDNgayHienTai;
    private javax.swing.JTable tableHDTheoNgay;
    private javax.swing.JTextField txtTongHDNgayHomNay;
    private javax.swing.JTextField txtTongSoHD;
    private javax.swing.JTextField txtTongTien;
    private javax.swing.JTextField txtTongTienNgayHomNay;
    // End of variables declaration//GEN-END:variables
}
