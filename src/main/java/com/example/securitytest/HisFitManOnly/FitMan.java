package com.example.securitytest.HisFitManOnly;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class FitMan {

    public static void main(String[] args) {

        int pageNo = 1;

        String HowPage = "https://hisfit.co.kr/category/look-book/133/?page=1";
        try{
            Document page = Jsoup.connect(HowPage).get();
            Elements totalNum = page.select(".last");
            String pageN = totalNum.get(0).attr("href");
            String[] result = pageN.split("=");
            pageNo = Integer.parseInt(result[1]);
            System.out.println(pageNo);
        }catch (IOException e){
            e.printStackTrace();
        }

        for (int i = 1; i <=pageNo; i++) {
            String wtUrl = "https://hisfit.co.kr/category/look-book/133/?page="+i;
            try {

                Document Musinsa = Jsoup.connect(wtUrl).get();
                //System.out.println(webtoonPage.select(".wt_viewer > img"));
                Elements imgUrl = Musinsa.select(".prdImg");
                Elements TagA = imgUrl.select("a");
                Elements imgPath = TagA.select("img");
                System.out.println(imgPath);
                Element img2 = imgPath.first();


                for (int j = 0; j < imgPath.size(); j++) {
                    System.out.println(imgPath.get(j).attr("src"));
                    String src =  "https:" + imgPath.get(j).attr("src");
                    System.out.println(src);

                    URL url = new URL(src);
                    HttpURLConnection conn =(HttpURLConnection) url.openConnection();

                    BufferedImage img = ImageIO.read(conn.getInputStream());

                    if(j%2 != 0) {
                        FileOutputStream out = new FileOutputStream("/Users/jominsu/Desktop/ManCody(HisFit)/HisFit" + i + "_" + (j + 1) + ".jpg");
                        ImageIO.write(img, "jpg", out);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
