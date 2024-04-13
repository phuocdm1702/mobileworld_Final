package mobileworld.service.ChiTietSanPhamService;

import java.util.List;
import mobileworld.model.CameraSau;
import mobileworld.repository.ChiTietSanPhamRepo.CameraSauRepository;

public class CameraSauService {

    CameraSauRepository repo = new CameraSauRepository();

    public List<CameraSau> getAll() {
        return repo.getAll();
    }

    public boolean add(CameraSau cam) {
        return repo.add(cam);
    }
}
