package by.bsuir.jaxb.test;

import by.bsuir.jaxb.model.Department;
import by.bsuir.jaxb.model.Employee;
import by.bsuir.jaxb.model.Organization;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class TestExample {
    private static final String XML_FILE = "src/main/resources/org-info.xml";
    public static void main(String[] args) {

        Employee emp1 = new Employee("E01", "Tom", null);
        Employee emp2 = new Employee("E02", "Mary", "E01");
        Employee emp3 = new Employee("E03", "John", null);

        List<Employee> list = new ArrayList<Employee>();
        list.add(emp1);
        list.add(emp2);
        list.add(emp3);

        Department dept1 = new Department("D01", "ACCOUNTING", "NEW YORK");
        dept1.setEmployees(list);

        Employee emp4 = new Employee("E04", "Alice", null);
        Employee emp5 = new Employee("E05", "Bob", "E04");
        Employee emp6 = new Employee("E06", "Charlie", null);

        List<Employee> list2 = new ArrayList<Employee>();
        list2.add(emp4);
        list2.add(emp5);
        list2.add(emp6);

        Department dept2 = new Department("D02", "SALES", "CHICAGO");
        dept2.setEmployees(list2);

        List<Department> list3 = new ArrayList<Department>();
        list3.add(dept1);
        list3.add(dept2);

        Organization org = new Organization(list3);

        try {
            JAXBContext context = JAXBContext.newInstance(by.bsuir.jaxb.model.Organization.class);

            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            m.marshal(org, System.out);

            File outFile = new File(XML_FILE);
            m.marshal(org, outFile);

            System.err.println("Write to file: " + outFile.getAbsolutePath());
            Unmarshaller um = context.createUnmarshaller();


            Organization orgFromFile1 = (Organization) um.unmarshal(new FileReader(
                    XML_FILE));
            List<Department> depts = orgFromFile1.getDepartments();
            for (Department dept : depts) {
                System.out.println("Department: " + dept.getDeptName());
                List<Employee> emps = dept.getEmployees();
                for (Employee emp : emps) {
                    System.out.println("Employee: " + emp.getEmpName());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
