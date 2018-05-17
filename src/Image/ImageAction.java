package Image;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import dao.ImgDao;
import org.apache.commons.io.FileUtils;
import java.io.*;
import java.util.Date;
import ocr.Test;

public class ImageAction extends ActionSupport
{
    private File myFile;
    private String myFileContentType;
    private String myFileFileName;
    private String destPath;
    private String ImgUname;
    private String suffix;
    private ImgDao imgs;
    private String ab_imgs;
    private String username;
    private String date;
    private String imgId;
    private String imgText;

    public String execute()
    {
        return ERROR;
    }

    public String Upload()
    {
        /* Copy file to a safe location */
        //destPath = "E:\\apache-tomcat-9.0.6\\work\\ocrImgs\\";

        destPath = System.getProperty("catalina.home") + "\\work\\ocrImgs";
        //destPath = System.getProperty("catalina.home") + "/work/ocrImgs";

        //System.getProperty("catalina.home")获取tomcat的目录
        try
        {
            //System.out.println("Dst File name: " + myFileFileName);

            //System.out.println("FileContentType: " + myFileContentType);
            //System.out.println("From user: " + username);
            //System.out.println(System.getProperty("catalina.home"));
            //System.out.println(username);
            //System.out.println(myFileFileName);

            suffix = myFileFileName.substring(myFileFileName.lastIndexOf('.') + 1);//图片后缀
            //System.out.println(suffix);

            date = Long.toString(new Date().getTime());//时间戳

            ImgUname = username + '_' + date + "." + suffix;

            File destFile  = new File(destPath, ImgUname);
            FileUtils.copyFile(myFile, destFile);
            //System.out.println("图片保存本地成功");
            //return SUCCESS;
        }
        catch(IOException e)
        {
            e.printStackTrace();
            //System.out.println("图片保存本地异常");
            return ERROR;
        }

        try {
            imgs = new ImgDao();
            //save img to mysql
            if(imgs.insert(username,date,ImgUname))
            {
                imgId = imgs.getInsert_id();
                //System.out.println("插入图片成功");
                //System.out.println("图片id: " + imgId);
//
//                ActionContext act = ActionContext.getContext();
//                act.put("id", imgId);
//                act.put("username", username);
//                act.put("date", date);

                //return SUCCESS;
            }
            else
            {
                //System.out.println("插入图片失败，请重试！");
                return ERROR;
            }
        }catch (Exception e){
            //System.out.println("图片存入sql异常");
            e.printStackTrace();
            return ERROR;
        }

        try {
            //System.out.println("开始识别");
            //long startTime=System.currentTimeMillis();   //获取开始时间
            ab_imgs = destPath + '\\' + ImgUname;
            //ab_imgs = destPath + '/' + ImgUname;
            Test t = new Test();
            imgText = t.test(ab_imgs);
            //System.out.println(imgText);
            //long endTime=System.currentTimeMillis(); //获取结束时间
            //System.out.println("识别结束");
            //System.out.println("耗时: " + (endTime - startTime) + "ms");
            ActionContext act = ActionContext.getContext();
            act.put("id", imgId);
            act.put("username", username);
            act.put("date", date);
            act.put("text", imgText);
        }
        catch (Exception e){
            //System.out.println("图片识别异常");
            e.printStackTrace();
            return ERROR;
        }
        String txtpath = "E:\\apache-tomcat-9.0.6\\work\\ocrTexts\\" + username + "_" + date +".txt";
        //String txtpath = System.getProperty("catalina.home") + "/work/ocrTexts/" + username + "_" + date + ".txt";
        try (FileWriter fw = new FileWriter(txtpath)){
            fw.write(imgText);
            return SUCCESS;
        }
        catch (IOException ioe)
        {
            ioe.printStackTrace();
            return ERROR;
        }
    }

    public String Synchro()
    {
        System.out.println("Synchro action");
        return ERROR;
    }

    /*public String Update()
    {

    }*/

    public File getMyFile() {
        return myFile;
    }

    public void setMyFile(File myFile) {
        this.myFile = myFile;
    }

    public String getMyFileContentType() {
        return myFileContentType;
    }

    public void setMyFileContentType(String myFileContentType) {
        this.myFileContentType = myFileContentType;
    }

    public String getMyFileFileName() {
        return myFileFileName;
    }

    public void setMyFileFileName(String myFileFileName) {
        this.myFileFileName = myFileFileName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getImgId() {
        return imgId;
    }

    public void setImgId(String imgId) {
        this.imgId = imgId;
    }

    public String getImgText() {
        return imgText;
    }

    public void setImgText(String imgText) {
        this.imgText = imgText;
    }

}
