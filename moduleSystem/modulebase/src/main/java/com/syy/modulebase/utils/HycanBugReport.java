package com.syy.modulebase.utils;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @author： jack
 * @date： 2022/5/5 2:00 下午
 * @description： TODO
 */
public class HycanBugReport {

    private static Iterator<BugReport> bugReportIterator = ServiceLoader
            .load(BugReport.class)
            .iterator();


    public static void report(Throwable throwable) {
        while (bugReportIterator.hasNext()) {
            BugReport bugReport = bugReportIterator.next();
            try {
                bugReport.report(throwable);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
