package edu.t1.models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@Getter
@Setter
@AllArgsConstructor
@ToString
@NoArgsConstructor
//@Validated
public class User {
    //@NotEmpty(message = "id не может быть пустым")
    Long id;
    //@NotEmpty(message = "username не может быть пустым")
    String username;
}
