package mobileworld.form;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import mobileworld.main.SessionStorage;
import mobileworld.model.KhachHang;
import mobileworld.service.KhachHangService.KhachHangService;
import mobileworld.service.KhachHangService.LichSuGiaoDichService;
import mobileworld.viewModel.LichSuGiaoDichViewModel;

public class ViewKhachHangNew extends javax.swing.JPanel {

    private List<KhachHang> list = new ArrayList<>();
    private List<KhachHang> listTK = new ArrayList<>();
    private DefaultTableModel dtm = new DefaultTableModel();
    private KhachHangService sv = new KhachHangService();

    private DefaultTableModel dtmLSGD = new DefaultTableModel();
    private List<LichSuGiaoDichViewModel> listLSGD = new ArrayList<>();
    private LichSuGiaoDichService srLSGD = new LichSuGiaoDichService();

    DecimalFormat decimalFormat = new DecimalFormat("###,###");

    public ViewKhachHangNew() {
        initComponents();
        setOpaque(false);
        list = sv.getAll();
        dtm = (DefaultTableModel) tblBang.getModel();
        dtmLSGD = (DefaultTableModel) tblBang2.getModel();
        showDataTable(list);
    }

    public void showDataTable(List<KhachHang> khachHangs) {
        dtm.setRowCount(0);
        int stt = 0;
        for (KhachHang khachHang1 : khachHangs) {
            String gioiTinh;
            stt++;
            if (khachHang1.getGioiTinh()) {
                gioiTinh = "Nam";
            } else {
                gioiTinh = "Nữ";

            }
            dtm.addRow(new Object[]{
                stt,
                khachHang1.getId(),
                khachHang1.getTen(),
                khachHang1.getSdt(),
                gioiTinh,
                khachHang1.getNgaySinh(),
                khachHang1.getDiaChi()
            });
        }
    }

    public void fillTableLSGG(List<LichSuGiaoDichViewModel> LSGG) {
        dtmLSGD.setRowCount(0);
        int i = 0;
        String tt = "";

        for (LichSuGiaoDichViewModel lsgg : LSGG) {
            i++;
            String tongTien = decimalFormat.format(lsgg.getTongTien());
            if (lsgg.getTrangThai() == 1) {
                tt = "Ðã thanh toán";
            } else {
                tt = "Chưa thanh toán";
            }
            dtmLSGD.addRow(new Object[]{i, lsgg.getIDNV(), lsgg.getIDKH(), lsgg.getTenKH(), lsgg.getMail(), lsgg.getSdt(), lsgg.getDiaChi(), tt, tongTien});
        }
    }

