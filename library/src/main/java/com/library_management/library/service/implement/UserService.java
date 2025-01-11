package com.library_management.library.service.implement;

import com.library_management.library.constant.ErrorCode;
import com.library_management.library.entity.User;
import com.library_management.library.exception.ApiException;
import com.library_management.library.repository.UserRepository;
import com.library_management.library.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserService implements IUserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public User getUserById(int userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ApiException(ErrorCode.USER_NOT_EXIST));
    }
}
