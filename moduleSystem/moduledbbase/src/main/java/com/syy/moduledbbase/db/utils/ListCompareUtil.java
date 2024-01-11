package com.syy.moduledbbase.db.utils;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.test.espresso.core.internal.deps.guava.collect.Lists;

import com.syy.moduledbbase.db.entity.moduleuav.plantask.PlanTask;
import com.syy.moduledbbase.db.entity.moduleuav.plantask.channel.PlanChannelAirTask;
import com.syy.moduledbbase.db.entity.moduleuav.plantask.fine.PlanFineAirTask;
import com.syy.moduledbbase.db.entity.moduleuav.plantask.line.ChildRoutes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListCompareUtil {

    public static void main(String[] args) {
        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        for (int i = 0; i < 3000000; i++) {
            list1.add("test" + i);
            list2.add("test" + i * 3);
        }

        mapCompare(list1, list2);

    }

    /**
     * 对比两个list取出差并和的集合
     *
     * @param oldList 旧集合
     * @param newList 新集合
     * @param flag    1,旧数据;2,重复的数据;3,新增的数据
     * @return
     */
    public static List<String> getCompareList(List<String> oldList, List<String> newList, Integer flag) {
        long st = System.nanoTime();

        Map<String, Integer> map = mapCompare(oldList, newList);
        List<String> result;

        List<String> oldData = Lists.newArrayList();
        List<String> addData = Lists.newArrayList();
        List<String> repeatData = Lists.newArrayList();

        map.entrySet().forEach(stringIntegerEntry -> {
            if (stringIntegerEntry.getValue() == 1) {
                oldData.add(stringIntegerEntry.getKey());
            } else if (stringIntegerEntry.getValue() == 2) {
                repeatData.add(stringIntegerEntry.getKey());
            } else {
                addData.add(stringIntegerEntry.getKey());
            }
        });

        if (flag.equals(1)) {
            result = oldData;
        } else if (flag.equals(2)) {
            result = repeatData;
        } else {
            result = addData;
        }
        System.out.println("getCompareList " + (System.nanoTime() - st));
        return result;

    }

    /**
     * 单独获取两个不用集合的数据，高效率
     *
     * @param list1
     * @param list2
     * @return
     */
    public static List<String> getDiffrentList(List<String> list1, List<String> list2) {
        long st = System.nanoTime();
        List<String> diff = Lists.newArrayList();
        //优先使用数据量大的list，提高效率
        List<String> maxList = list1;
        List<String> minList = list2;
        if (list2.size() > list1.size()) {
            maxList = list2;
            minList = list1;
        }
        Map<String, Integer> map = new HashMap<>(maxList.size());
        for (String string : maxList) {
            map.put(string, 1);
        }

        for (String string : minList) {
            if (map.get(string) != null) {
                map.put(string, 2);
                continue;
            }
            diff.add(string);
        }
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) {
                diff.add(entry.getKey());
            }
        }
        System.out.println("getDiffrentList total times " + (System.nanoTime() - st));
        return diff;

    }

    /**
     * 对比两个list，返回list并集
     *
     * @param oldList
     * @param newList
     * @return value为1, 旧数据;2,重复的数据;3,新增的数据
     */
    
    public static Map<String, Integer> mapCompare(List<String> oldList, List<String> newList) {
        long st = System.nanoTime();

        //若知道两个list大小区别较大，以大的list优先处理
        Map<String, Integer> map = new HashMap<>(oldList.size());

        //lambda for循环数据量越大，效率越高，小数据建议用普通for循环
        oldList.forEach(s -> map.put(s, 1));

        newList.forEach(s -> {
            if (map.get(s) != null) {
                //相同的数据
                map.put(s, 2);
            } else {
                //若只是比较不同数据，不需要此步骤，浪费资源
                map.put(s, 3);
            }
        });

        System.out.println("mapCompare total times " + (System.nanoTime() - st));
        return map;
    }

    /**
     * 对比两个list，返回list并集
     *
     * @param oldList
     * @param newList
     * @return value为1, 旧数据;2,重复的数据;3,新增的数据
     */
    
    public static List<PlanTask> comparePlanTaskList(List<PlanTask> oldList, List<PlanTask> newList) {
        long st = System.nanoTime();
        List<PlanTask> comparePlanTaskList = Lists.newArrayList();
        //若知道两个list大小区别较大，以大的list优先处理
        Map<PlanTask, Integer> map = new HashMap<>(oldList.size());

        //lambda for循环数据量越大，效率越高，小数据建议用普通for循环
//        oldList.forEach(s -> map.put(s, 1) );
//        newList.forEach(s -> {
//            if(map.get(s)!=null)
//            {
//                //相同的数据
//                map.put(s, 2);
//            }else {
//                //若只是比较不同数据，不需要此步骤，浪费资源
//                map.put(s,3);
//            }
//        });

        for (PlanTask planTask : oldList) {
            map.put(planTask, 1);
        }

        for (PlanTask s : newList) {
            if (map.get(s) != null) {
                //相同的数据
                map.put(s, 2);
            } else {
                //若只是比较不同数据，不需要此步骤，浪费资源
                map.put(s, 3);
            }
        }

        for (Map.Entry<PlanTask, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 3) {
                comparePlanTaskList.add(entry.getKey());
            }
        }

        System.out.println("mapCompare total times " + (System.nanoTime() - st));
        return comparePlanTaskList;
    }

    /**
     * 找出两个list中的不同元素
     * 用Map存放List1和List2的元素作为key，value为其在List1和List2中出现的次数
     * 出现次数为1的即为不同元素，查找次数为list1.size() + list2.size()，较方法1和2，是极大简化
     * 【效率最高】
     *
     * @param listA
     * @param listB
     * @return
     */
    public static List<PlanTask> getDiffPlanTaskList(List<PlanTask> listA, List<PlanTask> listB) {
        List<PlanTask> diff = new ArrayList<>();
        List<PlanTask> maxList = listA;
        List<PlanTask> minList = listB;
        if (listB.size() > listA.size()) {
            maxList = listB;
            minList = listA;
        }
        Map<String, Integer> map = new HashMap<>(maxList.size());
        LogUtils.e("reasonObj_onSuccess", "getDiffPlanTaskList-1:" + maxList.size());
        for (PlanTask planTask : maxList) {
            map.put(planTask.getId(), 1);
            diff.add(planTask);
        }
        for (PlanTask planTask : minList) {
            LogUtils.e("reasonObj_onSuccess", "getDiffPlanTaskList-1:" + (map.get(planTask.getId()) != null));
            if (map.get(planTask.getId()) != null) {
                map.put(planTask.getId(), 2);
                continue;
            }
            LogUtils.e("reasonObj_onSuccess", "getDiffPlanTaskList-2:" + (map.get(planTask.getId()) == null));
            diff.add(planTask);
        }
        return diff;
    }

    //找出两个list中的不同元素【retainAll】
    public static List<PlanTask> getDiff2PlanTaskList(List<PlanTask> listA, List<PlanTask> listB) {
        List<PlanTask> res = new ArrayList<>();//不同的元素
        List<PlanTask> dif = new ArrayList<>(listA); //交集
        //先求出两个list的交集；
        dif.retainAll(listB);
        res.addAll(listA);
        res.addAll(listB);
        //用合集去掉交集，就是不同的元素；
        res.removeAll(dif);
        return res;
    }

    /**
     * 找出两个list中的不同元素
     * 用Map存放List1和List2的元素作为key，value为其在List1和List2中出现的次数
     * 出现次数为1的即为不同元素，查找次数为list1.size() + list2.size()，较方法1和2，是极大简化
     * 【效率最高】
     *
     * @param listA
     * @param listB
     * @return
     */
    public static List<ChildRoutes> getDiffChildRoutesList(List<ChildRoutes> listA, List<ChildRoutes> listB) {
        List<ChildRoutes> diff = new ArrayList<>();
        List<ChildRoutes> maxList = listA;
        List<ChildRoutes> minList = listB;
        if (listB.size() > listA.size()) {
            maxList = listB;
            minList = listA;
        }
        Map<String, Integer> map = new HashMap<>(maxList.size());
        for (ChildRoutes childRoutes : maxList) {
            map.put(childRoutes.getId(), 1);
            diff.add(childRoutes);
        }
        for (ChildRoutes childRoutes : minList) {
            LogUtils.e("reasonObj_onSuccess", "getDiffPlanTaskList-1:" + (map.get(childRoutes.getId()) != null));
            if (map.get(childRoutes.getId()) != null) {
                map.put(childRoutes.getId(), 2);
                continue;
            }
            LogUtils.e("reasonObj_onSuccess", "getDiffPlanTaskList-2:" + (map.get(childRoutes.getId()) == null));
            diff.add(childRoutes);
        }
        return diff;
    }

    public static PlanFineAirTask getDiffPlanFineAirTaskList(PlanFineAirTask planFineAirTask, List<PlanFineAirTask> planFineAirTaskList) {
        PlanFineAirTask diff = null;
        for (PlanFineAirTask mPlanFineAirTask : planFineAirTaskList) {
            if (planFineAirTask.getId().equals(mPlanFineAirTask.getId())) {
                return diff;
            }
            diff = planFineAirTask;
        }
        return diff;
    }

    public static PlanChannelAirTask getDiffPlanChannelAirTaskList(PlanChannelAirTask planChannelAirTask, List<PlanChannelAirTask> planChannelAirTaskList) {
        PlanChannelAirTask diff = null;
        for (PlanChannelAirTask mPlanChannelAirTask : planChannelAirTaskList) {
            if (planChannelAirTask.getId().equals(mPlanChannelAirTask.getId())) {
                return diff;
            }
            diff = planChannelAirTask;
        }
        return diff;
    }

}

