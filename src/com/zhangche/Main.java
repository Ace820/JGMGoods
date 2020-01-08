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
    static String[] trainPos = {
            "670 1760",
            "820 1675",
            "975 1600",
    };
    static String[] buildPos = {
            "250 750",
            "480 620",
            "830 460",
            "300 1050",
            "550 900",
            "850 800",
            "250 1300",
            "550 1200",
            "850 1100",
    };
    private static Item[] finalItems = {
            new Item(1 ,"强国煤业",trainPos[0],buildPos[0],"fadc6cdd5eb4b3de459165a01c370dde",1),
            new Item(2 ,"强国煤业",trainPos[1],buildPos[0],"8f17a25f5b858b7aca4b2133cb1e44e4",1),
            new Item(3 ,"强国煤业",trainPos[2],buildPos[0],"284e03e751b61917812673b0f860c70e",1),
            new Item(4 ,"企鹅机械",trainPos[0],buildPos[1],"3503faa47f7214f52043b192cb012d3c",1),
            new Item(5 ,"企鹅机械",trainPos[1],buildPos[1],"9dfeaed0ad31d8a84a1b3b3b39b2ce9d",1),
            new Item(6 ,"企鹅机械",trainPos[2],buildPos[1],"a2d1000af8c004aba29c01909f043a3a",1),
            new Item(7 ," 民食斋 ",trainPos[0],buildPos[3],"b940c84c15d89828b864ba2619473239",1),
            new Item(8 ," 民食斋 ",trainPos[1],buildPos[3],"974e68c82f8f93a07c98dd2197092c41",1),
            new Item(9 ," 民食斋 ",trainPos[2],buildPos[3],"3e8fc56f4757bacc15b30e0b17c383a9",1),
            new Item(10,"人民石油",trainPos[0],buildPos[2],"1343f9fbc7c6cc4407184d69ac9fe475",1),
            new Item(11,"人民石油",trainPos[1],buildPos[2],"7f778b8e8a7a9cc3457c682b9a28992a",1),
            new Item(12,"人民石油",trainPos[2],buildPos[2],"d75de3ae9631318347f027e2e8a1cd89",1),
            new Item(10,"空中别墅",trainPos[0],buildPos[6],"745e57d3deb6c330aa3e1a2bd675c9fc",1),
            new Item(11,"空中别墅",trainPos[1],buildPos[6],"25c1c97b65e3d54bb3776247e15f0749",1),
            new Item(12,"空中别墅",trainPos[2],buildPos[6],"",1),
            new Item(10,"复兴公馆",trainPos[0],buildPos[7],"d4d4a08070d09a1107935b15e4065976",1),
            new Item(11,"复兴公馆",trainPos[1],buildPos[7],"2ee1707990f13dba19f4d5d4e826e446",1),
            new Item(12,"复兴公馆",trainPos[2],buildPos[7],"",1),
    };
    private static Item[] rareItems = {
            new Item(1 ," 零件厂 ",trainPos[0],buildPos[2],"091e9f9e23d1b6de5ef47c49df6a9f9f",1),
            new Item(2 ," 零件厂 ",trainPos[1],buildPos[2],"5c40b9fcab7949d54be8ac6763f58ddf",1),
            new Item(3 ," 零件厂 ",trainPos[2],buildPos[2],"b81ee203989ea0e68487a67c8debb6aa",1),
            new Item(4 ,"商贸中心",trainPos[0],buildPos[4],"72fd37c45fef11c52a8a176e84dfed15",1),
            new Item(5 ,"商贸中心",trainPos[1],buildPos[4],"0c8e46237d215c52376c1b3dad439735",1),
            new Item(6 ,"商贸中心",trainPos[2],buildPos[4],"3038c0bf85440eafcd5af84f4b4af827",1),
            new Item(7 ,"追梦快递",trainPos[0],buildPos[5],"bc0805fc315c84c165169be26ebde3c7",1),
            new Item(8 ,"追梦快递",trainPos[1],buildPos[5],"6f4c2485ae1682e7bf14b1e17ac55ea3",1),
            new Item(9 ,"追梦快递",trainPos[2],buildPos[5],"c9c68c12ba1d61a41551e84d10e886c1",1),
            new Item(10,"花园洋房",trainPos[0],buildPos[7],"10d6aef420f612aa57dfc6821b87f1cf",1),
            new Item(11,"花园洋房",trainPos[1],buildPos[7],"561695226f5c810411ca73564ef68a41",1),
            new Item(12,"花园洋房",trainPos[2],buildPos[7],"",1),
            new Item(13,"中式小楼",trainPos[0],buildPos[8],"ba033bd42d85bdd76d41b82853d9c6ba",1),
            new Item(14,"中式小楼",trainPos[1],buildPos[8],"6c3b99a247e08d3e3bef0444ee32e4e2",1),
            new Item(15,"中式小楼",trainPos[2],buildPos[8],"",1),
            new Item(16,"小型公寓",trainPos[0],buildPos[8],"e4f2dd88929e4013064784363a1882c5",1),
            new Item(17,"小型公寓",trainPos[1],buildPos[8],"65c17eb59b29c65cee4fe66cd0a39681",1),
            new Item(18,"小型公寓",trainPos[2],buildPos[8],"6607ab8581154b617b85d620d60e0663",1),
    };
    private static String[] emptyMd5 = {
            "011251b4b68cc4da6a310f014fd1eada",
            "db1b4f9f0e91b0ac5dc7958a26ea9fe0",
            "9e1c2a8cadf11db83fdeaadb9e671658",
    };
    private static final String trainArrived = "8c7b5e3f9540bde5d3e28dfb9967a713";
    private static final String trainWaiting = "6d4eae78b80354d8ce644712d3bbc0ef";
    private static final String trainLeaving = "7c9d6cad372de9f2aab785dcfcab847e";
    private static String statusMd5 = null;
    private static String item1Md5 = null;
    private static String item2Md5 = null;
    private static String item3Md5 = null;
    private static String item1CountMd5 = null;
    private static String item2CountMd5 = null;
    private static String item3CountMd5 = null;
    private static BufferedImage item1CountImg = null;
    private static BufferedImage item2CountImg = null;
    private static BufferedImage item3CountImg = null;
    private static BufferedImage item1Img = null;
    private static BufferedImage item2Img = null;
    private static BufferedImage item3Img = null;
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
    private static String fileDir = null;
    private static boolean isOver() {
return false;
    }

    public static String exec(String cmd) throws Exception{
        while (!isTop()) {
            print("not Top,Still Waiting");
            Thread.sleep(1000);
        }
        Process p = Runtime.getRuntime().exec(cmd);
        BufferedReader br=new BufferedReader(new InputStreamReader(p.getInputStream()));
        p.waitFor();
        String line=null;
        String result = "";

        while((line=br.readLine())!=null)
        {
            result += line;
            print(line);
        }
        return result;
    }

    boolean isDeviceOnline = false;
    private static boolean isTop() throws Exception{
//        System.out.print("check top ");
        Runtime.getRuntime().exec("adb wait-for-device").waitFor();
        BufferedReader br=new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec(
                "adb shell \"dumpsys activity -p com.tencent.jgm |grep top-activity \"").getInputStream()));
        String line=br.readLine();
