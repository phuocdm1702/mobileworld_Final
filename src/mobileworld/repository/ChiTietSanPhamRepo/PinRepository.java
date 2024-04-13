package mobileworld.repository.ChiTietSanPhamRepo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import mobileworld.config.DBConnect;
import mobileworld.model.Pin;

public class PinRepository {

    public List<Pin> getAll() {
        List<Pin> listPin = new ArrayList<>();

        String sql = """
            select ID,DungLuongPin from Pin WHERE Deleted = 1 ORDER BY ID DESC
        """;

        try ( Connection con = DBConnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Pin pin = new Pin();
                pin.setId(rs.getString(1));
                pin.setDungLuongPin(rs.getString(2));
                listPin.add(pin);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listPin;
    }
    
    public boolean add(Pin pin) {
        String sql = """
                            INSERT INTO [dbo].[Pin]
                            ([DungLuongPin]
                            ,[Deleted]
                      ,[CreatedAt]
                                                  ,[CreatedBy])
                      VALUES
                            (?,?,?,?)
                 """;
        int check = 0;
        try ( Connection con = DBConnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, pin.getDungLuongPin());
            ps.setObject(2, pin.getDeleted());
            ps.setObject(3, pin.getCreatedAt());
            ps.setObject(4, pin.getCreatedBy());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }
}
