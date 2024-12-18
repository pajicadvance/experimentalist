package me.pajic.experimentalist.util;

import com.google.common.collect.ImmutableList;

import java.util.List;

public class ModUtil {

    private static List<String> globalFeatures = ImmutableList.of("vanilla");

    public static void addGlobalFeature(String addition) {
        ImmutableList.Builder<String> builder = ImmutableList.builder();
        for (String s : globalFeatures) builder.add(s);
        builder.add(addition);
        globalFeatures = builder.build();
    }

    public static List<String> getGlobalFeatures() {
        return globalFeatures;
    }
}
