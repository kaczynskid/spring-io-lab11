package com.example.store.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableConfigurationProperties(MessageConfig.class)
public class MessageController {

    @Value("${message.text:hi}")
    private String text;

    @GetMapping("/msg1")
    Message getMessageFromValue() {
        return new Message(text);
    }

    @Autowired
    private Environment environment;

    @GetMapping("/msg2")
    Message getMessageFromEnvironment() {
        return new Message(environment.getProperty("message.text", "hi"));
    }

    @Autowired
    private MessageConfig config;

    @GetMapping("/msg3")
    Message getMessageFromConfgProps() {
        return new Message(config.getText());
    }

}

@Data
@AllArgsConstructor
class Message {

    private String text;
}

@Data
@ConfigurationProperties("message")
class MessageConfig {

    private String text = "hi";
}
