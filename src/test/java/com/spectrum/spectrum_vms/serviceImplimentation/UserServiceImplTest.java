package com.spectrum.spectrum_vms.serviceImplimentation;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.spectrum.spectrum_vms.enums.Role;
import com.spectrum.spectrum_vms.user.User;
import com.spectrum.spectrum_vms.user.UserRepository;

import java.awt.dnd.InvalidDnDOperationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {UserServiceImpl.class})
@ExtendWith(SpringExtension.class)
class UserServiceImplTest {
    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserServiceImpl userServiceImpl;

    /**
     * Method under test: {@link UserServiceImpl#save(User)}
     */
    @Test
    void testSave() {
        User user = new User("Jane", "Doe", "jane.doe@example.org", "iloveyou", Role.USER);

        when(userRepository.save((User) any())).thenReturn(user);
        when(passwordEncoder.encode((CharSequence) any())).thenReturn("secret");
        assertSame(user, userServiceImpl.save(new User("Jane", "Doe", "jane.doe@example.org", "iloveyou", Role.USER)));
        verify(userRepository).save((User) any());
        verify(passwordEncoder).encode((CharSequence) any());
    }

    /**
     * Method under test: {@link UserServiceImpl#save(User)}
     */
    @Test
    void testSave2() {
        when(userRepository.save((User) any()))
                .thenReturn(new User("Jane", "Doe", "jane.doe@example.org", "iloveyou", Role.USER));
        when(passwordEncoder.encode((CharSequence) any())).thenThrow(new InvalidDnDOperationException());
        assertThrows(InvalidDnDOperationException.class,
                () -> userServiceImpl.save(new User("Jane", "Doe", "jane.doe@example.org", "iloveyou", Role.USER)));
        verify(passwordEncoder).encode((CharSequence) any());
    }

    /**
     * Method under test: {@link UserServiceImpl#save(User)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testSave3() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "com.spectrum.spectrum_vms.user.User.getFirstName()" because "user" is null
        //       at com.spectrum.spectrum_vms.serviceImplimentation.UserServiceImpl.save(UserServiceImpl.java:27)
        //   See https://diff.blue/R013 to resolve this issue.

        when(userRepository.save((User) any()))
                .thenReturn(new User("Jane", "Doe", "jane.doe@example.org", "iloveyou", Role.USER));
        when(passwordEncoder.encode((CharSequence) any())).thenReturn("secret");
        userServiceImpl.save(null);
    }

    /**
     * Method under test: {@link UserServiceImpl#save(User)}
     */
    @Test
    void testSave4() {
        User user = new User("Jane", "Doe", "jane.doe@example.org", "iloveyou", Role.USER);

        when(userRepository.save((User) any())).thenReturn(user);
        when(passwordEncoder.encode((CharSequence) any())).thenReturn("secret");
        User user1 = mock(User.class);
        when(user1.getRole()).thenReturn(Role.USER);
        when(user1.getEmail()).thenReturn("jane.doe@example.org");
        when(user1.getFirstName()).thenReturn("Jane");
        when(user1.getLastName()).thenReturn("Doe");
        when(user1.getPassword()).thenReturn("iloveyou");
        assertSame(user, userServiceImpl.save(user1));
        verify(userRepository).save((User) any());
        verify(passwordEncoder).encode((CharSequence) any());
        verify(user1).getRole();
        verify(user1).getEmail();
        verify(user1).getFirstName();
        verify(user1).getLastName();
        verify(user1).getPassword();
    }

    /**
     * Method under test: {@link UserServiceImpl#update(User)}
     */
    @Test
    void testUpdate() throws Exception {
        assertThrows(InvalidDnDOperationException.class,
                () -> userServiceImpl.update(new User("Jane", "Doe", "jane.doe@example.org", "iloveyou", Role.USER)));
    }

