package mobileworld.form;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import mobileworld.service.HoaDonService.HoaDonCTService;
import mobileworld.service.HoaDonService.HoaDonService;
import mobileworld.viewModel.HoaDonChiTietModel;
import mobileworld.viewModel.HoaDonModel;
import mobileworld.model.HoaDon;
import mobileworld.model.PhuongThucThanhToan;
import mobileworld.print.print.ReportManager;
import mobileworld.print.print.model.FieldReportPayment;
import mobileworld.print.print.model.ParameterReportPayment;
import mobileworld.qrcode.qrcode;
import mobileworld.qrcode.qrcode.QRCodeListener;
import mobileworld.service.HoaDonService.LichSuHDService;
import mobileworld.service.HoaDonService.PhuongThucThanhToanService;
//import mobileworld.service.QRCodeScannerApp;
import mobileworld.viewModel.LichSuHDModel;

//import qrcode.qrcode.QRCodeListener;
public final class ViewHoaDon extends javax.swing.JPanel implements QRCodeListener {

    List<HoaDon> listHD = new ArrayList<>();
    List<HoaDonModel> list = new ArrayList<>();
    List<HoaDonModel> listHDM = new ArrayList<>();

    List<HoaDonChiTietModel> list22 = new ArrayList<>();
    List<LichSuHDModel> listLS2 = new ArrayList<>();
    List<HoaDonChiTietModel> list2 = new ArrayList<>();

    List<LichSuHDModel> listLS = new ArrayList<>();
    List<PhuongThucThanhToan> listPTTT = new ArrayList<>();

    DefaultTableModel tableModel = new DefaultTableModel();
    DefaultTableModel tableModel2 = new DefaultTableModel();
    DefaultTableModel tableModel3 = new DefaultTableModel();

    DefaultComboBoxModel comboBoxModel = new DefaultComboBoxModel();
    DefaultComboBoxModel comboBoxModel1 = new DefaultComboBoxModel();

    List<HoaDonModel> search = new ArrayList<>();

    PhuongThucThanhToanService srPTTT = new PhuongThucThanhToanService();
    HoaDonService sr = new HoaDonService();
    HoaDonCTService srCT = new HoaDonCTService();
    LichSuHDService srLSHD = new LichSuHDService();

    public ViewHoaDon() {
        initComponents();
        list = sr.getAllHD();
        listPTTT = srPTTT.getAll();

//        listHD = sr.getAllHD();
        tableModel = (DefaultTableModel) tblHienThi1.getModel();
        showDataTable(list);
//        showCBOTT(listHD);
//        showCBO(listPTTT);
        setDataCboNSXHDCT();
        setOpaque(false);
    }

