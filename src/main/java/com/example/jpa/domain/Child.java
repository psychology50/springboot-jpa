package com.example.jpa.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Child {
    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    private Parent parent;

    public void setParent(Parent parent) {
        if (this.parent != null)
            this.parent.getChildren().remove(this);

        this.parent = parent;
        parent.getChildren().add(this);
    }
}
