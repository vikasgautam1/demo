package com.example.demo;

import com.example.demo.config.EnvironmentModifier;
import com.example.demo.principle.lsp.violation.SalaryDisburserSimulator;
import com.example.demo.principle.ocp.violation.SuperHeroGame;
import com.example.demo.util.EncryptionUtil;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import java.util.Calendar;
import java.util.Date;

@SpringBootApplication
public class DemoApplication {

	@Autowired
	Environment env;

	@Autowired
	EnvironmentModifier environmentModifier;

	public static void main(String[] args) throws Exception {
		SpringApplication.run(DemoApplication.class, args);
		System.out.println("Everything is working fine...!");
		System.out.println(getDateWithHoursBuffer(-24));
		ocpSuperHeroGame();
		lspSalaryDisburserSimulator();
		String pubKey = "MIIDojCCAoqgAwIBAgIIBmMSCJVcNv4wDQYJKoZIhvcNAQELBQAwOzELMAkGA1UEBhMCSU4xETAPBgNVBAoMCGluZHVzaW5kMRkwFwYDVQQDDBBpbmR1c2luZC1lbmMtZGVjMB4XDTIyMDcxMjA3NDAxMVoXDTMyMDcwOTA3NDAxMVowOzELMAkGA1UEBhMCSU4xETAPBgNVBAoMCGluZHVzaW5kMRkwFwYDVQQDDBBpbmR1c2luZC1lbmMtZGVjMIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAu60AzxMOMrBQ4zrsyh4yftU82X+bUz5NqVAa7kvrHJQVawqfQJiI6T72tFDULHxyiBXu+zOmPQH9WGIk9RriIIAUT6iRKtmLfk7ihZkVoYSbvN3mKFAhOGghBJmlJeEL301yhU38y2Nu/nx0mm/Y/r5DsSAzhet+U5GNBL8fYo0uOZ9Ooziuv9h+nqX0u2tcIPJmausesw42ceXXDJulYjHOMIRg8cyidWSIYLEdebxocOzXuq9hcpoxF45F5br9+syYuQSqzSYDj02xRceenU/rh78Al4cRcYDTmQ6OrZL+OrAcUjiqkR+mX+QKPI5vpo4I5cQMIzkSg+SQFevWBwIDAQABo4GpMIGmMAwGA1UdEwQFMAMBAf8wHQYDVR0OBBYEFDlKyoJrELE0FtrzWSdZQNTGzCEYMGoGA1UdIwRjMGGAFDlKyoJrELE0FtrzWSdZQNTGzCEYoT+kPTA7MQswCQYDVQQGEwJJTjERMA8GA1UECgwIaW5kdXNpbmQxGTAXBgNVBAMMEGluZHVzaW5kLWVuYy1kZWOCCAZjEgiVXDb+MAsGA1UdDwQEAwICvDANBgkqhkiG9w0BAQsFAAOCAQEAs3VlD7kLZZ7TH9S4KGm5s+5feJdl7Xnjq1f+GE8lSKC7hPgHoeiCHb2r7TNWHszhHvBfMfYXPk0Pb60q2VaDZYQbcaetoZsyP33/S/ZxjMIL3KVb9sp7kMXIJTby+SqXNxAipoO0RJapiaEBidOgRspYFAjjgeGGvmmxU6yLIsSM12jIxGSm0MrdzzEkzOMADlPj4TW8Mwo7rSls7nQ120qJTZRwpqu2FsiSxk4Krt/L0WbIjzXjnxqQO1sDVzmo0g35a0+MhfFJvsJFJ4GcLu+s22GPXYvVMXn6WFcxgW/CN2LggO1VDYCsbmJIfMS8JWis0fdzPkCdPXWZvIY7OQ==";
		String encryptedData = EncryptionUtil.encryptSymmetricKeyWithCertificate("9d06b967ee7d4d9ab431a717b1277d6df964db8c86fbf71b03a74f95308f28a7", pubKey);
		System.out.println(encryptedData);
	}

	public static Date getDateWithHoursBuffer(Integer hours){
		Calendar c = Calendar.getInstance();
		c.add(Calendar.HOUR, hours);
		return c.getTime();
	}

	private static void ocpSuperHeroGame(){
		SuperHeroGame superHeroGame = new SuperHeroGame();
		superHeroGame.start();
	}

	private static void lspSalaryDisburserSimulator(){
		SalaryDisburserSimulator salaryDisburserSimulator = new SalaryDisburserSimulator();
		salaryDisburserSimulator.simulate();
	}

	@PostConstruct
	public void run(){
		System.out.println("Running post construct method...");
		System.out.println(env.getProperty("test.prop"));
		environmentModifier.setEnvironmentProperty("test.prop", "test2");
		System.out.println("Prop after update: " + env.getProperty("test.prop"));
	}

}
