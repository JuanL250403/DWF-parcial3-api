package sv.edu.udb.parcial3.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import sv.edu.udb.parcial3.controller.request.UserRequest;
import sv.edu.udb.parcial3.controller.response.UserResponse;
import sv.edu.udb.parcial3.repository.entity.User;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    List<UserResponse> toUserResponseList(List<User> usuarios);

    @Mapping(source = "firstname", target = "nombre")
    @Mapping(source = "lastname", target = "apellido")
    @Mapping(source = "age", target = "edad")
    @Mapping(source = "username", target = "nombreUsuario")
    UserResponse toUserResponse(User usuario);

    @Mapping(source = "nombre", target = "firstname")
    @Mapping(source = "apellido", target = "lastname")
    @Mapping(source = "edad", target = "age")
    @Mapping(source = "nombreUsuario", target = "username")
    @Mapping(source = "contrasenia", target = "password")
    User toUser(UserRequest usuario);
}
