package com.core.util.utilities;


import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;

public final class HashUtil {

    private HashUtil(){}

    public static String sha256(String base) {
        if (ObjUtil.isBlank(base))
            return null;
        return Hashing.sha256().hashString(base, Charsets.UTF_8).toString();
    }

    public static String md5(String base) {
        if (ObjUtil.isBlank(base))
            return null;
        return Hashing.md5().hashString(base, Charsets.UTF_8).toString();
    }
}
