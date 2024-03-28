package org.jsp.hibernatetemplatepractice.controller;

import java.util.List;
import java.util.Scanner;

import org.jsp.hibernatetemplatepractice.dao.UserDao;
import org.jsp.hibernatetemplatepractice.dto.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserController {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ApplicationContext context = new ClassPathXmlApplicationContext("spring-orm.xml");
		UserDao userDao = context.getBean("userDao", UserDao.class);
		System.out.println("1.Save User");
		System.out.println("2.Update User");
		System.out.println("3.Find User by Id");
		System.out.println("4.Delete User by Id");
		System.out.println("5.Verify User by phone and password");
		System.out.println("6.Verify User by email and password");
		System.out.println("7.Verify User by Id and password");
		System.out.println("8.Find User by Name");
		System.out.println("9.Find User by Phone");
		System.out.println("10.Find User by email");

		switch (sc.nextInt()) {
		case 1: {
			System.out.println("Enter name, phone, email, and password to save User");
			User user = new User();
			user.setName(sc.next());
			user.setPhone(sc.nextLong());
			user.setEmail(sc.next());
			user.setPassword(sc.next());
			user = userDao.saveUser(user);
			System.out.println("User saved with id: " + user.getId());
			break;
		}
		case 2: {
			System.out.println("Enter User id, name, phone, email, and password to save User");
			User user = new User();
			user.setId(sc.nextInt());
			user.setName(sc.next());
			user.setPhone(sc.nextLong());
			user.setEmail(sc.next());
			user.setPassword(sc.next());
			user = userDao.updateUser(user);
			System.out.println("User saved with id: " + user.getId());
			break;
		}
		case 3: {
			System.out.println("Enter the User Id to display details");
			User user = userDao.findById(sc.nextInt());
			if (user != null)
				System.out.println(user);
			else
				System.err.println("Invalid User Id");
			break;
		}
		case 4: {
			System.out.println("Enter User id to delete User");
			User user = new User();
			boolean deleted = userDao.delete(sc.nextInt());
			if (deleted) {
				System.out.println("User with id: " + user.getId() + " deleted sucessfully");
			} else {
				System.err.println("No User found with id: " + user.getId());
			}
			break;
		}
		case 5: {
			System.out.println("Enter phone no. and password to verify user");
			User user = userDao.verifyUser(sc.nextLong(), sc.next());
			if (user != null) {
				System.out.println("User verification sucessfull");
				System.out.println(user);
			} else {
				System.err.println("Invalid phone no. and password");
			}
			break;
		}
		case 6: {
			System.out.println("Enter email and password to verify user");
			User user = userDao.verifyUser(sc.next(), sc.next());
			if (user != null) {
				System.out.println("User verification by email sucessfull");
				System.out.println(user);
			} else {
				System.err.println("Invalid email and password");
			}
			break;
		}
		case 7: {
			System.out.println("Enter user id and password to verify user");
			User user = userDao.verifyUser(sc.nextInt(), sc.next());
			if (user != null) {
				System.out.println("User verification by id sucessfull");
				System.out.println(user);
			} else {
				System.err.println("Invalid id and password");
			}
			break;
		}
		case 8: {
			System.out.println("Enter the name to find users");
			List<User> users = userDao.findByName(sc.next());
			if (users.isEmpty())
				System.err.println("No User with this name");
			else
				for (User user : users)
					System.out.println(user);
			break;
		}
		case 9: {
			System.out.println("Enter the Phone number to find user");
			User user = userDao.findByPhone(sc.nextLong());
			if (user != null) {
				System.out.println("User Found with phoneNo: "+user.getPhone());
				System.out.println(user);
			} else
				System.err.println("Invalid Phone Number");
			break;
		}
		case 10: {
			System.out.println("Enter the Email Id to find user");
			User user = userDao.findByEmail(sc.next());
			if (user != null) {
				System.out.println("User Found with id:"+user.getEmail());
				System.out.println(user);
			} else
				System.err.println("Invalid Email Id");
			break;
		}

		default: {
			sc.close();
			((ClassPathXmlApplicationContext) context).close();
			System.err.println("Invalid Choice");
		}

		}
	}
}
