import java.io.*;
import java.util.*;

/**
 * @author chen
 */
public class Arcane1 {

    public static void main(String[] args) {
        String inputFileName = "/Users/chen/Desktop/test.txt";
        String outFileName00 = "/Users/chen/Desktop/outFile.txt";
        String inputFileNameZC = "/Users/chen/Desktop/testZC.txt";
        String inputFileNameOther = "/Users/chen/Desktop/testOT.txt";

        String outFileName0 = "/Users/chen/Desktop/outFileZC.txt";
        String outFileName1 = "/Users/chen/Desktop/outFileOT.txt";

//        Map<String,Boolean> allMap = new HashMap<>(10000);
//        read2MapByLines(inputFileName,allMap);
//        Map<String,Boolean> zcMap = new HashMap<>(500);
//        read2MapByLines(inputFileNameZC,zcMap);
//        Map<String,Boolean> otMap = new HashMap<>(500);
//        read2MapByLines(inputFileNameOther,otMap);
//
//        StringBuilder zcsb = new StringBuilder();
//        StringBuilder otsb = new StringBuilder();
//        for (String ID : allMap.keySet()) {
//            if (zcMap.containsKey(ID)){
//                zcsb.append(ID +  "\r\n");
//            }
//            if (otMap.containsKey(ID)){
//                otsb.append(ID +  "\r\n");
//            }
//        }
//        witeToFile(zcsb.toString(),outFileName0);
//        witeToFile(otsb.toString(),outFileName1);


        readFileByLinesToTable(inputFileName,outFileName00);


////
////        readFileByLines(inputFileName, outFileName0);
//
////        readFileByLinesToClass(inputFileName, outFileName0);
//        readFileByLinesUserEntity(inputFileName, outFileName0, outFileName1);
////        readFileByLinesToFile(inputFileName, outFileName0);
////        Integer a = null;
////        int b;
////        b = a;
////        System.out.println(b);
//
////        List<String> list = new ArrayList<>();
////        List<String> list1 = new ArrayList<>();
////        List<String> list2 = new ArrayList<>();
////        list.addAll(list);
////
////        Map<String,Object> map = new HashMap<>();
////        Integer a = (Integer) map.get("a");


    }

    public static void read2MapByLines(final String inputFile,final Map map) {
        File inputFileName = new File(inputFile);
        BufferedReader reader = null;
        try {
            System.out.println("以行为单位读取文件内容，一次读一整行：");
            reader = new BufferedReader(new FileReader(inputFileName));
            String tempString = null;
            int line = 1;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                //  "id" : 123456,	//id
                if (tempString.trim().equals("")){
                    continue;
                }
                map.put(tempString,true);
                line++;
            }
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

    public static void witeToFile(String value, String outFileName) {
        File outFile = new File(outFileName);
        BufferedWriter writer = null;
        try {
            System.out.println("以行为单位读取文件内容，一次写一整行：");
            writer = new BufferedWriter(new FileWriter(outFile));
            writer.write(value +  "\r\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e1) {
                }
            }
        }
    }

