package com.aileci.speechcenter.utils;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 根据实体对象生成sql语句
 * Created by aileci on 2015/6/29.
 */
public class CreateInsertSql<T> {

        public String createInsert(T entity,String tableName) {
               String sql = "Insert into ";
               String column = "";
               String c_values = "";
               List<Map<String, Object>> list = getFiledsInfo(entity);
               sql += tableName + " ";
               for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).get("f_name").toString() == "id")
                         i++;
                         if (list.get(i).get("f_value") != null){
                            column += list.get(i).get("f_name") + ",";
                            c_values += "'" + list.get(i).get("f_value") + "',";
                         }
               }
            sql += "(" + column.substring(0, column.length() - 1) + ") values ("
            + c_values.substring(0, c_values.length() - 1) + ");";
              return sql;
        }
        public String createUpdate(T entity, String tableName){
            String sql="Update ";
            return null;
        }
        public Object getFieldValueByName(String fieldName, Object o) {
               try {
                     String firstLetter = fieldName.substring(0, 1).toUpperCase();
                     String getter = "get" + firstLetter + fieldName.substring(1);
                     Method method = o.getClass().getMethod(getter, new Class[] {});
                     Object value = method.invoke(o, new Object[] {});
                        return value;
                } catch (Exception e) {
                 return null;
                }
        }
        public List getFiledsInfo(Object o) {
                    String obj_name = o.getClass().getSimpleName().toString();
                    Field[] fields = o.getClass().getDeclaredFields();
                    String[] fieldNames = new String[fields.length];
                    List<Map> list = new ArrayList();
                    Map<String, Object> infoMap;
                    for (int i = 0; i < fields.length; i++) {
                       infoMap = new HashMap<String, Object>();
                       infoMap.put("obj_name", obj_name);
                       infoMap.put("f_type", fields[i].getType().toString());
                       infoMap.put("f_name", fields[i].getName());
                       infoMap.put("f_value", getFieldValueByName(fields[i].getName(), o));
                       list.add(infoMap);
               }
               return list;
        }
}
