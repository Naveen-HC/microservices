package com.navi.loan.service.impl;

import com.navi.loan.constants.LoanConstants;
import com.navi.loan.dto.LoanDto;
import com.navi.loan.entity.Loan;
import com.navi.loan.exception.LoanAlreadyExistsException;
import com.navi.loan.exception.ResourceNotFoundException;
import com.navi.loan.mapper.LoanMapper;
import com.navi.loan.repository.LoanRepository;
import com.navi.loan.service.ILoanService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class LoanServiceImpl implements ILoanService {

    private LoanRepository loanRepository;

    /**
     * @param mobileNumber
     * @return
     */
    @Override
    public boolean create(String mobileNumber) {
        Optional<Loan> loanOptional = loanRepository.findByMobileNumber(mobileNumber);
        if(loanOptional.isPresent()){
            throw new LoanAlreadyExistsException("Loan already exists for the mobile number: " + mobileNumber);
        }
        Loan createdLoan = loanRepository.save(createNewLoan(mobileNumber));
        return createdLoan != null;
    }

    private Loan createNewLoan(String mobileNumber) {
        Loan newLoan = new Loan();
        long randomLoanNumber = 100000000000L + new Random().nextInt(900000000);
        newLoan.setLoanNumber(Long.toString(randomLoanNumber));
        newLoan.setMobileNumber(mobileNumber);
        newLoan.setLoanType(LoanConstants.HOME_LOAN);
        newLoan.setTotalLoan(LoanConstants.NEW_LOAN_LIMIT);
        newLoan.setAmountPaid(0);
        newLoan.setOutstandingAmount(LoanConstants.NEW_LOAN_LIMIT);
        return newLoan;
    }

    /**
     * @param mobileNumber
     * @return
     */
    @Override
    public LoanDto fecth(String mobileNumber) {
       Loan loan = loanRepository.findByMobileNumber(mobileNumber).orElseThrow(
               () -> new ResourceNotFoundException("Loan", "mobile number", mobileNumber)
       );
       return LoanMapper.mapLoanDto(loan, new LoanDto());
    }

    /**
     * @param loanDto
     * @return
     */
    @Override
    public boolean update(LoanDto loanDto) {
        Loan loan = loanRepository.findByLoanNumber(loanDto.getLoanNumber()).orElseThrow(
                () -> new ResourceNotFoundException("Loan", "loan number", loanDto.getLoanNumber())
        );
        Loan updatedLoan = loanRepository.save(LoanMapper.mapLoan(loanDto, loan));
        return updatedLoan != null;
    }

    /**
     * @param mobileNumber
     * @return
     */
    @Override
    public boolean delete(String mobileNumber) {
        Loan loan = loanRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Loan", "mobile number", mobileNumber)
        );
        loanRepository.delete(loan);
        return true;
    }
}
