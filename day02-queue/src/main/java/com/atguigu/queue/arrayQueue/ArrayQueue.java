package com.atguigu.queue.arrayQueue;

import java.util.Scanner;

public class ArrayQueue {
    public static void main(String[] args) {
//         先创建一个队列
        Queue queue = new Queue(3);
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
    private int maxSizr;//容量
    private int front;//对头
    private int rear;//对尾
    private int[] arr;//存储数据

//    初始化创建队列
    public Queue(int maxSizr) {
        this.maxSizr = maxSizr;
        arr=new int[maxSizr];
        front=-1;
        rear=-1;
    }

//    判断队列是否为满
    public boolean isFull(){
        return rear==maxSizr-1;
    }

//    判断队列是否 空了
    public boolean isEmpty(){
        return rear==front;
    }

//    添加队列
    public void addQueue(int n){
        if (isFull()){
            System.out.println("对满了");
            return;
        }
        rear++;//往后移
        arr[rear]=n;
    }

//    获取队列数据
    public int getQueue(){
        if ( isEmpty()){
            throw new RuntimeException("队列空，不能取数据");
        }
        front++;
        return arr[front];
    }

//    显示队列所有的数据
    public void showQueue(){
        if ( isEmpty()){
            System.out.println("对是空的，没有数据");
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("arr[%d]=%d\n",i,arr[i]);
        }
    }

//    显示队列的头数据
    public int headQueue(){
        if ( isEmpty()){
            throw new RuntimeException("队列空，不能取数据");
        }
        return arr[front+1];
    }
}
