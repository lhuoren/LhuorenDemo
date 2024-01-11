package com.gac.nioapp.test.bean;

/**
 * @Description：用于提醒积分增加
 * @Author：Sai
 * @Date：2019/4/18 10:30
 */
public class IntegralTipsResultBean {
    /**
     * addScore : false
     * score : 0
     */

    /**
     * "taskPopupExistAloneUnified": {
     * "title": "任务完成",
     * "content": "可获得积分奖励 2",
     * "cancel": "我知道了",
     * "type": 1
     * }
     */

    private boolean addScore;
    private String score;
    private String msg;
    private TaskPopupExistAloneUnified taskPopupExistAloneUnified;

    public TaskPopupExistAloneUnified getTaskPopupExistAloneUnified() {
        return taskPopupExistAloneUnified;
    }

    public void setTaskPopupExistAloneUnified(TaskPopupExistAloneUnified taskPopupExistAloneUnified) {
        this.taskPopupExistAloneUnified = taskPopupExistAloneUnified;
    }

    public boolean isAddScore() {
        return addScore;
    }

    public void setAddScore(boolean addScore) {
        this.addScore = addScore;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class TaskPopupExistAloneUnified {
        private String title;
        private String content;
        private String cancel;
        private int type;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getCancel() {
            return cancel;
        }

        public void setCancel(String cancel) {
            this.cancel = cancel;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }

}