//        print(line != null);

//        while((line=br.readLine())!=null)
//        {
//            print(line);
//        }
        return (line != null );
    }

    private static void reLogin() throws Exception{
//        exec("adb shell input tap 50 50");
//        Thread.sleep(500);
//        exec("adb shell input tap 300 1600");
//        Thread.sleep(3500);
//        exec("adb shell input tap 300 1900");
//        Thread.sleep(10000);
        exec("adb shell \"input keyevent KEYCODE_APP_SWITCH && sleep 1 && input swipe 600 1400 600 200 200\"");
        Thread.sleep(1000);
        Runtime.getRuntime().exec("adb shell am start -n com.tencent.jgm/.MainActivity");
        Thread.sleep(20000);
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
//        return (!statusMd5.equals(trainWaiting));
        return (!(statusMd5.equals(trainWaiting) || item3Md5.equals(trainLeaving)));
    }
    private static boolean isAllEmpty() throws Exception{
        boolean empty = (item1Md5.equals(emptyMd5[0]) && item2Md5.equals(emptyMd5[1]) && item3Md5.equals(emptyMd5[2]));
        if (empty) {
            exec("adb shell input swipe 850 400 850 1600 100");
            print("items is empty");
        }
        return empty;
    }
    private static void updateCount() throws Exception{
        String count = exec("adb shell getprop debug.jgm.`date +%y%m%d`.count");
        int countInt = 0;
        if(!count.equals(""))
            countInt = Integer.parseInt(count);
        countInt ++;
        print("Received " + countInt + " Goods");
        exec("adb shell setprop debug.jgm.`date +%y%m%d`.count " + countInt);
        if (countInt > 530 ) {
            exec("adb shell setprop debug.jgm.stop 1");
            Thread.sleep(5000);
            System.exit(0);
        }
    }
    private static void update(String pos1,String pos2) throws Exception{
        updateCount();
//        exec("adb shell input swipe 850 400 850 1600 100");
        exec("adb shell input swipe "+ pos1 + " " + pos2);
        exec("adb shell input swipe "+ pos1 + " " + pos2);
        exec("adb shell input swipe "+ pos1 + " " + pos2);
    }
    static boolean isReChecked = false;
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
        for (Item item :rareItems) {
            if (item.md5.equals(item1Md5)|| item.md5.equals(item2Md5) || item.md5.equals(item3Md5)) {
                print("find " + item.name + ",at position " + (item.id % 4));
                update(item.trainPos,item.buildPos);
                hasTarget = true;
            }
        }
        if (!hasTarget) {
//            print("no item found,relogin");
            print("Rechecked,Relogin = " + isReChecked);
            if (isReChecked) {
                reLogin();
                ImageIO.write(item1Img,"PNG",new File(fileDir +"1" +"_" +item1Md5+".png"));
                ImageIO.write(item2Img,"PNG",new File(fileDir +"2" +"_" +item2Md5+".png"));
                ImageIO.write(item3Img,"PNG",new File(fileDir +"3" +"_" +item3Md5+".png"));
                isReChecked = false;
            } else {
                Thread.sleep(2000);
                isReChecked = true;
                getImage();
            }
        } else
            isReChecked = false;
