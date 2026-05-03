package sv.edu.udb.parcial3.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sv.edu.udb.parcial3.controller.request.UserRequest;
import sv.edu.udb.parcial3.controller.response.UserResponse;
import sv.edu.udb.parcial3.repository.UserRepository;
import sv.edu.udb.parcial3.repository.entity.User;
import sv.edu.udb.parcial3.service.mapper.UserMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public List<UserResponse> obtenerUsuarios() {
        List<User> usuarios = userRepository.findAll();

        return userMapper.toUserResponseList(usuarios);
    }

    public UserResponse registrarUsuario(UserRequest usuario) {
        User usuarioCrear = userMapper.toUser(usuario);
        usuarioCrear.setPassword(passwordEncoder.encode(usuario.getContrasenia()));
        User usuarioCreado = userRepository.save(usuarioCrear);

        return userMapper.toUserResponse(usuarioCreado);
    }

}
