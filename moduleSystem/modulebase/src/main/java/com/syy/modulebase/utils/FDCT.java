package com.syy.modulebase.utils;

public class FDCT implements DCT {
    public FDCT() {
    }

    public static double[][] fDctTransform(double[][] ablk) {
        double[][] blk = new double[8][8];

        int j;
        for(j = 0; j < 8; ++j) {
            for(int i = 0; i < 8; ++i) {
                blk[j][i] = ablk[j][i];
            }
        }

        double S16;
        double S25;
        double S34;
        double S0734;
        double S1625;
        double D07;
        double D16;
        double D25;
        double D34;
        double D0734;
        double D1625;
        double S07;
        for(j = 0; j <= 7; ++j) {
            S07 = blk[j][0] + blk[j][7];
            S16 = blk[j][1] + blk[j][6];
            S25 = blk[j][2] + blk[j][5];
            S34 = blk[j][3] + blk[j][4];
            S0734 = S07 + S34;
            S1625 = S16 + S25;
            D07 = blk[j][0] - blk[j][7];
            D16 = blk[j][1] - blk[j][6];
            D25 = blk[j][2] - blk[j][5];
            D34 = blk[j][3] - blk[j][4];
            D0734 = S07 - S34;
            D1625 = S16 - S25;
            blk[j][0] = 0.5D * 0.707106781D * (S0734 + S1625);
            blk[j][1] = 0.5D * (0.98078528D * D07 + 0.831469612D * D16 + 0.555570233D * D25 + 0.195090322D * D34);
            blk[j][2] = 0.5D * (0.923879532D * D0734 + 0.382683432D * D1625);
            blk[j][3] = 0.5D * (0.831469612D * D07 - 0.195090322D * D16 - 0.98078528D * D25 - 0.555570233D * D34);
            blk[j][4] = 0.5D * 0.707106781D * (S0734 - S1625);
            blk[j][5] = 0.5D * (0.555570233D * D07 - 0.98078528D * D16 + 0.195090322D * D25 + 0.831469612D * D34);
            blk[j][6] = 0.5D * (0.382683432D * D0734 - 0.923879532D * D1625);
            blk[j][7] = 0.5D * (0.195090322D * D07 - 0.555570233D * D16 + 0.831469612D * D25 - 0.98078528D * D34);
        }

        for(j = 0; j <= 7; ++j) {
            S07 = blk[0][j] + blk[7][j];
            S16 = blk[1][j] + blk[6][j];
            S25 = blk[2][j] + blk[5][j];
            S34 = blk[3][j] + blk[4][j];
            S0734 = S07 + S34;
            S1625 = S16 + S25;
            D07 = blk[0][j] - blk[7][j];
            D16 = blk[1][j] - blk[6][j];
            D25 = blk[2][j] - blk[5][j];
            D34 = blk[3][j] - blk[4][j];
            D0734 = S07 - S34;
            D1625 = S16 - S25;
            blk[0][j] = 0.5D * 0.707106781D * (S0734 + S1625);
            blk[1][j] = 0.5D * (0.98078528D * D07 + 0.831469612D * D16 + 0.555570233D * D25 + 0.195090322D * D34);
            blk[2][j] = 0.5D * (0.923879532D * D0734 + 0.382683432D * D1625);
            blk[3][j] = 0.5D * (0.831469612D * D07 - 0.195090322D * D16 - 0.98078528D * D25 - 0.555570233D * D34);
            blk[4][j] = 0.5D * 0.707106781D * (S0734 - S1625);
            blk[5][j] = 0.5D * (0.555570233D * D07 - 0.98078528D * D16 + 0.195090322D * D25 + 0.831469612D * D34);
            blk[6][j] = 0.5D * (0.382683432D * D0734 - 0.923879532D * D1625);
            blk[7][j] = 0.5D * (0.195090322D * D07 - 0.555570233D * D16 + 0.831469612D * D25 - 0.98078528D * D34);
        }

        return blk;
    }
}
