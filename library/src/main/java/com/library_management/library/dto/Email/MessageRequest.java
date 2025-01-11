package com.library_management.library.dto.Email;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MessageRequest {
    String from;
    String to;
    String toName;
    String subject;
    String content;
}
