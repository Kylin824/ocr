package login;

import dao.UserDao;

import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;

public class LoginAction extends ActionSupport
{

    private String username;
    private String password;

    private UserDao user;



    public String login()
    {
        try{
            user=new UserDao();
            boolean result = user.check(username,password);
            if(result)
            {
                //System.out.println("登陆成功");
                return SUCCESS;
            }
            else
            {
                //System.out.println("登陆失败");
                return ERROR;
            }
        }catch (Exception e){
            //System.out.println("发生异常");
            return ERROR;
        }
    }

    public String regist()
    {
        try{
            user=new UserDao();
            ArrayList result=user.up_select(username);
            if(result.size()>0)
            {
                //System.out.println("该用户已经存在");
                return ERROR;
            }
            else
            {
                if(user.insert(username,password))
                {
                    //System.out.println("注册成功");
                    return SUCCESS;
                }
                else
                {
                    //System.out.println("发生未知错误，请重试！");
                    return ERROR;
                }

            }
        }catch (Exception e){
            return ERROR;
        }
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }


}
