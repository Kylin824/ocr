package ocr;

import java.io.File;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class Test
{
    public String test(String path)  throws Exception
    {
        String text = " ";
//        System.out.println("imgPath: ");
//        System.out.println(imgPath);
        File imageFile = new File(path);//指定识别图片
        ITesseract instance = new Tesseract();  // JNA Interface Mapping
        instance.setDatapath("D:\\Works\\ocr\\post_ocr\\src\\ocr\\tessdata");
        instance.setLanguage("chi_sim");//指定识别语种
        //System.out.println("开始识别");
        try {
            text = instance.doOCR(imageFile);
           // System.out.println(text);
            return text;
        } catch (TesseractException e) {
            System.err.println(e.getMessage());
            text = "error";
            return text;
        }
    }

    public void print() throws Exception
    {
        System.out.println("print success");
    }
}