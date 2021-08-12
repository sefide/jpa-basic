package hellojpa;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Member extends BaseEntity {

    @Id @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String username;

    private Integer age;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    private LocalDateTime testDateTime;

    @Lob
    private String description;

    public Member() {
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public Integer getAge() {
        return age;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getTestDateTime() {
        return testDateTime;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTestDateTime(LocalDateTime testDateTime) {
        this.testDateTime = testDateTime;
    }
}
