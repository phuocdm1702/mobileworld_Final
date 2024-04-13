package mobileworld.repository.ChiTietSanPhamRepo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import mobileworld.config.DBConnect;
import mobileworld.model.BoNho;

public class BoNhoRepository {

    public List<BoNho> getAll() {
        List<BoNho> listBN = new ArrayList<>();

        String sql = """
            select ID,DungLuongBoNho from BoNho WHERE Deleted = 1 ORDER BY ID DESC
        """;

        try ( Connection con = DBConnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                BoNho bn = new BoNho();
                bn.setId(rs.getString(1));
                bn.setDungLuongBoNho(rs.getString(2));
                listBN.add(bn);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listBN;
    }

    public boolean add(BoNho bn) {
        String sql = """
                            INSERT INTO [dbo].[BoNho]
                            ([DungLuongBoNho]
                            ,[Deleted]
                            ,[CreatedAt]
                            ,[CreatedBy])
                      VALUES
                            (?,?,?,?)
                 """;
        int check = 0;
        try ( Connection con = DBConnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, bn.getDungLuongBoNho());
            ps.setObject(2, bn.getDeleted());
            ps.setObject(3, bn.getCreatedAt());
            ps.setObject(4, bn.getCreatedBy());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }
}
