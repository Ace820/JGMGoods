package com.zhangche;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStreamReader;
import java.security.MessageDigest;

public class Main {
    private static Item[] finalItems = {
            new Item(1,"强国煤业","670 1760","250 750","fadc6cdd5eb4b3de459165a01c370dde",1),
            new Item(2,"强国煤业","820 1675","250 750","8f17a25f5b858b7aca4b2133cb1e44e4",1),
            new Item(3,"强国煤业","975 1600","250 750","284e03e751b61917812673b0f860c70e",1),
            new Item(4,"企鹅机械","670 1760","480 620","3503faa47f7214f52043b192cb012d3c",1),
            new Item(5,"企鹅机械","820 1675","480 620","9dfeaed0ad31d8a84a1b3b3b39b2ce9d",1),
            new Item(6,"企鹅机械","975 1600","480 620","a2d1000af8c004aba29c01909f043a3a",1),
            new Item(7,"民食斋","670 1760","300 1050","b940c84c15d89828b864ba2619473239",1),
            new Item(8,"民食斋","820 1675","300 1050","974e68c82f8f93a07c98dd2197092c41",1),
            new Item(9,"民食斋","975 1600","300 1050","3e8fc56f4757bacc15b30e0b17c383a9",1),
            new Item(10,"复兴公馆","670 1760","250 1300","d4d4a08070d09a1107935b15e4065976",1),
            new Item(11,"复兴公馆","820 1675","250 1300","",1),
            new Item(12,"复兴公馆","975 1600","250 1300","",1),
    };
    private Item[] rareItems = {
            new Item(1,"零件厂","670 1760","830 460","091e9f9e23d1b6de5ef47c49df6a9f9f",1),
            new Item(2,"零件厂","820 1675","830 460","5c40b9fcab7949d54be8ac6763f58ddf",1),
            new Item(3,"零件厂","975 1600","830 460","b81ee203989ea0e68487a67c8debb6aa",1),
            new Item(4,"商贸中心","670 1760","550 900","72fd37c45fef11c52a8a176e84dfed15",1),
            new Item(5,"商贸中心","820 1675","550 900","0c8e46237d215c52376c1b3dad439735",1),
            new Item(6,"商贸中心","975 1600","550 900","3038c0bf85440eafcd5af84f4b4af827",1),
            new Item(7,"追梦快递","670 1760","850 800","bc0805fc315c84c165169be26ebde3c7",1),
            new Item(8,"追梦快递","820 1675","850 800","6f4c2485ae1682e7bf14b1e17ac55ea3",1),
            new Item(9,"追梦快递","975 1600","850 800","c9c68c12ba1d61a41551e84d10e886c1",1),
            new Item(10,"花园洋房","670 1760","550 1200","10d6aef420f612aa57dfc6821b87f1cf",1),
            new Item(11,"花园洋房","820 1675","550 1200","561695226f5c810411ca73564ef68a41",1),
            new Item(12,"花园洋房","975 1600","550 1200","",1),
    };
    private static String[] emptyMd5 = {
            "011251b4b68cc4da6a310f014fd1eada",
            "db1b4f9f0e91b0ac5dc7958a26ea9fe0",
            "9e1c2a8cadf11db83fdeaadb9e671658",
    };
    private static final String trainArrived = "8c7b5e3f9540bde5d3e28dfb9967a713";
    private static final String trainWaiting = "6d4eae78b80354d8ce644712d3bbc0ef";
    private static String statusMd5 = null;
    private static String item1Md5 = null;
    private static String item2Md5 = null;
    private static String item3Md5 = null;
    protected static class Item {
        public int id;
        public String name = null;
        public String trainPos = null;
        public String buildPos = null;
        public String md5 = null;
        public int action; //0:跳过 1:收货 2:退出
        public Item(int id, String name, String trainPos, String buildPos, String md5, int action) {
            this.id = id;
            this.name = name;
            this.trainPos = trainPos;
            this.buildPos = buildPos;
            this.md5 = md5;
            this.action = action;
        }
    }
    private static boolean lock = false;
    private static boolean isOver() {
return false;
    }

