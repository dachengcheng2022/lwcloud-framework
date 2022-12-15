package com.autumn.utils.security;


import com.autumn.domain.user.MallUser;
import com.autumn.utils.JacksonUtils;
import com.autumn.vo.security.PayloadUser;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jose.jwk.RSAKey;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * <p>
 * JwtUtils
 * </p>
 *
 * @author livk
 */
@UtilityClass
public class JwtUtils {

    @SneakyThrows
    public String generateToken(MallUser mallUser, RSAKey key) {
        JWSHeader jwsHeader = new JWSHeader.Builder(JWSAlgorithm.RS256).type(JOSEObjectType.JWT).build();
        PayloadUser data = new PayloadUser(UUID.randomUUID().toString(), mallUser);
        JWSObject jwsObject = new JWSObject(jwsHeader, new Payload(JacksonUtils.toJsonStr(data)));
        RSASSASigner signer = new RSASSASigner(key);
        jwsObject.sign(signer);
        return jwsObject.serialize();
    }


    @SneakyThrows
    public PayloadUser parse(String token, RSAKey key) {
        JWSObject jwsObject = JWSObject.parse(token);
        RSASSAVerifier verifier = new RSASSAVerifier(key);
        if (jwsObject.verify(verifier)) {
            String json = jwsObject.getPayload().toString();
            return JacksonUtils.toBean(json, PayloadUser.class);
        }
        throw new RuntimeException("500");
    }
}
