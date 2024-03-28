package org.jsp.hibernatetemplatepractice.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.jsp.hibernatetemplatepractice.dto.User;
import org.springframework.orm.hibernate5.HibernateTemplate;

public class UserDao {
	private HibernateTemplate hibernateTemplate;

	@Transactional
	public User saveUser(User user) {
		hibernateTemplate.save(user);
		return user;
	}

	@Transactional
	public User updateUser(User user) {
		User dbUser = hibernateTemplate.get(User.class, user.getId());
		if (dbUser != null) {
			dbUser.setEmail(user.getEmail());
			dbUser.setName(user.getName());
			dbUser.setPhone(user.getPhone());
			dbUser.setPassword(user.getPassword());
			hibernateTemplate.update(dbUser);
			return user;
		}
		return null;
	}

	public User findById(int id) {
		return hibernateTemplate.get(User.class, id);
	}

	@Transactional
	public boolean delete(int id) {
		User user = findById(id);
		if (user != null) {
			hibernateTemplate.delete(user);
			return true;
		}
		return false;
	}

	@SuppressWarnings(value = { "deprecation", "unchecked" })
	public User verifyUser(long phone, String password) {
		List<User> users = (List<User>) hibernateTemplate
				.find("select u from User u where u.phone=?0 and u.password=?1", phone, password);
		if (users.isEmpty()) {
			return null;
		}
		return users.get(0);
	}

	@SuppressWarnings(value = { "deprecation", "unchecked" })
	public User verifyUser(String email, String password) {
		List<User> users = (List<User>) hibernateTemplate
//				.findByNamedParam("select u from User u where u.email=:em and u.password=:ps", new String[] { "em", "ps" },
//						new Object[] { email, password });
				.find("select u from User u where u.email=?0 and u.password=?1", email, password);

		if (users.isEmpty()) {
			return null;
		}
		return users.get(0);
	}

	@SuppressWarnings(value = { "deprecation", "unchecked" })
	public User verifyUser(int id, String password) {
		List<User> users = (List<User>) hibernateTemplate.findByNamedParam(
				"select u from User u where u.id=:id and u.password=:ps", new String[] { "id", "ps" },
				new Object[] { id, password });
		if (users.isEmpty())
			return null;
		return users.get(0);
	}

	@SuppressWarnings(value = { "deprecation", "unchecked" })
	public User findByEmail(String email) {
		List<User> users = (List<User>) hibernateTemplate.findByNamedParam("select u from User u where u.email=:em",
				new String[] { "em" }, new Object[] { email });
		if (users.isEmpty()) {
			return null;
		}
		return users.get(0);
	}

	@SuppressWarnings(value = { "deprecation", "unchecked" })
	public List<User> findByName(String name) {
		return (List<User>) hibernateTemplate.findByNamedParam("select u from User u where u.name=:nm",
				new String[] { "nm" }, new Object[] { name });
	}

	@SuppressWarnings(value = { "deprecation", "unchecked" })
	public User findByPhone(long phone) {
		List<User> users = (List<User>) hibernateTemplate.findByNamedParam("select u from User u where u.phone=:ph",
				new String[] { "ph" }, new Object[] { phone });
		if (users.isEmpty()) {
			return null;
		}
		return users.get(0);
	}

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

}
