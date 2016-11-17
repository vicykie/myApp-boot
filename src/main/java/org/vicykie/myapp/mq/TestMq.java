package org.vicykie.myapp.mq;

import java.io.*;

/**
 * Created by 李朝衡 on 2016/11/1.
 */
public class TestMq {
    public static void main(String[] args) {
        File file = new File("D:\\test.txt");
        try {
            InputStream inputStream = new FileInputStream(file);

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line1= bufferedReader.readLine();
            System.out.println(line1);
            String line2 = bufferedReader.readLine();
            System.out.println(line2);
            String line3 = bufferedReader.readLine();

            System.out.println(line3);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
