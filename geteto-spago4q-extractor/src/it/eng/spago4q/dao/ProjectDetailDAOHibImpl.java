package it.eng.spago4q.dao;

import it.eng.spago.error.EMFUserError;
import it.eng.spago4q.bo.ProjectDetail;
import it.eng.spago4q.metadata.QpsProjectDetail;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Expression;
import org.hibernate.exception.ConstraintViolationException;

public class ProjectDetailDAOHibImpl extends AbstractHibernateDAO implements
		IES4QDAO {

	private static final Logger LOG = Logger
			.getLogger(ProjectDetailDAOHibImpl.class);

	//@Override
	public boolean deleteObject(Integer id) throws EMFUserError {
		Session aSession = getCurrentSession();
		Transaction tx = null;
		try {
			tx = aSession.beginTransaction();
			QpsProjectDetail qpsProjectDetail = (QpsProjectDetail) aSession.load(
					QpsProjectDetail.class, id);
			aSession.delete(qpsProjectDetail);
			tx.commit();

		} catch (ConstraintViolationException cve) {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			LOG.error("Impossible to delete a ProjectDetail ", cve);
			// throw new EMFUserError(EMFErrorSeverity.WARNING, 10021);
		} catch (HibernateException e) {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			LOG.error("Error while delete a ProjectDetail ", e);
			// throw new EMFUserError(EMFErrorSeverity.ERROR, 10012);
		} finally {
			aSession.close();
		}
		return true;
	}

	//@Override
	public Integer insertObject(Object bo) throws EMFUserError {
		LOG.debug("IN");
		Integer idToReturn = null;
		Session aSession = null;
		Transaction tx = null;
		try {
			aSession = getCurrentSession();
			tx = aSession.beginTransaction();

			ProjectDetail projectDetail = (ProjectDetail) bo;

			QpsProjectDetail qpsProjectDetail = new QpsProjectDetail(
					projectDetail.getPrjDetailId(), projectDetail.getCode(),
					projectDetail.getDetail());

			idToReturn = (Integer) aSession.save(qpsProjectDetail);

			tx.commit();

		} catch (HibernateException he) {
			logException(he);
			if (tx != null) {
				tx.rollback();
			}
			// throw new EMFUserError(EMFErrorSeverity.ERROR, 10030);

		} finally {
			if (aSession != null && aSession.isOpen()) {
				aSession.close();
			}
		}
		LOG.debug("OUT");
		return idToReturn;
	}

	//@Override
	public Object loadObjectById(Integer id) throws EMFUserError {
		LOG.debug("IN");
		ProjectDetail toReturn = null;
		Session aSession = null;
		Transaction tx = null;
		try {
			aSession = getCurrentSession();
			tx = aSession.beginTransaction();
			QpsProjectDetail hibProjectDetail = (QpsProjectDetail) aSession
					.load(QpsProjectDetail.class, id);
			toReturn = new ProjectDetail(hibProjectDetail.getPrjDetailId(),
					hibProjectDetail.getCode(), hibProjectDetail.getDetail());
		} catch (HibernateException he) {
			LOG.error("Error while loading the S4qEProcess with id "
					+ ((id == null) ? "" : id.toString()), he);

			if (tx != null) {
				tx.rollback();
			}

			// throw new EMFUserError(EMFErrorSeverity.ERROR, 101);

		} finally {
			if (aSession != null && aSession.isOpen()) {
				aSession.close();
			}
			LOG.debug("OUT");
		}
		return toReturn;

	}

	//@Override
	public List loadObjectList(String fieldOrder, String typeOrder)
			throws EMFUserError {
		LOG.debug("IN");
		List<ProjectDetail> toReturn = null;
		Session aSession = null;
		Transaction tx = null;
		try {
			aSession = getCurrentSession();
			tx = aSession.beginTransaction();
			toReturn = new ArrayList<ProjectDetail>();
			List<QpsProjectDetail> toTransform = null;

			Criteria crit = aSession.createCriteria(QpsProjectDetail.class);
			//
			// if (fieldOrder != null && typeOrder != null) {
			// if (typeOrder.toUpperCase().trim().equals("ASC")) {
			// crit.addOrder(Order.asc(getProcessProperty(fieldOrder)));
			// }
			// if (typeOrder.toUpperCase().trim().equals("DESC")) {
			// crit.addOrder(Order.desc(getProcessProperty(fieldOrder)));
			// }
			// }
			toTransform = crit.list();

			for (Iterator<QpsProjectDetail> iterator = toTransform.iterator(); iterator
					.hasNext();) {
				QpsProjectDetail qpsProjectDetail = iterator.next();

				ProjectDetail aProjectDetail = new ProjectDetail(
						qpsProjectDetail.getPrjDetailId(), qpsProjectDetail
								.getCode(), qpsProjectDetail.getDetail());

				toReturn.add(aProjectDetail);
			}

		} catch (HibernateException he) {
			LOG.error("Error while loading the list of ProjectDetail", he);

			if (tx != null) {
				tx.rollback();
			}
			// throw new EMFUserError(EMFErrorSeverity.ERROR, 10031);

		} finally {
			if (aSession != null && aSession.isOpen()) {
				aSession.close();
			}
			LOG.debug("OUT");
		}
		return toReturn;
	}

	//@Override
	public void modifyObject(Object bo) throws EMFUserError {
		LOG.debug("IN");
		Session aSession = null;
		Transaction tx = null;
		try {
			aSession = getCurrentSession();
			tx = aSession.beginTransaction();
			ProjectDetail projectDetail = (ProjectDetail) bo;

			QpsProjectDetail qpsProjectDetail = (QpsProjectDetail) aSession
					.load(QpsProjectDetail.class, projectDetail
							.getPrjDetailId());

			qpsProjectDetail.setCode(projectDetail.getCode());
			qpsProjectDetail.setDetail(projectDetail.getDetail());

			aSession.saveOrUpdate(qpsProjectDetail);

			tx.commit();

		} catch (HibernateException he) {
			logException(he);

			if (tx != null) {
				tx.rollback();
			}

			// throw new EMFUserError(EMFErrorSeverity.ERROR, 10032);

		} finally {
			if (aSession != null && aSession.isOpen()) {
				aSession.close();
			}
		}
		LOG.debug("OUT");

	}

	public ProjectDetail loadObjectByCode(String projectCode) throws EMFUserError {
		LOG.debug("IN");
		QpsProjectDetail toTransform = null;
		ProjectDetail toReturn = null;
		Session aSession = null;
		Transaction tx = null;
		try {
			aSession = getCurrentSession();
			tx = aSession.beginTransaction();

			Criteria crit = aSession.createCriteria(QpsProjectDetail.class);
			crit.add(Expression.eq("code", projectCode));

			toTransform = (QpsProjectDetail) crit.uniqueResult();
			if(toTransform != null){
				toReturn = new ProjectDetail(toTransform.getPrjDetailId(),
						toTransform.getCode(), toTransform.getDetail());
			}
		} catch (HibernateException he) {
			LOG.error("Error while loading the ProjectDetail with code "
					+ ((projectCode == null) ? "" : projectCode), he);

			if (tx != null) {
				tx.rollback();
			}

			// throw new EMFUserError(EMFErrorSeverity.ERROR, 101);

		} finally {
			if (aSession != null && aSession.isOpen()) {
				aSession.close();
			}
			LOG.debug("OUT");
		}
		return toReturn;
	}
}
