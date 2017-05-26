package com.example.frist.greendao.gen;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.example.frist.bean.Photo;
import com.example.frist.bean.Student;
import com.example.frist.bean.Teacher;
import com.example.frist.bean.User;
import com.example.frist.bean.Users;

import com.example.frist.greendao.gen.PhotoDao;
import com.example.frist.greendao.gen.StudentDao;
import com.example.frist.greendao.gen.TeacherDao;
import com.example.frist.greendao.gen.UserDao;
import com.example.frist.greendao.gen.UsersDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig photoDaoConfig;
    private final DaoConfig studentDaoConfig;
    private final DaoConfig teacherDaoConfig;
    private final DaoConfig userDaoConfig;
    private final DaoConfig usersDaoConfig;

    private final PhotoDao photoDao;
    private final StudentDao studentDao;
    private final TeacherDao teacherDao;
    private final UserDao userDao;
    private final UsersDao usersDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        photoDaoConfig = daoConfigMap.get(PhotoDao.class).clone();
        photoDaoConfig.initIdentityScope(type);

        studentDaoConfig = daoConfigMap.get(StudentDao.class).clone();
        studentDaoConfig.initIdentityScope(type);

        teacherDaoConfig = daoConfigMap.get(TeacherDao.class).clone();
        teacherDaoConfig.initIdentityScope(type);

        userDaoConfig = daoConfigMap.get(UserDao.class).clone();
        userDaoConfig.initIdentityScope(type);

        usersDaoConfig = daoConfigMap.get(UsersDao.class).clone();
        usersDaoConfig.initIdentityScope(type);

        photoDao = new PhotoDao(photoDaoConfig, this);
        studentDao = new StudentDao(studentDaoConfig, this);
        teacherDao = new TeacherDao(teacherDaoConfig, this);
        userDao = new UserDao(userDaoConfig, this);
        usersDao = new UsersDao(usersDaoConfig, this);

        registerDao(Photo.class, photoDao);
        registerDao(Student.class, studentDao);
        registerDao(Teacher.class, teacherDao);
        registerDao(User.class, userDao);
        registerDao(Users.class, usersDao);
    }
    
    public void clear() {
        photoDaoConfig.clearIdentityScope();
        studentDaoConfig.clearIdentityScope();
        teacherDaoConfig.clearIdentityScope();
        userDaoConfig.clearIdentityScope();
        usersDaoConfig.clearIdentityScope();
    }

    public PhotoDao getPhotoDao() {
        return photoDao;
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

    public UsersDao getUsersDao() {
        return usersDao;
    }

}
