package com.muneebcode.email;

import lombok.Data;

@Data
public class EmailRequestBody {
    private String recipientEmail;
    private String subject;
    private String content;
}
