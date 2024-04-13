package mobileworld.repository.ChiTietSanPhamRepo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import mobileworld.config.DBConnect;
import mobileworld.model.Ram;

public class RamRepository {

    public List<Ram> getAll() {
        List<Ram> listRam = new ArrayList<>();

        String sql = """
            select ID,DungLuongRam from Ram WHERE Deleted = 1 ORDER BY ID DESC
        """;

        try ( Connection con = DBConnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Ram ram = new Ram();
                ram.setId(rs.getString(1));
                ram.setDungLuongRam(rs.getString(2));
                listRam.add(ram);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listRam;
    }

    public boolean add(Ram ram) {
        String sql = """
                            INSERT INTO [dbo].[Ram]
                            ([DungLuongRam]
                            ,[Deleted]
                            ,[CreatedAt]
                             ,[CreatedBy])
                      VALUES
                            (?,?,?,?)
                 """;
        int check = 0;
        try ( Connection con = DBConnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, ram.getDungLuongRam());
            ps.setObject(2, ram.getDeleted());
            ps.setObject(3, ram.getCreatedAt());
            ps.setObject(4, ram.getCreatedBy());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }
}