    public static void exec(String cmd) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec(cmd).getInputStream()));
        String line=null;

        while((line=br.readLine())!=null)
        {
            print(line);
        }
    }

    private static void reLogin() throws Exception{
        exec("adb shell input tap 50 50");
        Thread.sleep(500);
        exec("adb shell input tap 300 1600");
        Thread.sleep(3500);
        exec("adb shell input tap 300 1900");
        Thread.sleep(10000);
    }

    private static String getMd5(BufferedImage image) throws  Exception{
        ByteArrayOutputStream bs = new ByteArrayOutputStream();
        ImageIO.write(image,"PNG",bs);

        StringBuffer md5Sum = new StringBuffer();
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(bs.toByteArray());
        byte b[] = md.digest();
        int d;
        for (int i = 0; i < b.length; i++) {
            d = b[i];
            if (d < 0) {
                d = b[i] & 0xff;
            }
            if (d < 16)
                md5Sum.append("0");
            md5Sum.append(Integer.toHexString(d));
        }
        return md5Sum.toString();
    }
    private static boolean isTrainArrived() {
        return (!statusMd5.equals(trainWaiting));
    }
    private static boolean isAllEmpty() throws Exception{
        boolean empty = (item1Md5.equals(emptyMd5[0]) && item2Md5.equals(emptyMd5[1]) && item3Md5.equals(emptyMd5[2]));
        if (empty) {
            exec("adb shell input swipe 850 400 850 1600 100");
            print("items is empty");
        }
        return empty;
    }
    private static void update(String pos1,String pos2) throws Exception{
        exec("adb shell input swipe "+ pos1 + " " + pos2);
    }
    private static void checkItem() throws Exception{
        if (isAllEmpty())
            return;
        boolean hasTarget = false;
        for (Item item :finalItems) {
            if (item.md5.equals(item1Md5)|| item.md5.equals(item2Md5) || item.md5.equals(item3Md5)) {
                print("find " + item.name + ",at position " + (item.id % 4));
                update(item.trainPos,item.buildPos);
                hasTarget = true;
            }
        }
        if (!hasTarget) {
            print("no item found,relogin");
            reLogin();
        }
    }
    static int tt=0;
    private static void getImage() throws Exception {
        BufferedImage bufImage = ImageIO.read(Runtime.getRuntime().exec("adb exec-out screencap -p").getInputStream());
//        ImageIO.write(bufImage.getSubimage(620,1730,70,50),"PNG",new File("D:\\temp\\__1.png"));
        ImageIO.write(bufImage.getSubimage(780,1660,70,50),"PNG",new File("D:\\temp\\__2" +"_" +tt+ ".png"));
        ImageIO.write(bufImage.getSubimage(930,1560,70,50),"PNG",new File("D:\\temp\\__3" +"_" +tt+" .png"));
        tt++;
//        ImageIO.write(bufImage.getSubimage(350,1850,70,50),"PNG",new File("D:\\temp\\___.png"));
        item1Md5 = getMd5(bufImage.getSubimage(620,1730,70,50));
        item2Md5 = getMd5(bufImage.getSubimage(780,1660,70,50));
        item3Md5 = getMd5(bufImage.getSubimage(930,1560,70,50));
        statusMd5 = getMd5(bufImage.getSubimage(350,1850,70,50));

        if (isTrainArrived()) {
            print(isTrainArrived()? "Arrived":"waiting");
            checkItem();
        } else {
            Thread.sleep(3000);
        }
   }
   protected static void print(Object line) {
        jta.append(line.toString() + "\n");
        System.out.println(line.toString());
   }
   private static JTextArea jta;
   private static void showUI () {
       JFrame frame=new JFrame("家国梦拉货");    //创建Frame窗口
       frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
       JPanel jp=new JPanel();    //创建一个JPanel对象
       jta=new JTextArea("",20,40);
       jta.setLineWrap(true);    //设置文本域中的文本为自动换行
       jta.setForeground(Color.BLACK);    //设置组件的背景色
       jta.setFont(new Font("楷体",Font.BOLD,16));    //修改字体样式
//       jta.setBackground(Color.LIGHT_GRAY);    //设置按钮背景色
       JScrollPane jsp=new JScrollPane(jta);    //将文本域放入滚动窗口
       Dimension size=jta.getPreferredSize();    //获得文本域的首选大小
       jsp.setBounds(110,90,size.width,size.height);
       jp.add(jsp);    //将JScrollPane添加到JPanel容器中
       frame.add(jp);    //将JPanel容器添加到JFrame容器中
       frame.setBackground(Color.LIGHT_GRAY);
       frame.setSize(350,450);    //设置JFrame容器的大小
       frame.setVisible(true);
   }
    public static void main(String[] args) {
        showUI();
        try {
            while (true) {
                getImage();
                Thread.sleep(2);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
	// write your code here
    }
}
