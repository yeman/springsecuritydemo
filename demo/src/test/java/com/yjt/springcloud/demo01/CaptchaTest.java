package com.yjt.springcloud.demo01;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.ImageUtil;
import com.octo.captcha.component.image.backgroundgenerator.*;
import com.octo.captcha.component.image.color.ColorGenerator;
import com.octo.captcha.component.image.color.RandomListColorGenerator;
import com.octo.captcha.component.image.color.RandomRangeColorGenerator;
import com.octo.captcha.component.image.fontgenerator.FontGenerator;
import com.octo.captcha.component.image.fontgenerator.RandomFontGenerator;
import com.octo.captcha.component.image.fontgenerator.TwistedAndShearedRandomFontGenerator;
import com.octo.captcha.component.image.textpaster.RandomTextPaster;
import com.octo.captcha.component.image.textpaster.TextPaster;
import com.octo.captcha.component.image.wordtoimage.ComposedWordToImage;
import com.octo.captcha.component.image.wordtoimage.WordToImage;
import com.octo.captcha.component.word.DictionaryReader;
import com.octo.captcha.component.word.FileDictionary;
import com.octo.captcha.component.word.worddecorator.SpellerWordDecorator;
import com.octo.captcha.component.word.wordgenerator.DictionaryWordGenerator;
import com.octo.captcha.component.word.wordgenerator.DummyWordGenerator;
import com.octo.captcha.component.word.wordgenerator.RandomWordGenerator;
import com.octo.captcha.component.word.wordgenerator.WordGenerator;
import org.junit.Test;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Locale;
import java.util.Random;

/**
 * @ClassName CaptchaTest
 * @Description 验证码生成器
 * @Author: YM
 * @Version V1.0
 * @Since V1.0
 * @Date: 2019-09-19 09:00
 **/
public class CaptchaTest {

    @Test
    public void testWordGenerator(){
        String keys = "3^aciICLo01i069,;:`";
        DummyWordGenerator dummyWordGenerator = new DummyWordGenerator(keys);
        int length = new Random().nextInt(keys.length());
        String randomdummy = dummyWordGenerator.getWord(length);
        System.out.println("迷惑字符-> "+randomdummy);
    }
    @Test
    public void testDictionWordGenerator(){
        DictionaryReader reader = new FileDictionary("validatecode");
        DictionaryWordGenerator dictionaryWordGenerator = new DictionaryWordGenerator(reader);
        String words = dictionaryWordGenerator.getWord(5, Locale.getDefault());
        System.out.println("字典随机->"+words);
    }

    @Test
    public void testDecorate(){
        String[] seprators = new String[] {"/~","-/%","\\^-","`?`,)"};
        String separator = seprators[new Random().nextInt(seprators.length)];
        SpellerWordDecorator spellerWordDecorator = new SpellerWordDecorator(separator);
        RandomWordGenerator randomWordGenerator = new RandomWordGenerator("1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz");
        String randomStr= randomWordGenerator.getWord(4);
        String decorateStr = spellerWordDecorator.decorateWord(randomStr);
        System.out.println("干扰后:"+decorateStr);
    }

    @Test
    public void testImage(){
        WordGenerator wordGenerator = new RandomWordGenerator("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz");
        TextPaster textPaster = new RandomTextPaster(new Integer(5), new Integer(8), Color.WHITE);
        //背景图片生成器 默认 SingleColorGenerator
        //BackgroundGenerator backgroundGenerator = new FunkyBackgroundGenerator(new Integer(200), new Integer(50));
        //背景图片生成器 带字体
        ColorGenerator colorGenerator = new RandomListColorGenerator(new Color[]{Color.RED,Color.WHITE,Color.blue});
        //
        BackgroundGenerator backgroundGenerator = new FunkyBackgroundGenerator(new Integer(200), new Integer(50),colorGenerator);
         //TODO 颜色会导致异常
        //BackgroundGenerator backgroundGenerator = new MultipleShapeBackgroundGenerator(new Integer(200), new Integer(50));
        //BackgroundGenerator backgroundGenerator = new GradientBackgroundGenerator(new Integer(200), new Integer(50),Color.WHITE,Color.BLACK);
        //BackgroundGenerator backgroundGenerator = new UniColorBackgroundGenerator(new Integer(200), new Integer(50));
        FontGenerator fontGenerator = new RandomFontGenerator(new Integer(25), new Integer(30));
        WordToImage wordToImage = new ComposedWordToImage(fontGenerator, backgroundGenerator, textPaster);
        BufferedImage image = wordToImage.getImage(wordGenerator.getWord(5));
        FileOutputStream outputStream = null;
        try {
            File f = new File("C:\\Users\\Administrator\\Pictures\\验证码.jpg");
            if(f.exists()){
                f.delete();
            }
            outputStream = new FileOutputStream(f);
            ImageUtil.writeJpg(image,outputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            if(outputStream!=null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
