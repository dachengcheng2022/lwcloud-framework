package com.autumn.vo.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 * Payload
 * </p>
 *
 * @author livk
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PayloadUser {

    private String id;

    private TokenUserDetails tokenUserDetails;

//    过期时间
    private LocalDateTime expirTime;
}
