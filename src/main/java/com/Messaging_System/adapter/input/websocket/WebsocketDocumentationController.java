package com.Messaging_System.adapter.input.websocket;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Controller
@Tag(name = "Websocket", description = "Websocket operations")
public class WebsocketDocumentationController {

    @GetMapping("/user-message-ws")
    @Operation(
            summary = "Real-time communication between user and friend",
            description = "Need a client to connect. URL: ws://localhost:6969/user-message-ws. " +
                    "This is not an endpoint, you need a client to connect to the handshake and effectively use it.",
            requestBody = @RequestBody(
                    description = "Websocket accepted payloads",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            examples = {
                                    @ExampleObject(
                                            name = "friend request payload",
                                            value = """
                                                    {
                                                         "type": "FRIEND_REQUEST",
                                                         "friend_request": {
                                                             "request_user_name": "henrique#12346",
                                                             "request_type": "SEND"
                                                         }
                                                     }
                                                    """
                                    ),
                                    @ExampleObject(
                                            name = "send message payload",
                                            value = """
                                                    {
                                                        "type": "MESSAGE",
                                                        "message": {
                                                            "type": "SEND",
                                                            "content": "TESTE",
                                                            "receiver_name": "henrique#12346"
                                                        }
                                                    }
                                                    """
                                    ),
                                    @ExampleObject(
                                            name = "delete message payload",
                                            value = """
                                                    {
                                                         "type": "MESSAGE",
                                                         "message": {
                                                             "type": "DELETE",
                                                             "message_id": 1
                                                         }
                                                     }
                                                    """
                                    ),
                                    @ExampleObject(
                                            name = "read message payload",
                                            value = """
                                                    {
                                                          "type": "MESSAGE",
                                                          "message": {
                                                              "type": "READ",
                                                              "all_ids": [
                                                                  1,
                                                                  2,
                                                                  4
                                                              ]
                                                          }
                                                    }
                                                    """
                                    ),
                                    @ExampleObject(
                                            name = "accept friend request",
                                            value = """
                                                    {
                                                        "type": "FRIEND_REQUEST",
                                                        "friend_request": {
                                                            "sender_user_name": "henrique#12346",
                                                            "request_type": "ACCEPT"
                                                        }
                                                    }
                                                    """
                                    ),
                            }
                    )
            )
    )
    public String chatWebsocket(){
        return "You can use a client to communicate!";
    }

}
