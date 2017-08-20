package com.opendev.cn.bbparser;

import com.opendev.cn.bbparser.data.BBResult;
import com.opendev.cn.bbparser.data.BbType;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by root on 20/08/17.
 */

public class BBCodeParseTest {

    @Before
    public void init() {
        BBParser.init();
    }

    @Test
    public void parsingMultipleTagsProducesCorrectlySizedList() {
        List<BBResult> componentList = BBParser.parseCode("somesuperrandom stuff[img]https://forum.com/image.png[/img] hh[spoiler]texttohide[/spoiler]");
        assertEquals(4, componentList.size());
    }

    @Test
    public void postComponentDataSplitCorrectly() {
        List<BBResult> componentList = BBParser.parseCode("somesuperrandom  stuff[img]https://forum.com/image.png[/img] hh[spoiler]texttohide[/spoiler]");
        assertEquals("somesuperrandom  stuff", componentList.get(0).getData());
        assertEquals("https://forum.com/image.png", componentList.get(1).getData());
        assertEquals(" hh", componentList.get(2).getData());
        assertEquals("texttohide", componentList.get(3).getData());
    }

    @Test
    public void postComponentTypeAssignedCorrectly() {
        List<BBResult> componentList = BBParser.parseCode("somesuperrandom  stuff[img]https://forum.com/image.png[/img] hh[spoiler]texttohide[/spoiler]");
        assertEquals(BbType.RAW, componentList.get(0).getComponentType());
        assertEquals(BbType.IMAGE, componentList.get(1).getComponentType());
        assertEquals(BbType.RAW, componentList.get(2).getComponentType());
        assertEquals(BbType.SPOILER, componentList.get(3).getComponentType());
    }

    @Test
    public void realHfApiResultTest() {

        List<BBResult> componentList = BBParser.parseCode("[spoiler]This is an image:\n[img]https://thing.com/image.png[/img][/spoiler][url=https://www.google.com]link here[/url]");
        assertEquals("https://www.google.com", componentList.get(1).getTypeArgs());

    }

    @Test
    public void repeatedProcessCallOnReturnedData() {
        List<BBResult> componentList = BBParser.parseCode("[spoiler][img]https://image.com/image.png[/img][/spoiler]");
        componentList.addAll(BBParser.parseCode(componentList.get(0).getData()));
        //System.out.println(componentList.get(1).getData());
        assertEquals(2, componentList.size());
    }

}
