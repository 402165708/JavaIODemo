import java.io.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by chenzhitao on 2018/6/29
 */
public class test11 {


    public static void readFileByLinesToClass(String inputFileName, String outFileName) {
        File inputFile = new File(inputFileName);
        File outFile = new File(outFileName);

        BufferedReader reader = null;

        BufferedWriter writer = null;

        StringBuilder outStr = new StringBuilder();

        try {
            System.out.println("以行为单位读取文件内容，一次读一整行：");
            reader = new BufferedReader(new FileReader(inputFileName));
            writer = new BufferedWriter(new FileWriter(outFileName));
            String tempString = null;
            int line = 1;
            // 一次读入一行，直到读入null为文件结束

            StringBuilder sb = new StringBuilder();
            Set<String> hostNameSet = new HashSet<>();
            while ((tempString = reader.readLine()) != null) {
                //  "id" : 123456,	//id
                if (tempString.trim().equals("")){
                    continue;
                }
//                String[] strArr = tempStringtrim().split("(");
                String hostName = tempString.substring(tempString.indexOf("(") + 1,tempString.indexOf(")"));

                //注释内容
//                String ip = tempString.substring(tempString.indexOf(" ") + 1,tempString.indexOf(")"));
                hostNameSet.add(hostName);

//                String[] fields = strArr[0].trim().split(":") ;
//                String field  = fields[0].trim().substring(1,fields[0].lastIndexOf("\""));
//
//                String condition = fields[1].trim();
//
//                String fieldsType;
//                if (condition.startsWith("\"")){
//                    fieldsType = "String";
//
//                }else {
//                    fieldsType = "Long";
//                }

//
//                /**
//                 * 奥丁机器节点
//                 */
//                private String odinMachineNs;
//                String str = "\t/**" + "\r\n"
////                            + "\t * " + comment + "\r\n"
//                        + "\t */"  +  "\r\n"
//                        +  "\tprivate\t" + fieldsType + "\t" +  field + ";" +  "\r\n";
//
////                System.out.println(str);
//                outStr.append(str);
                line++;
            }
            for (String host : hostNameSet) {
                outStr.append(host +  "\r\n");
            }
            System.out.println(outStr.toString());
            writer.write(outStr.toString());
            writer.close();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
    }

}
