package com.example.cadastroalunos.util;

import static com.orm.util.ReflectionUtil.getDomainClasses;

import android.content.Context;

import com.orm.SugarRecord;

import java.util.List;

public class DeleteAll {
    public static void deleteAllrecords(Context applicationContext) {
        List<Class> domainClasses = getDomainClasses(applicationContext);
        for (Class domain : domainClasses) {
            SugarRecord.deleteAll(domain);
        }
    }
}