    @Override
    public void onQRCodeScanned(String result) {

        List<HoaDonModel> list = sr.getAllQR(result);
        showDataTable(list);
        if (!list.isEmpty()) {
            tableModel2 = (DefaultTableModel) tblHienThi2.getModel();
            List<HoaDonChiTietModel> list22 = srCT.getAll(result);
            showDataTable2(list22);

            tableModel3 = (DefaultTableModel) tblHienThi3.getModel();
            List<LichSuHDModel> listLS2 = srLSHD.getAll(result);
            showDataTable3(listLS2);

            System.out.println("Nhận giá trị" + result);

        } else {
            // Nếu danh sách rỗng, hiển thị thông báo yêu cầu người dùng thử lại hoặc chọn QR khác
            JOptionPane.showMessageDialog(null, "Quét không thành công. Vui lòng thử lại hoặc chọn QR khác.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

    }

    private void setDataCboNSXHDCT() {
        String[] paymentMethods = {"Tất cả", "Apple", "Samsung", "Xiaomi", "Oppo"};
        for (String method : paymentMethods) {
            cboNSX.addItem(method);
        }
        cboNSX.setSelectedIndex(0);
    }

    public void showDataTable(List<HoaDonModel> list1) {
        tableModel.setRowCount(0);
        int tt = 0;
        String trangThai = "";
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

        for (HoaDonModel hoaDonModel : list1) {

            tt++;

            if (hoaDonModel.getTrangThai() == 0) {
                trangThai = "Chờ thanh toán";
            } else {
                trangThai = "Đã thanh toán";
            }
            String formattedTongTien = currencyFormat.format(hoaDonModel.getTongTien());
            String formattedNgayThanhToan = hoaDonModel.getNgayThanhToan().format(dateFormatter);

            tableModel.addRow(new Object[]{
                tt,
                hoaDonModel.getIdHD(),
                hoaDonModel.getIdNV(),
                hoaDonModel.getTenKH(),
                hoaDonModel.getSDTKH(),
                hoaDonModel.getDiaChiKH(),
                formattedNgayThanhToan,
                hoaDonModel.getTenKieuThanhToan(),
                formattedTongTien,
                trangThai
            });
        }
    }

    public void showDataTable2(List<HoaDonChiTietModel> listHDCT) {
        tableModel2.setRowCount(0);
        int stt = 0;
        // Lấy định dạng tiền tệ của Việt Nam
        DecimalFormat currencyFormat = (DecimalFormat) DecimalFormat.getCurrencyInstance(new Locale("vi", "VN"));
        for (HoaDonChiTietModel hoaDonChiTietModel : listHDCT) {
            stt++;
            // Định dạng tổng tiền về kí hiệu tiền Việt Nam
            String formattedTongTien = currencyFormat.format(hoaDonChiTietModel.getTongTien());
            String formattedGiaBan = currencyFormat.format(hoaDonChiTietModel.getGiaBan());
            tableModel2.addRow(new Object[]{
                stt,
                hoaDonChiTietModel.getIdCTSP(),
                hoaDonChiTietModel.getTenDSP(),
                hoaDonChiTietModel.getTenNSX(),
                hoaDonChiTietModel.getTenMau(),
                hoaDonChiTietModel.getDungLuongPin(),
                hoaDonChiTietModel.getImel(),
                formattedGiaBan,
                formattedTongTien// Sử dụng tổng tiền đã định dạng
            });
        }
    }

    public void showDataTable3(List<LichSuHDModel> listLSHD) {
        tableModel3.setRowCount(0);
        int stt = 0;
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

        for (LichSuHDModel lichSuHDModel : listLSHD) {
            stt++;
            String formattedNgayGio = lichSuHDModel.getNgayGio().format(dateFormatter);

            tableModel3.addRow(new Object[]{
                stt,
                lichSuHDModel.getIdNV(),
                formattedNgayGio,
                lichSuHDModel.getHanhDong()
            });
        }
    }

    public void show() {

        int index = tblHienThi1.getSelectedRow();
        HoaDonModel hdm = list.get(index);

        tableModel2 = (DefaultTableModel) tblHienThi2.getModel();
        list2 = srCT.getAll(hdm.getIdHD());
        showDataTable2(list2);

        tableModel3 = (DefaultTableModel) tblHienThi3.getModel();
        listLS = srLSHD.getAll(hdm.getIdHD());
        showDataTable3(listLS);
    }

//    private void inHoaDon(String invoiceId) {
//        if (sr.inHD(invoiceId)) {
//            JOptionPane.showMessageDialog(this, "In hóa đơn thành công");
//        } else {
//            JOptionPane.showMessageDialog(this, "Có lỗi xảy ra khi in hóa đơn", "Lỗi", JOptionPane.ERROR_MESSAGE);
//        }
//    }
    private ByteArrayInputStream generateQrcode(String invoiceNumber) throws WriterException {
        try {
            // Kiểm tra xem invoiceNumber có giá trị không
            if (invoiceNumber == null || invoiceNumber.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Mã hóa đơn không hợp lệ");
                return null;
            }

            // Sử dụng mã hóa đơn để tạo mã QR
            int width = 300;
            int height = 300;
            Map<EncodeHintType, Object> hints = new Hashtable<>();

//            Map<EncodeHintType, Object> hints = new EnumMap<>(EncodeHintType.class);
//            hints.put(EncodeHintType.MARGIN, 0); // Đặt margin là 2
//            BitMatrix bitMatrix = new MultiFormatWriter().encode(invoiceNumber, com.google.zxing.BarcodeFormat.QR_CODE, width, height, hints);
            BitMatrix bitMatrix;
            bitMatrix = new MultiFormatWriter().encode(invoiceNumber, BarcodeFormat.QR_CODE, width, height, (Hashtable) hints);
            // Tiếp tục xử lý BitMatrix...

            // Chuyển đổi bitMatrix thành hình ảnh
            BufferedImage image = MatrixToImageWriter.toBufferedImage(bitMatrix);

            // Chuyển đổi hình ảnh thành mảng byte
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ImageIO.write(image, "png", outputStream);

            // Hiển thị kết quả
            System.out.println(invoiceNumber);

            // Trả về InputStream từ mảng byte
            return new ByteArrayInputStream(outputStream.toByteArray());
        } catch (IOException e) {
            // Xử lý ngoại lệ, ví dụ: hiển thị thông báo lỗi cho người dùng hoặc log lỗi
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi tạo mã QR Code: " + e.getMessage());
            return null;
        }
    }

    private void printHD() {
        try {
            //int rowIndex = jTable1.getSelectedRow();
            int rowIndex = tblHienThi1.getSelectedRow();

            if (rowIndex >= 0) {
                HoaDonModel hd = list.get(rowIndex);
                tableModel = (DefaultTableModel) tblHienThi2.getModel();
                list2 = srCT.getAll(hd.getIdHD());
                showDataTable2(list2);
                List<FieldReportPayment> fields = new ArrayList<>();

                // Lặp qua các hàng trong bảng jTable2 để lấy thông tin chi tiết
                for (int i = 0; i < tblHienThi2.getRowCount(); i++) {
                    // Lấy dữ liệu từ mỗi hàng
                    String tenSanPham = tblHienThi2.getValueAt(i, 2).toString();
                    String mauSac = (String) tableModel2.getValueAt(i, 4);
                    String pin = (String) tblHienThi2.getValueAt(i, 5);

                    String thanhTien = String.valueOf(tblHienThi2.getValueAt(i, 8));
                    String giaBan = String.valueOf(tblHienThi2.getValueAt(i, 7));

                    // Loại bỏ các kí tự không phải số và dấu chấm thập phân
                    giaBan = giaBan.replaceAll("[^\\d.]", "");
                    // In dữ liệu ra console
                    System.out.println("Tên sản phẩm: " + tenSanPham);
                    System.out.println("Tổng tiền: " + giaBan);

                    // Thêm thông tin vào danh sách fields để tạo báo cáo
                    fields.add(new FieldReportPayment(i + 1, tenSanPham, mauSac, pin, giaBan + "VND", thanhTien + "VND"));

                }
                // Kiểm tra nếu có dữ liệu để tạo báo cáo
                if (!fields.isEmpty()) {
                    // Tạo mã QR Code
                    ByteArrayInputStream qrCodeStream = generateQrcode(hd.getIdHD());
                    byte[] data = qrCodeStream.readAllBytes();
                    String qrCodeData = new String(data);
                    System.out.println("QR Code Data: " + qrCodeData);
                    if (qrCodeStream != null) {

                        // Tạo tham số để in báo cáo
                        ParameterReportPayment dataPrint = new ParameterReportPayment(hd.getIdHD(), String.valueOf(hd.getNgayThanhToan()), hd.getTenKH(), hd.getSDTKH(), hd.getDiaChiKH(), hd.getTenKieuThanhToan(), String.valueOf(hd.getTongTien()).replaceAll("[^\\d.]", ""), qrCodeStream, fields);
                        // Cập nhật trường hình ảnh trong ParameterReportPayment với dữ liệu của mã QR Code
                        // Trước khi gọi printReportPayment
                        ReportManager reportManager = ReportManager.getInstance();

                        // Check parameters before printing the report
                        reportManager.checkReportParameters(dataPrint);

                        // Check JRXML path and compilation before printing the report
                        reportManager.checkJRXMLPath();
                        reportManager.checkCompilation();

                        // Print the report after ensuring all preparations are done
                        reportManager.printReportPayment(dataPrint);

                        // Gọi phương thức in báo cáo
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Không có dữ liệu để tạo báo cáo.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Bạn chưa chọn hóa đơn để in ra.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

        @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHienThi1 = new mobileworld.swing.Table();
        jPanel3 = new javax.swing.JPanel();
        txtSearch = new mobileworld.swing.TextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        buttonCustom1 = new mobileworld.swing.ButtonCustom();
        reset = new mobileworld.swing.ButtonCustom();
        sliderGradient1 = new mobileworld.test.raven.slider.SliderGradient();
        jLabel9 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        badgeButton1 = new mobileworld.swing.BadgeButton();
        jLabel4 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        badgeButton2 = new mobileworld.swing.BadgeButton();
        jLabel5 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        badgeButton3 = new mobileworld.swing.BadgeButton();
        jLabel6 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        badgeButton5 = new mobileworld.swing.BadgeButton();
        jLabel8 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblHienThi2 = new mobileworld.swing.Table();
        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblHienThi3 = new mobileworld.swing.Table();
        jPanel9 = new javax.swing.JPanel();
        txtSearchHDCT = new mobileworld.swing.TextField();
        cboNSX = new mobileworld.swing.Combobox();
        combobox2 = new mobileworld.swing.Combobox();
        combobox1 = new mobileworld.swing.Combobox();
        combobox4 = new mobileworld.swing.Combobox();
        buttonCustom4 = new mobileworld.swing.ButtonCustom();
        btnXuatHoaDon = new mobileworld.swing.ButtonCustom();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hóa Đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 16), new java.awt.Color(12, 45, 87))); // NOI18N
        jPanel1.setOpaque(false);

        jScrollPane1.setBorder(null);

        tblHienThi1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã HĐ", "Mã NV ", "Tên KH", "SĐT KH", "Địa chỉ", "Ngày Thanh Toán", "Loại thanh toán", "Tổng tiền ", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true, true, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHienThi1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHienThi1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblHienThi1);
        if (tblHienThi1.getColumnModel().getColumnCount() > 0) {
            tblHienThi1.getColumnModel().getColumn(0).setMinWidth(10);
            tblHienThi1.getColumnModel().getColumn(0).setMaxWidth(40);
            tblHienThi1.getColumnModel().getColumn(1).setMinWidth(10);
            tblHienThi1.getColumnModel().getColumn(1).setMaxWidth(60);
            tblHienThi1.getColumnModel().getColumn(2).setMinWidth(10);
            tblHienThi1.getColumnModel().getColumn(2).setMaxWidth(55);
        }

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Bộ Lọc", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(45, 47, 90))); // NOI18N
        jPanel3.setOpaque(false);

        txtSearch.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtSearch.setLabelText("Tìm Kiếm");
        txtSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(153, 153, 153));
        jLabel2.setText("Giá Tối Thiểu");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(153, 153, 153));
        jLabel3.setText("Giá Tối Đa");

        buttonCustom1.setForeground(new java.awt.Color(255, 255, 255));
        buttonCustom1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mobileworld/icon/icons8-qr-code-30.png"))); // NOI18N
        buttonCustom1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        buttonCustom1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCustom1ActionPerformed(evt);
            }
        });

        reset.setForeground(new java.awt.Color(255, 255, 255));
        reset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mobileworld/icon/icons8-refresh-24.png"))); // NOI18N
        reset.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetActionPerformed(evt);
            }
        });

        sliderGradient1.setMaximum(1000000000);
        sliderGradient1.setColor2(new java.awt.Color(0, 51, 102));
        sliderGradient1.setOpaque(false);
        sliderGradient1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sliderGradient1StateChanged(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(12, 45, 87));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("0 VNĐ");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sliderGradient1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3))
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(102, 102, 102)
                .addComponent(reset, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonCustom1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(buttonCustom1, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                            .addComponent(reset, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                            .addComponent(jLabel9)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(sliderGradient1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tình Trạng Hóa Đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(12, 45, 87))); // NOI18N
        jPanel5.setOpaque(false);
        jPanel5.setLayout(new java.awt.GridLayout(1, 0, 10, 10));

        badgeButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mobileworld/icon/icons8-paid-30.png"))); // NOI18N
        badgeButton1.setText("4");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(12, 45, 87));
        jLabel4.setText("Đã Thanh Toán");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(badgeButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(badgeButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel5.add(jPanel6);

        badgeButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mobileworld/icon/icons8-paid-30.png"))); // NOI18N
        badgeButton2.setText("2");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(12, 45, 87));
        jLabel5.setText("Chưa Thanh Toán");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(badgeButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(badgeButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel5.add(jPanel7);

        badgeButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mobileworld/icon/icons8-delivery-30 (1).png"))); // NOI18N
        badgeButton3.setText("1");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(12, 45, 87));
        jLabel6.setText("Đang Giao Hàng");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(badgeButton3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(badgeButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel5.add(jPanel8);

        badgeButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mobileworld/icon/icons8-delivery-30 (1).png"))); // NOI18N
        badgeButton5.setText("0");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(12, 45, 87));
        jLabel8.setText("Hủy Giao Hàng");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(badgeButton5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(badgeButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel5.add(jPanel10);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hóa Đơn Chi Tiết", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 16), new java.awt.Color(12, 45, 87))); // NOI18N
        jPanel2.setOpaque(false);

        jScrollPane3.setBorder(null);

        tblHienThi2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã CTSP", "Tên SP", "NSX", "Màu", "Pin", "Imel", "Giá bán", "Tổng tiền"
            }
        ));
        jScrollPane3.setViewportView(tblHienThi2);
        if (tblHienThi2.getColumnModel().getColumnCount() > 0) {
            tblHienThi2.getColumnModel().getColumn(0).setMinWidth(10);
            tblHienThi2.getColumnModel().getColumn(0).setMaxWidth(40);
            tblHienThi2.getColumnModel().getColumn(3).setMinWidth(1);
            tblHienThi2.getColumnModel().getColumn(3).setMaxWidth(50);
        }

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Hóa Đơn");

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Lịch Sử Hóa Đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 16), new java.awt.Color(12, 45, 87))); // NOI18N
        jPanel4.setOpaque(false);

        jScrollPane4.setBorder(null);

        tblHienThi3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "STT", "Người tác động", "Ngày Giờ cập nhật", "Hành động"
            }
        ));
        jScrollPane4.setViewportView(tblHienThi3);
        if (tblHienThi3.getColumnModel().getColumnCount() > 0) {
            tblHienThi3.getColumnModel().getColumn(0).setMinWidth(10);
            tblHienThi3.getColumnModel().getColumn(0).setMaxWidth(40);
        }

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 474, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
        );

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Lọc Hóa Đơn Chi Tiết", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(12, 45, 87))); // NOI18N
        jPanel9.setOpaque(false);
        jPanel9.setLayout(new java.awt.GridLayout(1, 0, 15, 0));

        txtSearchHDCT.setLabelText("Tìm Kiếm");
        txtSearchHDCT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchHDCTActionPerformed(evt);
            }
        });
        jPanel9.add(txtSearchHDCT);

        cboNSX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboNSXActionPerformed(evt);
            }
        });
        jPanel9.add(cboNSX);
        jPanel9.add(combobox2);
        jPanel9.add(combobox1);
        jPanel9.add(combobox4);

        buttonCustom4.setForeground(new java.awt.Color(255, 255, 255));
        buttonCustom4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mobileworld/icon/icons8-pdf-40.png"))); // NOI18N
        buttonCustom4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        buttonCustom4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCustom4ActionPerformed(evt);
            }
        });
        jPanel9.add(buttonCustom4);

        btnXuatHoaDon.setForeground(new java.awt.Color(255, 255, 255));
        btnXuatHoaDon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mobileworld/icon/icons8-excel-30.png"))); // NOI18N
        btnXuatHoaDon.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnXuatHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatHoaDonActionPerformed(evt);
            }
        });
        jPanel9.add(btnXuatHoaDon);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnXuatHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatHoaDonActionPerformed
        // TODO add your handling code here:
        int check = JOptionPane.showConfirmDialog(this, "Bạn có muốn xuất danh sách Hóa Đơn không?", "Confirm", JOptionPane.YES_NO_CANCEL_OPTION);
        if (check == JOptionPane.YES_OPTION) {
            sr.xuatHoaDon();
            JOptionPane.showMessageDialog(this, "Xuất hóa đơn thành công.");
            return;
        }
        if (check == JOptionPane.NO_OPTION) {
            JOptionPane.showMessageDialog(this, "Bạn đã chọn NO.");
            return;
        } else {
            JOptionPane.showMessageDialog(this, "Bạn đã chọn CANCEL.");
            return;
        }
    }//GEN-LAST:event_btnXuatHoaDonActionPerformed

    private void buttonCustom4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCustom4ActionPerformed

        int index = tblHienThi1.getSelectedRow();
        if (index != -1) { // Đảm bảo rằng đã chọn một dòng trong bảng
            int check = JOptionPane.showConfirmDialog(this, "Bạn có muốn in Hoa Đơn đã chọn không?", "Confirm", JOptionPane.YES_NO_CANCEL_OPTION);
            if (check == JOptionPane.YES_OPTION) {
                // Nếu người dùng chọn Yes, thực hiện in Hoa Đơn
//                HoaDonModel hdm = list.get(index);
//                inHoaDon(hdm.getIdHD());
                  printHD();
            }
        } else {
            // Nếu không có hàng nào được chọn, hiển thị thông báo lỗi
            JOptionPane.showMessageDialog(this, "Vui lòng chọn hóa đơn cần in!");
        }
    }//GEN-LAST:event_buttonCustom4ActionPerformed

    private void tblHienThi1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHienThi1MouseClicked
        // TODO add your handling code here:
        int index = tblHienThi1.getSelectedRow();
        HoaDonModel hdm = list.get(index);

        tableModel2 = (DefaultTableModel) tblHienThi2.getModel();
        list2 = srCT.getAll(hdm.getIdHD());
        showDataTable2(list2);

        tableModel3 = (DefaultTableModel) tblHienThi3.getModel();
        listLS = srLSHD.getAll(hdm.getIdHD());
        showDataTable3(listLS);
    }//GEN-LAST:event_tblHienThi1MouseClicked

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed
        if (txtSearch.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập thông tin cần tìm kiếm.", "ERROR", JOptionPane.QUESTION_MESSAGE);
            txtSearch.setText("");
            return;
        }
        list = sr.search(txtSearch.getText()); // Cập nhật danh sách sau khi tìm kiếm
        showDataTable(list);
        int index = tblHienThi1.getRowCount();
        if (index >= 1) {
            JOptionPane.showMessageDialog(this, "Search thành công.");
            return;
        } else {
            JOptionPane.showMessageDialog(this, "Không tìm thấy dữ liệu.");
            return;
        }
    }//GEN-LAST:event_txtSearchActionPerformed

    private void buttonCustom1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCustom1ActionPerformed
        // TODO add your handling code here:
        int check = JOptionPane.showConfirmDialog(this, "Bạn có muốn quét QR không?", "Confirm", JOptionPane.YES_NO_CANCEL_OPTION);
        if (check == JOptionPane.YES_OPTION) {
            qrcode qr = new qrcode();
            qr.setQRCodeListener(this);
            qr.setVisible(true);
            return;
        }
        if (check == JOptionPane.NO_OPTION) {
            JOptionPane.showMessageDialog(this, "Bạn đã chọn NO.");
            return;
        } else {
            JOptionPane.showMessageDialog(this, "Banj đã chọn CANCEL.");
            return;
        }

    }//GEN-LAST:event_buttonCustom1ActionPerformed
    private BigDecimal convertStringToBigDecimal(String input) {
        // Loại bỏ tất cả các ký tự không phải là số từ chuỗi
        String cleanInput = input.replaceAll("[^0-9]", "");
        // Chuyển đổi chuỗi đã làm sạch thành BigDecimal
        return new BigDecimal(cleanInput);
    }
    private void resetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetActionPerformed
        // TODO add your handling code here:
        list = sr.getAllHD();
        showDataTable(list);

    }//GEN-LAST:event_resetActionPerformed

    private void sliderGradient1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sliderGradient1StateChanged
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        String formattedValue = decimalFormat.format(sliderGradient1.getValue());
        jLabel9.setText(formattedValue + " " + "VNĐ");
    }//GEN-LAST:event_sliderGradient1StateChanged

    private void txtSearchHDCTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchHDCTActionPerformed
        // TODO add your handling code here:
        if (txtSearchHDCT.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập thông tin cần tìm kiếm.", "ERROR", JOptionPane.QUESTION_MESSAGE);
            txtSearchHDCT.setText("");
            return;
        }
        list22 = srCT.SearchHDCT(txtSearchHDCT.getText());

        int index = tblHienThi2.getRowCount();
        if (index >= 1) {
            showDataTable2(list22);
            JOptionPane.showMessageDialog(this, "Search thành công.");
            return;
        } else {
            JOptionPane.showMessageDialog(this, "Không tìm thấy dữ liệu.");
            return;
        }

    }//GEN-LAST:event_txtSearchHDCTActionPerformed

    private void cboNSXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboNSXActionPerformed
        // TODO add your handling code here:
        String tenNSX = String.valueOf(cboNSX.getSelectedItem());
        showDataTable2(srCT.fillNSX(tenNSX));
        if (cboNSX.equals("Tất cả")) {
            showDataTable2(list22);

        }
    }//GEN-LAST:event_cboNSXActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private mobileworld.swing.BadgeButton badgeButton1;
    private mobileworld.swing.BadgeButton badgeButton2;
    private mobileworld.swing.BadgeButton badgeButton3;
    private mobileworld.swing.BadgeButton badgeButton5;
    private mobileworld.swing.ButtonCustom btnXuatHoaDon;
    private mobileworld.swing.ButtonCustom buttonCustom1;
    private mobileworld.swing.ButtonCustom buttonCustom4;
    private mobileworld.swing.Combobox cboNSX;
    private mobileworld.swing.Combobox combobox1;
    private mobileworld.swing.Combobox combobox2;
    private mobileworld.swing.Combobox combobox4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private mobileworld.swing.ButtonCustom reset;
    private mobileworld.test.raven.slider.SliderGradient sliderGradient1;
    private mobileworld.swing.Table tblHienThi1;
    private mobileworld.swing.Table tblHienThi2;
    private mobileworld.swing.Table tblHienThi3;
    private mobileworld.swing.TextField txtSearch;
    private mobileworld.swing.TextField txtSearchHDCT;
    // End of variables declaration//GEN-END:variables

}
