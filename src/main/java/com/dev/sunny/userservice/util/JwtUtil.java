package com.dev.sunny.userservice.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class JwtUtil {
    private static final String SECRET_KEY = "B24A9E8C52AF0141D61EFD2CE8123636847A5C9D898D08DF287A47FDE08415B5F5D68712C708511E0899D8FC5A7D45056F38A4370FB4B93D9C7DD646816F0DFD40FBD39F334C482FAEB6BD2BC1024455245A44972C6B49602BFB632D9D7467DE60C3E39DCCC7F2EC202F9B7C795DC127DA11986C1859172EB80D57EFB44BB1591C8A147EAB1583CE6B734F4F8078863687ABD7B021F39D8ADD7B45693F84C24D4876A1213CC33338071F01FEF5F0DC388030928D6B3811DAD622746915AA27A47BB7787880B1C35AA3DD46CA51F12C9E25F33BF4E9D54C592CA1B6CF6C9B3944712E4169F21D55B8834A5E71BC8EC403F7B05EC6A34416D36F08DF3CFBC74672";

    public static String generateToken(String email, Date expiryDate) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(expiryDate)
                .signWith(decodedKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private static Key decodedKey() {
        byte[] keySeq = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keySeq);
    }

    public static Jws<Claims> validateToken(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(decodedKey())
                    .build()
                    .parseClaimsJws(token);
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            throw new IllegalArgumentException("Invalid token");
        } catch (ExpiredJwtException e) {
            throw new IllegalArgumentException("Token expired");
        }
    }
}
