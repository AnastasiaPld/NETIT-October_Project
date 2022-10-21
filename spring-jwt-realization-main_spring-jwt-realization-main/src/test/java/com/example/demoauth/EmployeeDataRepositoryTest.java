package com.example.demoauth;

import static org.mockito.ArgumentMatchers.any;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.demoauth.models.Assignment;
import com.example.demoauth.models.EmployeePersonalData;
import com.example.demoauth.models.EmployeeSchedule;
import com.example.demoauth.models.Order;
import com.example.demoauth.service.EmployeeDataService;

@SpringBootTest
public class EmployeeDataRepositoryTest extends EmployeeDataService {

	@MockBean
	EmployeeDataService employeeService;

	@Test
	public void testCreateEmployee() {

		Assignment assignment = new Assignment();
		EmployeeSchedule schedule = new EmployeeSchedule();
		Order order = new Order();

		EmployeePersonalData employeeTest = new EmployeePersonalData("employeeNameTest", "surnameTest",
				"startWorkDayTest", 1, "salaryTest", "emailTest", assignment, schedule, order);
		Mockito.when(employeeService.save(any())).thenReturn(employeeTest);

		employeeService.save(employeeTest);

		Mockito.when(employeeService.findByName(any())).thenReturn(employeeTest);
		EmployeePersonalData employeeReturned = employeeService.findByName("employeeNameTest");

		Assertions.assertNotNull(employeeReturned);
		Assertions.assertTrue(employeeTest.equals(employeeReturned));
		
		//update data of created employee
		EmployeePersonalData employeeTestUpdate = new EmployeePersonalData("employeeNameUpdateTest", "surnameUpdateTest",
				"startWorkDayTest", 1, "salaryTest", "emailTest", assignment, schedule, order);
		Mockito.when(employeeService.save(any())).thenReturn(employeeTestUpdate);

		employeeService.save(employeeTestUpdate);

		Mockito.when(employeeService.findByName(any())).thenReturn(employeeTestUpdate);
		EmployeePersonalData employeeReturnedUpdate = employeeService.findByName("employeeNameUpdateTest");

		Assertions.assertNotNull(employeeReturnedUpdate);
		Assertions.assertTrue(employeeTestUpdate.equals(employeeReturnedUpdate));
		
	
	}

}
