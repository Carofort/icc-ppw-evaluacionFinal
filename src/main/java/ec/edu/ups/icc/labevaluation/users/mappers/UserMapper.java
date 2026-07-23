package ec.edu.ups.icc.labevaluation.users.mappers;
import java.util.Set;
import java.util.stream.Collectors;

import ec.edu.ups.icc.labevaluation.security.entities.RoleEntity;
import ec.edu.ups.icc.labevaluation.users.dtos.UserEligibleResponseDto;
import ec.edu.ups.icc.labevaluation.users.dtos.UserResponseDto;
import ec.edu.ups.icc.labevaluation.users.entities.UserEntity;
public final class UserMapper {
    private UserMapper() {}
    public static UserResponseDto toResponse(UserEntity entity){
        return new UserResponseDto(entity.getId(), entity.getFullName(), entity.getEmail());
    }

    public static UserEligibleResponseDto toEligibleDto(UserEntity entity) {
    Set<String> roleNames = entity.getRoles().stream()
            .map(RoleEntity::getFullName)
            .collect(Collectors.toSet());
    return new UserEligibleResponseDto(
            entity.getId(), entity.getFullName(), entity.getEmail(),
            entity.getAge(), entity.isActive(), roleNames
    );
}
}
