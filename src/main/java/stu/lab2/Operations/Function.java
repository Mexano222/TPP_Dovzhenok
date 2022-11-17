package stu.lab2.Operations;

import java.util.Collections;
import java.util.List;

class Function {
    String table;
    List<String> fields;
    List<String> data;

    Function(String table, List<String> fields, List<String> data) {
        this.table = table;
        this.fields = fields;
        this.data = data;
    }

    Function(String table, String field, List<String> data) {
        this.table = table;
        this.fields = Collections.singletonList(field);
        this.data = data;
    }

    Function(String table, String field, String data) {
        this.table = table;
        this.fields = Collections.singletonList(field);
        this.data = Collections.singletonList(data);
    }
}
