package com.ecommerce;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ecommerce.repository.ProductDetailRepository;
import com.ecommerce.repository.ProductRepository;
import com.ecommerce.repository.UserRepository;

@Component
public class DataLoader {
	@Autowired
    private UserRepository userRepository;

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ProductDetailRepository productDetailRepository;
	
	
	
    //method invoked during the startup
    @PostConstruct
    public void loadData() {
//    	User admin = new User();
//    	admin.setAdminrights(true);
//    	admin.setEmail("imran.raji@gmail.com");
//    	admin.setFirstname("imran");
//    	admin.setLastname("Raji");
//    	admin.setMobile("1112223333");
//    	admin.setPassword("admin");
//    	admin.setUserid(1);
//        userRepository.save(admin);
        
        
    }

    //method invoked during the shutdown
    @PreDestroy
    public void removeData() {
//    	productDetailRepository.deleteAll();
//        productRepository.deleteAll();
        
        
    }
}
