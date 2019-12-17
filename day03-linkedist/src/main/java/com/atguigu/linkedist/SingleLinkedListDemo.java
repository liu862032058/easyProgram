package com.atguigu.linkedist;

public class SingleLinkedListDemo {

    public static void main(String[] args) {
        //进行测试
        //先创建节点
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");

        //创建要给链表
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        //加入
//        singleLinkedList.add(hero1);
//        singleLinkedList.add(hero2);
//        singleLinkedList.add(hero3);
//        singleLinkedList.add(hero4);

        //加入按照编号的顺序
        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero4);
        singleLinkedList.addByOrder(hero2);
        singleLinkedList.addByOrder(hero3);

        System.out.println("原来的链表的情况");
        singleLinkedList.list();
        //测试修改节点的代码
        HeroNode newHeroNode = new HeroNode(2, "小卢", "玉麒麟~~");
        singleLinkedList.update(newHeroNode);

        System.out.println("修改后的链表情况~~");
        singleLinkedList.list();
        //删除一个节点
        singleLinkedList.del(1);
        singleLinkedList.del(4);
        System.out.println("删除后的链表情况~~");
        singleLinkedList.list();


    }
// 求单链表中有效节点的个数
    public static int getLength(HeroNode heroNode){
        if (heroNode.next ==null){
            return 0;
        }
        int count=0;
         HeroNode cur =heroNode.next;
        while (cur !=null){
            count++;
            cur = cur.next;
        }
        return count;
    }


//   查找单链表中的倒数第K个结点
    public static HeroNode findLastIndexNode(HeroNode heroNode,int index){
        if (heroNode.next == null){
            return null;
        }

        HeroNode temp =heroNode.next;
        boolean flag =false;
        int size = getLength(heroNode);//获取有效个数

//         先做index 的校验
        if (index<=0 || index>size){
            return null;
        }
//        第二次遍历，size - index 的位置，就是我们倒数第k个节点
        for (int i = 0; i <size-index ; i++) {
            temp = temp.next;
        }
        return temp;
    }


//单链表反转
public static void reverSetList(HeroNode heroNode){
//        如果当前是空链表或者只有一个节点的无须反转
        if (heroNode.next == null || heroNode.next.next==null){
            return;
        }
//        定义一个辅助指针，帮助我们遍历原来的表
        HeroNode temp =heroNode.next;
        HeroNode next = null;//指向当前的指针

         HeroNode reverseHead = new HeroNode(0,"","");//定义一个新的链表
//    遍历原来的链表，每遍历一个节点，就将其取出，并放在新的链表reverseHead 的最前端
    while (temp !=null){
        next=temp.next;//先暂时保存当前的节点的下一个节点，因为后面需要使用
        temp.next = reverseHead.next;//将temp 的下一个节点指向新的链表的最前端
        reverseHead.next =temp;//将cur连接到新的链表上
        temp = next;//让temp 往后移
    }
    heroNode.next =reverseHead.next;


}



//    从尾到头打印
//    合并两个 有序的单链表，合并之后的链表依然有序




}
//定义SingleLinkedList 管理我们的英雄
class SingleLinkedList{
    private HeroNode head = new HeroNode(0,"","");//定义一个头结点

//    返回头结点
    public HeroNode getHead(){
        return head;
    }

//    添加节点到单向链表
//    思路，当不考虑链表的最后节点
//    1.找到当前链表的最后节点
//    2.将最后的节点的next 指向 新的节点
    public void add(HeroNode heroNode){
//        因为head节点不能动，因此我们需要一个辅助遍历temp
        HeroNode temp = head;
//        遍历链表 找到最后
        while (true){
            //找到链表的最后
            if (temp.next==null){
                break;
            }
            temp=temp.next;
        }
//         当退出while循环时，temp就指向链表的最后
//        将最后的链表指向新节点
        temp.next =heroNode;
    }

//第二种添加英雄的时候，根据排名将英雄插入指定的位置
//    如果有这个排名，则添加失败，并给出提示
    public void addByOrder(HeroNode heroNode){
        //        因为head节点不能动，因此我们需要一个辅助遍历temp
        //因为单链表，因为我们找的temp 是位于 添加位置的前一个节点，否则插入不了
        HeroNode temp=head;
        boolean flag = false;//添加编号标志是否存在
        while (true){
            if (temp.next == null){
                break;//找到链表的最后,没有找到
            }
//            位置找到，就在temp的后面插入
            if (temp.next.no>heroNode.no){
                break;
            }else if (temp.next.no == heroNode.no){//说明希望添加的heroNode的编号依然存在
                flag=true;//说明编号存在
                break;
            }
            temp=temp.next;//后移，遍历当前链表
        }
        if (flag){
            System.out.printf("准备插入的英雄的编号%d已经存在，不能加入\n",heroNode.no);
        }else {
//            插入到链表中，temp 的后面
            heroNode.next =temp.next;
            temp.next=heroNode;
        }
    }

    /**
     * 修改节点的信息，根据no节点来修改
     * 说明
     * 1.根据newHeroNode 的no来修改即可
     */
    public void update(HeroNode heroNode){

        if (head.next == null){
            System.out.println("链表为空");
            return;
        }
        //找到需要修改的节点, 根据no编号
        //定义一个辅助变量
        HeroNode temp = head;
        boolean flag =false; //表示是否找到该节点
        while (true){
            if (temp.next==null){
                break; //已经遍历完链表
            }
            if (temp.next.no == heroNode.no){//找到
                flag=true;
                break;
            }
            temp =temp.next;
        }
        if (flag){
            temp.next.name =heroNode.name;
            temp.next.nickname =heroNode.nickname;
        }else {
            System.out.printf("没有找到 编号 %d 的节点，不能修改\n", heroNode.no);
        }
    }

    //删除节点
    //思路
    //1. head 不能动，因此我们需要一个temp辅助节点找到待删除节点的前一个节点
    //2. 说明我们在比较时，是temp.next.no 和  需要删除的节点的no比较
    public void del(int no){
        if (head.next == null){
            System.out.println("链表为空");
            return;
        }
        //找到需要修改的节点, 根据no编号
        //定义一个辅助变量
        HeroNode temp = head;
        boolean flag =false; //表示是否找到该节点

        while (true){
            if (temp.next==null){
                break; //已经遍历完链表
            }
            if (temp.next.no == no){//找到
                //找到的待删除节点的前一个节点temp
                flag=true;
                break;
            }
            temp =temp.next; //temp后移，遍历
        }
        if (flag){
            temp.next=temp.next.next;
        }else {
            System.out.printf("要删除的 %d 节点不存在\n", no);
        }

    }


//    显示链表的遍历
    public void list(){
//        判断链表是否为空
        if (head.next == null){
            System.out.println("链表为空");
            return;
        };
//        辅助变量
        HeroNode temp = head.next;
        while (true){
            if (temp==null){
                break;
            }
//            输出节点信息
            System.out.println(temp);
//            然后temp要往后 一定小心
            temp= temp.next;
        }


    }






}

//定义HeroNode ， 每个HeroNode 对象就是一个节点
class HeroNode{
    public int no;
    public String name;
    public String nickname;
    public HeroNode next;

    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
