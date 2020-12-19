package com.example.common.util;

import com.esotericsoftware.reflectasm.FieldAccess;
import com.esotericsoftware.reflectasm.MethodAccess;
import lombok.extern.slf4j.Slf4j;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.ParseException;
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
import java.math.BigDecimal;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author: zhuhui bao
 * @date: 9:01 2020/6/19
 **/
@Slf4j
public class Utils {

    public static String leftPadZero(String arg, int length) {

        return String.format("%0" + length + "d", Integer.parseInt(arg));
    }

    /**
     * 截字符串，从指定字符串的后面开始截，比如str=abc123,prefix=abc，返回123
     * @param str
     * @param prefix
     * @return
     */
    public static String substring(String str, String prefix) {

        if (!StringUtils.isEmpty(str)) {
            str = str.replaceFirst(prefix, "").trim();
        }
        return str;
    }

    public static String[] split(String str, String startWith) {
        if (StringUtils.isEmpty(startWith)) {
            if (str.indexOf("(") >=0 || str.indexOf("(") >=0) {
                throw new RuntimeException("111");
            }

        } else if (str.startsWith(startWith)) {
            str = str.replaceFirst(startWith, "").replaceFirst("\\(","").replaceFirst("\\)","");
        }
        return str.split("\\|");
    }

    public static Object getFieldVal(ParamValidateVo paramValidateVo, String fieldName) {
        return getObjField(paramValidateVo, fieldName);

    }

    public static String substring(String str, int beginIdx, int endIdx) {

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

    public static Object getObjFieldAsm(Object vo, String fieldName) {
        // asm不能对私有属性取值，所以改成get方法取
        MethodAccess access = MethodAccess.get(vo.getClass());
        return access.invoke(vo, fieldName);
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
    public static void setObjFieldAsm(Object vo, String fieldName, String value) {

        MethodAccess access = MethodAccess.get(vo.getClass());
        access.invoke(vo, fieldName, value);
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

    //        char[] ca = fieldName.toCharArray();
//        ca[0] -=32;
//        String methodNm = org.apache.commons.lang3.StringUtils.join("get" + String.valueOf(ca));





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

    public static boolean eval2(String expression, Map<String, String> varMap) {

        Boolean result = Boolean.FALSE;
        ExpressionParser parser = new SpelExpressionParser();

        try {
            Expression parseExpression = parser.parseExpression(expression);

            EvaluationContext context = new StandardEvaluationContext();
            for (Map.Entry<String, String> entry : varMap.entrySet()) {
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
