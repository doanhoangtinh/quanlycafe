/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.DBConnection;
import Model.Ban;
import Model.Button;
import Model.ChiTietHD;
import Model.DanhMuc;
import Model.HoaDon;
import Model.Mon;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ASUS
 */
public class BanHang extends javax.swing.JPanel {

    static String maNhanVien;

    /**
     * Creates new form BanHang
     */
    public BanHang() {
        initComponents();
        txtMaNhanVien.setText(maNhanVien);
        loadDanhMuc();
        taoBan();
    }

    void loadChiTietHD(String maHoaDon) {
        float tongTien = 0;
        String[] title = {"MÃ HÓA ĐƠN", "MÃ MÓN", "TÊN MÓN", "SỐ LƯỢNG", "ĐƠN GIÁ", "THÀNH TIỀN"};
        DefaultTableModel defaultTableModel = new DefaultTableModel(title, 0);
        DBConnection dBConnection = new DBConnection();
        ArrayList<ChiTietHD> chiTietHDList = dBConnection.getChiTietHD(maHoaDon);
        for (int i = 0; i < chiTietHDList.size(); i++) {
            Vector vector = new Vector();
            vector.add(chiTietHDList.get(i).getMaHD());
            vector.add(chiTietHDList.get(i).getMon().getMaMon());
            vector.add(chiTietHDList.get(i).getMon().getTenMon());
            vector.add(chiTietHDList.get(i).getSoLuong());
            vector.add(chiTietHDList.get(i).getMon().getGiamon());
            vector.add(chiTietHDList.get(i).getThanhTien());
            defaultTableModel.addRow(vector);
            tongTien += chiTietHDList.get(i).getThanhTien();
        }
        tableChiTietHD.setRowHeight(30);
        tableChiTietHD.setModel(defaultTableModel);
        txtTongTien.setText(tongTien + "");
        int giamGia = Integer.parseInt((cboGiamGia.getSelectedItem().toString()));
        txtTongTienPhaiTra.setText(tongTien - (tongTien * giamGia / 100) + "");

    }

    public void loadDanhMuc() {
        DefaultListModel defaultListModel = new DefaultListModel();
        DBConnection dBConnection = new DBConnection();
        ArrayList<DanhMuc> danhMucList = dBConnection.getDSDanhMuc();
        for (int i = 0; i < danhMucList.size(); i++) {
            defaultListModel.addElement(danhMucList.get(i).getTenDanhMuc());
        }
        listDanhMuc.setModel(defaultListModel);
    }

