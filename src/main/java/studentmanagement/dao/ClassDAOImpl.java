package studentmanagement.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import studentmanagement.dto.ClassDTO;
import studentmanagement.service.HibernateUtil;

@Component
@Repository
public class ClassDAOImpl implements ClassDAO {

	@Override
	public int insertClass(ClassDTO dto) {
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

	@SuppressWarnings("unchecked")
	@Override
	public List<ClassDTO> selectOne(ClassDTO dto) {
		List<ClassDTO> outputDTO = new ArrayList<ClassDTO>();
		Session session = null;
		Transaction t = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			t = session.beginTransaction();
			outputDTO = session.createQuery(
					"from ClassDTO where id=:id")
					.setParameter("id", dto.getId()).getResultList();
		} finally {
			session.close();
		}
		return outputDTO;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ClassDTO> selectAll() {
		List<ClassDTO> list = new ArrayList<ClassDTO>();
		Session session = null;
		Transaction t = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			t = session.beginTransaction();
			list = session.createQuery("from ClassDTO").getResultList();
		} finally {
			session.close();
		}
		return list;
	}
	
	
}
