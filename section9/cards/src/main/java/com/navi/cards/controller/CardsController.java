package com.navi.cards.controller;

import com.navi.cards.constants.CardsConstants;
import com.navi.cards.dto.CardsDto;
import com.navi.cards.dto.CardsProperties;
import com.navi.cards.dto.ErrorResponseDto;
import com.navi.cards.dto.ResponseDto;
import com.navi.cards.service.ICardsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "CURD operation in Card microservices",
        description = "CREATE, UPDATE, GET, DELETE of Card in NaviBank"
)
@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)

public class CardsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CardsController.class);

    private final ICardsService iCardsService;

    public CardsController(ICardsService iCardsService){
        this.iCardsService = iCardsService;
    }

    @Autowired
    private Environment environment;

    @Autowired
    private CardsProperties cardsProperties;


    @Operation(
            summary = "Card create REST API",
            description = "For creating Card in NaviBank"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Failed due to Exception"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> create(@RequestParam String mobileNumber){
        iCardsService.create(mobileNumber);
        return  ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(CardsConstants.STATUS_201, CardsConstants.MESSAGE_201));
    }

    @Operation(
            summary = "Card information fetch REST API",
            description = "For fetching card information from the NaviBank"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "302",
                    description = "Http Status FOUND"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Failed due to Exception"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Http Status INTERNAL_SERVER_ERROR"
            )
    })
    @GetMapping("/fetch")
    public ResponseEntity<CardsDto> fetchCardsDetails(@RequestParam String mobileNumber){
        LOGGER.info("fetchCardsDetails method starts");
        CardsDto cardsDto = iCardsService.fetch(mobileNumber);
        LOGGER.info("fetchCardsDetails method ends");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(cardsDto);
    }

    @Operation(
            summary = "Card information update REST API",
            description = "For updating card information from the NaviBank"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Http Status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Failed due to Exception"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Http Status INTERNAL_SERVER_ERROR"
            )
    })
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> update(@RequestBody CardsDto cardsDto){
        boolean isUpdated = iCardsService.update(cardsDto);
        if(isUpdated){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(CardsConstants.MESSAGE_200, CardsConstants.MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(CardsConstants.STATUS_417, CardsConstants.MESSAGE_417_UPDATE));
        }
    }

    @Operation(
            summary = "Card information update REST API",
            description = "For updating card information from the NaviBank"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Http Status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Failed due to Exception"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Http Status INTERNAL_SERVER_ERROR"
            )
    })
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> delete(@RequestParam String mobileNumber){
        boolean isUpdated = iCardsService.deleteCard(mobileNumber);
        if(isUpdated){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(CardsConstants.MESSAGE_200, CardsConstants.MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(CardsConstants.MESSAGE_417_DELETE, CardsConstants.MESSAGE_417_DELETE));
        }
    }

    @Operation(
            summary = "Java version details",
            description = "Java version details that is deployed in the Accounts microservice"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "HTTP Status INTERNAL SERVER ERROR"
            )
    })
    @GetMapping("/java-version")
    public ResponseEntity<String> getJavaVersion(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(environment.getProperty("JAVA_HOME"));

    }

    @Operation(
            summary = "Contact info",
            description = "Contact info details that ypu can reach out on any issues"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Http Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Http Status INTERNAL_SERVER_ERROR"
            )
    })
    @GetMapping("/contact-info")
    public ResponseEntity<CardsProperties> getContactInfo(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(cardsProperties);
    }
}
