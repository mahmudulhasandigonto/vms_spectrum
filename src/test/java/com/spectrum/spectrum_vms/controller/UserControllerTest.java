package com.spectrum.spectrum_vms.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.spectrum.spectrum_vms.enums.Role;
import com.spectrum.spectrum_vms.error.DeleteRequestException;
import com.spectrum.spectrum_vms.service.UserService;
import com.spectrum.spectrum_vms.user.User;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {UserController.class})
@ExtendWith(SpringExtension.class)
class UserControllerTest {
    @Autowired
    private UserController userController;

    @MockBean
    private UserService userService;

    /**
     * Method under test: {@link UserController#save(User)}
     */
    @Test
    void testSave() {
        User user = new User("Jane", "Doe", "jane.doe@example.org", "iloveyou", Role.USER);

        when(userService.save((User) any())).thenReturn(user);
        ResponseEntity<User> actualSaveResult = userController
                .save(new User("Jane", "Doe", "jane.doe@example.org", "iloveyou", Role.USER));
        assertEquals(user, actualSaveResult.getBody());
        assertTrue(actualSaveResult.getHeaders().isEmpty());
        assertEquals(200, actualSaveResult.getStatusCodeValue());
        verify(userService).save((User) any());
    }

    /**
     * Method under test: {@link UserController#update(User)}
     */
    @Test
    void testUpdate() throws Exception {
        when(userService.update((User) any()))
                .thenReturn(new User("Jane", "Doe", "jane.doe@example.org", "iloveyou", Role.USER));
        ResponseEntity<String> actualUpdateResult = userController
                .update(new User("Jane", "Doe", "jane.doe@example.org", "iloveyou", Role.USER));
        assertEquals("User information has been updated successfully", actualUpdateResult.getBody());
        assertEquals(200, actualUpdateResult.getStatusCodeValue());
        assertTrue(actualUpdateResult.getHeaders().isEmpty());
        verify(userService).update((User) any());
    }

    /**
     * Method under test: {@link UserController#update(User)}
     */
    @Test
    void testUpdate2() throws Exception {
        when(userService.update((User) any())).thenThrow(new Exception());
        ResponseEntity<String> actualUpdateResult = userController
                .update(new User("Jane", "Doe", "jane.doe@example.org", "iloveyou", Role.USER));
        assertNull(actualUpdateResult.getBody());
        assertEquals(422, actualUpdateResult.getStatusCodeValue());
        assertTrue(actualUpdateResult.getHeaders().isEmpty());
        verify(userService).update((User) any());
    }

    /**
     * Method under test: {@link UserController#deleteByIds(Long[])}
     */
    @Test
    void testDeleteByIds() throws DeleteRequestException {
        doNothing().when(userService).deleteByIds((Long[]) any());
        ResponseEntity<String> actualDeleteByIdsResult = userController.deleteByIds(1L);
        assertEquals("ID [1] has been deleted successfully", actualDeleteByIdsResult.getBody());
        assertEquals(200, actualDeleteByIdsResult.getStatusCodeValue());
        assertTrue(actualDeleteByIdsResult.getHeaders().isEmpty());
        verify(userService).deleteByIds((Long[]) any());
    }

    /**
     * Method under test: {@link UserController#getDataById(Long)}
     */
    @Test
    void testGetDataById() {
        when(userService.getDataById((Long) any()))
                .thenReturn(new User("Jane", "Doe", "jane.doe@example.org", "iloveyou", Role.USER));
        ResponseEntity<User> actualDataById = userController.getDataById(1L);
        assertTrue(actualDataById.hasBody());
        assertTrue(actualDataById.getHeaders().isEmpty());
        assertEquals(200, actualDataById.getStatusCodeValue());
        verify(userService).getDataById((Long) any());
    }

    /**
     * Method under test: {@link UserController#getData()}
     */
    @Test
    void testGetData() {
        ArrayList<User> userList = new ArrayList<>();
        when(userService.getData()).thenReturn(userList);
        List<User> actualData = userController.getData();
        assertSame(userList, actualData);
        assertTrue(actualData.isEmpty());
        verify(userService).getData();
    }
}

