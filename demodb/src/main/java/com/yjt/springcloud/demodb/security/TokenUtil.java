package com.yjt.springcloud.demodb.security;

import cn.hutool.core.date.DateTime;
import com.google.common.collect.Maps;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Clock;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClock;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoField;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

/**
 * TODO
 * ClassName: TokenUtil
 * Date: 2019-10-22 19:30
 * author Administrator
 * version V1.0
 */
@Component
public class TokenUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    @Value("${jwt.header}")
    private String tokenHeader;

    private Clock clock = DefaultClock.INSTANCE;

    public String getUserNameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    public Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    public String createToken(UserDetails userDetails) {
        Map claims = Maps.newHashMap();
        return generateToken(claims, userDetails.getUsername());
    }

    public String generateToken(Map claims, String username) {
        Date now = clock.now();
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(calExpiration(now))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();

    }

    public String refreshToken(String token) {
        final Date createDate = clock.now();
        final DateTime expirationDate = calExpiration(createDate);
        final Claims claims = getAllClaimsFromToken(token);
        claims.setIssuedAt(createDate);
        claims.setExpiration(expirationDate);
        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    private DateTime calExpiration(final Date now) {
        Instant instant = now.toInstant();
        instant.plusSeconds(expiration);
        return new DateTime(instant.getLong(ChronoField.MILLI_OF_SECOND));
    }

    //获取token过期时间
    public DateTime getExpirationDateFromToken(String token) {
        final Date expireDate = getClaimFromToken(token, Claims::getExpiration);
        return new DateTime(expireDate);
    }

    private Boolean ignoreTokenExpiration(String token) {
        return false;
    }

    public boolean isTokenExpired(String token) {
        DateTime dateTime = getExpirationDateFromToken(token);
        return dateTime.isBefore(DateTime.now());
    }

    public DateTime getIssuedAtDateFromToken(String token) {
        return new DateTime(getClaimFromToken(token, Claims::getIssuedAt));
    }

    private Boolean isCreatedBeforeLastPasswordReset(DateTime created, DateTime lastPasswordReset) {
        return (lastPasswordReset != null && created.before(lastPasswordReset));
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        JwtUser user = (JwtUser) userDetails;
        final DateTime created = getIssuedAtDateFromToken(token);
        return (!isTokenExpired(token)
                && !isCreatedBeforeLastPasswordReset(created, new DateTime(user.getPasswordExpireDate().getLong(ChronoField.MILLI_OF_SECOND)))
        );
    }


}
