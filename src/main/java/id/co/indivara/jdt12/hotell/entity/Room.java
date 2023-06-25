package id.co.indivara.jdt12.hotell.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "rooms")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Room extends BaseEntity {
    @Id
    @Column(name = "room_id")
    @GeneratedValue(generator = "system-uuid")//perintah agar id auto increment
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private Integer roomId;

    @Column(name = "room_number")
    private Integer roomNumber;
    @Column(name = "room_type")
    private String roomType;
    @Column(name = "price")
    private Integer price;
    @Column(name = "description")
    private String description;
}
