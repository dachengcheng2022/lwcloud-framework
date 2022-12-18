package com.autumn.utils.security;


import com.alibaba.fastjson2.JSONObject;
import com.autumn.vo.security.PayloadUser;
import com.autumn.vo.security.TokenUserDetails;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jose.jwk.RSAKey;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;
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
    private final static int TOKEN_EXPIRTIME_HOURS = -24;

    @SneakyThrows
    public String generateToken(TokenUserDetails tokenUserDetails, RSAKey key) {
//        擦除敏感信息
        tokenUserDetails.setPassword("");
        JWSHeader jwsHeader = new JWSHeader.Builder(JWSAlgorithm.RS256).type(JOSEObjectType.JWT).build();
        PayloadUser data = new PayloadUser(UUID.randomUUID().toString(), tokenUserDetails, LocalDateTime.now().plusHours(TOKEN_EXPIRTIME_HOURS));
        JWSObject jwsObject = new JWSObject(jwsHeader, new Payload(JSONObject.toJSONString(data)));
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
            return JSONObject.parseObject(json, PayloadUser.class);
        }
        throw new RuntimeException("500");
    }
}
