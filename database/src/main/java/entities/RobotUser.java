package entities;


import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class RobotUser {

    @Setter
    private String username;

    @Setter
    private String password;

    @Id
    @Column(name="username",unique=true, nullable=false, length=45)
    public String getUsername(){
        return this.username;
    }

    @Column(name="password", nullable = false, length=60)
    public String getPassword(){
        return this.password;
    }
}
