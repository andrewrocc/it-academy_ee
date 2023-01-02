package my.first.service;

import lombok.RequiredArgsConstructor;
import my.first.dao.AppUserDao;
import my.first.model.AppUser;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AppUserService {

    private final AppUserDao userDao;

    public List<AppUser> findUserByUsername(String username) {
        System.out.println(username);
        return userDao.findByUserName(username);
    }
}
