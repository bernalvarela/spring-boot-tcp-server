package dev.bernalvarela.config;

import dev.bernalvarela.endpoint.ServerSocketHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.ip.dsl.Tcp;
import org.springframework.integration.ip.tcp.serializer.TcpCodecs;

/**
 * Spring annotation based configuration
 */
@Configuration
@EnableIntegration
@IntegrationComponentScan
public class TcpServerSocketConfiguration {

    @Value("${tcp.server.port}")
    private int socketPort;

    @Bean
    public IntegrationFlow server(ServerSocketHandler serverSocketHandler) {
        return IntegrationFlow.from(Tcp.inboundGateway(
                Tcp.netServer(socketPort)
                    //.deserializer(TcpCodecs.crlf()) // TELNET
                    //.serializer(TcpCodecs.crlf()))) // TELNET
                    .deserializer(TcpCodecs.lf()) // NETCAT
                    .serializer(TcpCodecs.lf()))) // NETCAT
            .handle(serverSocketHandler::handleMessage)
            .get();
    }


    @Bean
    public ServerSocketHandler serverSocketHandler() {
        return new ServerSocketHandler();
    }

}
