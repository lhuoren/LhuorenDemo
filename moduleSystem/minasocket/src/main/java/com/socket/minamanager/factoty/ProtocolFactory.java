package com.socket.minamanager.factoty;

public class ProtocolFactory {

    /**
     * 生成控制云台的数据
     *
     * @param roll        2000-40000
     * @param pitch       2000-40000
     * @param yaw         2000-40000
     * @param videoChange 1-3 视频切换 2->3
     * @param regulate    1-3 打档  2->3
     * @param videoModel  1-3 伪彩  2->3
     * @param times       1-3 变倍  2->3 zoom++ 2->1 zoom--
     * @param action      1-3 拍照/录像 2->3 拍照 2->1录像 x->2 取消状态
     * @param gimbal      1-3 1 跟随(roll=4000) 2 回中(roll=3000) 3 自由(roll=2000)
     * @return
     */
    public static byte[] getData(int roll, int pitch, int yaw, int videoChange, int regulate, int videoModel, int times, int action, int gimbal) {
        byte[] data = new byte[19];

        data[0] = 0x23;
        data[1] = 0x31;
        data[2] = 0x0d;
        data[3] = 0x0a;
        data[4] = 0x4b;
        data[5] = 0x43;
        data[6] = (byte) 0xe0;
        data[7] = 0x08;
        data[8] = 0x05;

        int rocker_1 = roll & 0x0FF;
        int rocker_2 = ((roll & 0xF00) >> 8) + ((pitch & 0x00F) << 4);
        int rocker_3 = pitch >> 4;
        int rocker_4 = yaw & 0x0FF;
        int rocker_5 = (yaw & 0xF00) >> 8;

        int control_1 = videoChange + (regulate << 2) + (videoModel << 4) + (times << 6);

        int control_2 = action + (gimbal << 2)+0x10;//+0x10 显示返回

        data[9] = (byte) rocker_1;
        data[10] = (byte) rocker_2;
        data[11] = (byte) rocker_3;
        data[12] = (byte) rocker_4;
        data[13] = (byte) rocker_5;

        data[14] = (byte) control_1;
        data[15] = (byte) control_2;

        int d6 = data[6]&0x0FF;
        int d7 = data[7]&0x0FF;
        int d8 = data[8]&0x0FF;
        int d9 = data[9]&0x0FF;
        int d10 = data[10]&0x0FF;
        int d11 = data[11]&0x0FF;
        int d12 = data[12]&0x0FF;
        int d13 = data[13]&0x0FF;
        int d14 = data[14]&0x0FF;
        int d15 = data[15]&0x0FF;

        int date16 = d6+d7+d8+d9+d10+d11+d12+d13+d14+d15;

        data[16] = (byte)(date16&0x0FF);
        data[17] = 0x5a;
        data[18] = 0x51;

        return data;

    }

}
