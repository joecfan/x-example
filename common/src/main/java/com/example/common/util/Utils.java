package com.example.common.util;

import com.esotericsoftware.reflectasm.FieldAccess;
import com.esotericsoftware.reflectasm.MethodAccess;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.ParseException;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.ExpressionState;
import org.springframework.expression.spel.SpelNode;
import org.springframework.expression.spel.ast.VariableReference;
import org.springframework.expression.spel.standard.SpelExpression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.util.StringUtils;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author: zhuhui bao
 * @date: 9:01 2020/6/19
 **/
@Slf4j
public class Utils {

    public static void main(String[] args) {

        List<String> list = new ArrayList<>();
        list.add("11");
        list.add("12");
        Iterator it = list.iterator();
        while(it.hasNext()) {
            it.next();
            it.remove();
        }

        long startTime = System.currentTimeMillis();
        for (int i=0; i<1; i++) {
            boolean b = eval("2>" + i);
            //System.out.println(b);
        }
        System.out.println(System.currentTimeMillis()-startTime);

        startTime = System.currentTimeMillis();
        for (int i=0; i<1; i++) {
            boolean b = eval2("2>" + i);
            //System.out.println(b);//
        }
        System.out.println(System.currentTimeMillis()-startTime);
        Vo vo = new Vo();
        System.out.println(vo.getClass().getSimpleName());
        vo.setAa("testaaa");
        Object aa;
         startTime = System.nanoTime();
//       for (int i=0; i< 1; i++) {
//            Utils.setObjField(vo, "aa","tsss");
//            //System.out.println(aa);
//        }
//        System.out.println(System.nanoTime()-startTime);
/*
        startTime = System.nanoTime();
        for (int i=0; i< 1000000; i++) {
            vo.setAa("ssss");
            //System.out.println(aa);
        }
        System.out.println(System.nanoTime()-startTime);*/

//        startTime = System.nanoTime();
//        MethodAccess access = MethodAccess.get(vo.getClass());
//        for (int i=0; i< 50; i++) {
//            Utils.setObjFieldAsm(access, vo, "setAa","tsss");
//            //System.out.println(aa);
//        }
//        System.out.println(System.nanoTime()-startTime);
//
//        startTime = System.nanoTime();
//        for (int i=0; i< 1; i++) {
//            vo.setAa("ssss");
//            //System.out.println(aa);
//        }
//        System.out.println(System.nanoTime()-startTime);

//        startTime = System.nanoTime();
//        Field[] fields = vo.getClass().getDeclaredFields();
//
//
//        startTime = System.nanoTime();
//        for (int i=0;i<50;i++) {
//            fields[0].setAccessible(true);
//            fields[0].get(vo);
//        }
//
//        System.out.println(System.nanoTime()-startTime);

        startTime = System.nanoTime();
        for (int i=0;i<50;i++) {
            Utils.getObjField(vo, "aa");
        }
        System.out.println(System.nanoTime()-startTime);

        startTime = System.nanoTime();
        //Class c = vo.getClass();
        for (int i=0;i<50;i++) {
            //Utils.getObjField(c, vo,"aa");
            Utils.getObjField(vo, "bb");
        }

        System.out.println(System.nanoTime()-startTime);

    }

