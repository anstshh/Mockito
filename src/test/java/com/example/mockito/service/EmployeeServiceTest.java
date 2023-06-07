package com.example.mockito.service;

import com.example.mockito.domain.Employee;
import com.example.mockito.exceptions.EmployeeAlreadyAddedException;
import com.example.mockito.exceptions.EmployeeNotFoundException;
import com.example.mockito.exceptions.InvalidInputException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.ReflectionUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static com.example.mockito.constant.Constant.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;


class EmployeeServiceTest {
    private EmployeeService out;

    @BeforeEach
    void setUp() {
        out = new EmployeeService();
    }

    @Test
    void getEmployees() {
        out.add("Miranda", "Lawson", 10, 1);

        Map<String, Employee> extend = Map.of(EMPLOYEE.getFullName(), EMPLOYEE);

        assertEquals(extend, out.getEmployees());
    }

    @Test
    void getEmployeesNotExist() {
        assertTrue(out.getEmployees().isEmpty());
    }

    @Test
    void addCorrectName() {
        Employee result = out.add("Miranda", "Lawson", 10, 1);

        assertEquals(EMPLOYEE, result);

    }

    @Test
    void addUpperCaseName() {
        Employee result = out.add("MIRANDA", "LAWSON", 10, 1);

        assertEquals(GOOD_NAME, result.getFirstName());

    }

    @Test
    void addWhenKeyEqual() {
        out.add("Miranda", "Lawson", 10, 1);

        assertThrows(EmployeeAlreadyAddedException.class, () -> out.add(
                "Miranda",
                "Lawson",
                10,
                1));

    }

    @Test
    void removeCorrectName() {
        out.add("Miranda", "Lawson", 10, 1);

        Employee result = out.remove("Miranda", "Lawson", 10, 1);

        assertEquals(EMPLOYEE, result);
    }

    @Test
    void removeNotExist() {
        assertThrows(EmployeeNotFoundException.class, () -> out.remove(
                "Miranda",
                "Lawson",
                10,
                1));
    }

    @Test
    void findCorrectName() {
        out.add("Miranda", "Lawson", 10, 1);

        Employee result = out.find("Miranda", "Lawson", 10, 1);

        assertEquals(EMPLOYEE,result);
    }

    @Test
    void findNotExist() {
        assertThrows(EmployeeNotFoundException.class, () -> out.find(
                "Miranda",
                "Lawson",
                10,
                1));
    }

    @Test
    void changeEmployeeSalaryCorrect() {
        out.add("Miranda", "Lawson", 10, 1);

        Employee result = out.changeEmployeeSalary("Miranda", "Lawson", 5);

        assertEquals(EMPLOYEE, result);
        assertEquals(EMPLOYEE_NEW_SALARY, result.getSalary());
    }

    @Test
    void changeEmployeeSalaryNotExist() {
        assertThrows(EmployeeNotFoundException.class, ()->out.changeEmployeeSalary(
                "Miranda",
                "Lawson",
                5));
    }

    @Test
    void changeEmployeeDepartCorrect() {
        out.add("Miranda", "Lawson", 10, 1);

        Employee result = out.changeEmployeeDepart("Miranda", "Lawson", 2);

        assertEquals(EMPLOYEE, result);
        assertEquals(EMPLOYEE_NEW_DEPARTMENT, result.getDepartment());
    }

    @Test
    void changeEmployeeDepartNotExist() {
        assertThrows(EmployeeNotFoundException.class, ()->out.changeEmployeeDepart(
                "Miranda",
                "Lawson",
                2));
    }

    @Test
    void employeesByDepartmentCorrect() {
        out.add("Miranda", "Lawson", 10, 1);

        List<Employee> extend = List.of(EMPLOYEE);
        List<Employee> result = out.employeesByDepartment(CORRECT_DEPARTMENT);

        assertEquals(extend,result);
    }

    @Test
    void employeesByDepartmentNotExist() {

        assertTrue(out.employeesByDepartment(anyInt()).isEmpty());
    }

    @Test
    void getAllSalaryCorrect() {
        out.add("Miranda", "Lawson", 10, 1);
        out.add("Liara", "T'soni", 20, 1);

        assertEquals(EMPLOYEE_SUM, out.getAllSalary());
    }

    @Test
    void getAllSalaryCorrectNotExist() {

        assertEquals(0, out.getAllSalary());
    }

    @Test
    void getEmployeeMinSalaryCorrect() {
        out.add("Miranda", "Lawson", 10, 1);
        out.add("Liara", "T'soni", 20, 1);

        assertEquals(EMPLOYEE_MIN, out.getEmployeeMinSalary());
    }

    @Test
    void getEmployeeMaxSalaryCorrect() {
        out.add("Miranda", "Lawson", 10, 1);
        out.add("Liara", "T'soni", 20, 1);

        assertEquals(EMPLOYEE_MAX, out.getEmployeeMaxSalary());
    }

    @Test
    void getEmployeeMaxMinSalaryNotExist() {

        assertThrows(EmployeeNotFoundException.class, ()->out.getEmployeeMaxSalary());
        assertThrows(EmployeeNotFoundException.class, ()->out.getEmployeeMinSalary());
    }

    @Test
    void getAverageSalaryCorrect() {
        out.add("Miranda", "Lawson", 10, 1);
        out.add("Liara", "T'soni", 20, 1);

        assertEquals(EMPLOYEE_AVERAGE, out.getAverageSalary());
    }

    @Test
    void getAverageSalaryNotExist() {

        assertEquals(0, out.getAverageSalary());
    }

    @Test
    void showAll() {
        out.add("Miranda", "Lawson", 10, 1);
        out.add("Liara", "T'soni", 20, 1);

        Collection<Employee> expected = Collections.unmodifiableCollection(EMPLOYEE_LIST.values());
        Collection<Employee> result = out.showAll();

        assertIterableEquals(expected,result);
    }

    @Test
    void validateCorrect(){
        try {
            Method method = EmployeeService.class.getDeclaredMethod("validate", String.class, String.class);
            method.setAccessible(true);
            assertNull((method.invoke(out, GOOD_NAME, GOOD_NAME)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void validateIncorrectNameException(){

        assertThrows(InvalidInputException.class, ()-> out.add(
                "Miranda123",
                "T'soni",
                10,
                1));
    }


}
