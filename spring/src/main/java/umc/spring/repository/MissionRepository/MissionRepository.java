package umc.spring.repository.MissionRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import umc.spring.domain.Mission;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.domain.User;
import umc.spring.domain.enums.MissionStatus;

public interface MissionRepository extends JpaRepository<Mission, Long>, MissionRepositoryCustom {
    Page<Mission> findAllByStore(Store store, PageRequest pageRequest);

    @Query("SELECT m FROM Mission m JOIN UserMission um ON m.id = um.mission.id WHERE um.user = :user AND um.status = :status")
    Page<Mission> findChallengingMissionsByUser(@Param("user") User user, @Param("status") MissionStatus status, PageRequest pageRequest);
}