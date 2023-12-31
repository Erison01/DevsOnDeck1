package com.erison.devsondeck3.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.annotations.Cascade;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "developers")
public class Developer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 4,message = "Name must be 4 or more characters")
    private String firstName;

    @NotBlank
    @Size(min = 4,message = "Name must be 4 or more characters")
    private String lastName;

    @Email(message = "Enter a valid email")
    @Column(unique = true,nullable = false)
    @NotEmpty(message = "Email can not be empty")
    @Size(min = 10,max = 30 ,message = "Email must be between 10 and 30 characters")
    private String email;

    @NotEmpty(message = "Must not be empty")
    private String address;

    @NotEmpty(message = "Must not be empty")
    private String city;

    @NotEmpty(message = "Must not be empty")
    private String state;




    @NotEmpty(message="Password is required!")
    @Size(min=8, max=128, message="Password must be between 8 and 128 characters")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d).+$", message = "Password must contain at least one capital letter and one number")
    private String password;


    @Transient
    private String confirm;

    private String shortBio;

    // The relationships

    @ManyToMany(fetch = FetchType.LAZY )
    @JoinTable(
            name = "developers_skills",
            joinColumns = @JoinColumn(name = "developer_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id")

    )
    private List<Skill> skills;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "developers_positions",
            joinColumns = @JoinColumn(name = "developer_id"),
            inverseJoinColumns = @JoinColumn(name = "position_id")
    )
    private List<Position>positions;


    @Column(updatable=false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createdAt;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updatedAt;

    public Developer(){}

    public Developer(Long id, String firstName, String lastName, String email, String address, String city, String state,String shortBio, String password, String confirm, List<Skill> skills, List<Position> positions) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.city = city;
        this.state = state;
        this.password = password;
        this.confirm = confirm;
        this.skills = skills;
        this.positions = positions;
        this.shortBio=shortBio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

   public String getShortBio() {
        return shortBio;
   }

   public void setShortBio(String shortBio) {
       this.shortBio = shortBio;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    public List<Position> getPositions() {
        return positions;
    }

    public void setPositions(List<Position> positions) {
        this.positions = positions;
    }

    @PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }
}

