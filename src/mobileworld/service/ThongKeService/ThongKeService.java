/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mobileworld.service.ThongKeService;

import java.time.LocalDate;
import java.util.List;
import mobileworld.viewModel.ThongKe.HoaDonTK;

/**
 *
 * @author Admin
 */
public interface ThongKeService {

    public List<HoaDonTK> getListByHoaDon();

    public float getDoanhThu();

    public float soTienDaThuDuoc();

    public int soHD();

    public float hoaDonChuaThanhToan();

    public List<HoaDonTK> timTheoNam(String Year);

    public List<HoaDonTK> timTheoNamTable(String Year);

    public List<HoaDonTK> timTheoThoiGianTable(LocalDate ngayBD, LocalDate ngayKT);

    public List<HoaDonTK> hienBang();

    public List<HoaDonTK> thongKeTheoNgayChart(LocalDate ngayHT);

    public List<HoaDonTK> thongKeTheoNgayTable(LocalDate ngayHT);

    public List<HoaDonTK> timTheoThoiGianChart(LocalDate ngayBD, LocalDate ngayKT);

    public List<HoaDonTK> sanPhamBanChayTable();

    public List<HoaDonTK> sanPhamBanChayPerYear(String Year);

    public List<HoaDonTK> sanPhamBanChayPerTime(LocalDate ngayBD, LocalDate ngayKT);

    public List<HoaDonTK> sanPhamBanChayToday(LocalDate ngayHT);
}