    public KhachHang getFormData() {
        String ten = txtHoten.getText();
        String sdt = txtSđt.getText();
        boolean gioiTinh = rbtNam.isSelected();
        String diaChi = txtDiaChi.getText();
        String ma = txtMaKH.getText();
        String email = txtEmail.getText().trim();
        LocalDateTime ngayThucTe = LocalDateTime.now();
        String maNV = SessionStorage.getInstance().getUsername();
        KhachHang khachHang = new KhachHang(ten, sdt, gioiTinh, diaChi, 1, ngayThucTe, maNV, ngayThucTe, maNV, ma, email);
        return khachHang;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grp1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        txtSđt = new mobileworld.swing.TextField();
        jPanel4 = new javax.swing.JPanel();
        rbtNam = new javax.swing.JRadioButton();
        rbtNu = new javax.swing.JRadioButton();
        txtMaKH = new mobileworld.swing.TextField();
        txtHoten = new mobileworld.swing.TextField();
        txtDiaChi = new mobileworld.swing.TextField();
        btnThem = new mobileworld.swing.ButtonCustom();
        btnSua = new mobileworld.swing.ButtonCustom();
        btnXoa = new mobileworld.swing.ButtonCustom();
        btnLamMoi = new mobileworld.swing.ButtonCustom();
        txtEmail = new mobileworld.swing.TextField();
        materialTabbed1 = new mobileworld.swing.MaterialTabbed();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblBang = new mobileworld.swing.Table();
        txtTimKiem = new mobileworld.swing.TextField();
        jPanel7 = new javax.swing.JPanel();
        txtTimKiem2 = new mobileworld.swing.TextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblBang2 = new mobileworld.swing.Table();

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Quản Lý Khách Hàng");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thiết Lập Thông Tin Khách Hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(12, 45, 87))); // NOI18N
        jPanel1.setOpaque(false);

        txtSđt.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtSđt.setLabelText("Số Điện Thoại");

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)), "Giới Tính", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(153, 153, 153))); // NOI18N
        jPanel4.setOpaque(false);

        grp1.add(rbtNam);
        rbtNam.setText("Nam");

        grp1.add(rbtNu);
        rbtNu.setText("Nữ");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(rbtNam)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(rbtNu)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbtNam)
                    .addComponent(rbtNu)))
        );

        txtMaKH.setEnabled(false);
        txtMaKH.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtMaKH.setLabelText("Mã KH");

        txtHoten.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtHoten.setLabelText("Họ Và Tên");

        txtDiaChi.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtDiaChi.setLabelText("Địa Chỉ");

        btnThem.setForeground(new java.awt.Color(255, 255, 255));
        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mobileworld/icon/icons8-add-24.png"))); // NOI18N
        btnThem.setText("Thêm");
        btnThem.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setForeground(new java.awt.Color(255, 255, 255));
        btnSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mobileworld/icon/icons8-refresh-24.png"))); // NOI18N
        btnSua.setText("Sửa");
        btnSua.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoa.setForeground(new java.awt.Color(255, 255, 255));
        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mobileworld/icon/icons8-delete-24.png"))); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnLamMoi.setForeground(new java.awt.Color(255, 255, 255));
        btnLamMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mobileworld/icon/icons8-clear-24.png"))); // NOI18N
        btnLamMoi.setText("Làm Mới");
        btnLamMoi.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });

        txtEmail.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtEmail.setLabelText("Email");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtHoten, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtMaKH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtSđt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtDiaChi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnLamMoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnSua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(txtEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtHoten, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(txtSđt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setOpaque(false);

        tblBang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã Khách Hàng", "Tên", "SĐT", "Giới Tính", "Địa Chỉ"
            }
        ));
        tblBang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblBangMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblBang);

        txtTimKiem.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtTimKiem.setLabelText("Tìm Kiếm");
        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 828, Short.MAX_VALUE)
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel6Layout.createSequentialGroup()
                    .addGap(4, 4, 4)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(524, Short.MAX_VALUE)))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 593, Short.MAX_VALUE))
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel6Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(597, Short.MAX_VALUE)))
        );

        materialTabbed1.addTab("Thông Tin Khách Hàng", jPanel6);

        jPanel7.setOpaque(false);

        txtTimKiem2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtTimKiem2.setLabelText("Tìm Kiếm");
        txtTimKiem2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiem2KeyReleased(evt);
            }
        });

        jScrollPane1.setBorder(null);
        jScrollPane1.setOpaque(false);

        tblBang2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã NV", "Mã KH", "Tên KH", "SĐT", "Email KH", "Địa Chỉ", "Tổng tiền", "Trạng Thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, true, false, true, true, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblBang2.setOpaque(false);
        jScrollPane1.setViewportView(tblBang2);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 819, Short.MAX_VALUE)
                        .addGap(5, 5, 5))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(txtTimKiem2, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtTimKiem2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 587, Short.MAX_VALUE)
                .addContainerGap())
        );

        materialTabbed1.addTab("Lịch Sử Giao Dịch", jPanel7);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(materialTabbed1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel1)
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(materialTabbed1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:`
        if (txtHoten.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng không được để trống tên khách hàng");
            return;
        }
        if (txtSđt.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng không được để trống sđt khách hàng");
            return;
        }
        if (txtDiaChi.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng không được để trống địa chỉ khách hàng");
            return;
        }
        if (txtEmail.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng không được để trống Email khách hàng");
            return;
        }
        if (!rbtNam.isSelected() && !rbtNu.isSelected()) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn giới tính khách hàng");
            return;
        }

        int lc = JOptionPane.showConfirmDialog(this, "Bạn có muốn thêm thông tin khách hàng không?");
        if (lc == JOptionPane.YES_OPTION) {
            sv.add(getFormData());
            list = sv.getAll();
            showDataTable(list);
            JOptionPane.showMessageDialog(this, "Đã thêm khách hàng");
            refeshText();
        } else {
            JOptionPane.showMessageDialog(this, "Bạn đã chọn No");
        }

    }//GEN-LAST:event_btnThemActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        int lc = JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa thông tin khách hàng không?");
        int xoa = tblBang.getSelectedRow();
        if (xoa == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn 1 dòng trên bảng \"Thông tin khách hàng\" để xóa");
            return;
        }
        LocalDateTime ngayThucTe = LocalDateTime.now();
        String maNV = SessionStorage.getInstance().getUsername();
        if (lc == JOptionPane.YES_OPTION) {
            if (listTK.isEmpty()) {
                KhachHang nv = list.get(xoa);
                sv.delete(nv.getId(), ngayThucTe, maNV);
            } else {
                KhachHang nv = listTK.get(xoa);
                sv.delete(nv.getId(), ngayThucTe, maNV);
                listTK.clear();
            }
            list = sv.getAll();
            showDataTable(list);
            listTK.clear();
            refeshText();
            JOptionPane.showMessageDialog(this, "Đã xóa khách hàng");
        } else {
            JOptionPane.showMessageDialog(this, "Bạn đã chọn No");
        }

    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        if (txtHoten.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng không được để trống tên khách hàng");
            return;
        }
        if (txtSđt.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng không được để trống sđt khách hàng");
            return;
        }
        if (txtDiaChi.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng không được để trống địa chỉ khách hàng");
            return;
        }
        if (txtEmail.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng không được để trống Email khách hàng");
            return;
        }
        if (!rbtNam.isSelected() && !rbtNu.isSelected()) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn giới tính khách hàng");
            return;
        }

        int lc = JOptionPane.showConfirmDialog(this, "Bạn có muốn sửa thông tin khách hàng không?");
        int sua = tblBang.getSelectedRow();
        if (sua == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn 1 dòng trên bảng \"Thông tin khách hàng\" để sửa");
            return;
        }
        if (lc == JOptionPane.YES_OPTION) {
            if (listTK.isEmpty()) {
                KhachHang nv = list.get(sua);
                sv.update(getFormData(), nv.getId());
            } else {
                KhachHang nv = listTK.get(sua);
                sv.update(getFormData(), nv.getId());
                listTK.clear();
            }
            list = sv.getAll();
            showDataTable(list);
            refeshText();
            JOptionPane.showMessageDialog(this, "Đã sửa thông tin khách hàng");
        } else {
            JOptionPane.showMessageDialog(this, "Bạn đã chọn No");
        }

    }//GEN-LAST:event_btnSuaActionPerformed

    private void refeshText() {
        txtHoten.setText("");
        txtMaKH.setText("");
        txtSđt.setText("");
        txtDiaChi.setText("");
        txtEmail.setText("");
        txtTimKiem.setText("");
        grp1.clearSelection();
        txtTimKiem2.setText("");
    }

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        // TODO add your handling code here:
        refeshText();
        materialTabbed1.setSelectedComponent(jPanel6);
        listTK.clear();
        listLSGD.clear();
        list = sv.getAll();
        showDataTable(list);
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void tblBangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBangMouseClicked
        // TODO add your handling code here:
        int row = tblBang.getSelectedRow();
        if (listTK.isEmpty()) {
            KhachHang kh = list.get(row);
            txtMaKH.setText(kh.getId());
            txtHoten.setText(kh.getTen());
            txtSđt.setText(kh.getSdt());
            txtDiaChi.setText(kh.getDiaChi());
            txtEmail.setText(kh.getEmail());
            if (kh.getGioiTinh()) {
                rbtNam.setSelected(true);
            } else {
                rbtNu.setSelected(true);
            }
        } else {
            KhachHang kh = listTK.get(row);
            txtMaKH.setText(kh.getId());
            txtHoten.setText(kh.getTen());
            txtSđt.setText(kh.getSdt());
            txtDiaChi.setText(kh.getDiaChi());
            txtEmail.setText(kh.getEmail());
            if (kh.getGioiTinh()) {
                rbtNam.setSelected(true);
            } else {
                rbtNu.setSelected(true);
            }
        }

    }//GEN-LAST:event_tblBangMouseClicked

    private void txtTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyReleased
        if (txtTimKiem.getText().trim().equals("")) {
            showDataTable(sv.getAll());
        } else {
            listTK = sv.search(txtTimKiem.getText().trim());
            showDataTable(listTK);
        }
    }//GEN-LAST:event_txtTimKiemKeyReleased

    private void txtTimKiem2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiem2KeyReleased
        listLSGD = srLSGD.searchLSGG(txtTimKiem2.getText().trim());
        fillTableLSGG(listLSGD);
    }//GEN-LAST:event_txtTimKiem2KeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private mobileworld.swing.ButtonCustom btnLamMoi;
    private mobileworld.swing.ButtonCustom btnSua;
    private mobileworld.swing.ButtonCustom btnThem;
    private mobileworld.swing.ButtonCustom btnXoa;
    private javax.swing.ButtonGroup grp1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private mobileworld.swing.MaterialTabbed materialTabbed1;
    private javax.swing.JRadioButton rbtNam;
    private javax.swing.JRadioButton rbtNu;
    private mobileworld.swing.Table tblBang;
    private mobileworld.swing.Table tblBang2;
    private mobileworld.swing.TextField txtDiaChi;
    private mobileworld.swing.TextField txtEmail;
    private mobileworld.swing.TextField txtHoten;
    private mobileworld.swing.TextField txtMaKH;
    private mobileworld.swing.TextField txtSđt;
    private mobileworld.swing.TextField txtTimKiem;
    private mobileworld.swing.TextField txtTimKiem2;
    // End of variables declaration//GEN-END:variables
}
