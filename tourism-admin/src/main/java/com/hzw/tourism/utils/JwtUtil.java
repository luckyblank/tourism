package com.hzw.tourism.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

public class JwtUtil {
    //有效期
    public static final Long JWT_TTL=60*60*7000L;
    //密钥
    public static  final String JWT_Key="hzw";

    /**
     * 生成uuid
     * @return
     */
    public static String getUUID(){
        String token = UUID.randomUUID().toString().replaceAll("-", "");
        return token;
    }

    /**
     *生成jwt
     * @param subject token中存放的数据（
     * @param ttlMillis token超时时间
     * @return
     */
  public static String createJWT(String subject,Long ttlMillis,String uuid ){
      JwtBuilder builder = getJwtBuilder(subject, ttlMillis, uuid);
      return builder.compact();

  }

  public static JwtBuilder getJwtBuilder(String subject,Long ttlMillis,String uuid ) {
      SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
      SecretKey secretKey=generalKey();
      long nowMillis = System.currentTimeMillis();
      Date now = new Date(nowMillis);
      if (ttlMillis==null){
          ttlMillis=JwtUtil.JWT_TTL;
      }
      long expMills=nowMillis+ttlMillis;
      Date expDate = new Date(expMills);
      return Jwts.builder()
              .setId(uuid)          //唯一的ID
              .setSubject(subject)   //JOSN数据
              .setIssuer("sg")      //签发者
              .setIssuedAt(now)     //签发时间
              .signWith(signatureAlgorithm,secretKey)  //对HS256对称加密算法签名
              .setExpiration(expDate);

  }

    /**
     * 生成jwt
     * @param subject token中要存放的数据(josn格式)
     * @return
     */
  public static String createJWT(String subject ){
      JwtBuilder builder = getJwtBuilder(subject, null, getUUID());
      return builder.compact();
  }

    public static String createJWT(String subject,Long ttlMills){
        JwtBuilder builder = getJwtBuilder(subject, ttlMills, getUUID());
        return builder.compact();
    }


public static void  main(String[] args) throws Exception{
   // String jwt = createJWT("1234");
    Claims claims = parseJWT("eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI5YTkyODM0NGY5NzY0Mzc0YTJlZmQ5OWVjN2IyZTBiMSIsInN1YiI6IjEyMzQiLCJpc3MiOiJzZyIsImlhdCI6MTY3NDA0MDQ4MiwiZXhwIjoxNjc0MDQ0MDgyfQ.7AGeaAwFpy1Op-3Q-EGzZZFXxj1_-pcm1xaE7bNxer8");
    String subject = claims.getSubject();
    System.out.println(subject);

}


    /**
     * 生成加密后的密钥secretKet
     */
    public static SecretKey generalKey(){
        byte[] encodeKey = Base64.getDecoder().decode(JwtUtil.JWT_Key);
        SecretKey key = new SecretKeySpec(encodeKey,0,encodeKey.length,"AES");
        return key;
    }

    /**
     * 解析
     * @param jwt
     * @return
     * @throws Exception
     */
    public static Claims parseJWT(String jwt) throws Exception{
      SecretKey secretKey=generalKey();
      return  Jwts.parser()
              .setSigningKey(secretKey)
              .parseClaimsJws(jwt)
              .getBody();

    }

}