    public static String subString(String str, int beginIdx, int endIdx) {

        try {
            str = str == null ? "" : str;
            str = str.substring(beginIdx, endIdx);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return str;
    }



    public static Object getObjField(Object vo, String fieldName) {

        Field field;
        Object o = null;
        try {
            field = vo.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            o = field.get(vo);
        } catch (NoSuchFieldException e) {
            log.error(e.getMessage());
        } catch (IllegalAccessException e) {
            log.error(e.getMessage());
        }
        return o;
    }

    public static Object getObjField(Class c, Vo vo,String fieldName) {

        Field field;
        Object o = null;
        try {
            field = c.getDeclaredField(fieldName);
            field.setAccessible(true);
            o = field.get(vo);
        } catch (NoSuchFieldException e) {
            log.error(e.getMessage());
        } catch (IllegalAccessException e) {
            log.error(e.getMessage());
        }
        return o;
    }

    public static Object getObjFieldAsm(Object vo, String fieldName) {

        FieldAccess fieldAccess = FieldAccess.get(vo.getClass());
        return fieldAccess.get(vo, fieldName);
    }

    public static void setObjField(Object vo, String fieldName, Object fieldValue) {

        Field field;
        try {
            field = vo.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(vo, fieldValue);
            Object o = field.get(vo);
        } catch (NoSuchFieldException e) {
            log.error(e.getMessage());
        } catch (IllegalAccessException e) {
            log.error(e.getMessage());
        }
    }

    public static void setObjFieldAsm(MethodAccess access, Object vo, String fieldName, String value) {

//        FieldAccess fieldAccess = FieldAccess.get(vo.getClass());
//        fieldAccess.set(vo, fieldName, value);
        //int addNameIndex = access.getIndex(fieldName);
        //invoke setName方法name值
        //access.invoke(vo, addNameIndex, value);
        access.invoke(vo, fieldName, value);
       // String name = (String)access.invoke(vo, "getAa", null);
      //  System.out.println(name);
    }

    public boolean isEmpty(Object object) {

        boolean isEmpty = false;

        if (object == null) {
            isEmpty = true;

        } else {
            if (object instanceof List) {
                if (((List)object).isEmpty()) {
                    isEmpty = true;
                }
            } else if (StringUtils.isEmpty(String.valueOf(object))){
                isEmpty = true;
            }
        }
        return isEmpty;
    }

    private static ThreadLocal<ScriptEngine> engine = new ThreadLocal<>();

    public static boolean eval(String expression) {
        if (engine.get() == null) {
            ScriptEngineManager manager = new ScriptEngineManager();
            engine.set(manager.getEngineByName("js"));
        }

        Boolean result = Boolean.FALSE;
        try {
            result = (Boolean)engine.get().eval(expression);
        } catch (ScriptException e) {

        }
        return result.booleanValue();
    }

    public static boolean eval2(String expression) {

        Boolean result = Boolean.FALSE;

        ExpressionParser parser = new SpelExpressionParser();
        try {
            result = parser.parseExpression(expression).getValue(Boolean.class);
        } catch (ParseException e) {
            log.error(e.getMessage(), e);
        }

        return result.booleanValue();
    }

    public static boolean eval2(String expression, Map<String, Integer> varMap) {

        Boolean result = Boolean.FALSE;
        ExpressionParser parser = new SpelExpressionParser();

        try {
            Expression parseExpression = parser.parseExpression(expression);

            EvaluationContext context = new StandardEvaluationContext();
            for (Map.Entry<String, Integer> entry : varMap.entrySet()) {
                context.setVariable(entry.getKey(), entry.getValue());
            }

            result = parseExpression.getValue(context, Boolean.class);
        } catch (ParseException e) {
            log.error(e.getMessage(), e);
        }

        return result.booleanValue();
    }

    public static boolean eval3(String expression, Map<String, Integer> varMap) {

        Boolean result = Boolean.FALSE;
        SpelExpressionParser parser = new SpelExpressionParser();
        EvaluationContext context = new StandardEvaluationContext();
        for (Map.Entry<String, Integer> entry : varMap.entrySet()) {
            context.setVariable(entry.getKey(), entry.getValue());
        }

        try {
            SpelExpression parseExpression = (SpelExpression)parser.parseExpression(expression);
            SpelNode spelNode = parseExpression.getAST();
            List<String> varList = new ArrayList<>();
            ExpressionState state = new ExpressionState(context);

            getVarName(spelNode, varList);

            result = parseExpression.getValue(context, Boolean.class);
        } catch (ParseException e) {
            log.error(e.getMessage(), e);
        }

        return result.booleanValue();
    }

    private static void getVarName(SpelNode spelNode, List<String> varList) {
        for (int i = 0; i < spelNode.getChildCount(); i++) {
            if (spelNode.getChildCount() > 0 && spelNode.getChild(i) instanceof VariableReference) {
               // varList.add(((VariableReference)spelNode.getChild(i).getChild(i)));
            }
            getVarName(spelNode.getChild(i), varList);

        }
    }

    public static String getContent(String tempalte, Map<String,String> parameters){
        Pattern p = Pattern.compile("(\\{([a-zA-Z]+)\\})");
        Matcher m = p.matcher(tempalte);
        StringBuffer stringBuffer = new StringBuffer();
        while (m.find()){
            String key = m.group(2);
            String value = null;
            if (parameters.containsKey(key)){
                value = parameters.get(key);
            }
            value = (value == null) ? "" : value;
            m.appendReplacement(stringBuffer,value);
        }
        m.appendTail(stringBuffer);
        return stringBuffer.toString();
    }

    public static void getExpressionVar(String tempalte){
        Pattern p = Pattern.compile("(\\#([a-zA-Z0-9]+)\\s+)");
        Matcher m = p.matcher(tempalte);
       List<String> varList = new ArrayList<>();
        while (m.find()){
            String key = m.group(2);
            varList.add(key);
        }
        System.out.println();
    }
}
