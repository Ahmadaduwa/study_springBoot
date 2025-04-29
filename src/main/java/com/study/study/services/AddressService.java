package com.study.study.services;

import com.study.study.models.Address;
import com.study.study.models.Employee;
import com.study.study.repository.AddressRepository;
import com.study.study.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {
    private final AddressRepository addressRepository;
    private final EmployeeRepository employeeRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository, EmployeeRepository employeeRepository) {
        this.addressRepository = addressRepository;
        this.employeeRepository = employeeRepository;
    }

    // CREATE
    public Address addAddressToEmployee(int employeeId, Address address) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new IllegalStateException("Employee with id " + employeeId + " not found."));

        address.setEmployee(employee);
        return addressRepository.save(address);
    }

    // READ
    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    // READ
    public Address getAddressByEmployeeId(int employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new IllegalStateException("Employee with id " + employeeId + " not found."));

        Address address = employee.getAddress();
        if (address == null) {
            throw new IllegalStateException("Employee with id " + employeeId + " does not have an address.");
        }
        return address;
    }

    // UPDATE
    public Address updateAddress(int addressId, Address updatedAddress) {
        Address existingAddress = addressRepository.findById(addressId)
                .orElseThrow(() -> new IllegalStateException("Address with id " + addressId + " not found."));

        existingAddress.setStreet(updatedAddress.getStreet());
        existingAddress.setHouseNumber(updatedAddress.getHouseNumber());
        existingAddress.setZipCode(updatedAddress.getZipCode());

        return addressRepository.save(existingAddress);
    }

    // DELETE
    public void deleteAddress(int addressId) {
        if (!addressRepository.existsById(addressId)) {
            throw new IllegalStateException("Address with id " + addressId + " not found.");
        }
        addressRepository.deleteById(addressId);
    }
}
