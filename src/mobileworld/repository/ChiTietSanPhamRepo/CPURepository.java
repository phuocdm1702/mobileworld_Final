package mobileworld.repository.ChiTietSanPhamRepo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import mobileworld.config.DBConnect;
import mobileworld.model.CPU;

public class CPURepository {

    public List<CPU> getAll() {
        List<CPU> listCpu = new ArrayList<>();

        String sql = """
            select ID,CPU from CPU WHERE Deleted = 1 ORDER BY ID DESC
        """;

        try ( Connection con = DBConnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                CPU cpu = new CPU();
                cpu.setId(rs.getString(1));
                cpu.setCpu(rs.getString(2));
                listCpu.add(cpu);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listCpu;
    }

    public boolean add(CPU cpu) {
        String sql = """
                                        INSERT INTO [dbo].[CPU]
                                                        ([CPU]
                                                        ,[Deleted]
                                                        ,[CreatedAt]
                                                        ,[CreatedBy])
                                                  VALUES
                                                        (?,?,?,?)
                 """;
        int check = 0;
        try ( Connection con = DBConnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, cpu.getCpu());
            ps.setObject(2, cpu.getDeleted());
            ps.setObject(3, cpu.getCreatedAt());
            ps.setObject(4, cpu.getCreatedBy());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }
}
