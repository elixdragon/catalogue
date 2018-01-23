package com.interncart.catalogue;

public class MyUtils {

    public static String makeJSONString(String...args){
        int len = args.length;
        if (args.length % 2 != 0)
            len--;
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (int i = 0 ; i < len ; i+=2){
            sb.append("\"");
            sb.append(args[i]);//key
            sb.append("\" : \"");
            sb.append(args[i + 1]);//value

            sb.append("\"");
            if (i < len - 2)
            sb.append(", \n");
        }
        sb.append("}");
        return sb.toString();
    }
}
