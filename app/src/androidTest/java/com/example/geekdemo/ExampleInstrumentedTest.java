package com.example.geekdemo;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.example.geekdemo", appContext.getPackageName());
    }


    @Test
    public void parseHtml() {
        try {
            Document document = Jsoup.connect("https://www.v2ex.com/?tab=tech").timeout(10000).get();


            Elements itemElements = document.select("div.cell.item");    //item根节点
            int count = itemElements.size();
            for (int i = 0; i < count; i++) {
                Elements titleElements = itemElements.get(i).select("div.cell.item table tr td span.item_title > a");   //标题
                Elements imgElements = itemElements.get(i).select("div.cell.item table tr td img.avatar");              //头像
                Elements nodeElements = itemElements.get(i).select("div.cell.item table tr span.small.fade a.node");    //节点
                Elements commentElements = itemElements.get(i).select("div.cell.item table tr a.count_livid");          //评论数
                Elements nameElements = itemElements.get(i).select("div.cell.item table tr span.small.fade strong a");  //作者 & 最后回复
                Elements timeElements = itemElements.get(i).select("div.cell.item table tr span.small.fade");           //更新时间


            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
