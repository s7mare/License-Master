package top.gzii.license.common.context;

import top.gzii.license.model.UserInfo;

public class LoginUserHolder {
    private static final ThreadLocal<UserInfo> holder=new ThreadLocal<>();

    public static UserInfo get(){
        return holder.get();
    }

    public static void set(UserInfo userInfo){
        holder.set(userInfo);
    }

    public static void remove(){
        holder.remove();
    }

}
