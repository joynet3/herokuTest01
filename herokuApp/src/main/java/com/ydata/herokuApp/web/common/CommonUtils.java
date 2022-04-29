package com.ydata.herokuApp.web.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

/**
 * <pre>
 * 공통 유틸 정의
 * 
 * <<개정이력>>
 * 수정일                 수정자               수정내용
 * ----------     ----------    ---------------------
 * 2020.04.15.    조일근      최초 생성
 * </pre>
 *
 * @author ikcho <joynet9478@gmail.com>
 * @since 2020.04.15.
 * @version 1.0
 */

public class CommonUtils {
    /**
     * fileName 에 해당하는 text 파일을 읽어서, 줄 단위 List 로 반환한다.<br>
     * UTF-8 로 인코딩 된 것으로 간주한다.
     * 
     * @param fileName 해당 file 의 full path
     * @return
     * @throws Exception
     */
    public static List<String> loadFileByLine(String fileName) throws Exception {
        return loadFileByLine(fileName, "UTF-8");
    }

    /**
     * fileName 에 해당하는 text 파일을 읽어서, 줄 단위 List 로 반환한다.
     * 
     * @param fileName    해당 file 의 full path
     * @param charsetName file 의 charset, null인 경우 UTF-8 로 설정됨.
     * @return
     * @throws Exception
     */
    public static List<String> loadFileByLine(String fileName, String charsetName) throws Exception {
        return loadFileByLine(new FileInputStream(fileName), charsetName);
    }

    /**
     * file 에 해당하는 text 파일을 읽어서, 줄 단위 List 로 반환한다.<br>
     * UTF-8 로 인코딩 된 것으로 간주한다.
     * 
     * @param file
     * @return
     * @throws Exception
     */
    public static List<String> loadFileByLine(File file) throws Exception {
        return loadFileByLine(file, "UTF-8");
    }

    /**
     * file 에 해당하는 text 파일을 읽어서, 줄 단위 List 로 반환한다.
     * 
     * @param file
     * @param charsetName file 의 charset, null인 경우 UTF-8 로 설정됨.
     * @return
     * @throws Exception
     */
    public static List<String> loadFileByLine(File file, String charsetName) throws Exception {
        return loadFileByLine(new FileInputStream(file), charsetName);
    }

    /**
     * fileInputStream 에 해당하는 text 파일을 읽어서, 줄 단위 List 로 반환한다.<br>
     * UTF-8 로 인코딩 된 것으로 간주한다.
     * 
     * @param fis
     * @return
     * @throws Exception
     */
    public static List<String> loadFileByLine(FileInputStream fis) throws Exception {
        return loadFileByLine(fis, "UTF-8");
    }

    /**
     * fileInputStream 에 해당하는 text 파일을 읽어서, 줄 단위 List 로 반환한다.
     * 
     * @param fis
     * @param charsetName file 의 charset, null인 경우 UTF-8 로 설정됨.
     * @return
     * @throws Exception
     */
    public static List<String> loadFileByLine(FileInputStream fis, String charsetName) throws Exception {
        if (StringUtils.isBlank(charsetName)) {
            charsetName = "UTF-8";
        }
        InputStreamReader isr = new InputStreamReader(fis, charsetName);
        List<String> result = new ArrayList<String>();
        char[] cbuf = new char[1];
        StringBuilder sb = new StringBuilder();
        while (isr.read(cbuf) > 0) {
            if (Character.toString(cbuf[0]).equals("\r")) {
                result.add(sb.toString());
                isr.read(cbuf);
                sb = new StringBuilder();
                if (!Character.toString(cbuf[0]).equals("\n")) {
                    sb.append(cbuf);
                }
            } else if (Character.toString(cbuf[0]).equals("\n")) {
                result.add(sb.toString());
                sb = new StringBuilder();
            } else {
                sb.append(cbuf);
            }
        }
        result.add(sb.toString());
        return result;
    }

    /**
     * 파일 명에서 확장자를 뽑아서 반환한다.<br>
     * (즉, 마지막 . 이후의 글자를 반환한다.)<br>
     * 반환되는 확장자는 소문자로 치환해서 반환한다.<br>
     * 확장자가 없는 경우 빈 string("") 을 반환한다.
     * 
     * @param fileNm
     * @return
     */
    public static String getFileExt(String fileNm) {
        if (StringUtils.isBlank(fileNm)) {
            return "";
        }
        int lastIdx = fileNm.lastIndexOf(".");
        if (lastIdx < 0) {
            return "";
        }
        return fileNm.substring(lastIdx + 1).toLowerCase();
    }

    /**
     * 숫자를 정해진 양식으로 다듬어서 반환한다.
     * 
     * @param val
     * @param maxFractionDigits
     * @return
     */
    private static String formatNumber(double val, int maxFractionDigits) {
        NumberFormat nf = NumberFormat.getInstance();
        nf.setMaximumFractionDigits(maxFractionDigits);
        return nf.format(val);
    }

    /**
     * 입력받은 list 의 순서를 역순으로 조정한 새 list 를 반환한다.
     * 
     * @param list
     * @return list
     */
    public static <T> List<T> createReverseList(List<T> list) {
        if (null == list) {
            return null;
        }
        int listCount = list.size();
        List<T> newList = new ArrayList<T>(listCount);
        for (int i = listCount - 1; i >= 0; i--) {
            newList.add(list.get(i));
        }
        return newList;
    }

    /**
     * 입력받은 string 이 숫자로만 이루어져 있는지 여부를 반환한다.
     * 
     * @param str
     * @return
     */
    public static boolean isNumber(String str) {
        if (StringUtils.isBlank(str)) {
            return false;
        }
        String numPattern = "[\\d]+";
        return Pattern.matches(numPattern, str);
    }

}
