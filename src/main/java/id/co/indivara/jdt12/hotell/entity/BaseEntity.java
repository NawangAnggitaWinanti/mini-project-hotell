package id.co.indivara.jdt12.hotell.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.Date;

@MappedSuperclass
@Getter
@Setter
public class BaseEntity {
    @JsonIgnore
    @Column(name = "created_date")
    private Date createdDate;

    @JsonIgnore
    @Column(name = "last_update")
    private Date lastUpdate;

    @PrePersist
    private void onCreate(){
        this.createdDate = new Date();
        this.lastUpdate = this.createdDate;
    }

    @PreUpdate
    private void onUpdate(){
        this.lastUpdate = new Date();

    }

}
