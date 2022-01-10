package edu.greenriver.sdev.myspringproject.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

/**
 * This class sets up a Permission object/entity class.
 *
 * @author Dana Clemmer
 * @version 1.0
 * 12/1/21
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Permission implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int permissionId;

    private String role;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @Override
    public String getAuthority()
    {
        return role;
    }
}
