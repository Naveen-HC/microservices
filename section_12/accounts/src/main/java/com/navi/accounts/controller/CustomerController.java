package com.navi.accounts.controller;

import com.navi.accounts.dto.CustomerDetailsDto;
import com.navi.accounts.service.ICustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(
        name = "CURD Operation in Account microservices",
        description = "CREATE, UPDATE, GET, DELETE of Accounts in NaviBank"
)
@AllArgsConstructor
public class CustomerController {

    private static  final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);

    private ICustomerService iCustomerService;

    @Operation(
            summary = "Customer details fetch REST API",
            description = "Customer details REST API to fetch customer details based on mobile number"
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
    @GetMapping("/fetchCustomerDetails")
    public ResponseEntity<CustomerDetailsDto> getCustomerDetails(@RequestParam
                                                                 @Pattern(regexp = "^$|[0-9]{10}",
                                                                         message = "Mobile number must have 10 digits")
                                                                 String mobileNumber){
        LOGGER.info("fetchCustomerDetails method starts");
        CustomerDetailsDto customerDetailsDto = iCustomerService.fetchCustomerAllDetails(mobileNumber);
        LOGGER.info("fetchCustomerDetails method ends");
        return ResponseEntity
                .status(HttpStatus.SC_OK)
                .body(customerDetailsDto);
    }
}
