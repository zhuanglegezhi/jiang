package interview.skiplist;

import java.util.Random;
import java.util.Stack;

/**
 * Created by zz on 2022/11/8.
 * <p>
 * https://www.cnblogs.com/bigsai/p/14193225.html
 * <p>
 * 跳表(SkipList，全称跳跃表)是用于有序元素序列快速搜索查找的一个数据结构，跳表是一个随机化的数据结构，实质就是一种可以进行二分查找的有序链表。跳表在原有的有序链表上面增加了多级索引，通过索引来实现快速查找。跳表不仅能提高搜索性能，同时也可以提高插入和删除操作的性能。它在性能上和红黑树，AVL树不相上下，但是跳表的原理非常简单，实现也比红黑树简单很多。
 */
public class SkipList<T> {

    SkipNode<T> headNode;//头节点，入口
    int highLevel;//当前跳表索引层数
    Random random;// 用于投掷硬币
    final int MAX_LEVEL = 32;//最大的层

    SkipList() {
        random = new Random();
        headNode = new SkipNode<>(Integer.MIN_VALUE, null);
        highLevel = 0;
    }

    //其他方法

    public SkipNode<T> search(int key) {
        SkipNode<T> team = headNode;
        while (team != null) {
            if (team.key == key) {
                return team;
            } else if (team.right == null) {
                //右侧没有了，只能下降
                team = team.down;
            } else if (team.right.key > key) {
                //需要下降去寻找
                team = team.down;
            } else {//右侧比较小向右
                team = team.right;
            }
        }
        return null;
    }

    public void delete(int key) {
        //删除不需要考虑层数
        SkipNode<T> team = headNode;
        while (team != null) {
            if (team.right == null) {
                //右侧没有了，说明这一层找到，没有只能下降
                team = team.down;
            } else if (team.right.key == key) {
                //找到节点，右侧即为待删除节点
                team.right = team.right.right;//删除右侧节点
                team = team.down;//向下继续查找删除
            } else if (team.right.key > key) {
                //右侧已经不可能了，向下
                team = team.down;
            } else {
                //节点还在右侧
                team = team.right;
            }
        }
    }

    public void add(SkipNode<T> node) {
        int key = node.key;
        SkipNode<T> findNode = search(key);
        //如果存在这个key的节点
        if (findNode != null) {
            findNode.value = node.value;
            return;
        }
        //存储向下的节点，这些节点可能在右侧插入节点
        Stack<SkipNode<T>> stack = new Stack<>();
        //查找待插入的节点   找到最底层的哪个节点。
        SkipNode<T> team = headNode;
        while (team != null) {//进行查找操作
            //右侧没有了，只能下降
            if (team.right == null) {
                stack.add(team);        //将曾经向下的节点记录一下
                team = team.down;
            } else if (team.right.key > key)//需要下降去寻找
            {
                stack.add(team);//将曾经向下的节点记录一下
                team = team.down;
            } else //向右
            {
                team = team.right;
            }
        }
        int level = 1;//当前层数，从第一层添加(第一层必须添加，先添加再判断)
        SkipNode<T> downNode = null;//保持前驱节点(即down的指向，初始为null)
        while (!stack.isEmpty()) {
            //在该层插入node
            team = stack.pop();//抛出待插入的左侧节点
            SkipNode<T> nodeTeam = new SkipNode<>(node.key, node.value);//节点需要重新创建
            nodeTeam.down = downNode;//处理竖方向
            downNode = nodeTeam;//标记新的节点下次使用
            if (team.right == null) {//右侧为null 说明插入在末尾
                team.right = nodeTeam;
            }
            //水平方向处理
            else {//右侧还有节点，插入在两者之间
                nodeTeam.right = team.right;
                team.right = nodeTeam;
            }
            //考虑是否需要向上
            if (level > MAX_LEVEL)//已经到达最高级的节点啦
                break;
            double num = random.nextDouble();//[0-1]随机数
            if (num > 0.5)//运气不好结束
                break;
            level++;
            if (level > highLevel)//比当前最大高度要高但是依然在允许范围内 需要改变head节点
            {
                highLevel = level;
                //需要创建一个新的节点
                SkipNode<T> highHeadNode = new SkipNode<>(Integer.MIN_VALUE, null);
                highHeadNode.down = headNode;
                headNode = highHeadNode;//改变head
                stack.add(headNode);//下次抛出head
            }
        }
    }

    static class SkipNode<T> {
        int key;
        T value;
        SkipNode<T> right, down;//右、下个方向的指针

        public SkipNode(int key, T value) {
            this.key = key;
            this.value = value;
        }
    }

}
