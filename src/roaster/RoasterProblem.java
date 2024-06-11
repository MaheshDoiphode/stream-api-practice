//package roaster;
//
//import java.util.*;
//
///*
//Problem Statement -
//have to make a roaster for 7 days for a team of 9 people based on the below conditions.
//* Conditions for assigning/unassigning
//1. 9 people - A, B, C, D , E , F, G, H , I.
//2. Morning shift, Afternooon shift and Night shift.
//3. At least 2 people in a single shift must work and at most any number of people can work.
//4. The project is open from monday till sunday to work.
//5. Each shift has at least 2 people working.
//6. 4 people lets say (A, B, C, D) have classes on every Saturday, so they can't work on Saturday neither they can work in night shifts for the previous day i.e., Friday, so following the consecutive days of condition these people can have offs on Saturday and Sunday or 2nd option as Friday and Saturday and can't have night shifts on Friday.
//7. 4 people lets say (E, F, G, H) have classes on every Sunday, so they can't work on sunday neither they can work in night shifts for the previous day i.e., Saturday, so following the consecutive days of condition these people can have offs on Sunday and Monday or 2nd option as Saturday and Sunday and can't have night shifts on Saturdays.
//8. Person I is flexible for having week offs on any days of the week without any condition.
//9. Each person works 5 days a week and has 2 consecutive days off.
//10. The offs should be consecutive and unbroken.
//* */
//import java.util.*;
//
//public class RoasterProblem {
//    private static final int DAYS = 7;
//    private static final int SHIFTS = 3;
//    private static final int EMPLOYEES = 9;
//
//    public static void main(String[] args) {
//        List<Shift> shifts = new ArrayList<>();
//        for (int i = 0; i < DAYS; i++) {
//            shifts.add(new Shift(i, new ArrayList<>()));
//        }
//        Roaster roaster = new Roaster(shifts);
//        if (assignShifts(0, 0, roaster)) {
//            printRoaster(roaster);
//        } else {
//            System.out.println("No valid roaster found.");
//        }
//    }
//
//    private static boolean assignShifts(int day, int shift, Roaster roaster) {
//        if (day == DAYS) {
//            return checkConditions(roaster);
//        }
//
//        for (Employee employee : roaster.shifts.get(day).employees) {
//            if (canAssignShift(employee, day, shift)) {
//                employee.shifts[day] = shift;
//
//                if (assignShifts(day + 1, (shift + 1) % SHIFTS, roaster)) {
//                    return true;
//                }
//
//                employee.shifts[day] = 0; // backtrack
//            }
//        }
//
//        return false;
//    }
//
//    private static boolean checkConditions(Roaster roaster) {
//        // Implement conditions #6, #7, #8 here
//        return true;
//    }
//
//    private static void printRoaster(Roaster roaster) {
//        for (Shift shift : roaster.shifts) {
//            System.out.println("Day " + shift.day + ":");
//            for (Employee employee : shift.employees) {
//                System.out.println("Employee " + employee.id + " - Shift " + employee.shifts[shift.day]);
//            }
//        }
//    }
//}
//
//class Roaster {
//    List<Shift> shifts;
//
//    public Roaster(List<Shift> shifts) {
//        this.shifts = shifts;
//    }
//}
//
//class Shift {
//    int day;
//    List<Employee> employees;
//
//    public Shift(int day, List<Employee> employees) {
//        this.day = day;
//        this.employees = employees;
//    }
//}
//
//class Employee {
//    int id;
//    int[] shifts = new int[RoasterProblem.DAYS];
//
//    public Employee(int id) {
//        this.id = id;
//    }
//}
//
//class Shift {
//    String name;
//    List<Employee> employees;
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public void setEmployees(List<Employee> employees) {
//        this.employees = employees;
//    }
//
//    public Shift(String name, List<Employee> employees) {
//        this.name = name;
//        this.employees = employees;
//    }
//
//    public Shift(String name) {
//        this.name = name;
//        this.employees = new ArrayList<>();
//    }
//
//    public void addEmployee(Employee employee) {
//        this.employees.add(employee);
//    }
//
//    public void removeEmployee(Employee employee) {
//        this.employees.remove(employee);
//    }
//
//    public List<Employee> getEmployees() {
//        return this.employees;
//    }
//}
//
//class Employee {
//    String name;
//    int offDay1;
//    int offDay2;
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public int getOffDay1() {
//        return offDay1;
//    }
//
//    public void setOffDay1(int offDay1) {
//        this.offDay1 = offDay1;
//    }
//
//    public int getOffDay2() {
//        return offDay2;
//    }
//
//    public void setOffDay2(int offDay2) {
//        this.offDay2 = offDay2;
//    }
//
//    public Employee(String name, int offDay1, int offDay2) {
//        this.name = name;
//        this.offDay1 = offDay1;
//        this.offDay2 = offDay2;
//    }
//}
//
