package edu.greenriver.sdev.myspringproject.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class sets up an Adventure object/entity class.
 *
 * @author Dana Clemmer
 * @version 1.0
 * 10/16/21
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity //this marks the class as a Hibernate Entity (a table will be created to store Adventure objects)
@Table(name = "pnw_adventures")
public class Adventure {

    //surrogate key
    @Id //this is a primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //this is auto-increment
    private int adventureId;

    private String state; //WA, OR
    private String region; //North Cascades, etc
    private String destination;
    private boolean hikeIncluded;
    private boolean beachIncluded;
    private boolean campIncluded;
    private boolean waterfallIncluded;
    private String itinerary; //name of all stops on this adventure
    private String imagePath;

    //one-to-many
    //@ToString.Exclude
    @OneToMany(mappedBy = "adventure", fetch = FetchType.LAZY)
    //@JsonIgnore
    private List<Hike> hikes;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    /**
     * Converts itinerary String to a List of Strings
     *
     * @return List of Strings
     */
    public List<String> itineraryToList() {
        List<String> list = new ArrayList<>();
        StringBuilder word = new StringBuilder();
        for (int i = 0; i < itinerary.length(); i++) {
            if(itinerary.charAt(i) != ',') {
                word.append(itinerary.charAt(i));
            }
            else {
                list.add(word.toString().trim());
                word = new StringBuilder();
            }
        }
        list.add(word.toString().trim());
        return list;
    }
}
