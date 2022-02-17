package studentmanagement.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import studentmanagement.dto.UserDTO;
import studentmanagement.service.HibernateUtil;

@Component
@Repository
public class UserDAOImpl implements UserDAO {

	@Override
	public int insertUser(UserDTO dto) {
		int result = 0;
		Session session = null;
		Transaction t = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			t = session.beginTransaction();
			session.persist(dto);
			t.commit();
			result=1;
		}finally {
			session.close();
		}
		return result;
	}

	@Override
	public int updateUser(UserDTO dto) {
		int result = 0;
		Session session = null;
		Transaction t = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			t = session.beginTransaction();
			session.update(dto);
			t.commit();
			result=1;
		}finally {
			session.close();
		}
		return result;
	}

	@Override
	public int deleteUser(UserDTO dto) {
		int result = 0;
		Session session = null;
		Transaction t = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			t = session.beginTransaction();
			session.delete(dto);
			t.commit();
			result=1;
		}finally {
			session.close();
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserDTO> selectOne(UserDTO dto) {
		List<UserDTO> outputDTO = new ArrayList<UserDTO>();
		Session session = null;
		Transaction t = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			t = session.beginTransaction();
			outputDTO = session.createQuery(
					"from UserDTO where id=:id or name=:name")
					.setParameter("id", dto.getId())
					.setParameter("name", dto.getName()).getResultList();
		} finally {
			session.close();
		}
		return outputDTO;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserDTO> selectAll() {
		List<UserDTO> lstUser = new ArrayList<UserDTO>();
		Session session = null;
		Transaction t = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			t = session.beginTransaction();
			lstUser = session.createQuery("from UserDTO").getResultList();
		} finally {
			session.close();
		}
		return lstUser;
	}

	
}