//        ImageIO.write(item1CountImg,"PNG",new File(fileDir +"Count1 " +"_" +item1CountMd5+" .png"));
//        ImageIO.write(item2CountImg,"PNG",new File(fileDir +"Count2 " +"_" +item2CountMd5+" .png"));
//        ImageIO.write(item3CountImg,"PNG",new File(fileDir +"Count3 " +"_" +item3CountMd5+" .png"));
    }
    private static void getImage() throws Exception {
        while (!isTop()) {
            Thread.sleep(5000);
        }
        BufferedImage bufImage = ImageIO.read(Runtime.getRuntime().exec("adb exec-out screencap -p").getInputStream());
        item1Img = bufImage.getSubimage(620,1730,70,50);
        item2Img = bufImage.getSubimage(780,1660,70,50);
        item3Img = bufImage.getSubimage(930,1560,70,50);
        item1CountImg = bufImage.getSubimage(705,1780,30,40);
        item2CountImg = bufImage.getSubimage(860,1698,30,40);
        item3CountImg = bufImage.getSubimage(1010,1610,30,40);
        item1CountMd5 = getMd5(item1CountImg);
        item2CountMd5 = getMd5(item2CountImg);
        item3CountMd5 = getMd5(item3CountImg);
        item1Md5 = getMd5(item1Img);
        item2Md5 = getMd5(item2Img);
        item3Md5 = getMd5(item3Img);
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
//        jta.selectAll();
        jta.setCaretPosition(jta.getDocument().getLength());
        System.out.println(line.toString());
   }
   private static JTextArea jta;
   private static void showUI () {
       JFrame frame=new JFrame("家国梦拉货");    //创建Frame窗口
       frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
       JPanel jp=new JPanel();    //创建一个JPanel对象
       jta=new JTextArea("",20,35);
       jta.setLineWrap(true);    //设置文本域中的文本为自动换行
       jta.setForeground(Color.BLACK);    //设置组件的背景色
       jta.setFont(new Font("楷体",Font.BOLD,16));    //修改字体样式
       jta.setEditable(false);
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
        File dir = new File("images");
        if (!dir.exists())
            dir.mkdir();

        fileDir = System.getProperty("os.name").toLowerCase().startsWith("win") ? "images\\":"images/";
        print("Current dir :" + System.getProperty("user.dir"));
        while (true) {
            try {
//            print("connect 100");
//            exec("adb connect 192.168.156.100:5555");
//            print("connect 101");
//            exec("adb connect 192.168.156.101:5555");
//            print("connect done");

//                exec("adb shell input swipe 850 400 850 1600 100");
                getImage();
//                Thread.sleep(1500);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    // write your code here
    }
}
