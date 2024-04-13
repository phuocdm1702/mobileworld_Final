/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package mobileworld.form;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import mobileworld.model.PhieuGiamGia;
import mobileworld.main.SessionStorage;
import mobileworld.model.KhachHang;
import mobileworld.service.PhieuGiamGiaService.PhieuGiamGiaService;

/**
 *
 * @author Admin
 */
public class ViewInputPhieuGiamGia extends javax.swing.JPanel {

    private DefaultTableModel dtm = new DefaultTableModel();
    DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    SimpleDateFormat spxHien = new SimpleDateFormat("dd-MM-yyyy");
    private PhieuGiamGiaService sr = new PhieuGiamGiaService();
    DecimalFormat decimalFormat = new DecimalFormat("###,###");
    private DefaultComboBoxModel dcbm = new DefaultComboBoxModel();
    private List<KhachHang> dsKH = new ArrayList<>();
    private ViewGiamGia jpanelHien;
    ArrayList<String> selectedEmail = new ArrayList<>(); // List để lưu trữ Email khách hàng được chọn
    ArrayList<String> selectedTen = new ArrayList<>(); // List để lưu trữ tên khách hàng được chọn

    /**
     * Creates new form ViewInputPhieuGiamGia
     */
    public ViewInputPhieuGiamGia() {
        initComponents();
        dsKH = sr.getAllKH();
        dtm = (DefaultTableModel) tblBang.getModel();
        fillTable(dsKH);
    }

    private void fillTable(List<KhachHang> KHList) {
        dtm.setRowCount(0);
        int i=0;
        for (KhachHang kh : KHList) {
            i++;
            dtm.addRow(new Object[]{i,kh.getTen(), kh.getSdt(), kh.getEmail()});
        }
    }

