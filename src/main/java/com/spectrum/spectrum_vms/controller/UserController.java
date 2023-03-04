package com.spectrum.spectrum_vms.controller;

import com.spectrum.spectrum_vms.entity.Driver;
import com.spectrum.spectrum_vms.error.DeleteRequestException;
import com.spectrum.spectrum_vms.service.DriverService;
import com.spectrum.spectrum_vms.service.UserService;
import com.spectrum.spectrum_vms.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/user/")
public class UserController implements BaseController<User, Long>{



    final private UserService userService;


    //all information save purpose
    @Override
    public ResponseEntity<User> save(@RequestBody User user) {
        userService.save(user);
        return ResponseEntity.ok(user);
    }


    //all information update purpose
    @PreAuthorize("hasAuthority('USER')")
    @Override
    public ResponseEntity<String> update(@RequestBody User user) throws Exception {
        try {
            userService.update(user);
            return ResponseEntity.ok("User information has been updated successfully");
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity().body(e.getMessage());
        }
    }


    //single or multiple user information delete purpose
    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public ResponseEntity<String> deleteByIds(@PathVariable("ids") Long... ids) throws DeleteRequestException {
        try {
            userService.deleteByIds(ids);
            return ResponseEntity.ok("ID " + Arrays.toString(ids) + " has been deleted successfully");
        }
        catch (Exception ex){
            throw new DeleteRequestException("Error deleting user with id " + ids);
        }

    }


    //single information get purpose
    @Override
    public ResponseEntity<User> getDataById(@PathVariable("id") Long id) {
        User user = userService.getDataById(id);
        return ResponseEntity.ok(user);
    }

    //all information get purpose
    @Override
    public List<User> getData() {
        return userService.getData();
    }
}
