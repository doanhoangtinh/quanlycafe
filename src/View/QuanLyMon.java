/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.DBConnection;
import Model.Mon;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ASUS
 */
public class QuanLyMon extends javax.swing.JPanel {

    ThemMon themMon;
    SuaMon suaMon;

    /**
     * Creates new form QuanLyNV
     */
    public QuanLyMon() {
        initComponents();
        loadMon();
    }

    static void loadMon() {

        String[] array = {"Mã Món", "Tên Món", "Danh Mục", "Giá Món"};
        DefaultTableModel defaultTableModel = new DefaultTableModel(array, 0);
        DBConnection dBConnection = new DBConnection();
        ArrayList<Mon> monList = dBConnection.getDSMon();
        for (int i = 0; i < monList.size(); i++) {
            Vector vector = new Vector();
            vector.add(monList.get(i).getMaMon());
            vector.add(monList.get(i).getTenMon());
            vector.add(monList.get(i).getDanhMuc().getTenDanhMuc());
            vector.add(monList.get(i).getGiamon());
            defaultTableModel.addRow(vector);
        }
        tableQuanLyMon.setRowHeight(35);
        tableQuanLyMon.setModel(defaultTableModel);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tableQuanLyMon = new javax.swing.JTable();
        btnSua = new javax.swing.JLabel();
        btnThem = new javax.swing.JLabel();
        btnXoa = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        tableQuanLyMon.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tableQuanLyMon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tableQuanLyMon);

        btnSua.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Sua.png"))); // NOI18N
        btnSua.setText("Sửa");
        btnSua.setPreferredSize(new java.awt.Dimension(81, 32));
        btnSua.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnSuaMousePressed(evt);
            }
        });

        btnThem.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Them.png"))); // NOI18N
        btnThem.setText("Thêm");
        btnThem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnThemMousePressed(evt);
            }
        });

        btnXoa.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Xoa.png"))); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.setPreferredSize(new java.awt.Dimension(81, 32));
        btnXoa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnXoaMousePressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(383, 383, 383)
                        .addComponent(btnThem)
                        .addGap(18, 18, 18)
                        .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 528, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 630, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 119, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThem)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(49, 49, 49))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemMousePressed
        // TODO add your handling code here:
        themMon = new ThemMon(null, true);
        themMon.setVisible(true);
    }//GEN-LAST:event_btnThemMousePressed

    private void btnSuaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSuaMousePressed
        // TODO add your handling code here:
        try {
            SuaMon.maMon = tableQuanLyMon.getValueAt(tableQuanLyMon.getSelectedRow(), 0).toString();
            SuaMon.tenMon = tableQuanLyMon.getValueAt(tableQuanLyMon.getSelectedRow(), 1).toString();
            SuaMon.gia = Float.parseFloat(tableQuanLyMon.getValueAt(tableQuanLyMon.getSelectedRow(), 3).toString());
            suaMon = new SuaMon(null, true);
            suaMon.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Vui lòng chọn hàng bạn muốn chỉnh sửa!!!");
        }
    }//GEN-LAST:event_btnSuaMousePressed

    private void btnXoaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXoaMousePressed
        // TODO add your handling code here:
        String mamon = tableQuanLyMon.getValueAt(tableQuanLyMon.getSelectedRow(), 0).toString();        
        DBConnection dBConnection = new DBConnection();
        int res = JOptionPane.showConfirmDialog(this, "Bạn có muốn tiếp tục?", "Xác nhận ", JOptionPane.YES_NO_OPTION);
        if (res == JOptionPane.YES_OPTION) {
            dBConnection.deleteMon(mamon);
            loadMon();
        }
    }//GEN-LAST:event_btnXoaMousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnSua;
    private javax.swing.JLabel btnThem;
    private javax.swing.JLabel btnXoa;
    private javax.swing.JScrollPane jScrollPane1;
    private static javax.swing.JTable tableQuanLyMon;
    // End of variables declaration//GEN-END:variables
}