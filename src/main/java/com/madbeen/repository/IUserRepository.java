package com.madbeen.repository;

import com.madbeen.entity.User;
import org.springframework.stereotype.Repository;

/**
 * @author: madbeen
 * @date: 2022/03/20/11:13 PM
 */
@Repository
public interface IUserRepository {

    User queryUserByUserName(String userName);

}
