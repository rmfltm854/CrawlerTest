package com.example.securitytest.Musinsa;

import java.io.FileOutputStream;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;


import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.HttpURLConnection;

import javax.imageio.ImageIO;

public class CrollerMan {

    public static void main(String[] args) {

        int pageNo = 0;

        String HowPage = "https://www.musinsa.com/search/musinsa/coordi?q=%EB%82%A8%EC%9E%90+%EB%B4%84+%EC%BD%94%EB%94%94&list_kind=small&sortCode=term_date&sub_sort=&page=1&display_cnt=0&saleGoods=&includeSoldOut=&setupGoods=&popular=&category1DepthCode=&category2DepthCodes=&category3DepthCodes=&selectedFilters=&category1DepthName=&category2DepthName=&brandIds=&price=&colorCodes=&contentType=&styleTypes=&includeKeywords=&excludeKeywords=&originalYn=N&tags=&campaignId=&serviceType=&eventType=&type=&season=&measure=&openFilterLayout=N&selectedOrderMeasure=&shoeSizeOption=&groupSale=&d_cat_cd=&attribute=";
        try{
            Document page = Jsoup.connect(HowPage).get();
            Elements totalNum = page.select(".totalPagingNum");
            pageNo = Integer.parseInt(totalNum.get(0).text());
        }catch (IOException e){
            e.printStackTrace();
        }

        for (int i = 1; i <=pageNo; i++) {
            String wtUrl = "https://www.musinsa.com/search/musinsa/coordi?q=%EB%82%A8%EC%9E%90+%EB%B4%84+%EC%BD%94%EB%94%94&list_kind=small&sortCode=term_date&sub_sort=&page=1&display_cnt=0&saleGoods=&includeSoldOut=&setupGoods=&popular=&category1DepthCode=&category2DepthCodes=&category3DepthCodes=&selectedFilters=&category1DepthName=&category2DepthName=&brandIds=&price=&colorCodes=&contentType=&styleTypes=&includeKeywords=&excludeKeywords=&originalYn=N&tags=&campaignId=&serviceType=&eventType=&type=&season=&measure=&openFilterLayout=N&selectedOrderMeasure=&shoeSizeOption=&groupSale=&d_cat_cd=&attribute=";
            try {
                System.out.println(wtUrl);
                Document Musinsa = Jsoup.connect(wtUrl).get();
                //System.out.println(webtoonPage.select(".wt_viewer > img"));
                Elements imgUrl = Musinsa.select(".style-list-thumbnail > img");
//                Elements totalNum = Musinsa.select(".totalPagingNum");
//                pageNo = Integer.parseInt(totalNum.get(0).text());
                System.out.println(i);
//                System.out.println(totalNum.attr(".totalPagingNum"));

                for (int j = 0; j < imgUrl.size(); j++) {
                    System.out.println(imgUrl.get(j).attr("data-original"));
                    String src =  imgUrl.get(j).attr("data-original");

                    URL url = new URL(src);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                    conn.setRequestProperty("Referer", src);
                    BufferedImage img = ImageIO.read(conn.getInputStream());

                    FileOutputStream out = new FileOutputStream("/Users/jominsu/Desktop/ManCody(Munsinsa)/MusinSaM"+pageNo+"_"+(j + 1) + ".jpg");
                    ImageIO.write(img, "jpg", out);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}