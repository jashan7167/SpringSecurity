package com.jashan.learn_oauth;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldResource {

    @GetMapping
    public String helloWorld(Authentication authentication) {
        System.out.println(authentication);
        System.out.println(authentication.getPrincipal());
        return "Hello World";
    }//OAuth2AuthenticationToken [Principal=Name: [115034830981035608406], Granted Authorities: [[OIDC_USER, SCOPE_https://www.googleapis.com/auth/userinfo.email, SCOPE_https://www.googleapis.com/auth/userinfo.profile, SCOPE_openid]], User Attributes: [{at_hash=w91N2lFN80pF44gHalyXGA, sub=115034830981035608406, email_verified=true, iss=https://accounts.google.com, given_name=Jashanjot, nonce=VEyVQlPrJBGrQ1XZ0vi1R0L3yW0veddJWTSILOlAB1A, picture=https://lh3.googleusercontent.com/a/ACg8ocI6hu2qje1lwEx4nDexxLzfox6nDOufw3o4FRXRBKT7qnuyRpU=s96-c, aud=[732279405446-b8g58qkf3n18fk9vr66kjd3dte74vttt.apps.googleusercontent.com], azp=732279405446-b8g58qkf3n18fk9vr66kjd3dte74vttt.apps.googleusercontent.com, name=Jashanjot Singh Bhatia, exp=2024-06-20T20:39:21Z, family_name=Singh Bhatia, iat=2024-06-20T19:39:21Z, email=jashandev20@gmail.com}], Credentials=[PROTECTED], Authenticated=true, Details=WebAuthenticationDetails [RemoteIpAddress=127.0.0.1, SessionId=94DF091E213457858959457C1D2CE074], Granted Authorities=[OIDC_USER, SCOPE_https://www.googleapis.com/auth/userinfo.email, SCOPE_https://www.googleapis.com/auth/userinfo.profile, SCOPE_openid]]
    //Name: [115034830981035608406], Granted Authorities: [[OIDC_USER, SCOPE_https://www.googleapis.com/auth/userinfo.email, SCOPE_https://www.googleapis.com/auth/userinfo.profile, SCOPE_openid]], User Attributes: [{at_hash=w91N2lFN80pF44gHalyXGA, sub=115034830981035608406, email_verified=true, iss=https://accounts.google.com, given_name=Jashanjot, nonce=VEyVQlPrJBGrQ1XZ0vi1R0L3yW0veddJWTSILOlAB1A, picture=https://lh3.googleusercontent.com/a/ACg8ocI6hu2qje1lwEx4nDexxLzfox6nDOufw3o4FRXRBKT7qnuyRpU=s96-c, aud=[732279405446-b8g58qkf3n18fk9vr66kjd3dte74vttt.apps.googleusercontent.com], azp=732279405446-b8g58qkf3n18fk9vr66kjd3dte74vttt.apps.googleusercontent.com, name=Jashanjot Singh Bhatia, exp=2024-06-20T20:39:21Z, family_name=Singh Bhatia, iat=2024-06-20T19:39:21Z, email=jashandev20@gmail.com}]

}
