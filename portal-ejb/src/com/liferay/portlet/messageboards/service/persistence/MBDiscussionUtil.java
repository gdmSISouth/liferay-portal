/**
 * Copyright (c) 2000-2006 Liferay, LLC. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.liferay.portlet.messageboards.service.persistence;

import com.liferay.portal.model.ModelListener;
import com.liferay.portal.spring.util.SpringUtil;
import com.liferay.portal.util.PropsUtil;

import com.liferay.util.GetterUtil;
import com.liferay.util.Validator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.context.ApplicationContext;

/**
 * <a href="MBDiscussionUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author  Brian Wing Shun Chan
 *
 */
public class MBDiscussionUtil {
	public static final String CLASS_NAME = MBDiscussionUtil.class.getName();
	public static final String LISTENER = GetterUtil.getString(PropsUtil.get(
				"value.object.listener.com.liferay.portlet.messageboards.model.MBDiscussion"));

	public static com.liferay.portlet.messageboards.model.MBDiscussion create(
		java.lang.String discussionId) {
		return getPersistence().create(discussionId);
	}

	public static com.liferay.portlet.messageboards.model.MBDiscussion remove(
		java.lang.String discussionId)
		throws com.liferay.portlet.messageboards.NoSuchDiscussionException, 
			com.liferay.portal.SystemException {
		ModelListener listener = null;

		if (Validator.isNotNull(LISTENER)) {
			try {
				listener = (ModelListener)Class.forName(LISTENER).newInstance();
			}
			catch (Exception e) {
				_log.error(e);
			}
		}

		if (listener != null) {
			listener.onBeforeRemove(findByPrimaryKey(discussionId));
		}

		com.liferay.portlet.messageboards.model.MBDiscussion mbDiscussion = getPersistence()
																				.remove(discussionId);

		if (listener != null) {
			listener.onAfterRemove(mbDiscussion);
		}

		return mbDiscussion;
	}

	public static com.liferay.portlet.messageboards.model.MBDiscussion remove(
		com.liferay.portlet.messageboards.model.MBDiscussion mbDiscussion)
		throws com.liferay.portal.SystemException {
		ModelListener listener = null;

		if (Validator.isNotNull(LISTENER)) {
			try {
				listener = (ModelListener)Class.forName(LISTENER).newInstance();
			}
			catch (Exception e) {
				_log.error(e);
			}
		}

		if (listener != null) {
			listener.onBeforeRemove(mbDiscussion);
		}

		mbDiscussion = getPersistence().remove(mbDiscussion);

		if (listener != null) {
			listener.onAfterRemove(mbDiscussion);
		}

		return mbDiscussion;
	}

	public static com.liferay.portlet.messageboards.model.MBDiscussion update(
		com.liferay.portlet.messageboards.model.MBDiscussion mbDiscussion)
		throws com.liferay.portal.SystemException {
		ModelListener listener = null;

		if (Validator.isNotNull(LISTENER)) {
			try {
				listener = (ModelListener)Class.forName(LISTENER).newInstance();
			}
			catch (Exception e) {
				_log.error(e);
			}
		}

		boolean isNew = mbDiscussion.isNew();

		if (listener != null) {
			if (isNew) {
				listener.onBeforeCreate(mbDiscussion);
			}
			else {
				listener.onBeforeUpdate(mbDiscussion);
			}
		}

		mbDiscussion = getPersistence().update(mbDiscussion);

		if (listener != null) {
			if (isNew) {
				listener.onAfterCreate(mbDiscussion);
			}
			else {
				listener.onAfterUpdate(mbDiscussion);
			}
		}

		return mbDiscussion;
	}

	public static com.liferay.portlet.messageboards.model.MBDiscussion update(
		com.liferay.portlet.messageboards.model.MBDiscussion mbDiscussion,
		boolean saveOrUpdate) throws com.liferay.portal.SystemException {
		ModelListener listener = null;

		if (Validator.isNotNull(LISTENER)) {
			try {
				listener = (ModelListener)Class.forName(LISTENER).newInstance();
			}
			catch (Exception e) {
				_log.error(e);
			}
		}

		boolean isNew = mbDiscussion.isNew();

		if (listener != null) {
			if (isNew) {
				listener.onBeforeCreate(mbDiscussion);
			}
			else {
				listener.onBeforeUpdate(mbDiscussion);
			}
		}

		mbDiscussion = getPersistence().update(mbDiscussion, saveOrUpdate);

		if (listener != null) {
			if (isNew) {
				listener.onAfterCreate(mbDiscussion);
			}
			else {
				listener.onAfterUpdate(mbDiscussion);
			}
		}

		return mbDiscussion;
	}

	public static com.liferay.portlet.messageboards.model.MBDiscussion findByPrimaryKey(
		java.lang.String discussionId)
		throws com.liferay.portlet.messageboards.NoSuchDiscussionException, 
			com.liferay.portal.SystemException {
		return getPersistence().findByPrimaryKey(discussionId);
	}

	public static com.liferay.portlet.messageboards.model.MBDiscussion fetchByPrimaryKey(
		java.lang.String discussionId)
		throws com.liferay.portal.SystemException {
		return getPersistence().fetchByPrimaryKey(discussionId);
	}

	public static com.liferay.portlet.messageboards.model.MBDiscussion findByC_C(
		java.lang.String className, java.lang.String classPK)
		throws com.liferay.portlet.messageboards.NoSuchDiscussionException, 
			com.liferay.portal.SystemException {
		return getPersistence().findByC_C(className, classPK);
	}

	public static com.liferay.portlet.messageboards.model.MBDiscussion fetchByC_C(
		java.lang.String className, java.lang.String classPK)
		throws com.liferay.portal.SystemException {
		return getPersistence().fetchByC_C(className, classPK);
	}

	public static java.util.List findAll()
		throws com.liferay.portal.SystemException {
		return getPersistence().findAll();
	}

	public static java.util.List findAll(int begin, int end)
		throws com.liferay.portal.SystemException {
		return getPersistence().findAll(begin, end);
	}

	public static java.util.List findAll(int begin, int end,
		com.liferay.util.dao.hibernate.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence().findAll(begin, end, obc);
	}

	public static void removeByC_C(java.lang.String className,
		java.lang.String classPK)
		throws com.liferay.portlet.messageboards.NoSuchDiscussionException, 
			com.liferay.portal.SystemException {
		getPersistence().removeByC_C(className, classPK);
	}

	public static int countByC_C(java.lang.String className,
		java.lang.String classPK) throws com.liferay.portal.SystemException {
		return getPersistence().countByC_C(className, classPK);
	}

	public static void initDao() {
		getPersistence().initDao();
	}

	public static MBDiscussionPersistence getPersistence() {
		ApplicationContext ctx = SpringUtil.getContext();
		MBDiscussionUtil util = (MBDiscussionUtil)ctx.getBean(CLASS_NAME);

		return util._persistence;
	}

	public void setPersistence(MBDiscussionPersistence persistence) {
		_persistence = persistence;
	}

	private static Log _log = LogFactory.getLog(MBDiscussionUtil.class);
	private MBDiscussionPersistence _persistence;
}