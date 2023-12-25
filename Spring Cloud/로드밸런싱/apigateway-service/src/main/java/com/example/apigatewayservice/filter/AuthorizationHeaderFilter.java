package com.example.apigatewayservice.filter;

import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@Slf4j
// 필터로 작용을 하기 위해 AbstractGatewayFilterFactory 클래스를 상속한다.
public class AuthorizationHeaderFilter extends AbstractGatewayFilterFactory<AuthorizationHeaderFilter.Config> {
    Environment env;

    public AuthorizationHeaderFilter(Environment env) {
        // Config 클래스에 아무 것도 없지만, filter에 적용 하는 부가 정보로 캐스팅 시켜주는 작업을 부모 클래스에 알려주어야 한다.
        super(Config.class);

        this.env = env;
    }

    // 설정 작업을 전달 하기 위한 이너 클래스 생성
    public static class Config {}

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();

            // 헤더에 인증 정보가 있는지 확인
            if (!request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION))
                return onError(exchange, "No authorization header", HttpStatus.UNAUTHORIZED);

            // 반환 값은 List 형태이기 때문에 0번을 가져 온다.
            String authorizationHeader = request.getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
            String jwt = authorizationHeader.replace("Bearer", ""); // Bearer값을 가지고 token 정보가 전달 됨

            // 토큰 검증
            if (!isJwtVaild(jwt)) {
                return onError(exchange, "JWT token is not valid", HttpStatus.UNAUTHORIZED);
            }
            return chain.filter(exchange);
        });
    }

    private boolean isJwtVaild(String jwt) {
        boolean returnValue = true;

        String subject = null;

        try {
            subject = Jwts.parser().setSigningKey(env.getProperty("token.secret")) // 복호화
                    .parseClaimsJws(jwt).getBody() // 복호화 대상 지정
                    .getSubject();
        } catch (Exception ex) {
            returnValue = false;
        }

        if (subject == null || subject.isEmpty())
            returnValue = false;

        return returnValue;
    }

    /*
      apigateway-service는 Spring MVC로 구성된 것이 아니라 Spring Webflux라고하는 비동기 방식으로 처리하는 방법으로 만들어졌다.
      Webflux 안에서 데이터를 처리하는 두가지 단위 중 하나가 Mono이다. Mono라는 단위 값에 전달하고자 하는 데이터를 넣어서 반환할 수 있다.
      단위 값 -> Mono
      여러가지 값 -> Flux
    */
    private Mono<Void> onError(ServerWebExchange exchange, String err, HttpStatus httpStatus) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);

        log.error(err);
        // response를 Complate로 전달하면 Mono 타입으로 전달할 수 있다.
        return response.setComplete();
    }
}
