package mobileworld.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ChiTietSP {

    private String imel;
    private String idNsx;
    private String idDsp;
    private String idMauSac;
    private String idPin;
    private String idManHinh;
    private String idRam;
    private String IdboNho;
    private String idCpu;
    private String idCamTruoc;
    private String idCamSau;
    private String ghiChu;
    private BigDecimal giaBan;
    private float deleted;
    private LocalDate createdAt;
    private String createdBy;
    private LocalDate updatedAt;
    private String updateBy;
    private String id;

    public ChiTietSP() {
    }

    public ChiTietSP(String imel, String idNsx, String idDsp, String idMauSac, String idPin, String idManHinh, String idRam, String IdboNho, String idCpu, String idCamTruoc, String idCamSau, String ghiChu, BigDecimal giaBan, float deleted, LocalDate createdAt, String createdBy) {
        this.imel = imel;
        this.idNsx = idNsx;
        this.idDsp = idDsp;
        this.idMauSac = idMauSac;
        this.idPin = idPin;
        this.idManHinh = idManHinh;
        this.idRam = idRam;
        this.IdboNho = IdboNho;
        this.idCpu = idCpu;
        this.idCamTruoc = idCamTruoc;
        this.idCamSau = idCamSau;
        this.ghiChu = ghiChu;
        this.giaBan = giaBan;
        this.deleted = deleted;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
    }

    public String getImel() {
        return imel;
    }

    public void setImel(String imel) {
        this.imel = imel;
    }

    public String getIdNsx() {
        return idNsx;
    }

    public void setIdNsx(String idNsx) {
        this.idNsx = idNsx;
    }

    public String getIdDsp() {
        return idDsp;
    }

    public void setIdDsp(String idDsp) {
        this.idDsp = idDsp;
    }

    public String getIdMauSac() {
        return idMauSac;
    }

    public void setIdMauSac(String idMauSac) {
        this.idMauSac = idMauSac;
    }

    public String getIdPin() {
        return idPin;
    }

    public void setIdPin(String idPin) {
        this.idPin = idPin;
    }

    public String getIdManHinh() {
        return idManHinh;
    }

    public void setIdManHinh(String idManHinh) {
        this.idManHinh = idManHinh;
    }

    public String getIdRam() {
        return idRam;
    }

    public void setIdRam(String idRam) {
        this.idRam = idRam;
    }

    public String getIdboNho() {
        return IdboNho;
    }

    public void setIdboNho(String IdboNho) {
        this.IdboNho = IdboNho;
    }

    public String getIdCpu() {
        return idCpu;
    }

    public void setIdCpu(String idCpu) {
        this.idCpu = idCpu;
    }

    public String getIdCamTruoc() {
        return idCamTruoc;
    }

    public void setIdCamTruoc(String idCamTruoc) {
        this.idCamTruoc = idCamTruoc;
    }

    public String getIdCamSau() {
        return idCamSau;
    }

    public void setIdCamSau(String idCamSau) {
        this.idCamSau = idCamSau;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public BigDecimal getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(BigDecimal giaBan) {
        this.giaBan = giaBan;
    }

    public float getDeleted() {
        return deleted;
    }

    public void setDeleted(float deleted) {
        this.deleted = deleted;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
