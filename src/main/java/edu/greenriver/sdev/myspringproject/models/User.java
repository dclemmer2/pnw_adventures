package edu.greenriver.sdev.myspringproject.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

/**
 * This class sets up a User object/entity class.
 *
 * @author Dana Clemmer
 * @version 1.0
 * 12/1/21
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String city;
    private String state;

    @OneToMany(fetch = FetchType.EAGER,
            cascade = CascadeType.PERSIST,
            mappedBy = "user")
    private List<Permission> permissions;

    @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST)

    private List<Adventure> adventures;

    /**
     * Gets username of user
     *
     * @return username
     */
    @Override
    public String getUsername()
    {
        return username;
    }

    /**
     * Gets password of user
     *
     * @return password
     */
    @Override
    public String getPassword()
    {
        return password;
    }

    /**
     * Returns permissions/authorities of user
     *
     * @return permissions/authorities
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        return permissions;
    }

    /**
     * Returns that user account is not expired
     *
     * @return true
     */
    @Override
    public boolean isAccountNonExpired()
    {
        return true;
    }

    /**
     * Returns that user account is not locked
     *
     * @return true
     */
    @Override
    public boolean isAccountNonLocked()
    {
        return true;
    }

    /**
     * Returns that user account credentials are not expired
     *
     * @return true
     */
    @Override
    public boolean isCredentialsNonExpired()
    {
        return true;
    }

    /**
     * Returns that user account is enabled
     *
     * @return true
     */
    @Override
    public boolean isEnabled()
    {
        return true;
    }
}
