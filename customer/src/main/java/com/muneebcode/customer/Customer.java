package com.muneebcode.customer;

import lombok.*;

import javax.persistence.*;

@Data
@Builder(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Customer {
    @Id
    @SequenceGenerator(name = "customer_id_sequence", sequenceName = "customer_id_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_id_sequence")
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    @Column(name = "email_sent", nullable = false, columnDefinition = "int default 0")
    private int emailSent;
}
