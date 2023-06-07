package com.example.mockito.constant;

import com.example.mockito.domain.Employee;

import java.util.Map;

public class Constant {
    public final static Employee EMPLOYEE = new Employee("Miranda","Lawson", 10,1);
    public final static Employee EMPLOYEE_MIN = new Employee("John","Shepard", 10,1);
    public final static Employee EMPLOYEE_MAX = new Employee("Liara","T'soni", 20,1);
    public final static String GOOD_NAME = "Miranda";
    public final static String BAD_NAME = "Miranda11";
    public final static int EMPLOYEE_SUM = 30;
    public final static double EMPLOYEE_AVERAGE = 15.0;
    public final static int EMPLOYEE_NEW_SALARY = 5;
    public final static int EMPLOYEE_NEW_DEPARTMENT = 2;
    public final static int CORRECT_DEPARTMENT = 1;
    public final static int INCORRECT_DEPARTMENT = 2;

    public final static Map<String,Employee> EMPLOYEE_LIST = Map.of(
            EMPLOYEE_MIN.getFullName(),
            EMPLOYEE_MIN,
            EMPLOYEE_MAX.getFullName(),
            EMPLOYEE_MAX
    );


}
