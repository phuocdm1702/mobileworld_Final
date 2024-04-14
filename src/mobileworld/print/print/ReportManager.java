/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mobileworld.print.print;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;
import mobileworld.print.print.model.ParameterReportPayment;

/**
 *
 * @author VHC
 */
public class ReportManager {

    private static ReportManager instance;

    private JasperReport reportPay;

    public static ReportManager getInstance() {
        if (instance == null) {
            instance = new ReportManager();
        }
        return instance;
    }

    private ReportManager() {
    }

    public void compileReport() throws JRException {
//        reportPay = JasperCompileManager.compileReport(getClass().getResourceAsStream("C:\\Users\\ADMIN\\Documents\\mobileWorld3 - Sao chép (2)\\src\\mobileworld\\inHoaDon\\HoaDon.jrxml"));
        reportPay = JasperCompileManager.compileReport(getClass().getResourceAsStream("/mobileworld/print/print/PrintHoaDon.jrxml"));

    }
    
    
public void printReportPayment(ParameterReportPayment data) {
    try {
        Map<String, Object> para = new HashMap<>();
        para.put("maHoaDon", data.getMaHoaDon());
        para.put("tenKhachHang", data.getTenKhachHang());
        para.put("soDienThoai", data.getSoDienThoai());
        para.put("diaChi", data.getDiaChi());
        para.put("thanhToan", data.getThanhToan());
        para.put("thoiGian", data.getThoiGian());

        // Check if tongTien is not null before performing operations
        String tongTien = data.getTongTien();
        para.put("tongTien", tongTien!= null ? tongTien.replaceAll("[^\\d.]", "") : "");

        // Convert InputStream to BufferedImage
        InputStream qrInputStream = data.getQR();
        if (qrInputStream != null) {
            BufferedImage qrCodeImage = ImageIO.read(qrInputStream);
            para.put("QR", qrCodeImage);
        } else {
            para.put("QR", null); // Provide null if QR code InputStream is null
        }

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(data.getFields());
        JasperPrint print = JasperFillManager.fillReport(reportPay, para, dataSource);
        view(print);
    } catch (JRException | IOException e) {
        e.printStackTrace(); // Handle or log the exception according to your application's requirements
    }
}


    private void view(JasperPrint print) throws JRException {
        JasperViewer.viewReport(print, false);
    }

    public void checkJRXMLPath() {
        try {
            InputStream inputStream = getClass().getResourceAsStream("/mobileworld/print/print/PrintHoaDon.jrxml");
            if (inputStream != null) {
                System.out.println("JRXML path is valid.");
            } else {
                System.out.println("JRXML path is invalid. Check the path.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void checkCompilation() {
        try {
            compileReport();
            if (reportPay != null) {
                System.out.println("JRXML compilation successful.");
            } else {
                System.out.println("JRXML compilation failed.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void checkReportParameters(ParameterReportPayment data) {
        try {
            System.out.println("Mã hóa đơn: " + data.getMaHoaDon());
            System.out.println("Khách hàng: " + data.getTenKhachHang());
            System.out.println("Số điện thoại: " + data.getSoDienThoai());
            System.out.println("Địa chỉ: " + data.getDiaChi());
            System.out.println("Thanh toán: " + data.getThanhToan());
            System.out.println("Tong Tien: " + data.getTongTien());
            System.out.println("QR Code: " + data.getQR());

        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

}
