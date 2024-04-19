package org.message.sender.answering.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.message.sender.answering.service.answering.AnsweringService;
import org.message.sender.answering.service.model.dto.CreateMessageRequest;
import org.message.sender.answering.service.model.dto.CreateMessageResponse;
import org.message.sender.answering.service.model.dto.MessageDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Answering", description = "Message sending APIs")
@RestController
@RequestMapping("/api/v1/answering")
public class AnsweringController
{
    private final AnsweringService answeringService;

    public AnsweringController(final AnsweringService answeringService)
    {
        this.answeringService = answeringService;
    }

    @Operation(
            summary = "Create Message for sending it via SMTP",
            description = "Create message and then publish it to MQ for being processed.",
            tags = { "messages", "post" })
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = { @Content(schema = @Schema(implementation = CreateMessageResponse.class),
                    mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "503", content = { @Content(schema = @Schema()) }) })
    @PostMapping("/messages")
    @ResponseStatus(HttpStatus.CREATED)
    public CreateMessageResponse send(@Parameter(description = "Request to create new message.", example = "CreateMessageRequest")
                                          @RequestBody CreateMessageRequest createMessageDto)
    {
        return answeringService.publishMessage(createMessageDto);
    }

    @Operation(
            summary = "Get Message info by id.",
            description = "Get message info to analyze state.",
            tags = { "messages", "get" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = MessageDto.class),
                    mediaType = "application/json") }),
            @ApiResponse(responseCode = "206", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "503", content = { @Content(schema = @Schema()) }) })
    @GetMapping("/messages/{uniqueMessageIdentifier}")
    @ResponseStatus(HttpStatus.OK)
    public MessageDto answer(@Parameter(description = "Message id as path variable to find saved message.",
            example = "127hxew8") @PathVariable String uniqueMessageIdentifier)
    {
        return answeringService.getMessage(uniqueMessageIdentifier);
    }

}
