package dev.bernalvarela.endpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.MessageHeaders;

public class ServerSocketHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(ServerSocketHandler.class);

	public String handleMessage(byte[] message, MessageHeaders messageHeaders) {
		String messageContent = new String(message);
		LOGGER.info("Receive message: {}", messageContent);
		return String.format("Message \"%s\" is processed", messageContent);
	}
}