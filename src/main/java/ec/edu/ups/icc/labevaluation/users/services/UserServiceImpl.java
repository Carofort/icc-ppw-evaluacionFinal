package ec.edu.ups.icc.labevaluation.users.services;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import ec.edu.ups.icc.labevaluation.users.dtos.UserEligibleResponseDto;
import ec.edu.ups.icc.labevaluation.users.dtos.UserResponseDto;
import ec.edu.ups.icc.labevaluation.users.entities.UserEntity;
import ec.edu.ups.icc.labevaluation.users.mappers.UserMapper;
import ec.edu.ups.icc.labevaluation.users.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    public UserServiceImpl(UserRepository repository){this.repository=repository;}

    @Override public List<UserResponseDto> findEligible(){
        return repository.findAll().stream().filter(user -> !user.isDeleted()).map(UserMapper::toResponse).toList();
    }

    @Override
public List<UserEligibleResponseDto> getEligibleUsers() {
    List<UserEntity> users = repository
            .findByAgeGreaterThanEqualAndActiveTrueAndDeletedFalseOrderByFullNameAsc(18);
    return users.stream().map(UserMapper::toEligibleDto).collect(Collectors.toList());
}
}
