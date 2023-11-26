package com.example.boardlogin.repository;

import com.example.boardlogin.entity.UserEntity;
import com.example.boardlogin.vo.UserVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByUserId(UserVO userVO);

}