    /**
     * Method under test: {@link UserServiceImpl#update(User)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUpdate2() throws Exception {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "com.spectrum.spectrum_vms.user.User.hasId()" because "user" is null
        //       at com.spectrum.spectrum_vms.serviceImplimentation.UserServiceImpl.update(UserServiceImpl.java:38)
        //   See https://diff.blue/R013 to resolve this issue.

        userServiceImpl.update(null);
    }

    /**
     * Method under test: {@link UserServiceImpl#update(User)}
     */
    @Test
    void testUpdate3() throws Exception {
        User user = new User("Jane", "Doe", "jane.doe@example.org", "iloveyou", Role.USER);

        when(userRepository.save((User) any())).thenReturn(user);
        when(passwordEncoder.encode((CharSequence) any())).thenReturn("secret");
        User user1 = mock(User.class);
        when(user1.getRole()).thenReturn(Role.USER);
        when(user1.getEmail()).thenReturn("jane.doe@example.org");
        when(user1.getFirstName()).thenReturn("Jane");
        when(user1.getLastName()).thenReturn("Doe");
        when(user1.getPassword()).thenReturn("iloveyou");
        when(user1.hasId()).thenReturn(true);
        assertSame(user, userServiceImpl.update(user1));
        verify(userRepository).save((User) any());
        verify(passwordEncoder).encode((CharSequence) any());
        verify(user1).hasId();
        verify(user1).getRole();
        verify(user1).getEmail();
        verify(user1).getFirstName();
        verify(user1).getLastName();
        verify(user1).getPassword();
    }

    /**
     * Method under test: {@link UserServiceImpl#update(User)}
     */
    @Test
    void testUpdate4() throws Exception {
        when(userRepository.save((User) any()))
                .thenReturn(new User("Jane", "Doe", "jane.doe@example.org", "iloveyou", Role.USER));
        when(passwordEncoder.encode((CharSequence) any())).thenReturn("secret");
        User user = mock(User.class);
        when(user.getRole()).thenThrow(new InvalidDnDOperationException());
        when(user.getEmail()).thenThrow(new InvalidDnDOperationException());
        when(user.getFirstName()).thenThrow(new InvalidDnDOperationException());
        when(user.getLastName()).thenThrow(new InvalidDnDOperationException());
        when(user.getPassword()).thenThrow(new InvalidDnDOperationException());
        when(user.hasId()).thenReturn(true);
        assertThrows(InvalidDnDOperationException.class, () -> userServiceImpl.update(user));
        verify(user).hasId();
        verify(user).getFirstName();
    }

    /**
     * Method under test: {@link UserServiceImpl#deleteByIds(Long[])}
     */
    @Test
    void testDeleteByIds() {
        doNothing().when(userRepository).deleteAllById((Iterable<Long>) any());
        userServiceImpl.deleteByIds(new Long[]{1L});
        verify(userRepository).deleteAllById((Iterable<Long>) any());
    }

    /**
     * Method under test: {@link UserServiceImpl#deleteByIds(Long[])}
     */
    @Test
    void testDeleteByIds2() {
        doThrow(new InvalidDnDOperationException()).when(userRepository).deleteAllById((Iterable<Long>) any());
        assertThrows(InvalidDnDOperationException.class, () -> userServiceImpl.deleteByIds(new Long[]{1L}));
        verify(userRepository).deleteAllById((Iterable<Long>) any());
    }

    /**
     * Method under test: {@link UserServiceImpl#getDataById(Long)}
     */
    @Test
    void testGetDataById() {
        User user = new User("Jane", "Doe", "jane.doe@example.org", "iloveyou", Role.USER);

        when(userRepository.findById((Long) any())).thenReturn(Optional.of(user));
        assertSame(user, userServiceImpl.getDataById(1L));
        verify(userRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link UserServiceImpl#getDataById(Long)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetDataById2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.util.NoSuchElementException: No value present
        //       at java.util.Optional.get(Optional.java:143)
        //       at com.spectrum.spectrum_vms.serviceImplimentation.UserServiceImpl.getDataById(UserServiceImpl.java:53)
        //   See https://diff.blue/R013 to resolve this issue.

        when(userRepository.findById((Long) any())).thenReturn(Optional.empty());
        userServiceImpl.getDataById(1L);
    }

    /**
     * Method under test: {@link UserServiceImpl#getDataById(Long)}
     */
    @Test
    void testGetDataById3() {
        when(userRepository.findById((Long) any())).thenThrow(new InvalidDnDOperationException());
        assertThrows(InvalidDnDOperationException.class, () -> userServiceImpl.getDataById(1L));
        verify(userRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link UserServiceImpl#getData()}
     */
    @Test
    void testGetData() {
        ArrayList<User> userList = new ArrayList<>();
        when(userRepository.findAll()).thenReturn(userList);
        List<User> actualData = userServiceImpl.getData();
        assertSame(userList, actualData);
        assertTrue(actualData.isEmpty());
        verify(userRepository).findAll();
    }

    /**
     * Method under test: {@link UserServiceImpl#getData()}
     */
    @Test
    void testGetData2() {
        when(userRepository.findAll()).thenThrow(new InvalidDnDOperationException());
        assertThrows(InvalidDnDOperationException.class, () -> userServiceImpl.getData());
        verify(userRepository).findAll();
    }
}