    private void refeshText() {
        txtHoaDonToiThieu.setText("");
        txtSoTienGiamToiDa.setText("");
        txtTen.setText("");
        cboNgayBatDau.setDate(null);
        cboNgayKT.setDate(null);
        txtSoLan.setText("");
        txtPhanTramGiam.setText("");
        grp1.clearSelection();
        dsKH = sr.getAllKH();
        fillTable(dsKH);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grp1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtSoLan = new mobileworld.swing.TextField();
        txtPhanTramGiam = new mobileworld.swing.TextField();
        txtTen = new mobileworld.swing.TextField();
        txtHoaDonToiThieu = new mobileworld.swing.TextField();
        txtSoTienGiamToiDa = new mobileworld.swing.TextField();
        jLabel4 = new javax.swing.JLabel();
        cboNgayBatDau = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        cboNgayKT = new com.toedter.calendar.JDateChooser();
        btnAdd = new mobileworld.swing.ButtonCustom();
        btnBack = new mobileworld.swing.ButtonCustom();
        jLabel6 = new javax.swing.JLabel();
        rdoCK = new javax.swing.JRadioButton();
        rdoNCK = new javax.swing.JRadioButton();
        JScroll = new javax.swing.JScrollPane();
        tblBang = new mobileworld.swing.Table();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Thêm Phiếu Giảm Giá");

        txtSoLan.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtSoLan.setLabelText("Số Lượng Được Phép Sử Dụng");
        txtSoLan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSoLanKeyReleased(evt);
            }
        });

        txtPhanTramGiam.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtPhanTramGiam.setLabelText("Phần Trăm Giảm");
        txtPhanTramGiam.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPhanTramGiamKeyReleased(evt);
            }
        });

        txtTen.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtTen.setLabelText("Tên Khuyến Mãi");

        txtHoaDonToiThieu.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtHoaDonToiThieu.setLabelText("Hóa Đơn Tối Thiểu");
        txtHoaDonToiThieu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtHoaDonToiThieuKeyReleased(evt);
            }
        });

        txtSoTienGiamToiDa.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtSoTienGiamToiDa.setLabelText("Số Tiền Giảm Tối Đa");
        txtSoTienGiamToiDa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSoTienGiamToiDaKeyReleased(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(153, 153, 153));
        jLabel4.setText("Ngày Bắt Đầu");

        cboNgayBatDau.setDateFormatString("dd-MM-yyyy");
        cboNgayBatDau.setOpaque(false);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(153, 153, 153));
        jLabel5.setText("Kiểu phiếu giảm giá");

        cboNgayKT.setDateFormatString("dd-MM-yyyy");
        cboNgayKT.setOpaque(false);

        btnAdd.setForeground(new java.awt.Color(255, 255, 255));
        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mobileworld/icon/icons8-add-24.png"))); // NOI18N
        btnAdd.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnBack.setForeground(new java.awt.Color(255, 255, 255));
        btnBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mobileworld/icon/icons8-back-24.png"))); // NOI18N
        btnBack.setAlignmentX(0.5F);
        btnBack.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(153, 153, 153));
        jLabel6.setText("Ngày Sử Dụng Cuối");

        grp1.add(rdoCK);
        rdoCK.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        rdoCK.setForeground(new java.awt.Color(12, 45, 87));
        rdoCK.setText("Công khai");

        grp1.add(rdoNCK);
        rdoNCK.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        rdoNCK.setForeground(new java.awt.Color(12, 45, 87));
        rdoNCK.setText("Không công khai");

        tblBang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "STT", "Tên", "Số điện thoại ", "Email", "Chọn"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblBang.setSelectionBackground(new java.awt.Color(255, 255, 255));
        JScroll.setViewportView(tblBang);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(404, 404, 404)
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(cboNgayKT, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 602, Short.MAX_VALUE)
                        .addComponent(cboNgayBatDau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtSoTienGiamToiDa, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtTen, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtPhanTramGiam, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addComponent(rdoCK)
                            .addGap(18, 18, 18)
                            .addComponent(rdoNCK))
                        .addComponent(txtSoLan, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtHoaDonToiThieu, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(46, 46, 46)
                .addComponent(JScroll, javax.swing.GroupLayout.DEFAULT_SIZE, 627, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSoLan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtPhanTramGiam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtHoaDonToiThieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtSoTienGiamToiDa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboNgayBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboNgayKT, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rdoCK)
                            .addComponent(rdoNCK))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 37, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnAdd, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBack, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(JScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 603, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)))
                .addGap(60, 60, 60))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    public PhieuGiamGia getFormData() {
        String ten = txtTen.getText().trim();
        int soLan = Integer.valueOf(Integer.parseInt(txtSoLan.getText().trim().replace(",", "")));
        float phanTram = Float.valueOf(Float.parseFloat(txtPhanTramGiam.getText().trim().replace("%", "")));
        String hoaDonFormat = txtHoaDonToiThieu.getText().replace(",", "");
        String soTienFormat = txtSoTienGiamToiDa.getText().replace(",", "");
        float hoaDonMin = Float.valueOf(Float.parseFloat(hoaDonFormat));
        float soTiemGiamMax = Float.valueOf(Float.parseFloat(soTienFormat));
        String maNV = SessionStorage.getInstance().getUsername();
        int tt = 1;

        //Bien thoi gian
        Date getNgayBD = cboNgayBatDau.getDate();
        LocalDateTime ngayBD = getNgayBD.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        LocalDate ngayBDLocalDate = ngayBD.toLocalDate();
        Date getNgayKT = cboNgayKT.getDate();
        LocalDateTime ngayKT = getNgayKT.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        LocalDate ngayKTLocalDate = ngayKT.toLocalDate();
        LocalDateTime ngayThucTe = LocalDateTime.now();
        LocalDate ngayTTLC = ngayThucTe.toLocalDate();

        if (ngayBDLocalDate.equals(ngayTTLC) && ngayKTLocalDate.isAfter(ngayTTLC)) {
            tt = 1;
        } else if (ngayBDLocalDate.isAfter(ngayTTLC) && ngayKTLocalDate.isAfter(ngayTTLC)) {
            tt = 2;
        }

        boolean kieuPGG = rdoCK.isSelected();
        PhieuGiamGia pgg = new PhieuGiamGia(ten, soLan, phanTram, soTiemGiamMax, hoaDonMin, ngayBD, ngayKT, tt, ten, 1, ngayThucTe, maNV, ngayThucTe, maNV, kieuPGG);
        return pgg;
    }

    private void sendEmail() {
        String tenPGG = txtTen.getText().trim();
        LocalDate ngayBDLocalDate = cboNgayBatDau.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        String ngayBDDisplay = ngayBDLocalDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        LocalDate ngayKTLocalDate = cboNgayKT.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        String ngayKTDisplay = ngayKTLocalDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

        selectedEmail.clear();
        selectedTen.clear();

        boolean anyRowSelected = false; // Check xem có hàng được chọn không
        for (int i = 0; i < tblBang.getRowCount(); i++) {
            Boolean value = (Boolean) tblBang.getValueAt(i, 4);
            if (value != null && value) {
                anyRowSelected = true;
                KhachHang kh = null;
                kh = dsKH.get(i);
                if (kh != null) {
                    String email = kh.getEmail();
                    String ten = kh.getTen();
                    if (!selectedEmail.contains(email)) {
                        selectedEmail.add(email);
                    }
                    if (!selectedTen.contains(ten)) {
                        selectedTen.add(ten);
                    }
                }
            }
        }

        if (anyRowSelected) {
            sr.sendEmail(selectedEmail, selectedTen, tenPGG, ngayBDDisplay, ngayKTDisplay);
        }
    }


    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        String hoaDonFormat = txtHoaDonToiThieu.getText().replace(",", "");
        String soTienFormat = txtSoTienGiamToiDa.getText().replace(",", "");
        String soLanFormat = txtSoLan.getText().replace(",", "");
        String phanTramFormat = txtPhanTramGiam.getText().replace("%", "");
        if (txtTen.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui long nhap ten khuyen mai");
            return;
        }

        if (txtSoLan.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số lần được phép sử dụng");
            return;
        }

        if (txtHoaDonToiThieu.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập hóa đơn tối thiếu");
            return;
        }
        if (!hoaDonFormat.matches("[0-9]+")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập lại hóa đơn tối thiếu");
            return;
        }
        if (txtPhanTramGiam.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập phần trăm giảm");
            return;
        }
        if (!phanTramFormat.matches("[0-9]+")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập lại phần trăm giảm");
            return;
        }
        if (txtSoTienGiamToiDa.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số tiền giảm tối đa");
            return;
        }
        Float phanTramGiam= Float.parseFloat(txtPhanTramGiam.getText().trim().replace("%", " "));
        if(phanTramGiam>100){
            JOptionPane.showMessageDialog(this, "Vui lòng nhập lại phần trăm giảm dưới hoặc bằng 100%");
            return;
        }
        if (!soTienFormat.matches("[0-9]+")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập lại số tiền giảm tối đa");
            return;
        }

        Date getNgayBD = cboNgayBatDau.getDate();
        Date getNgayKT = cboNgayKT.getDate();

        if (getNgayBD == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn ngày bắt đầu");
            return;
        }

        if (getNgayKT == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn ngày sử dụng cuối cùng");
            return;
        }

        LocalDateTime ngayBD = getNgayBD.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        LocalDate ngayBDLocalDate = ngayBD.toLocalDate();
        LocalDateTime ngayKT = getNgayKT.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        LocalDate ngayKTLocalDate = ngayKT.toLocalDate();
        LocalDateTime ngayThucTe = LocalDateTime.now();
        LocalDate ngayTTLC = ngayThucTe.toLocalDate();

        if (ngayBDLocalDate.isBefore(ngayTTLC)) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn lại ngày bắt đầu");
            return;
        }

        if (ngayKTLocalDate.isBefore(ngayBDLocalDate)) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn lại ngày sử dụng cuối cùng");
            return;
        }

        if (!rdoCK.isSelected() && !rdoNCK.isSelected()) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn kiểu phiếu giảm giá");
            return;
        }

        if (rdoNCK.isSelected()) {
            int selectedRow = tblBang.getSelectedRow();
            boolean anyRowSelected = false; // Biến để kiểm tra xem có hàng nào được chọn không
            for (int i = 0; i < tblBang.getRowCount(); i++) {
                Boolean value = (Boolean) tblBang.getValueAt(i, 4);
                if (value != null && value) {
                    anyRowSelected = true; // Đã có ít nhất một hàng được chọn
                    break;
                }
            }
            if (!anyRowSelected) { // Nếu không có hàng nào được chọn
                JOptionPane.showMessageDialog(this, "Vui lòng chọn ít nhất 1 khách hàng để gửi mail");
                return;
            }
        }

        int lc = JOptionPane.showConfirmDialog(this, "Bạn có muốn Add dữ liệu không?", "Notification", JOptionPane.YES_NO_OPTION);
        if (lc == JOptionPane.YES_OPTION) {
            sr.addData(getFormData());
            if (rdoNCK.isSelected()) {
                sendEmail();
            }
            JOptionPane.showMessageDialog(this, "Đã Add dữ liệu");
            int lc2 = JOptionPane.showConfirmDialog(this, "Bạn có muốn quay về trang phiếu giảm giá chính không?", "Notification", JOptionPane.YES_NO_OPTION);
            if (lc2 == JOptionPane.YES_OPTION) {
                jpanelHien = new ViewGiamGia();
                jPanel1.setVisible(false);
                jLabel1.setVisible(false);
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
                frame.getContentPane().remove(this);
                javax.swing.GroupLayout layout = (javax.swing.GroupLayout) this.getLayout();
                layout.replace(jPanel1, jpanelHien);
                jpanelHien.setBounds(jPanel1.getBounds());
            } else {
                JOptionPane.showMessageDialog(this, "Bạn đã chọn NO");
                refeshText();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Bạn đã chọn NO");
        }


    }//GEN-LAST:event_btnAddActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        jpanelHien = new ViewGiamGia();
        jPanel1.setVisible(false);
        jLabel1.setVisible(false);
        btnBack.setVisible(false);
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        frame.getContentPane().remove(this);
        javax.swing.GroupLayout layout = (javax.swing.GroupLayout) this.getLayout();
        layout.replace(jPanel1, jpanelHien);
        jpanelHien.setBounds(jPanel1.getBounds());
    }//GEN-LAST:event_btnBackActionPerformed

    private void txtSoLanKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSoLanKeyReleased
        // TODO add your handling code here:
        if (!txtSoLan.getText().trim().isBlank()) {
            Document document = txtSoLan.getDocument();
            try {
                String text = document.getText(0, document.getLength());
                if (!text.isEmpty()) {
                    text = text.replaceAll("[^\\d]", "");
                    long number = Long.parseLong(text);
                    DecimalFormat decimalFormat = new DecimalFormat("#,##0");
                    String formattedNumber = decimalFormat.format(number);
                    txtSoLan.setText(formattedNumber);
                }
            } catch (BadLocationException | NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập số lượng sử dụng là số");
                return;
            }
        }

    }//GEN-LAST:event_txtSoLanKeyReleased

    private void txtHoaDonToiThieuKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtHoaDonToiThieuKeyReleased
        // TODO add your handling code here:
        if (!txtHoaDonToiThieu.getText().trim().isEmpty()) {
            String text = txtHoaDonToiThieu.getText();
            text = text.replaceAll("[^\\d]", "");

            if (!text.isEmpty()) {
                try {
                    long number = Long.parseLong(text);

                    // Định dạng số và hiển thị trong JTextField
                    DecimalFormat decimalFormat = new DecimalFormat("#,##0");
                    txtHoaDonToiThieu.setText(decimalFormat.format(number));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng định dạng số");
                    return;
                }
            }
        }
    }//GEN-LAST:event_txtHoaDonToiThieuKeyReleased

    private void txtSoTienGiamToiDaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSoTienGiamToiDaKeyReleased
        // TODO add your handling code here:
        if (!txtSoTienGiamToiDa.getText().trim().isBlank()) {
            Document document = txtSoTienGiamToiDa.getDocument();
            try {
                String text = document.getText(0, document.getLength());
                text = text.replaceAll("[^\\d]", "");
                long number = Long.parseLong(text);

                DecimalFormat decimalFormat = new DecimalFormat("#,##0");
                String formattedNumber = decimalFormat.format(number);
                txtSoTienGiamToiDa.setText(formattedNumber);

            } catch (BadLocationException | NumberFormatException ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_txtSoTienGiamToiDaKeyReleased

    private void txtPhanTramGiamKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPhanTramGiamKeyReleased
        // TODO add your handling code here:
        if (!txtPhanTramGiam.getText().trim().isEmpty()) {
            String text = txtPhanTramGiam.getText().trim().replaceAll("[^\\d]", "");
            if (!text.isEmpty()) {
                long number = Long.parseLong(text);
                DecimalFormat decimalFormat = new DecimalFormat("###'%'");
                String formattedNumber = decimalFormat.format(number);
                txtPhanTramGiam.setText(formattedNumber);
            }
        }

    }//GEN-LAST:event_txtPhanTramGiamKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane JScroll;
    private mobileworld.swing.ButtonCustom btnAdd;
    private mobileworld.swing.ButtonCustom btnBack;
    private com.toedter.calendar.JDateChooser cboNgayBatDau;
    private com.toedter.calendar.JDateChooser cboNgayKT;
    private javax.swing.ButtonGroup grp1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton rdoCK;
    private javax.swing.JRadioButton rdoNCK;
    private mobileworld.swing.Table tblBang;
    private mobileworld.swing.TextField txtHoaDonToiThieu;
    private mobileworld.swing.TextField txtPhanTramGiam;
    private mobileworld.swing.TextField txtSoLan;
    private mobileworld.swing.TextField txtSoTienGiamToiDa;
    private mobileworld.swing.TextField txtTen;
    // End of variables declaration//GEN-END:variables
}
