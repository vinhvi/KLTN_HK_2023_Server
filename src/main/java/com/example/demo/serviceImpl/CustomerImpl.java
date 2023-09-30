package com.example.demo.serviceImpl;

import com.example.demo.entity.Avatar;
import com.example.demo.entity.Customer;
import com.example.demo.entity.ShoppingCart;
import com.example.demo.repository.CustomerRepo;
import com.example.demo.service.AvatarService;
import com.example.demo.service.CustomerService;
import com.example.demo.service.ShoppingCartService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CustomerImpl implements CustomerService {
    private final CustomerRepo customerRepo;
    private final AvatarService avatarService;
    private final ShoppingCartService shoppingCartService;

    @Override
    public Customer addCustomer(Customer customer) {

        //set image default in database for customer if image null
        if (customer.getAvatar() == null) {
            Avatar avatar = new Avatar();
            avatar.setIdCloud("default");
            avatar.setImageLink("https://res.cloudinary.com/dv329zg5e/image/upload/v1692689754/user_default_txm2pe.png");
            customer.setAvatar(avatarService.addAvatar(avatar));
        }
        Customer customerSave = customerRepo.save(customer);
        ShoppingCart shoppingCart = new ShoppingCart();
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        shoppingCart.setDate(date);
        shoppingCart.setCustomer(customerSave);
        return customerSave;
    }

    @Override
    public Customer getById(String id) {
        return customerRepo.findCustomerById(id);
    }

    @Override
    public List<Customer> getAll() {
        return customerRepo.findAll();
    }

    @Override
    public Customer getByEmail(String email) {
        return customerRepo.findCustomerByEmail(email);
    }

    @Override
    public Customer getByPhone(String phone) {
        return customerRepo.findByPhone(phone);
    }

    @Override
    public String randomId() {
        Random random = new Random();
        String id = "";
        boolean check = false;
        int attempts = 0;
        int maxAttempts = 10000; // set a maximum number of attempts to prevent infinite loop
        while (!check && attempts < maxAttempts) {
            int number = random.nextInt(10000);
            id = "KH" + String.format("%04d", number);
            if (customerRepo.findCustomerById(id) == null) {
                check = true;
            }
            attempts++;
        }
        if (!check) {
            throw new RuntimeException("Unable to generate a unique ID after " + maxAttempts + " attempts.");
        }
        return id;
    }
}
