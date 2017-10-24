package com.example.anonymous.greendao.gen;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.example.xiao.myappshuihu.entity.NZYData;
import com.example.xiao.myappshuihu.entity.SettingsDataSql_item1_Bean;
import com.example.xiao.myappshuihu.entity.SettingsDataSqlBean;
import com.example.xiao.myappshuihu.entity.ShangChenLiBiaoBean;
import com.example.xiao.myappshuihu.entity.ZDYData;

import com.example.anonymous.greendao.gen.NZYDataDao;
import com.example.anonymous.greendao.gen.SettingsDataSql_item1_BeanDao;
import com.example.anonymous.greendao.gen.SettingsDataSqlBeanDao;
import com.example.anonymous.greendao.gen.ShangChenLiBiaoBeanDao;
import com.example.anonymous.greendao.gen.ZDYDataDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig nZYDataDaoConfig;
    private final DaoConfig settingsDataSql_item1_BeanDaoConfig;
    private final DaoConfig settingsDataSqlBeanDaoConfig;
    private final DaoConfig shangChenLiBiaoBeanDaoConfig;
    private final DaoConfig zDYDataDaoConfig;

    private final NZYDataDao nZYDataDao;
    private final SettingsDataSql_item1_BeanDao settingsDataSql_item1_BeanDao;
    private final SettingsDataSqlBeanDao settingsDataSqlBeanDao;
    private final ShangChenLiBiaoBeanDao shangChenLiBiaoBeanDao;
    private final ZDYDataDao zDYDataDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        nZYDataDaoConfig = daoConfigMap.get(NZYDataDao.class).clone();
        nZYDataDaoConfig.initIdentityScope(type);

        settingsDataSql_item1_BeanDaoConfig = daoConfigMap.get(SettingsDataSql_item1_BeanDao.class).clone();
        settingsDataSql_item1_BeanDaoConfig.initIdentityScope(type);

        settingsDataSqlBeanDaoConfig = daoConfigMap.get(SettingsDataSqlBeanDao.class).clone();
        settingsDataSqlBeanDaoConfig.initIdentityScope(type);

        shangChenLiBiaoBeanDaoConfig = daoConfigMap.get(ShangChenLiBiaoBeanDao.class).clone();
        shangChenLiBiaoBeanDaoConfig.initIdentityScope(type);

        zDYDataDaoConfig = daoConfigMap.get(ZDYDataDao.class).clone();
        zDYDataDaoConfig.initIdentityScope(type);

        nZYDataDao = new NZYDataDao(nZYDataDaoConfig, this);
        settingsDataSql_item1_BeanDao = new SettingsDataSql_item1_BeanDao(settingsDataSql_item1_BeanDaoConfig, this);
        settingsDataSqlBeanDao = new SettingsDataSqlBeanDao(settingsDataSqlBeanDaoConfig, this);
        shangChenLiBiaoBeanDao = new ShangChenLiBiaoBeanDao(shangChenLiBiaoBeanDaoConfig, this);
        zDYDataDao = new ZDYDataDao(zDYDataDaoConfig, this);

        registerDao(NZYData.class, nZYDataDao);
        registerDao(SettingsDataSql_item1_Bean.class, settingsDataSql_item1_BeanDao);
        registerDao(SettingsDataSqlBean.class, settingsDataSqlBeanDao);
        registerDao(ShangChenLiBiaoBean.class, shangChenLiBiaoBeanDao);
        registerDao(ZDYData.class, zDYDataDao);
    }
    
    public void clear() {
        nZYDataDaoConfig.clearIdentityScope();
        settingsDataSql_item1_BeanDaoConfig.clearIdentityScope();
        settingsDataSqlBeanDaoConfig.clearIdentityScope();
        shangChenLiBiaoBeanDaoConfig.clearIdentityScope();
        zDYDataDaoConfig.clearIdentityScope();
    }

    public NZYDataDao getNZYDataDao() {
        return nZYDataDao;
    }

    public SettingsDataSql_item1_BeanDao getSettingsDataSql_item1_BeanDao() {
        return settingsDataSql_item1_BeanDao;
    }

    public SettingsDataSqlBeanDao getSettingsDataSqlBeanDao() {
        return settingsDataSqlBeanDao;
    }

    public ShangChenLiBiaoBeanDao getShangChenLiBiaoBeanDao() {
        return shangChenLiBiaoBeanDao;
    }

    public ZDYDataDao getZDYDataDao() {
        return zDYDataDao;
    }

}
