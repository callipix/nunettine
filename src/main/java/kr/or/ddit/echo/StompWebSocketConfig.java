package kr.or.ddit.echo;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

// 웹소켓 설정을 위한 클래스
@Slf4j
@Configuration                        // 설정 파일을 만들기 위한 어노테이션 선언
@RequiredArgsConstructor
@EnableWebSocketMessageBroker        // websocket 메세지 브로커 활성화(stomp 사용 선언)
public class StompWebSocketConfig implements WebSocketMessageBrokerConfigurer {

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {

		registry.addEndpoint("/stomp/chat").setAllowedOrigins("*").withSockJS();

	}

	@Override
	public void configureMessageBroker(MessageBrokerRegistry config) {
		config.setApplicationDestinationPrefixes("/pub");        // 클라이언트에서 send 요청 처리
		config.enableSimpleBroker("/sub");                        // 해당 경로로 SimpleBroker 등록O
		log.info(config.toString());
	}

}
