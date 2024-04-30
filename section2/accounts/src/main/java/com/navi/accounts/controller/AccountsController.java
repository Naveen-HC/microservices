package com.navi.accounts.controller;

import com.navi.accounts.constants.AccountConstants;
import com.navi.accounts.dto.AccountDto;
import com.navi.accounts.dto.CustomerDto;
import com.navi.accounts.dto.ErrorResponseDto;
import com.navi.accounts.dto.ResponseDto;
import com.navi.accounts.service.IAccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(
        name = "CURD Operation in Account microservices",
        description = "CREATE, UPDATE, GET, DELETE of Accounts in NaviBank"
)
@AllArgsConstructor
@Validated
public class AccountsController {

    private IAccountService iAccountService;

    @Operation(
            summary = "Customer & Account Create REST API",
            description = "For creating new Customer and Account inside NaviBank"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status CREATED"
    )
    @PostMapping("/createAccount")
    public ResponseEntity<ResponseDto> createAccount(@RequestBody @Valid CustomerDto customerDto){
        iAccountService.createAccount(customerDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountConstants.STATUS_201, AccountConstants.MESSAGE_201));
    }

    @Operation(
            summary = "Customer & Account Get REST API",
            description = "For fetching Customer and Account details inside NaviBank"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status FOUND"
    )
    @GetMapping("/fetchCustomer")
    public ResponseEntity<CustomerDto> fetch(@RequestParam
                                             @Pattern(regexp = "^$|[0-9]{10}",
                                                     message = "Mobile number must have 10 digits")
                                             String mobileNumber){
        CustomerDto customerDto = iAccountService.fetchCustomerDetails(mobileNumber);
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(customerDto);
    }

    @Operation(
            summary = "Customer & Account Put REST API",
            description = "For updating Customer and Account details inside NaviBank"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "HTTP Status INTERNAL SERVER ERROR",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @PutMapping("/updateCustomer")
    public ResponseEntity<ResponseDto> updateCustomer(@RequestBody @Valid CustomerDto customerDto){
        if(iAccountService.updateCustomer(customerDto)){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(AccountConstants.STATUS_200, AccountConstants.MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(AccountConstants.STATUS_417, AccountConstants.MESSAGE_417_UPDATE));
        }
    }

    @Operation(
            summary = "Account Put REST API",
            description = "For updating Account details inside NaviBank"
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
    @PutMapping("/updateAccount")
    public ResponseEntity<ResponseDto> updateAccount(@RequestBody @Valid AccountDto accountDto){
        if(iAccountService.updateAccount(accountDto)){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(AccountConstants.STATUS_200, AccountConstants.MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(AccountConstants.STATUS_417, AccountConstants.MESSAGE_417_UPDATE));
        }
    }

    @Operation(
            summary = "Customer & Account Delete REST API",
            description = "For deleting Customer and Account details inside NaviBank"
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
    @DeleteMapping("/deleteAccount")
    public ResponseEntity<ResponseDto> deleteAccount(@RequestParam
                                                     @Pattern(regexp = "^$|[0-9]{10}",
                                                        message = "Mobile number must have 10 digits")
                                                     String mobileNumber){
        if(iAccountService.deleteAccount(mobileNumber)){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(AccountConstants.STATUS_200, AccountConstants.MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(AccountConstants.STATUS_417, AccountConstants.MESSAGE_417_UPDATE));
        }
    }
}
