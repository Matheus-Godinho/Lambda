package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

import model.entities.Employee;

public class Program {

	public static void main(String[] args) {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		File file;
			String path;
		List<String> document = Arrays.asList("Maria,maria@gmail.com,3200.00",
				"Alex,alex@gmail.com,1900.00", "Marco,marco@gmail.com,1700.00",
				"Bob,bob@gmail.com,3500.00", "Anna,anna@gamil.com,2800.00");
			
		//path = sc.next();
		path = "c:\\temp\\in.txt";
		file = new File(path);
		try {
			try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
				for (String d: document) {
					bw.append(d);
					bw.newLine();
				}
			}
			
			List<Employee> employees;
				String name, email;
				double salary;
			String line;
			String[] fields;
			
			employees = new ArrayList<>();
			try (BufferedReader br = new BufferedReader(new FileReader(file))) {
				line = br.readLine();
				while (line != null) {
					fields = line.split(",");
					name = fields[0];
					email = fields[1];
					salary = Double.parseDouble(fields[2]);
					employees.add(new Employee(name, email, salary));
					line = br.readLine();
				}
			}
			
			double minValue;
			List<String> emails;
			
			//minValue = sc.nextDouble();
			minValue = 2000.00;
			emails = employees.stream()
					.filter(e -> e.getSalary() > minValue)
					.map(e -> e.getEmail())
					.sorted()
					.collect(Collectors.toList());
			emails.forEach(System.out::println);
			
			double salarySum;
			
			salarySum = employees.stream()
					.filter(e -> e.getName().charAt(0) == 'M')
					.map(e -> e.getSalary())
					.reduce(0.00, (s1, s2) -> s1 + s2);
			System.out.printf("%.2f", salarySum);
			
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		
		sc.close();

	}

}
