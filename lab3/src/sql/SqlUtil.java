package sql;

import annotation.Column;
import annotation.Table;
import db.BaseDB;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.*;

public class SqlUtil<T extends BaseDB> {

    private String getTableName(Class clazz) throws Exception {
        if (clazz.isAnnotationPresent(Table.class)) {
            Table table = (Table) clazz.getAnnotation(Table.class);
            return table.value();
        }
        throw new Exception("Require Annotation Table");
    }

    private Map<String, Object> getColumns(Class clazz, T t) throws Exception {
        Map<String, Object> map = new HashMap<>();
        for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                if (field.isAnnotationPresent(Column.class)) {
                    map.put(field.getAnnotation(Column.class).value(), field.get(t));
                }
            }
        }
        return map;
    }

    /**
     * 根据传入的参数返回查询语句
     *
     * @param t
     * @return 返回查询语句
     */
    public String query(T t) throws Exception {
        StringBuilder builder = new StringBuilder();
        builder.append("SELECT * FROM ");
        Class clazz = t.getClass();
        builder.append("`" + getTableName(clazz) + "` WHERE ");
        ArrayList<Map.Entry<String, Object>> arrayList = new ArrayList<>(getColumns(clazz, t).entrySet());
        boolean first = true;
        for (Map.Entry<String, Object> entry : arrayList) {
            Object value = entry.getValue();
            String name = entry.getKey();

            if (value instanceof Integer) {
                if (!first) {
                    builder.append(" AND ");
                }
                builder.append("`" + name + "` = " + value);
                first = false;
            } else if (value instanceof String) {
                if (!first) {
                    builder.append(" AND ");
                }
                builder.append("`" + name + "` LIKE '" + value + "'");
                first = false;
            }
        }
        builder.append(" ;");
        return builder.toString();
    }

    private List<String> getKeys(Map<String, Object> map) {
        List<String> keys = new ArrayList<>();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            if (entry.getValue() != null) {
                keys.add(entry.getKey());
            }
        }
        return keys;
    }

    private String getInsertFields(List<String> keys) {
        StringBuilder builder = new StringBuilder();
        builder.append("(");
        boolean first = true;
        for (String name : keys) {
            if (!first) {
                builder.append(",");
            }
            builder.append("`").append(name).append("`");
            first = false;

        }
        builder.append(")");
        return builder.toString();
    }

    private String getInsertValues(Map<String, Object> map, List<String> keys) {
        StringBuilder builder = new StringBuilder();
        builder.append("(");
        boolean first = true;
        for (String name : keys) {
            Object value = map.get(name);
            if (value instanceof Integer) {
                if (!first) {
                    builder.append(",");
                }
                builder.append(value);
                first = false;
            } else if (value instanceof String) {
                if (!first) {
                    builder.append(",");
                }
                builder.append("'" + value + "'");
                first = false;
            }
            if (value == null) {
                if (!name.equals("id")) {
                    if (!first) {
                        builder.append(",");
                    }
                    builder.append("'null'");
                    first = false;
                }
            }
        }
        builder.append(")");
        return builder.toString();
    }

    /**
     * 根据传入的参数返回插入语句
     *
     * @param t
     * @return 返回插入语句
     */
    public String insert(T t) throws Exception {
        StringBuilder builder = new StringBuilder();
        builder.append("INSERT INTO ");
        Class clazz = t.getClass();
        builder.append("`").append(getTableName(clazz)).append("` ");
        Map<String, Object> map = getColumns(clazz, t);
        List<String> keys = getKeys(map);
        builder.append(getInsertFields(keys)).append(" VALUES ");
        builder.append(getInsertValues(map, keys));
        builder.append(";");
        return builder.toString();
    }

    /**
     * 根据传入的参数返回插入语句
     *
     * @param list
     * @return 返回插入语句
     */
    public String insert(List<T> list) throws Exception {
        StringBuilder builder = new StringBuilder();
        builder.append("INSERT INTO ");
        boolean first = true;
        for (T t : list) {
            Class clazz = t.getClass();
            Map<String, Object> map = getColumns(clazz, t);
            List<String> keys = getKeys(map);
            if (!first) {
                builder.append(",");
            } else {
                builder.append("`").append(getTableName(clazz)).append("` ");
                builder.append(getInsertFields(keys)).append(" VALUES ");
            }
            first = false;
            builder.append(getInsertValues(map, keys));
        }

        builder.append(";");
        return builder.toString();
    }

    /**
     * 根据传入的参数返回删除语句（删除id为user.id的记录）
     *
     * @param t
     * @return 返回删除语句
     */
    public String delete(T t) throws Exception {
        StringBuilder builder = new StringBuilder();
        builder.append("DELETE FROM ");
        Class clazz = t.getClass();
        builder.append("`" + getTableName(clazz) + "` WHERE ");
        ArrayList<Map.Entry<String, Object>> arrayList = new ArrayList<>(getColumns(clazz, t).entrySet());
        boolean first = true;
        for (Map.Entry<String, Object> entry : arrayList) {
            Object value = entry.getValue();
            String name = entry.getKey();

            if (value instanceof Integer) {
                if (!first) {
                    builder.append(" AND ");
                }
                builder.append("`" + name + "` = " + value);
                first = false;
            } else if (value instanceof String) {
                if (!first) {
                    builder.append(" AND ");
                }
                builder.append("`" + name + "` LIKE '" + value + "'");
                first = false;
            }
        }
        builder.append(" ;");
        return builder.toString();
    }

    /**
     * 根据传入的参数返回更新语句（将id为user.id的记录的其它字段更新成user中的对应值）
     *
     * @param t
     * @return 返回更新语句
     */
    public String update(T t) throws Exception {
        StringBuilder builder = new StringBuilder();
        builder.append("UPDATE ");
        Class clazz = t.getClass();
        builder.append("`" + getTableName(clazz) + "` SET ");
        ArrayList<Map.Entry<String, Object>> arrayList = new ArrayList<>(getColumns(clazz, t).entrySet());
        Integer id = null;
        boolean first = true;
        for (Map.Entry<String, Object> entry : arrayList) {
            Object value = entry.getValue();
            String name = entry.getKey();
            if (name.equals("id")) {
                id = (Integer) value;
                if (id == null) {
                    throw new Exception(" id 不能为空");
                }
                continue;
            }
            if (value instanceof Integer) {
                if (!first) {
                    builder.append(" , ");
                }
                builder.append("`" + name + "` = " + value);
                first = false;
            } else if (value instanceof String) {
                if (!first) {
                    builder.append(" , ");
                }
                builder.append("`" + name + "` LIKE '" + value + "'");
                first = false;
            }
        }
        if (id == null) {
            throw new Exception(" id 不能为空");
        }
        builder.append(" WHERE `id` = " + id.toString() + " ;");
        return builder.toString();
    }

}
