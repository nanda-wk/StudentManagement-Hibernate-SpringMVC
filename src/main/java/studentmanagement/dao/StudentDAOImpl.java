package studentmanagement.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import studentmanagement.dto.StudentDTO;
import studentmanagement.service.HibernateUtil;

@Component
@Repository
public class StudentDAOImpl implements StudentDAO {

	@Override
	public int insertStudent(StudentDTO dto) {
		int i = 0;
		Session session = null;
		Transaction t = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			t = session.beginTransaction();
			session.persist(dto);
			t.commit();
			i=1;
		}finally {
			session.close();
		}
		return i;
	}

	@Override
	public int updateStudent(StudentDTO dto) {
		int i = 0;
		Session session = null;
		Transaction t = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			t = session.beginTransaction();
			session.update(dto);
			t.commit();
			i=1;
		}finally {
			session.close();
		}
		return i;
	}

	@Override
	public int deleteStudent(StudentDTO dto) {
		int i = 0;
		Session session = null;
		Transaction t = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			t = session.beginTransaction();
			session.delete(dto);
			t.commit();
			i=1;
		}finally {
			session.close();
		}
		return i;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<StudentDTO> selectOne(StudentDTO dto) {
		List<StudentDTO> outputDTO = new ArrayList<StudentDTO>();
		Session session = null;
		Transaction t = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			t = session.beginTransaction();
			outputDTO = session.createQuery(
					"from StudentDTO where studentId=:studentId or studentName=:studentName or className=:className")
					.setParameter("studentId", dto.getStudentId())
					.setParameter("studentName", dto.getStudentName())
					.setParameter("className", dto.getClassName()).getResultList();
		} finally {
			session.close();
		}
		return outputDTO;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<StudentDTO> selectAll() {
		List<StudentDTO> lstUser = new ArrayList<StudentDTO>();
		Session session = null;
		Transaction t = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			t = session.beginTransaction();
			lstUser = session.createQuery("from StudentDTO").getResultList();
		} finally {
			session.close();
		}
		return lstUser;
	}

	
}
