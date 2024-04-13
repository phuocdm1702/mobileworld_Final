package mobileworld.service.BanHangService;

import java.util.List;
import mobileworld.model.HinhThucThanhToanEntity;
import mobileworld.model.HoaDon;
import mobileworld.model.HoaDonChiTietEntity;
import mobileworld.model.PhuongThucThanhToan;
import mobileworld.repository.BanHangRepo.BanHangRepository;
import mobileworld.viewModel.BanHangViewModel.HoaDonViewModel;
import mobileworld.viewModel.ChiTietSanPhamViewModel;

public class BanHangService {

    BanHangRepository repo = new BanHangRepository();

    public List<HoaDonViewModel> getHD() {
        return repo.getHD();
    }

    public List<ChiTietSanPhamViewModel> getSP() {
        return repo.getSP();
    }

    public HoaDonViewModel getNewestHoaDon() {
        // Lấy danh sách hóa đơn từ repository
        List<HoaDonViewModel> listHD = repo.getHD();

        // Nếu danh sách không rỗng, trả về hóa đơn đầu tiên (được sắp xếp theo ngày tạo giảm dần)
        if (!listHD.isEmpty()) {
            return listHD.get(0);
        }

        // Trả về null nếu không có hóa đơn nào
        return null;
    }

    public boolean addNewBlankInvoice(HoaDon hd) {
        return repo.addNewBlankInvoice(hd);
    }

    public List<HoaDonViewModel> searchHD(String text) {
        return repo.searchHD(text);
    }

    public boolean deleteHD(String idHD) {
        return repo.deleteHD(idHD);
    }

    public List<ChiTietSanPhamViewModel> LocSP(String Nsx, String Pin, String ManHinh, String Cpu) {
        return repo.LocSP(Nsx, Pin, ManHinh, Cpu);
    }

    public List<ChiTietSanPhamViewModel> search(String keyword) {
        return repo.search(keyword);
    }

    public List<ChiTietSanPhamViewModel> getGioHang(String imel) {
        return repo.getGioHang(imel);
    }

    public List<ChiTietSanPhamViewModel> deleteGioHang(List<String> imels) {
        return repo.deleteGioHang(imels);
    }

    public List<ChiTietSanPhamViewModel> selectIdDSP(String idDsp) {
        return repo.selectIdDSP(idDsp);
    }

    public List<ChiTietSanPhamViewModel> deleteIdDSP(String idDsp) {
        return repo.deleteIdDSP(idDsp);
    }

    public boolean ThanhToanHD(HoaDon hd, List<HoaDonChiTietEntity> hdctList, String idHD, PhuongThucThanhToan pttt, HinhThucThanhToanEntity httte) {
        return repo.ThanhToanHD(hd, hdctList, idHD, pttt, httte);
    }

    public List<ChiTietSanPhamViewModel> getSPTuHoaDon(String idHD) {
        return repo.getSPTuHoaDon(idHD);
    }

    public boolean updateDeleteHD(String idHD) {
        return repo.updateDeleteHD(idHD);
    }

    public boolean GiaoHang(HoaDon hd, List<HoaDonChiTietEntity> hdctList, String idHD, PhuongThucThanhToan pttt, HinhThucThanhToanEntity httte) {
        return repo.GiaoHang(hd, hdctList, idHD, pttt, httte);
    }

    public boolean HuyGiaoHang(String idHD) {
        return repo.HuyGiaoHang(idHD);
    }

    public List<ChiTietSanPhamViewModel> searchByImel(String Imel) {
        return repo.searchByImel(Imel);
    }

    public List<String> getIdCtspQR(String Imel) {
        return repo.getIdCtspQR(Imel);
    }
}
