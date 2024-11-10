package umc.spring.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import umc.spring.domain.common.BaseEntity;
import umc.spring.domain.enums.Gender;
import umc.spring.domain.enums.SocialType;
import umc.spring.domain.enums.UserStatus;
import umc.spring.domain.mapping.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(nullable = false, length = 20)
    private String nickname;

    @Column(nullable = false, length = 40)
    private String address;

    @Column(nullable = false, length = 40)
    private String specAddress;

    @Column(nullable = false, length = 15)
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(10)")
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private SocialType socialType;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(15) DEFAULT 'ACTIVE'")
    private UserStatus status;

    private LocalDate inactiveDate;

    @Column(nullable = false, length = 50)
    private String email;

    @ColumnDefault("0")
    private Integer point;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @Builder.Default
    private List<UserPrefer> UserPreferList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @Builder.Default
    private List<Review> reviewList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @Builder.Default
    private List<UserMission> UserMissionList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    @Builder.Default
    private List<UserRegion> UserRegion = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    @Builder.Default
    private List<UserAgree> UserAgreeList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    @Builder.Default
    private List<UserAlert> UseralertList = new ArrayList<>();

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", point=" + point +
                ", status='" + status + '\'' +
                '}';
    }

}
