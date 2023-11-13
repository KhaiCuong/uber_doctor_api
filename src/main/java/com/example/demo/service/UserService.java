package com.example.demo.service;

import com.example.demo.dtos.UserDTO;
import com.example.demo.entites.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.requests.user.ChangePasswordRequest;
import com.example.demo.requests.user.UpdateInforByAdminRequest;
import com.example.demo.requests.user.UpdateInforByUserRequest;
import com.example.demo.response.user.UpdateInforByUserResponse;
import com.example.demo.utils.TransferDataUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<UserDTO> getList() {
        ModelMapper modelMapper = new ModelMapper();
        List<User> listUser = userRepository.findAll();

        List<UserDTO> listUserDTO = listUser.stream()
                .map(entity -> modelMapper.map(entity, UserDTO.class))
                .collect(Collectors.toList());
        return listUserDTO;
    }

    public UpdateInforByUserResponse updateInforByUser(UpdateInforByUserRequest updateInforByUserRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        // Retrieve the user's current password from the database
        User userEntity = findByEmail(email);
        if (userEntity == null) {
            return null;
        }
        TransferDataUtils<User, UpdateInforByUserRequest> transferRequest = new TransferDataUtils<>();
        User user = transferRequest.DTOToEntity(updateInforByUserRequest, userEntity);

        User updatedUser = userRepository.save(user);

        TransferDataUtils<User, UpdateInforByUserResponse> transferResponese = new TransferDataUtils<>();
        UpdateInforByUserResponse updateInforByUserResponse = transferResponese.EntityToDTO(updatedUser, new UpdateInforByUserResponse());

        return updateInforByUserResponse;
    }

    public User updateInforByAdmin(UpdateInforByAdminRequest updateInforByAdminRequest) {
        Optional<User> optionalUserEntity = userRepository.findById(updateInforByAdminRequest.getId());
        if (!optionalUserEntity.isPresent()) {
            return null;
        }
        User userEntity = optionalUserEntity.get();
        TransferDataUtils<User, UpdateInforByAdminRequest> transferRequest = new TransferDataUtils<>();
        User user = transferRequest.DTOToEntity(updateInforByAdminRequest, userEntity);

        User updatedUser = userRepository.save(user);

//        TransferDataUtils<UserEntity, UpdateInforByAdminResponse> transferResponese = new TransferDataUtils<>();
//        UpdateInforByAdminResponse updateInforByAdminResponse = transferResponese.EntityToDTO(updatedUser, new UpdateInforByAdminResponse());

        return updatedUser;
    }

    public boolean deleteUser(Long id) {
        Optional<User> optionalUserEntity = userRepository.findById(id);
        if (optionalUserEntity.isPresent()) {
            User userEntity = optionalUserEntity.get();
            userRepository.delete(userEntity);
            return true;
        }
        return false;
    }

    public boolean changePassword(ChangePasswordRequest changePasswordRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        // Retrieve the user's current password from the database
        User userEntity = findByEmail(email);

        // Verify the old password
        if (!passwordEncoder.matches(changePasswordRequest.getOldPassword(), userEntity.getPassword())) {
            return false;
        }

        if (!changePasswordRequest.getPassword().equals(changePasswordRequest.getConfirmPassword())) {
            return false;
        }

        // Hash the new password
        String newPasswordHash = passwordEncoder.encode(changePasswordRequest.getPassword());

        TransferDataUtils<User, ChangePasswordRequest> transfer = new TransferDataUtils<>();
        User user = transfer.DTOToEntity(changePasswordRequest, userEntity);
        user.setPassword(newPasswordHash);
        userRepository.save(user);
        return true;
    }


}
