

public class AparseArray {
    public static void main(String[] args) {
        // 创建一个原始的二维数组 11 * 11
        // 0: 表示没有棋子， 1 表示 黑子 2 表蓝子
        int[][] chessarr= new int[11][11];
         chessarr[1][2] = 1;
         chessarr[2][3] = 2;
         chessarr[4][5] = 2;
        System.out.println("原始二维数组");
        for (int[] ints : chessarr) {
            for (int anInt : ints) {
                System.out.printf("%d\t",anInt);
            }
            System.out.println();
        }
        // 将二维数组 转 稀疏数组的思想
        // 1. 先遍历二维数组 得到非0数据的个数
        int num = 0;
        for (int i = 0; i < 11; i++) {
            for (int i1 = 0; i1 < 11; i1++) {
//               计算非零的个数
                if (chessarr[i][i1] !=0){
                    num++;
                }
            }
        }
        System.out.println(num);
        // 2. 创建对应的稀疏数组 你要设计稀疏数组
        int[][] sparseArry = new int[num+1][3];
        sparseArry[0][0]=11;
        sparseArry[0][1]=11;
        sparseArry[0][2]=num;

        // 遍历二维数组，将非0的值存放到 sparseArr中
        //1.count 用于记录是第几个非0数据
        int count = 0;
        for (int i = 0; i < 11; i++) {
            for (int i1 = 0; i1 < 11; i1++) {
//               计算非零的个数
                if (chessarr[i][i1] !=0){
                    count++;
                    sparseArry[count][0]=i;//行
                    sparseArry[count][1]=i1;//列
                    sparseArry[count][2]=chessarr[i][i1];//数据
                }
            }
        }
        // 输出稀疏数组的形式
        System.out.println();
        System.out.println("得到稀疏数组为~~~~");
        for (int i = 0; i < sparseArry.length; i++) {
            System.out.printf("%d\t%d\t%d\t\n",sparseArry[i][0],sparseArry[i][1],sparseArry[i][2]);
        }
        System.out.println();
        //将稀疏数组 --》 恢复成 原始的二维数组
        /**
         * 1.先读取稀疏数组的第一行，根据第一行的数据，创建原始数据， 比如上面的  chessArr2 = int [11][11]
         * 2.读取稀疏数组的后几行数据，并赋值给原始二维数组数据；
         */

//       1. 先读取稀疏数组的第一行，根据第一行的数据，创建原始的二维数
        int[][] chessArry2 =new int[sparseArry[0][0]][sparseArry[0][1]];
//       //2. 在读取稀疏数组后几行的数据(从第二行开始)，并赋给 原始的二维数组 即可
        for (int i = 1; i < sparseArry.length; i++) {
           chessArry2[sparseArry[i][0]][sparseArry[i][1]] = sparseArry[i][2];
        }

//        输出恢复后的二维数组
        System.out.println();
        System.out.println("恢复后的二维数组");
        for (int[] ints : chessArry2) {
            for (int anInt : ints) {
                System.out.printf("%d\t",anInt);
            }
            System.out.println();
        }







    }


}
