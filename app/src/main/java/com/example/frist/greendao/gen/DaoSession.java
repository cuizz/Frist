package com.example.frist.greendao.gen;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.example.frist.bean.Student;
import com.example.frist.bean.Teacher;
import com.example.frist.bean.User;

import com.example.frist.greendao.gen.StudentDao;
import com.example.frist.greendao.gen.TeacherDao;
import com.example.frist.greendao.gen.UserDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig studentDaoConfig;
    private final DaoConfig teacherDaoConfig;
    private final DaoConfig userDaoConfig;

    private final StudentDao studentDao;
    private final TeacherDao teacherDao;
    private final UserDao userDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        studentDaoConfig = daoConfigMap.get(StudentDao.class).clone();
        studentDaoConfig.initIdentityScope(type);

        teacherDaoConfig = daoConfigMap.get(TeacherDao.class).clone();
        teacherDaoConfig.initIdentityScope(type);

        userDaoConfig = daoConfigMap.get(UserDao.class).clone();
        userDaoConfig.initIdentityScope(type);

        studentDao = new StudentDao(studentDaoConfig, this);
        teacherDao = new TeacherDao(teacherDaoConfig, this);
        userDao = new UserDao(userDaoConfig, this);

        registerDao(Student.class, studentDao);
        registerDao(Teacher.class, teacherDao);
        registerDao(User.class, userDao);
    }
    
    public void clear() {
        studentDaoConfig.clearIdentityScope();
        teacherDaoConfig.clearIdentityScope();
        userDaoConfig.clearIdentityScope();
    }

    public StudentDao getStudentDao() {
        return studentDao;
    }

    public TeacherDao getTeacherDao() {
        return teacherDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }

}
