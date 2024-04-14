/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mobileworld.repository.HoaDonRepo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import mobileworld.viewModel.HoaDonChiTietModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import mobileworld.config.DBConnect;

/**
 *
 * @author ADMIN
 */
public class HoaDonCTRepository {

    public List<HoaDonChiTietModel> getAll(String idHD) {
        List<HoaDonChiTietModel> list = new ArrayList<>();
        String sql = """
                      SELECT 
                          dbo.HoaDon.ID AS [ID Hóa Đơn], 
                          dbo.HoaDonChiTiet.ID AS [ID Hóa Đơn Chi Tiết], 
                          dbo.ChiTietSP.ID AS [ID Chi Tiết SP], 
                          dbo.DongSP.TenDsp AS [Tên Dòng SP], 
                          dbo.NhaSanXuat.[TenNsx] AS [Tên Nhà Sản Xuất], 
                          dbo.MauSac.TenMau AS [Tên Màu Sắc], 
                          dbo.Pin.DungLuongPin AS [Dung Lượng Pin], 
                          dbo.Imel.Imel AS [Imel], 
                          dbo.ChiTietSP.GiaBan AS [Giá Bán], 
                          dbo.HoaDonChiTiet.DonGia AS [Đơn Giá]
                      FROM   
                          dbo.HoaDon
                      INNER JOIN
                          dbo.HoaDonChiTiet ON dbo.HoaDon.ID = dbo.HoaDonChiTiet.IDHoaDon
                      INNER JOIN
                          dbo.ChiTietSP ON dbo.HoaDonChiTiet.IDCTSP = dbo.ChiTietSP.ID
                      INNER JOIN
                          dbo.DongSP ON dbo.ChiTietSP.IDDongSP = dbo.DongSP.ID
                      INNER JOIN
                          dbo.NhaSanXuat ON dbo.ChiTietSP.IDNSX = dbo.NhaSanXuat.ID
                      INNER JOIN
                          dbo.MauSac ON dbo.ChiTietSP.IDMauSac = dbo.MauSac.ID
                      INNER JOIN
                          dbo.Pin ON dbo.ChiTietSP.IDPin = dbo.Pin.ID
                      INNER JOIN
                          dbo.Imel ON dbo.HoaDonChiTiet.IDImel = dbo.Imel.ID
                      WHERE dbo.HoaDonChiTiet.IDHoaDon= ? 
                     """;
        try (Connection cnt = DBConnect.getConnection(); PreparedStatement ps = cnt.prepareStatement(sql)) {
            ps.setObject(1, idHD);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDonChiTietModel hdctm = new HoaDonChiTietModel();
                hdctm.setIdHD(rs.getString(1));
                hdctm.setIdHDinHDCT(rs.getString(2));
                hdctm.setIdCTSP(rs.getString(3));
                hdctm.setTenDSP(rs.getString(4));
                hdctm.setTenNSX(rs.getString(5));
                hdctm.setTenMau(rs.getString(6));
                hdctm.setDungLuongPin(rs.getString(7));
                hdctm.setImel(rs.getString(8));
                hdctm.setGiaBan(rs.getBigDecimal(9));
                hdctm.setTongTien(rs.getBigDecimal(10));
                list.add(hdctm);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<HoaDonChiTietModel> SearchHDCT(String txt) {
        List<HoaDonChiTietModel> list = new ArrayList<>();
        String sql = """
                      SELECT 
                                                    dbo.HoaDon.ID AS [ID Hóa Đơn], 
                                                    dbo.HoaDonChiTiet.ID AS [ID Hóa Đơn Chi Tiết], 
                                                    dbo.ChiTietSP.ID AS [ID Chi Tiết SP], 
                                                    dbo.DongSP.TenDsp AS [Tên Dòng SP], 
                                                    dbo.NhaSanXuat.[TenNsx] AS [Tên Nhà Sản Xuất], 
                                                    dbo.MauSac.TenMau AS [Tên Màu Sắc], 
                                                    dbo.Pin.DungLuongPin AS [Dung Lượng Pin], 
                                                    dbo.Imel.Imel AS [Imel], 
                                                    dbo.ChiTietSP.GiaBan AS [Giá Bán], 
                                                    dbo.HoaDonChiTiet.DonGia AS [Đơn Giá]
                                                FROM   
                                                    dbo.HoaDon
                                                INNER JOIN
                                                    dbo.HoaDonChiTiet ON dbo.HoaDon.ID = dbo.HoaDonChiTiet.IDHoaDon
                                                INNER JOIN
                                                    dbo.ChiTietSP ON dbo.HoaDonChiTiet.IDCTSP = dbo.ChiTietSP.ID
                                                INNER JOIN
                                                    dbo.DongSP ON dbo.ChiTietSP.IDDongSP = dbo.DongSP.ID
                                                INNER JOIN
                                                    dbo.NhaSanXuat ON dbo.ChiTietSP.IDNSX = dbo.NhaSanXuat.ID
                                                INNER JOIN
                                                    dbo.MauSac ON dbo.ChiTietSP.IDMauSac = dbo.MauSac.ID
                                                INNER JOIN
                                                    dbo.Pin ON dbo.ChiTietSP.IDPin = dbo.Pin.ID
                                                INNER JOIN
                                                    dbo.Imel ON dbo.HoaDonChiTiet.IDImel = dbo.Imel.ID
                      WHERE 
                            dbo.HoaDonChiTiet.ID LIKE ? ESCAPE '!'
                         OR dbo.ChiTietSP.ID LIKE ? ESCAPE '!'
                         OR dbo.DongSP.TenDsp LIKE ? ESCAPE '!'
                         OR dbo.NhaSanXuat.[TenNsx] LIKE ? ESCAPE '!'
                         OR dbo.MauSac.TenMau LIKE ? ESCAPE '!'
                         OR dbo.Pin.DungLuongPin LIKE ? ESCAPE '!'
                         OR dbo.Imel.Imel LIKE ? ESCAPE '!'
                         OR dbo.ChiTietSP.GiaBan LIKE ? ESCAPE '!'
                         OR dbo.HoaDonChiTiet.DonGia LIKE ? ESCAPE '!'
                     """;
        try (Connection cnt = DBConnect.getConnection(); PreparedStatement ps = cnt.prepareStatement(sql)) {
            for (int i = 0; i < txt.length(); i++) {
                ps.setString(1, "%" + txt + "%");
                ps.setString(2, "%" + txt + "%");
                ps.setString(3, "%" + txt + "%");
                ps.setString(4, "%" + txt + "%");
                ps.setString(5, "%" + txt + "%");
                ps.setString(6, "%" + txt + "%");
                ps.setString(7, "%" + txt + "%");
                ps.setString(8, "%" + txt + "%");
                ps.setString(9, "%" + txt + "%");
                ps.setString(9, "%" + txt + "%");
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        HoaDonChiTietModel hdctm = new HoaDonChiTietModel();
                        hdctm.setIdHD(rs.getString(1));
                        hdctm.setIdHDinHDCT(rs.getString(2));
                        hdctm.setIdCTSP(rs.getString(3));
                        hdctm.setTenDSP(rs.getString(4));
                        hdctm.setTenNSX(rs.getString(5));
                        hdctm.setTenMau(rs.getString(6));
                        hdctm.setDungLuongPin(rs.getString(7));
                        hdctm.setImel(rs.getString(8));
                        hdctm.setGiaBan(rs.getBigDecimal(9));
                        hdctm.setTongTien(rs.getBigDecimal(10));
                        list.add(hdctm);
                    }
                }
                break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<HoaDonChiTietModel> fillNSX(String tenNSX) {
        List<HoaDonChiTietModel> list = new ArrayList<>();
        String sql = """
                      SELECT 
                          dbo.HoaDon.ID AS [ID Hóa Đơn], 
                          dbo.HoaDonChiTiet.ID AS [ID Hóa Đơn Chi Tiết], 
                          dbo.ChiTietSP.ID AS [ID Chi Tiết SP], 
                          dbo.DongSP.TenDsp AS [Tên Dòng SP], 
                          dbo.NhaSanXuat.[TenNsx] AS [Tên Nhà Sản Xuất], 
                          dbo.MauSac.TenMau AS [Tên Màu Sắc], 
                          dbo.Pin.DungLuongPin AS [Dung Lượng Pin], 
                          dbo.Imel.Imel AS [Imel], 
                          dbo.ChiTietSP.GiaBan AS [Giá Bán], 
                          dbo.HoaDonChiTiet.DonGia AS [Đơn Giá]
                      FROM   
                          dbo.HoaDon
                      INNER JOIN
                          dbo.HoaDonChiTiet ON dbo.HoaDon.ID = dbo.HoaDonChiTiet.IDHoaDon
                      INNER JOIN
                          dbo.ChiTietSP ON dbo.HoaDonChiTiet.IDCTSP = dbo.ChiTietSP.ID
                      INNER JOIN
                          dbo.DongSP ON dbo.ChiTietSP.IDDongSP = dbo.DongSP.ID
                      INNER JOIN
                          dbo.NhaSanXuat ON dbo.ChiTietSP.IDNSX = dbo.NhaSanXuat.ID
                      INNER JOIN
                          dbo.MauSac ON dbo.ChiTietSP.IDMauSac = dbo.MauSac.ID
                      INNER JOIN
                          dbo.Pin ON dbo.ChiTietSP.IDPin = dbo.Pin.ID
                      INNER JOIN
                          dbo.Imel ON dbo.HoaDonChiTiet.IDImel = dbo.Imel.ID
                      WHERE dbo.NhaSanXuat.[TenNsx] = ? 
                     """;
        try (Connection cnt = DBConnect.getConnection(); PreparedStatement ps = cnt.prepareStatement(sql)) {
            ps.setObject(1, tenNSX);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDonChiTietModel hdctm = new HoaDonChiTietModel();
                hdctm.setIdHD(rs.getString(1));
                hdctm.setIdHDinHDCT(rs.getString(2));
                hdctm.setIdCTSP(rs.getString(3));
                hdctm.setTenDSP(rs.getString(4));
                hdctm.setTenNSX(rs.getString(5));
                hdctm.setTenMau(rs.getString(6));
                hdctm.setDungLuongPin(rs.getString(7));
                hdctm.setImel(rs.getString(8));
                hdctm.setGiaBan(rs.getBigDecimal(9));
                hdctm.setTongTien(rs.getBigDecimal(10));
                list.add(hdctm);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}
