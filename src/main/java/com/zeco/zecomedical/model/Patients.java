package com.zeco.zecomedical.model;


/*@Entity
@Table(name = "patient")
@Data
@Builder*/
public class Patients {

   /* @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")*/
    private Long id;

    /*@Column(name = "name")
    @NonNull
    private String name;

    @Column(name = "username")
    @NonNull
    private String username;

    @Column(name = "gender")
    @NonNull
    private String gender;

    @Column(name = "dob")//date of birth
    @NonNull
    private Date dob;

    @Column(name ="address")
    @NonNull
    private String address;

    @Column(name = "email")
    @NonNull
    private String email;


    @Column(name = "weight")
    @NonNull
    private Float weight;

    @Column(name = "bloodGroup")
    private String bloodGroup;

    @Column(name = "bloodPressure")
    private String bloodPressure;

    @Column(name = "password")
    @NonNull
    private String password;

    @Column(name = "role")
    @OneToOne
    @JoinColumn(name = "role" , referencedColumnName = "id")
    @NonNull
    private Roles role;


    @Column(name = "isAuthenticated")
    @NonNull
    private Boolean isAuthenticated;
*/


}
