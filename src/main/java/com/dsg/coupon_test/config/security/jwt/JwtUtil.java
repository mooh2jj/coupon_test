package com.dsg.coupon_test.config.security.jwt;

import com.dsg.coupon_test.config.security.auth.PrincipalDetails;
import com.dsg.coupon_test.exception.CustomApiException;
import com.dsg.coupon_test.exception.ResponseCode;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String jwtSecret;           // 설정 파일에서 jwt 토큰에 서명하는데 사용할 secret key를 가져온다.

    @Value("${jwt.accessTokenExpirationPeriod}")
    private int jwtExpirationInMs;      // 설정 파일에서 jwt 토큰의 유효기간을 가져온다.


    // generate token
    public String generateToken(PrincipalDetails principalDetails) {
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + jwtExpirationInMs);  //
        log.info("jwtExpirationInMs: {}", jwtExpirationInMs);
        log.info("expireDate: " + expireDate);

        return Jwts.builder()
                .setSubject(principalDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    // get username from the token
    public String getUsernameFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    // validate JWT token
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (SignatureException ex){
            throw new CustomApiException(ResponseCode.INVALID_REQUEST, "Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            throw new CustomApiException(ResponseCode.INVALID_REQUEST, "Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            throw new CustomApiException(ResponseCode.EXPIRED_JWT_TOKEN, "Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            throw new CustomApiException(ResponseCode.INVALID_REQUEST, "Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            throw new CustomApiException(ResponseCode.INVALID_REQUEST, "JWT claims string is empty.");
        }
    }

}
