package com.spectrum.spectrum_vms.serviceImplimentation;

import com.spectrum.spectrum_vms.entity.Vehicle;
import com.spectrum.spectrum_vms.error.VehicleRequestNotFoundException;
import com.spectrum.spectrum_vms.service.UserService;
import com.spectrum.spectrum_vms.user.User;
import com.spectrum.spectrum_vms.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.awt.dnd.InvalidDnDOperationException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User save(User user) {
        User userDemo = User.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .password(passwordEncoder.encode(user.getPassword()))
                .role(user.getRole())
                .build();
        return userRepository.save(userDemo);
    }

    @Override
    public User update(User user) throws Exception {
        if (user.hasId()) {
            return save(user);
        } else {
            throw new InvalidDnDOperationException("User id has been required for update operation");
        }
    }


    @Override
    public void deleteByIds(Long[] ids) {
        userRepository.deleteAllById(Arrays.asList(ids));
    }

    @Override
    public User getDataById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public List<User> getData() {
        return userRepository.findAll();
    }
}
