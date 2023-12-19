package com.muneebcode.job.email;

import com.muneebcode.customer.Customer;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
@Component
public class CustomerMapper implements org.springframework.jdbc.core.RowMapper<Customer> {
    @Override
    public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Customer.builder().id(rs.getInt("id")).email(rs.getString("email")).firstName(rs.getString("first_name")).lastName(rs.getString("last_name")).emailSent(rs.getInt("email_sent")).build();
    }
}