    public static void readFileByLinesToFile(String inputFileName, String outFileName) {
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
                String hostName = "";
                try {
                     hostName = tempString.substring(tempString.indexOf("$") + 1, tempString.lastIndexOf("$"));
                }catch (Exception e ){
                    continue;
                }

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

    public static void readFileByLines(String inputFileName, String outFileName) {
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
            while ((tempString = reader.readLine()) != null) {
                String str = tempString
                        .substring(tempString.indexOf("|") + 1, tempString.lastIndexOf("|") - 1).trim();
                outStr.append("\"" + str + "\",");
                line++;
            }
            outStr.deleteCharAt(outStr.length() - 1);
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
            while ((tempString = reader.readLine()) != null) {
                //  "id" : 123456,	//id
                if (tempString.trim().equals("")){
                    continue;
                }
                String[] strArr = tempString.trim().split("//");
                //注释内容
                String comment = strArr[1].trim();

                String[] fields = strArr[0].trim().split(":") ;
                String field  = fields[0].trim().substring(1,fields[0].lastIndexOf("\""));

                String condition = fields[1].trim();

                String fieldsType;
                if (condition.startsWith("\"")){
                    fieldsType = "String";

                }else {
                    fieldsType = "Long";
                }

//
//                /**
//                 * 奥丁机器节点
//                 */
//                private String odinMachineNs;
                String str = "\t/**" + "\r\n"
//                            + "\t * " + comment + "\r\n"
                            + "\t */"  +  "\r\n"
                            +  "\tprivate\t" + fieldsType + "\t" +  field + ";" +  "\r\n";

//                System.out.println(str);
                outStr.append(str);
                line++;
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

    /**
     * @param inputFileName 类字段输入
     * @param outFileName0  输出文本格式：字段 类型  注释  是
     * @param outFileName1  输出Json文本格式："字段" : "值", //注释; 或者 "字段" : 值
     */
    public static void readFileByLinesUserEntity(String inputFileName, String outFileName0, String outFileName1) {
        File inputFile = new File(inputFileName);
        File outFile0 = new File(outFileName0);
        File outFile1 = new File(outFileName1);


        BufferedReader reader = null;

        BufferedWriter writer0 = null;
        BufferedWriter writer1 = null;

        StringBuilder outStr0 = new StringBuilder();
        StringBuilder outStr1 = new StringBuilder();

        try {
            System.out.println("以行为单位读取文件内容，一次读一整行：");
            reader = new BufferedReader(new FileReader(inputFileName));
            writer0 = new BufferedWriter(new FileWriter(outFileName0));
            writer1 = new BufferedWriter(new FileWriter(outFileName1));
            String tempString = "";
            //注释内容
            String comment = "";

            int line = 1;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {

                String tmpStr = tempString.trim();
                //方法体开始
                if (tmpStr.endsWith("{")) {
                    line++;
                    continue;
                }
                //获得注释内容
                if (tmpStr.startsWith("* ")) {
                    comment = tmpStr.substring(2);
                    line++;
                    continue;
                }
                //字段切分
                String[] strArr;
                if (tmpStr.startsWith("private")) {
                    strArr = tmpStr.split(" ");
                    int firstBlankIndex = -1;
                    int blankCount = 0;
                    //统计一共有多少个空格
                    for (int i = 0; i < strArr.length; i++) {
                        if ("".equals(strArr[i]) && !"".equals(strArr[i - 1])) {
                            //当前元素为空，前一个元素不为空，则保存该元素的索引,
                            firstBlankIndex = i;
                            blankCount = 1;
                        } else if ("".equals(strArr[i])) {
                            blankCount++;
                        } else if (!"".equals(strArr[i]) && firstBlankIndex !=-1) {
                            strArr[firstBlankIndex] = strArr[i];
                            firstBlankIndex++;
                            blankCount--;
                        }
                    }
                    if (strArr[2].endsWith(";")) {
                        //字段是分号结尾的
                        strArr[2] = strArr[2].substring(0, strArr[2].length() - 1);
                    }

                    //格式0：字段 类型  注释  是
                    String str0 = strArr[2].trim() + "\t" + strArr[1] + "\t" + comment + "\t" + "是";
                    outStr0.append(str0 + "\r\n");
                    System.out.println("str0:" + str0);

                    //格式1："字段" : "值", //注释; 或者 "字段" : 值
                    String str1 = "";
                    if ("String".equals(strArr[1])) {
                        str1 = "\"" + strArr[2] + "\"" + " : " + "\"" + strArr[2] + "\"" + "," + "  //" + comment;
                    } else if ("long".equals(strArr[1]) || "Long".equals(strArr[1]) ||
                            "int".equals(strArr[1]) || "Integer".equals(strArr[1]) ||
                            "Boolean".equals(strArr[1]) || "boolean".equals(strArr[1])) {
                        str1 = "\"" + strArr[2] + "\"" + " : " + strArr[2] + "," + "  //" + comment;
                    }
                    System.out.println("str1: " + str1);
                    outStr1.append(str1 + "\r\n");
                }
                line++;

            }
            writer0.write(outStr0.toString());
            writer1.write(outStr1.toString());
            writer0.close();
            writer1.close();
            reader.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
    }

    /**
     * @param inputFileName 类字段输入
     * @param outFileName0  输出文本格式：注释 字段 类型    是
     */
    public static void readFileByLinesToTable(String inputFileName, String outFileName0) {
        File inputFile = new File(inputFileName);
        File outFile0 = new File(outFileName0);


        BufferedReader reader = null;

        BufferedWriter writer0 = null;

        StringBuilder outStr0 = new StringBuilder();

        try {
            System.out.println("以行为单位读取文件内容，一次读一整行：");
            reader = new BufferedReader(new FileReader(inputFileName));
            writer0 = new BufferedWriter(new FileWriter(outFileName0));
            String tempString = "";
            //注释内容
            String comment = "";

            int line = 1;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {

                String tmpStr = tempString.trim();
                //方法体开始
                if (tmpStr.endsWith("{")) {
                    line++;
                    continue;
                }
                //获得注释内容
                if (tmpStr.startsWith("* ")) {
                    comment = tmpStr.substring(2);
                    line++;
                    continue;
                }
                //字段切分
                String[] strArr;
                if (tmpStr.startsWith("private")) {
                    strArr = tmpStr.split(" ");
                    int firstBlankIndex = -1;
                    int blankCount = 0;
                    //统计一共有多少个空格
                    for (int i = 0; i < strArr.length; i++) {
                        if ("".equals(strArr[i]) && !"".equals(strArr[i - 1])) {
                            //当前元素为空，前一个元素不为空，则保存该元素的索引,
                            firstBlankIndex = i;
                            blankCount = 1;
                        } else if ("".equals(strArr[i])) {
                            blankCount++;
                        } else if (!"".equals(strArr[i]) && firstBlankIndex !=-1) {
                            strArr[firstBlankIndex] = strArr[i];
                            firstBlankIndex++;
                            blankCount--;
                        }
                    }
                    if (strArr[2].endsWith(";")) {
                        //字段是分号结尾的
                        strArr[2] = strArr[2].substring(0, strArr[2].length() - 1);
                    }

                    //格式0：注释 字段 类型    是
                    String str0 =  comment + "\t" + strArr[2].trim() + "\t" + strArr[1] + "\t" + "是";
                    outStr0.append(str0 + "\r\n");
                    System.out.println("str0:" + str0);

                }
                line++;

            }
            writer0.write(outStr0.toString());
            writer0.close();
            reader.close();
        } catch (IOException ex) {
            ex.printStackTrace();
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
