package com.example.boardlogin.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Objects;

@Getter
@ToString
@Table(name = "userTable", indexes = {
        @Index(columnList = "userId"),
        @Index(columnList = "name"),
        @Index(columnList = "phoneNumber")
})
@Entity
public class UserEntity extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Setter
    @Column(nullable = false, length = 20)
    private String userId;

    @Setter
    @Column(nullable = false, length = 200)
    private String password;

    @Setter
    @Column(nullable = false, length = 10)
    private String name;

    @Setter
    @Column(nullable = false, length = 20)
    private String phoneNumber;

    @Setter
    @Column(nullable = false, length = 100)
    private String addr;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return Objects.equals(idx, that.idx);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idx);
    }
}
