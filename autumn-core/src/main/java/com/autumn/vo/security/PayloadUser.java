package com.autumn.vo.security;

import com.autumn.domain.user.MallUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    private MallUser mallUser;

}
