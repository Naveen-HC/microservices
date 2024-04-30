package com.navi.loan.controller;

import com.navi.loan.constants.LoanConstants;
import com.navi.loan.dto.LoanDto;
import com.navi.loan.dto.ResponseDto;
import com.navi.loan.service.ILoanService;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Validated
public class LoanController {

    private ILoanService iLoanService;

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
    public ResponseEntity<LoanDto> fecth(@RequestParam
                                         @Pattern(regexp = "^$|[0-9]{10}",
                                                 message = "Mobile number must have 10 digits")
                                         String mobileNumber){
        LoanDto loanDto = iLoanService.fecth(mobileNumber);
            return ResponseEntity
                    .status(HttpStatus.FOUND)
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
}