    public void taoBan() {
        DBConnection dBConnection = new DBConnection();
        ArrayList<Ban> banList = dBConnection.getDSBan();
        for (int i = 0; i < banList.size(); i++) {
            Button btnBan = new Button();
            btnBan.setFont(new Font("Tahoma", 0, 18));
            btnBan.setText(banList.get(i).getMaBan());
            btnBan.setSoLuongCho(banList.get(i).getSoLuongCho());
            btnBan.setViTri(banList.get(i).getViTri());
            btnBan.setTrangThai(banList.get(i).getTrangThai());
            btnBan.setBackground(Color.white);
            if (btnBan.getTrangThai().equals("Có Người")) {
                btnBan.setIcon(new ImageIcon(getClass().getResource("/Picture/coffee2.png")));
            } else {
                btnBan.setIcon(new ImageIcon(getClass().getResource("/Picture/coffee1.png")));
            }
            btnBan.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (btnBan.getTrangThai().equals("Trống")) {
                        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                        Date date = new Date();
                        LocalDateTime now = LocalDateTime.now();
                        txtNgay.setText(dateTimeFormatter.format(now));
                        txtGhiChu.setEditable(true);
                        int res = JOptionPane.showConfirmDialog(null, "Bạn có muốn tạo hóa đơn cho bàn " + btnBan.getText() + "?", "Xác nhận ", JOptionPane.YES_NO_OPTION);
                        if (res == JOptionPane.YES_OPTION) {
                            int soLuong = dBConnection.getSoLuongHoaDon() + 1;
                            String maHoaDon = "HD-" + soLuong;
                            dBConnection.insertHoaDon(maHoaDon, btnBan.getText(), txtMaNhanVien.getText(), txtNgay.getText(), "Chưa thanh toán", 0, 0, 0);
                            dBConnection.updateTrangThaiBan(btnBan.getText(), "Có Người");
                            txtMaHoaDon.setText(maHoaDon);
                            txtBan.setText(btnBan.getText());
                            txtViTriBan.setText(btnBan.getViTri());
                            loadChiTietHD(maHoaDon);
                            btnBan.setTrangThai("Có Người");
                            btnBan.setIcon(new ImageIcon(getClass().getResource("/Picture/coffee2.png")));
                        }
                    } else {
                        txtGhiChu.setEditable(true);
                        txtMaHoaDon.setText(dBConnection.getMaHoaDonTheoBan(btnBan.getText()));
                        txtBan.setText(btnBan.getText());
                        txtViTriBan.setText(btnBan.getViTri());
                        loadChiTietHD(dBConnection.getMaHoaDonTheoBan(btnBan.getText()));
                        loadHDTheoBan(txtMaHoaDon.getText());
                    }
                }
            });
            panelBan.add(btnBan);

        }
    }

    void loadHDTheoBan(String maHD) {
        DBConnection dBConnection = new DBConnection();
        HoaDon hd = dBConnection.getHoaDon(maHD);
        txtNgay.setText(hd.getNgayTaoHD().toString());
        txtMaNhanVien.setText(hd.getNhanVien().getMaNhanVien());
        txtGhiChu.setText(hd.getGhiChu());
    }

    void loadMon(String maDM) {
        String[] title = {"MÃ MÓN", "TÊN MÓN", "GIÁ MÓN"};
        DefaultTableModel defaultTableModel = new DefaultTableModel(title, 0);
        DBConnection dBConnection = new DBConnection();
        ArrayList<Mon> monList = dBConnection.getDSMon(maDM);
        for (int i = 0; i < monList.size(); i++) {
            Vector vector = new Vector();
            vector.add(monList.get(i).getMaMon());
            vector.add(monList.get(i).getTenMon());
            vector.add(monList.get(i).getGiamon());
            defaultTableModel.addRow(vector);
        }
        tableDSMon.setRowHeight(30);
        tableDSMon.setModel(defaultTableModel);
    }

    void resetField() {
        txtMaHoaDon.setText("");
        txtBan.setText("");
        txtViTriBan.setText("");
        String[] title = {"MÃ HÓA ĐƠN", "MÃ MÓN", "TÊN MÓN", "SỐ LƯỢNG", "ĐƠN GIÁ", "THÀNH TIỀN"};
        DefaultTableModel defaultTableModel = new DefaultTableModel(title, 0);
        tableChiTietHD.setModel(defaultTableModel);
        txtTongTien.setText("");
        txtTongTienPhaiTra.setText("");
        txtGhiChu.setText("");
        txtGhiChu.setEditable(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelBan = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        listDanhMuc = new javax.swing.JList<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableDSMon = new javax.swing.JTable();
        btnThemMon = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        btnXoaMon = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtMaHoaDon = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtBan = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtNgay = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtMaNhanVien = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableChiTietHD = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        txtTongTienPhaiTra = new javax.swing.JTextField();
        btnInHD = new javax.swing.JButton();
        btnThanhToan = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtTongTien = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        cboGiamGia = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        txtViTriBan = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtGhiChu = new javax.swing.JTextArea();

        setBackground(new java.awt.Color(255, 255, 255));

        panelBan.setBackground(new java.awt.Color(255, 255, 255));
        panelBan.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        listDanhMuc.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        listDanhMuc.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listDanhMucValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(listDanhMuc);

        tableDSMon.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        tableDSMon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "MÃ MÓN", "TÊN MÓN", "GIÁ MÓN"
            }
        ));
        jScrollPane1.setViewportView(tableDSMon);

        btnThemMon.setBackground(new java.awt.Color(30, 100, 201));
        btnThemMon.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnThemMon.setForeground(new java.awt.Color(255, 255, 255));
        btnThemMon.setText("Thêm món");
        btnThemMon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemMonActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 0, 51));
        jLabel9.setText("DANH MỤC MÓN");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 0, 51));
        jLabel10.setText("DANH SÁCH MÓN");

        btnXoaMon.setBackground(new java.awt.Color(30, 100, 201));
        btnXoaMon.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnXoaMon.setForeground(new java.awt.Color(255, 255, 255));
        btnXoaMon.setText("Xóa món");
        btnXoaMon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaMonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jScrollPane2)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(147, 147, 147)
                                .addComponent(jLabel10))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(100, 100, 100)
                                .addComponent(btnThemMon)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnXoaMon, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(149, 149, 149)
                                .addComponent(jLabel9)))
                        .addGap(0, 90, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel10)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThemMon, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoaMon, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(76, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Hóa đơn");

        txtMaHoaDon.setEditable(false);
        txtMaHoaDon.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Bàn");

        txtBan.setEditable(false);
        txtBan.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("Ngày");

        txtNgay.setEditable(false);
        txtNgay.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("Mã nhân viên");

        txtMaNhanVien.setEditable(false);
        txtMaNhanVien.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtMaNhanVien.setHorizontalAlignment(javax.swing.JTextField.LEFT);

        tableChiTietHD.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        tableChiTietHD.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "MÃ HÓA ĐƠN", "MÃ MÓN", "TÊN MÓN", "SỐ LƯỢNG", "ĐƠN GIÁ", "THÀNH TIỀN"
            }
        ));
        jScrollPane3.setViewportView(tableChiTietHD);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText("Tổng tiền phải trả");

        txtTongTienPhaiTra.setEditable(false);
        txtTongTienPhaiTra.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        btnInHD.setBackground(new java.awt.Color(30, 100, 201));
        btnInHD.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnInHD.setForeground(new java.awt.Color(255, 255, 255));
        btnInHD.setText("In hóa đơn");
        btnInHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInHDActionPerformed(evt);
            }
        });

        btnThanhToan.setBackground(new java.awt.Color(30, 100, 201));
        btnThanhToan.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnThanhToan.setForeground(new java.awt.Color(255, 255, 255));
        btnThanhToan.setText("Thanh toán");
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 0, 51));
        jLabel8.setText("THÔNG TIN HÓA ĐƠN");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setText("Giảm giá");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setText("Tổng tiền");

        txtTongTien.setEditable(false);
        txtTongTien.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtTongTien.setText("0");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel11.setText("%");

        cboGiamGia.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cboGiamGia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "5", "10", "15", "20", "25", "30", "35", "40", "45", "50", "55", "60", "65", "70", "75", "80", "85", "90", "95", "100" }));
        cboGiamGia.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboGiamGiaItemStateChanged(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel12.setText("Vị trí");

        txtViTriBan.setEditable(false);
        txtViTriBan.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel13.setText("Ghi chú");

        txtGhiChu.setEditable(false);
        txtGhiChu.setColumns(20);
        txtGhiChu.setFont(new java.awt.Font("Monospaced", 0, 16)); // NOI18N
        txtGhiChu.setRows(5);
        txtGhiChu.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtGhiChuCaretUpdate(evt);
            }
        });
        jScrollPane4.setViewportView(txtGhiChu);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4))
                                .addGap(15, 15, 15)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtMaNhanVien)
                                    .addComponent(txtNgay)
                                    .addComponent(txtMaHoaDon)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(txtBan, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel12)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                                        .addComponent(txtViTriBan, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(119, 119, 119)
                        .addComponent(jLabel8)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(cboGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel11))
                                    .addComponent(txtTongTienPhaiTra, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel13))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnInHD, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtMaHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(txtViTriBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtNgay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtMaNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13)
                .addGap(2, 2, 2)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel11)
                    .addComponent(cboGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtTongTienPhaiTra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnInHD, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelBan, javax.swing.GroupLayout.PREFERRED_SIZE, 632, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelBan, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemMonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemMonActionPerformed
        // TODO add your handling code here:
        try {
            String maHD = txtMaHoaDon.getText();
            if (!maHD.equals("")) {
                String maMon = tableDSMon.getValueAt(tableDSMon.getSelectedRow(), 0).toString();
                int soLuong = Integer.parseInt(JOptionPane.showInputDialog(null, "Nhập số lượng"));
                DBConnection dBConnection = new DBConnection();
                dBConnection.insertChiTietHoaDon(maHD, maMon, soLuong);
                loadChiTietHD(txtMaHoaDon.getText());
            } else {
                JOptionPane.showMessageDialog(null, "Bạn phải chọn bàn bạn muốn thêm!!!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Bạn phải chọn món bạn muốn thêm!!!");
        }
    }//GEN-LAST:event_btnThemMonActionPerformed

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
        // TODO add your handling code here:
        try {
            int res = JOptionPane.showConfirmDialog(null, "Bạn có muốn thanh toán hóa đơn ", "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (res == JOptionPane.YES_OPTION) {
                DBConnection dBConnection = new DBConnection();
                String maHoaDon = txtMaHoaDon.getText();
                String trangThai = "Đã thanh toán";
                float tongTien = Float.parseFloat(txtTongTien.getText());
                int giamGia = Integer.parseInt((cboGiamGia.getSelectedItem().toString()));
                float phaiTra = Float.parseFloat(txtTongTienPhaiTra.getText());
                String ghiChu = txtGhiChu.getText();
                dBConnection.updateHoaDon(maHoaDon, trangThai, tongTien, giamGia, phaiTra, ghiChu);
                dBConnection.updateTrangThaiBan(txtBan.getText(), "Trống");
                panelBan.setVisible(false);
                panelBan.removeAll();
                panelBan.setVisible(true);
                taoBan();
                resetField();
                JOptionPane.showMessageDialog(null, "Thanh toán thành công");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Bạn phải chọn bàn bạn muốn thanh toán");
        }
    }//GEN-LAST:event_btnThanhToanActionPerformed

    private void listDanhMucValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listDanhMucValueChanged
        // TODO add your handling code here:
        if (evt.getValueIsAdjusting()) {
            DBConnection dBConnection = new DBConnection();
            String maDM = null;
            ArrayList<DanhMuc> arr = dBConnection.getDSDanhMuc();
            for (int i = 0; i < arr.size(); i++) {
                if (listDanhMuc.getSelectedValue().equals(arr.get(i).getTenDanhMuc())) {
                    maDM = arr.get(i).getMaDanhMuc();
                }
            }
            loadMon(maDM);
        }
    }//GEN-LAST:event_listDanhMucValueChanged

    private void btnXoaMonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaMonActionPerformed
        // TODO add your handling code here:
        try {
            DBConnection dBConnection = new DBConnection();
            String maHoaDon = tableChiTietHD.getValueAt(tableChiTietHD.getSelectedRow(), 0).toString();
            String maMon = tableChiTietHD.getValueAt(tableChiTietHD.getSelectedRow(), 1).toString();
            int res = JOptionPane.showConfirmDialog(this, "Bạn có muốn tiếp tục?", "Xác nhận ", JOptionPane.YES_NO_OPTION);
            if (res == JOptionPane.YES_OPTION) {
                dBConnection.deleteChiTietHD(maHoaDon, maMon);
                loadChiTietHD(maHoaDon);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Bạn phải chọn món trong bảng chi tiết hóa đơn");
        }
    }//GEN-LAST:event_btnXoaMonActionPerformed

    private void cboGiamGiaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboGiamGiaItemStateChanged
        // TODO add your handling code here:
        float tongTien = Float.parseFloat(txtTongTien.getText());
        int giamGia = Integer.parseInt((cboGiamGia.getSelectedItem().toString()));
        txtTongTienPhaiTra.setText(tongTien - (tongTien * giamGia / 100) + "");
    }//GEN-LAST:event_cboGiamGiaItemStateChanged

    private void txtGhiChuCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtGhiChuCaretUpdate
        // TODO add your handling code here:
        DBConnection dBConnection = new DBConnection();
        String maHoaDon = txtMaHoaDon.getText();
        String ghiChu = txtGhiChu.getText();
        dBConnection.updateChuThich(maHoaDon, ghiChu);
    }//GEN-LAST:event_txtGhiChuCaretUpdate

    private void btnInHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInHDActionPerformed

        if (txtMaHoaDon.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Không có hóa đơn để xuất");
        } else {
            try {

                int res = JOptionPane.showConfirmDialog(null, "Bạn có muốn xuất hóa đơn ", "Xác nhận", JOptionPane.YES_NO_OPTION);
                if (res == JOptionPane.YES_OPTION) {
                    Document document = new Document();
                    PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\ASUS\\Documents\\LuuTruHoaDon\\" + txtMaHoaDon.getText() + ".pdf"));
                    document.open();

                    com.itextpdf.text.Font fontTenQuan = new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.TIMES_ROMAN, 20, com.itextpdf.text.Font.BOLD);
                    Paragraph tenQuan = new Paragraph("QUAN CAFE NLCSNKTPM", fontTenQuan);
                    tenQuan.setAlignment(Element.ALIGN_CENTER);

                    com.itextpdf.text.Font fontDiaChi = new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.TIMES_ROMAN, 14, com.itextpdf.text.Font.NORMAL);
                    Paragraph diaChi = new Paragraph("Dia chi: Ky Tuc Xa Khu B Dai Hoc Can Tho, Duong 3/2, Phuong Xuan Khanh, Quan Ninh Kieu, TP Can Tho", fontDiaChi);
                    diaChi.setAlignment(Element.ALIGN_CENTER);

                    com.itextpdf.text.Font fontHD = new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.TIMES_ROMAN, 16, com.itextpdf.text.Font.BOLD);
                    Paragraph hoaDon = new Paragraph("HOA DON THANH TOAN", fontHD);
                    hoaDon.setAlignment(Element.ALIGN_CENTER);

                    com.itextpdf.text.Font fontTT = new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.TIMES_ROMAN, 14, com.itextpdf.text.Font.BOLD);
                    Paragraph mahoaDon = new Paragraph("Ma hoa don: " + txtMaHoaDon.getText(), fontHD);
                    mahoaDon.setAlignment(Element.ALIGN_LEFT);

                    Paragraph ban = new Paragraph("Ban: " + txtBan.getText() + " Vi tri: " + txtViTriBan.getText(), fontTT);
                    ban.setAlignment(Element.ALIGN_LEFT);

                    Paragraph ngay = new Paragraph("Ngay: " + txtNgay.getText(), fontTT);
                    ngay.setAlignment(Element.ALIGN_LEFT);

                    Paragraph maNV = new Paragraph("Ma nhan vien: " + txtMaNhanVien.getText(), fontTT);
                    maNV.setAlignment(Element.ALIGN_LEFT);

                    Paragraph ghiChu = new Paragraph("Ghi chu: " + txtGhiChu.getText(), fontTT);
                    ghiChu.setAlignment(Element.ALIGN_LEFT);

                    Paragraph tongTien = new Paragraph("Tong tien: " + txtTongTien.getText(), fontTT);
                    tongTien.setAlignment(Element.ALIGN_LEFT);

                    Paragraph giamGia = new Paragraph("Giam gia: " + cboGiamGia.getSelectedItem() + " %", fontTT);
                    giamGia.setAlignment(Element.ALIGN_LEFT);

                    Paragraph phaiTra = new Paragraph("Tong tien phai tra: " + txtTongTienPhaiTra.getText(), fontTT);
                    phaiTra.setAlignment(Element.ALIGN_LEFT);

                    document.add(tenQuan);
                    document.add(diaChi);
                    document.add(hoaDon);
                    document.add(mahoaDon);
                    document.add(ban);
                    document.add(ngay);
                    document.add(maNV);
                    Paragraph trong = new Paragraph("-----------------------------------------------------------------------------------------", fontTT);
                    Paragraph title = new Paragraph("Thanh tien   Don gia    SL      Ten mon ", fontTT);
                    document.add(title);

                    for (int i = 0; i < tableChiTietHD.getRowCount(); i++) {
                        String thanhTien = String.format("%-15s", tableChiTietHD.getValueAt(i, 5).toString());
                        String donGia = String.format("%-12s", tableChiTietHD.getValueAt(i, 4).toString());
                        String soLuong = String.format("%-12s", tableChiTietHD.getValueAt(i, 3).toString());
                        String tenMon = String.format(tableChiTietHD.getValueAt(i, 2).toString());
                        Paragraph chiTiet = new Paragraph(thanhTien + donGia + soLuong + tenMon, fontTT);
                        document.add(trong);
                        document.add(chiTiet);
                    }
                    document.add(trong);
                    document.add(ghiChu);
                    document.add(tongTien);
                    document.add(giamGia);
                    document.add(phaiTra);

                    document.close();

                    File file = new File("C:\\Users\\ASUS\\Documents\\LuuTruHoaDon\\" + txtMaHoaDon.getText() + ".pdf");
                    Desktop desktop = Desktop.getDesktop();
                    desktop.open(file);

                    JOptionPane.showMessageDialog(null, "Xuất hóa đơn thành công");
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }
    }//GEN-LAST:event_btnInHDActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnInHD;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JButton btnThemMon;
    private javax.swing.JButton btnXoaMon;
    private javax.swing.JComboBox<String> cboGiamGia;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JList<String> listDanhMuc;
    private javax.swing.JPanel panelBan;
    private javax.swing.JTable tableChiTietHD;
    private javax.swing.JTable tableDSMon;
    private javax.swing.JTextField txtBan;
    private javax.swing.JTextArea txtGhiChu;
    private javax.swing.JTextField txtMaHoaDon;
    private javax.swing.JTextField txtMaNhanVien;
    private javax.swing.JTextField txtNgay;
    private javax.swing.JTextField txtTongTien;
    private javax.swing.JTextField txtTongTienPhaiTra;
    private javax.swing.JTextField txtViTriBan;
    // End of variables declaration//GEN-END:variables
}
