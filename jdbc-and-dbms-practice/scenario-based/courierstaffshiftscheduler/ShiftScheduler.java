package courierstaffshiftscheduler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

public class ShiftScheduler {

    private final List<Employee> employeeList  = new ArrayList<>();
    private final Map<ShiftTime, List<Employee>> shiftMap      = new EnumMap<>(ShiftTime.class);
    private final Map<String, Set<ShiftTime>>employeeShiftTracker = new HashMap<>();

    public ShiftScheduler() {
        for (ShiftTime shift : ShiftTime.values()) {
            shiftMap.put(shift, new ArrayList<>());
        }
    }

    public void addEmployee(Employee employee) {
        if (employeeList.contains(employee)) {
            System.out.println("[WARN]  Employee " + employee.getEmployeeId() + " already exists.");
            return;
        }
        employeeList.add(employee);
        employeeShiftTracker.put(employee.getEmployeeId(), new HashSet<>());
        System.out.println("Added: " + employee);
    }

    public Employee findEmployee(String employeeId) {
        return employeeList.stream()
                .filter(e -> e.getEmployeeId().equals(employeeId))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Employee not found: " + employeeId));
    }

    public void removeEmployee(String employeeId) {
        Employee emp = findEmployee(employeeId);
        employeeList.remove(emp);
        employeeShiftTracker.remove(employeeId);
        shiftMap.values().forEach(list -> list.remove(emp));
        System.out.println("Removed: " + emp.getName());
    }

    public void assignShift(String employeeId, ShiftTime shift) throws ShiftAlreadyAssignedException {
        Employee emp = findEmployee(employeeId);

        if (emp.getStatus() != EmployeeStatus.ACTIVE) {
            System.out.println("[SKIP]  " + emp.getName() + " is not ACTIVE (status=" + emp.getStatus() + ").");
            return;
        }

        Set<ShiftTime> assignedShifts = employeeShiftTracker.get(employeeId);

        if (assignedShifts.contains(shift)) {
            throw new ShiftAlreadyAssignedException(employeeId, shift.name());
        }

        shiftMap.get(shift).add(emp);
        assignedShifts.add(shift);

        System.out.println("[ASSIGN] " + emp.getName() + " → " + shift + " shift");
    }

    public void unassignShift(String employeeId, ShiftTime shift) {
        Employee emp = findEmployee(employeeId);
        boolean removed = shiftMap.get(shift).remove(emp);

        if (removed) {
            employeeShiftTracker.get(employeeId).remove(shift);
            System.out.println("[UNASSIGN] " + emp.getName() + " removed from " + shift + " shift");
        } else {
            System.out.println("[WARN]  " + emp.getName() + " was not in " + shift + " shift.");
        }
    }

    public List<Employee> getEmployeesOnShift(ShiftTime shift) {
        return Collections.unmodifiableList(shiftMap.get(shift));
    }

    public Set<ShiftTime> getShiftsForEmployee(String employeeId) {
        return Collections.unmodifiableSet(
                employeeShiftTracker.getOrDefault(employeeId, Collections.emptySet()));
    }

    public List<Employee> getUnassignedEmployees() {
        List<Employee> unassigned = new ArrayList<>();
        for (Employee emp : employeeList) {
            if (employeeShiftTracker.get(emp.getEmployeeId()).isEmpty()) {
                unassigned.add(emp);
            }
        }
        return unassigned;
    }

    public List<Employee> getByDepartment(Department department) {
        List<Employee> result = new ArrayList<>();
        for (Employee emp : employeeList) {
            if (emp.getDepartment() == department) result.add(emp);
        }
        return result;
    }

    public void printAllEmployees() {
        System.out.println("\nAll Employees (" + employeeList.size() + ")");
        employeeList.forEach(e -> System.out.println("  " + e));
    }

    public void printFullSchedule() {
        System.out.println("\nFull Shift Schedule");
        for (Map.Entry<ShiftTime, List<Employee>> entry : shiftMap.entrySet()) {
            System.out.println("  " + entry.getKey() + " (" + entry.getValue().size() + " staff):");
            if (entry.getValue().isEmpty()) {
                System.out.println("(none assigned)");
            } else {
                entry.getValue().forEach(e ->
                    System.out.println("    - " + e.getName() + " [" + e.getDepartment() + "]"));
            }
        }
    }

    public void printEmployeeShifts(String employeeId) {
        Employee emp = findEmployee(employeeId);
        Set<ShiftTime> shifts = getShiftsForEmployee(employeeId);
        System.out.println("\nShifts for " + emp.getName() + ": " + (shifts.isEmpty() ? "(none)" : shifts));
    }

    public void printUnassigned() {
        List<Employee> list = getUnassignedEmployees();
        System.out.println("\nUnassigned Employees (" + list.size() + ")");
        if (list.isEmpty()) { System.out.println("  (all employees are assigned)"); return; }
        list.forEach(e -> System.out.println("  " + e));
    }
}
