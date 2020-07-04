package com.team1.iss.trial.repo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

 

import com.team1.iss.trial.domain.Admin;
import com.team1.iss.trial.domain.Employee;
import com.team1.iss.trial.domain.Manager;
import com.team1.iss.trial.domain.User;


@SpringBootTest
public class seeder {
	
	
	@Autowired
    private UserRepository urepo;
    
    @Test
    public void createData() {
        
        User u1 = new Employee();
        u1.setAnnualLeaveEntitlement(3);
        u1.setName("Tom Ng");
        u1.setEmail("tom@gmail.com");
        u1.setPassword("123");
        u1.setMedicalLeaveEntitlement(40);
        u1.setEnabled(true);
        urepo.save(u1);
        
        User u2 = new Employee ();
        u2.setAnnualLeaveEntitlement(7);
        u2.setName("Mary Ng");
        u2.setEmail("mary@gmail.com");
        u2.setPassword("123");
        u2.setMedicalLeaveEntitlement(50);
        u2.setEnabled(true);
        urepo.save(u2);
        
        User u3 = new Employee ();
        u3.setAnnualLeaveEntitlement(1);
        u3.setName("John Ng");
        u3.setEmail("john@gmail.com");
        u3.setPassword("123");
        u3.setMedicalLeaveEntitlement(50);
        u3.setEnabled(true);
        urepo.save(u3);
        
        User u4 = new Admin();
        u4.setAnnualLeaveEntitlement(1);
        u4.setName("Jerry Ng");
        u4.setEmail("jerry@gmail.com");
        u4.setPassword("123");
        u4.setMedicalLeaveEntitlement(50);
        u4.setEnabled(true);
        urepo.save(u4);
        
        User u5 = new Admin();
        u5.setAnnualLeaveEntitlement(7);
        u5.setName("Jasper Ng");
        u5.setEmail("jasper@gmail.com");
        u5.setPassword("123");
        u5.setMedicalLeaveEntitlement(40);
        u5.setEnabled(true);
        urepo.save(u5);
        
        User u6 = new Admin();
        u6.setAnnualLeaveEntitlement(8);
        u6.setName("Susan Ng");
        u6.setEmail("susan@gmail.com");
        u6.setPassword("123");
        u6.setMedicalLeaveEntitlement(50);
        u6.setEnabled(true);
//        u6.setManager(u8);
        urepo.save(u6);
        
        User u7 = new Manager();
        u7.setAnnualLeaveEntitlement(6);
        u7.setName("Esther Ng");
        u7.setEmail("esther@gmail.com");
        u7.setPassword("123");
        u7.setMedicalLeaveEntitlement(40);
        u7.setEnabled(true);
        urepo.save(u7);
        
        Manager u8 = new Manager();
        u8.setAnnualLeaveEntitlement(3);
        u8.setName("Grace Ng");
        u8.setEmail("grace@gmail.com");
        u8.setPassword("123");
        u8.setMedicalLeaveEntitlement(30);
        u8.setEnabled(true);
        urepo.save(u8);
        
        User u9 = new Manager();
        u9.setAnnualLeaveEntitlement(1);
        u9.setName("Andrew Ng");
        u9.setEmail("andrew@gmail.com");
        u9.setPassword("123");
        u9.setMedicalLeaveEntitlement(40);
        u9.setEnabled(true);
        urepo.save(u9);
        
        User u10 = new Manager();
        u10.setAnnualLeaveEntitlement(1);
        u10.setName("Richard Ng");
        u10.setEmail("richard@gmail.com");
        u10.setPassword("123");
        u10.setMedicalLeaveEntitlement(50);
        u10.setEnabled(false);
        urepo.save(u10);
    }
    

}
