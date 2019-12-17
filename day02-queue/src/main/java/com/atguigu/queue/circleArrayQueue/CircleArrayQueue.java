package com.atguigu.queue.circleArrayQueue;

import java.util.Scanner;

public class CircleArrayQueue {
    public static void main(String[] args) {
//         先创建一个队列
        Queue queue =new Queue(5);
        char key=' ';//接受用户的输入
        Scanner scanner = new Scanner(System.in);
        boolean lock = true;
        while (lock){
            System.out.println("s(show): 显示队列");
            System.out.println("e(exit): 退出程序");
            System.out.println("a(add): 添加数据到队列");
            System.out.println("g(get): 从队列取出数据");
            System.out.println("h(head): 查看队列头的数据");
            key=scanner.next().charAt(0);//接受一个字符
            switch (key){
                case 's'://显示队列
                    queue.showQueue();
                    break;
                case 'a'://入队
                    int val= scanner.nextInt();
                    queue.addQueue(val);
                    break;
                case 'g'://出队
                    int queue1 = queue.getQueue();
                    System.out.printf("取出的数据是%d\n", queue1);
                    break;
                case 'h'://查看对头
                    int i = queue.headQueue();
                    System.out.printf("队列头的数据是%d\n", i);
                    break;
                case 'e'://退出程序
                    scanner.close();
                    lock=false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出~~");
    }
}

class Queue{
    private int maxSize;//表示数组的最大容量
    //front 变量的含义做一个调整： front 就指向队列的第一个元素, 也就是说 arr[front] 就是队列的第一个元素
    //front 的初始值 = 0
    private int front;
    //rear 变量的含义做一个调整：rear 指向队列的最后一个元素的后一个位置. 因为希望空出一个空间做为约定.
    //rear 的初始值 = 0
    private int rear; // 队列尾
    private int[] data ;// 该数据用于存放数据, 模拟队列

    public Queue(int maxSize) {
        this.maxSize = maxSize;
        data=new int[maxSize];
    }

    //   判断满
    public boolean full(){
        return front==rear;
    }
//  判断空
    public boolean isEmpty(){
        return (rear+1)%maxSize==front;
    }

//    添加数据到队列
    public void addQueue(int n){
        if ( full()) {
            System.out.println("队列满，不能加入数据~");
            return;
        }
        data[rear]=n;
//        将rear后移，这里必须考虑取模
        rear=(rear+1)%maxSize;
    }

//    获取队列的数据, 出队列
    public int getQueue(){

// 判断队列是否空
        if (isEmpty()) {
            // 通过抛出异常
            throw new RuntimeException("队列空，不能取数据");
        }

//       这是需要分析出front是指向队列的第一个元素
//        1.先把front 对应的值保留保留到一个临时变量
//        2.将front 后移，考虑取模
//        3.将临时保存的变量返回
        int val =data[front];
        front = (front+1)%maxSize;
        return val;
    }

//    显示队列的元素
    public void showQueue(){
        // 遍历
        if (isEmpty()) {
            System.out.println("队列空的，没有数据~~");
            return;
        }
        for (int i =front ;i< front+size(); i++){
            System.out.printf("arr[%d]=%d\n", i % maxSize, data[i % maxSize]);
        }


    }

//    当前的队列的有效个数
    public int size(){
        // rear = 2
        // front = 1
        // maxSize = 3
        return (rear+maxSize-front)%maxSize;
    }

// 显示队列的头数据， 注意不是取出数据
    public int headQueue(){
        if (isEmpty()) {
            throw new RuntimeException("队列空，不能取数据");
        }
        return data[front];

    }




}
