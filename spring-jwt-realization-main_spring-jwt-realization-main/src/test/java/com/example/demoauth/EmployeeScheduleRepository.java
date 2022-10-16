package com.example.demoauth;

import static org.mockito.ArgumentMatchers.any;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.demoauth.models.EmployeeSchedule;
import com.example.demoauth.service.EmployeeScheduleService;

@SpringBootTest
public class EmployeeScheduleRepository {

	@MockBean
	EmployeeScheduleService employeeService;

	@Test
	public void testCreateEmployee() {
		Long scheduleId = (long) 1;

		EmployeeSchedule employeeScheduleTest = new EmployeeSchedule(scheduleId, 1, 1, 1, 1, 1, 1, "no");
		Mockito.when(employeeService.save(any())).thenReturn(employeeScheduleTest);

		employeeService.save(employeeScheduleTest);
		
		Mockito.when(employeeService.getScheduleBySpecifyId(any())).thenReturn(employeeScheduleTest);
		EmployeeSchedule employeeReturned = employeeService.getScheduleBySpecifyId(scheduleId);

		Assertions.assertNotNull(employeeReturned);
		Assertions.assertTrue(employeeScheduleTest.equals(employeeReturned));
		
		EmployeeSchedule employeeScheduleUpdate = new EmployeeSchedule(scheduleId, 2, 2, 2, 2, 2, 2, "no");
		Mockito.when(employeeService.save(any())).thenReturn(employeeScheduleUpdate);

		employeeService.save(employeeScheduleUpdate);
		
		Mockito.when(employeeService.getScheduleBySpecifyId(any())).thenReturn(employeeScheduleUpdate);
		EmployeeSchedule employeeUpdate = employeeService.getScheduleBySpecifyId(scheduleId);
		
		Assertions.assertNotNull(employeeUpdate);
		Assertions.assertTrue(employeeScheduleUpdate.equals(employeeUpdate));
		
	}

}
