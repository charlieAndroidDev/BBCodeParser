package com.opendev.cn.bbparser;

import com.opendev.cn.bbparser.data.BBResult;
import com.opendev.cn.bbparser.data.BbType;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by root on 20/08/17.
 */

public class BBParser {

    private static Pattern bbCodePattern;
    private static Matcher patternMatcher;

    private static final int TYPE_INDEX = 1;
    private static final int ARGS_INDEX = 2;
    private static final int DATA_INDEX = 3;

    private static final String BB_IMAGE = "IMG";
    private static final String BB_CODE = "CODE";
    private static final String BB_BOLD = "B";
    private static final String BB_QUOTE = "QUOTE";
    private static final String BB_ITALIC = "I";
    private static final String BB_UNDERLINED = "U";
    private static final String BB_URL = "URL";
    private static final String BB_EMAIL = "EMAIL";
    private static final String BB_SPOILER = "SPOILER";
    private static final String BB_REGEX = "\\[(.*?)(=.*?)?](.*?)\\[/\\1]";

    public static void init() {
        bbCodePattern = Pattern.compile(BB_REGEX, Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
    }

    public static List<BBResult> parseCode(final String code) {

        List<BBResult> postComponents = new ArrayList<>();

        patternMatcher = bbCodePattern.matcher(code);

        int lastEnd = 0;

        while(patternMatcher.find()) {
            if(lastEnd != patternMatcher.start()) {
                postComponents.add(new BBResult(code.substring(lastEnd, patternMatcher.start()), BbType.RAW, null));
            }
            postComponents.add(new BBResult(patternMatcher.group(DATA_INDEX), getComponentType(patternMatcher.group(TYPE_INDEX)), patternMatcher.group(ARGS_INDEX)));
            lastEnd = patternMatcher.end();
        }

        return postComponents;

    }

    private static BbType getComponentType(final String group) {

        switch (group.toUpperCase()) {
            case BB_BOLD:
                return BbType.BOLD;
            case BB_CODE:
                return BbType.CODE;
            case BB_EMAIL:
                return BbType.EMAIL;
            case BB_IMAGE:
                return BbType.IMAGE;
            case BB_SPOILER:
                return BbType.SPOILER;
            case BB_QUOTE:
                return BbType.QUOTE;
            case BB_ITALIC:
                return BbType.ITALIC;
            case BB_UNDERLINED:
                return BbType.UNDERLINED;
            case BB_URL:
                return BbType.URL;
            default:
                return BbType.RAW;
        }

    }

}
