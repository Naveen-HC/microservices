package com.navi.loan.controller;

import com.navi.loan.constants.LoanConstants;
import com.navi.loan.dto.LoanDto;
import com.navi.loan.dto.LoanProperties;
import com.navi.loan.dto.ResponseDto;
import com.navi.loan.service.ILoanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class LoanController {

    private final ILoanService iLoanService;

    public LoanController(ILoanService iLoanService){
        this.iLoanService = iLoanService;
    }

    @Autowired
    private Environment environment;

    @Autowired
    private LoanProperties loanProperties;

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> create(@RequestParam
                                              @Pattern(regexp = "^$|[0-9]{10}",
                                                message = "Mobile number must have 10 digits")
                                              String mobileNumber){
        if(iLoanService.create(mobileNumber)){
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(new ResponseDto(LoanConstants.STATUS_201, LoanConstants.MESSAGE_201));
        } else {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(LoanConstants.STATUS_500, LoanConstants.MESSAGE_500));
        }
    }

    @GetMapping("/fetch")
    public ResponseEntity<LoanDto> fetch(@RequestParam
                                         @Pattern(regexp = "^$|[0-9]{10}",
                                                 message = "Mobile number must have 10 digits")
                                         String mobileNumber){
        LoanDto loanDto = iLoanService.fecth(mobileNumber);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(loanDto);
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDto> update(@RequestBody LoanDto loanDto){
        if(iLoanService.update(loanDto)){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(LoanConstants.STATUS_417, LoanConstants.MESSAGE_417_UPDATE));
        } else {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(LoanConstants.STATUS_500, LoanConstants.MESSAGE_500));
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

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> delete(@RequestParam
                                         @Pattern(regexp = "^$|[0-9]{10}",
                                                 message = "Mobile number must have 10 digits")
                                         String mobileNumber){
        iLoanService.delete(mobileNumber);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto(LoanConstants.STATUS_200, LoanConstants.MESSAGE_200));
    }

    @GetMapping("/contact-info")
    public ResponseEntity<LoanProperties> getLoanContact(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(loanProperties);
    }
}
