package ec.edu.ups.icc.labevaluation.users.dtos;

import java.util.Set;

public class UserEligibleResponseDto {
    private Long id;
    private String fullName;
    private String email;
    private Integer age;
    private Boolean active;
    private Set<String> roles; 

    public UserEligibleResponseDto(Long id, String fullName, String email,
                                    Integer age, Boolean active, Set<String> roles) {
        this.id = id; this.fullName = fullName; this.email = email;
        this.age = age; this.active = active; this.roles = roles;
    }
    
    public Long getId() { return id; }
    public String getFullName() { return fullName; }
    public String getEmail() { return email; }
    public Integer getAge() { return age; }
    public Boolean getActive() { return active; }
    public Set<String> getRoles() { return roles; }
}