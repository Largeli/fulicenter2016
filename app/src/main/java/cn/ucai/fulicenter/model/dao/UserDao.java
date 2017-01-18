package cn.ucai.fulicenter.model.dao;

import cn.ucai.fulicenter.controller.application.FuLiCenterApplication;
import cn.ucai.fulicenter.model.bean.User;

/**
 * Created by Administrator on 2017/1/17 0017.
 */

public class UserDao {
    static final String USER_TABLE_NAME = "t_fulicenter_user";
    static final String USER_COLUMN_NAME = "m_user_name";
    static final String USER_COLUMN_NICK = "m_user_nick";
    static final String USER_COLUMN_AVATAR = "m_user_avatar_id";
    static final String USER_COLUMN_AVATAR_PATH = "m_user_avatar_path";
    static final String USER_COLUMN_ACATAR_SUFFIX = "m_user_avatar_suffix";
    static final String USER_COLUMN_ACATAR_TYPE = "m_user_avatar_type";
    static final String USER_COLUMN_ACATAR_UPDATE_TIME = "m_user_update_time";

    private static UserDao instance;
    public UserDao(){
        DBManager.onInit(FuLiCenterApplication.getInstance());
    }
    public static UserDao getInstance(){
        if (instance == null) {
            instance = new UserDao();
        }
        return instance;
    }
    public boolean savaUser(User user){
        FuLiCenterApplication.setUser(user);
        return DBManager.getInstance().saveUser(user);
    }

    public User getUser(String username) {
        return DBManager.getInstance().getUser(username);
    }
}
