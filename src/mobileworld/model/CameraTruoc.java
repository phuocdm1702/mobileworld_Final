package mobileworld.model;

import java.time.LocalDate;

public class CameraTruoc {

    private String soMP;
    private float deleted;
    private LocalDate createdAt;
    private String createdBy;
    private LocalDate updatedAt;
    private String updateBy;
    private String id;

    public CameraTruoc() {
    }

    public CameraTruoc(String soMP, float deleted, LocalDate createdAt, String createdBy, LocalDate updatedAt, String updateBy, String id) {
        this.soMP = soMP;
        this.deleted = deleted;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.updatedAt = updatedAt;
        this.updateBy = updateBy;
        this.id = id;
    }

    public CameraTruoc(String soMP, float deleted, LocalDate createdAt, String createdBy) {
        this.soMP = soMP;
        this.deleted = deleted;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
    }
    

    public String getSoMP() {
        return soMP;
    }

    public void setSoMP(String soMP) {
        this.soMP = soMP;
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
