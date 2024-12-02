package umc.spring.service.StoreService;

import org.springframework.data.domain.Page;
import umc.spring.domain.Mission;
import umc.spring.domain.Review;

public interface StoreQueryService {
    Page<Mission> getMissionList(Long StoreId, Integer page);
}
