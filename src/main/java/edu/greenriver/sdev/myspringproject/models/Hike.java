package edu.greenriver.sdev.myspringproject.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

/**
 * This class sets up a Hike object/entity class.
 *
 * @author Dana Clemmer
 * @version 1.0
 * 11/16/21
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "hikes")
public class Hike {
    //surrogate key
    @Id //this is a primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //this is auto-increment
    private int hikeId;

    private String name;
    private double miles;
    private double difficulty; //1-5 stars

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name="adventureId")
    @JsonIgnore
    private Adventure adventure;
}
