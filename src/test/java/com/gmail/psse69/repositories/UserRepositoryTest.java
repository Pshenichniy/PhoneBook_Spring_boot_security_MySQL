package com.gmail.psse69.repositories;


import com.gmail.psse69.configuration.RepositoryConfiguration;
import com.gmail.psse69.model.User;
import com.gmail.psse69.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {RepositoryConfiguration.class})
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {

        this.userRepository = userRepository;
    }


    @Test
    public void testSaveUser() {
        User user = new User();
        user.setPassword("123456");
        user.setName("TestFromTest");
        user.setLastName("TestFromTest");
        user.setActive(1);
        user.setEmail("testfromtest@test.com");

        assertNull(user.getId());
        userRepository.save(user);
        assertNotNull(user.getId());

        User someUser = userRepository.findOne(user.getId());

        assertNotNull(someUser);

        assertEquals(user.getId(), someUser.getId());
        assertEquals(user.getEmail(), someUser.getEmail());

        someUser.setEmail("testfromtest2@test.com");
        userRepository.save(someUser);

        User someUserUpdate = userRepository.findOne(someUser.getId());
        assertEquals(someUser.getEmail(), someUserUpdate.getEmail());

        long userCount = userRepository.count();
        assertEquals(userCount,4);

        Iterable<User> users = userRepository.findAll();

        int count = 0;

        for (User us : users) {
            count++;
        }
        assertEquals(count, 4);
    }
}