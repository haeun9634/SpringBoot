package umc.spring.domain.mapping;

import jakarta.persistence.*;
import lombok.*;
import umc.spring.domain.Alert;
import umc.spring.domain.User;
import umc.spring.domain.common.BaseEntity;
import umc.spring.domain.enums.AgreeStatus;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UserAlert extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "alert_id")
    private Alert alert;  // 알림 정보

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AgreeStatus alertAgree;  // 알림에 대한 동의 상태 (예: 동의, 거부 등)

    @Column(nullable = false)
    private boolean isread;  // 알림 읽음 여부 (true/false)
}

