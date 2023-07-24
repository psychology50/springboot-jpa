package com.example.jpa.domain;

import com.example.jpa.domain.model.Address;
import com.example.jpa.domain.model.Period;
import com.example.jpa.domain.model.PhoneNumber;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    @Id @GeneratedValue
    private Long id;
    private String username;
    private Integer age;

    @Embedded private Address homeAddress;

    @ElementCollection
    @CollectionTable(name = "favorite_food", joinColumns = @JoinColumn(name = "member_id"))
    @Column(name = "food_name")
    private List<String> favoriteFoods = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "address", joinColumns = @JoinColumn(name = "member_id"))
    private List<Address> addressHistory = new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "team_id")
    private Team team;

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<Order> orders = new ArrayList<>();

    @Builder
    public Member(String username, Integer age, Address homeAddress) {
        this.username = username;
        this.age = age;
        this.homeAddress = homeAddress;
    }

    void setTeam(Team team) {
        this.team = team;
    }
}
