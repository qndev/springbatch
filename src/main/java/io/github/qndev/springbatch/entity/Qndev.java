package io.github.qndev.springbatch.entity;

import io.github.qndev.springbatch.statechanged.event.QndevStateChangedEventListener;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "qndev")
@EntityListeners(QndevStateChangedEventListener.class)
public class Qndev implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

}
