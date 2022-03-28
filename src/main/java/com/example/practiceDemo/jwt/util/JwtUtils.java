package com.example.practiceDemo.jwt.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Date;

public class JwtUtils {

    public static final String AUTHORIZATION_STARTER = "Bearer ";
    public static final String CLAIM_KEY_USERID = "userId";
    public static final String CLAIM_KEY_USERNAME = "userName";
    public static final String CLAIM_KEY_MOBILE = "mobile";
    public static final String SECRET = "56F85F2A987D5C98DB1A554BEE929B92"; //BACN 进行MD532位大写 生成的密钥
    /**
     签发对象：这个用户的id
     签发时间：现在
     有效时间：12小时
     载荷内容：暂时设计为：这个人的名字，这个人的昵称
     加密密钥：这个人的id加上一串字符串
     */
    public static String createToken(String userId,String userName, String mobile) {

        Calendar nowTime = Calendar.getInstance();
        nowTime.add(Calendar.HOUR,12);
        Date expiresDate = nowTime.getTime();

        return JWT.create().withAudience(userId)   //签发对象
                .withIssuedAt(new Date())    //发行时间
                .withExpiresAt(expiresDate)  //有效时间
                .withClaim(CLAIM_KEY_USERID, userId)    //载荷
                .withClaim(CLAIM_KEY_USERNAME, userName)    //载荷
                .withClaim(CLAIM_KEY_MOBILE, mobile)    //载荷
                .sign(Algorithm.HMAC256(SECRET));   //加密
    }

    /**
     * 检验合法性，其中secret参数就应该传入的是用户的id
     * @param token
     * @throws Exception
     */
    public static void verifyToken(String token, String secret) throws Exception {
        DecodedJWT jwt = null;
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
            jwt = verifier.verify(token);
        } catch (Exception e) {
            //效验失败
            //这里抛出的异常是我自定义的一个异常，你也可以写成别的
            throw new Exception();
        }
    }

    /**
     * 获取签发对象
     */
    public static String getAudience(String token) throws Exception {
        String audience = null;
        try {
            audience = JWT.decode(token).getAudience().get(0);
        } catch (JWTDecodeException j) {
            //这里是token解析失败
            throw new Exception();
        }
        return audience;
    }


    /**
     * 通过载荷名字获取载荷的值
     */
    public static Claim getClaimByName(String token, String name){
        return JWT.decode(token).getClaim(name);
    }
}
