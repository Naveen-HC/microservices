package com.navi.accounts.service;

import com.navi.accounts.dto.CustomerDetailsDto;

public interface ICustomerService {

    CustomerDetailsDto fetchCustomerAllDetails(String mobileNumber);
}
