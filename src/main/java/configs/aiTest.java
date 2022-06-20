package configs;

import org.junit.jupiter.api.Test;

public class aiTest {
    /**
     *     //用来估值每个情形的价值
     *     @param flag;//AI执棋时的颜色，flag==1为黑棋，flag==2为白棋 （int）
     *     @param value;//当前形势的价值   （int）
     *     @param chessOnBoard;//保存这个情形时的棋子情况   （int[][]）
     *     @param change;//保存每个情形相对于主情形的变化情况    （三元对象x，y，棋子颜色（用数字表示））
     *     @param parent;//父节点                          （BoardValue）
     *     @param children;//子树                            (BoardValue组成的链表)
     *     @method getTmpChessBoard() 获得当前形势数组
     *     @method getChildren() 获得子树的链表
     *     @method getValue() 计算当前情形的价值，价值越高说明黑棋越有优势，价值越低说明白棋越有优势
     *
     */
    @Test
    void testAi(BoardValue boardValue){
        //本测试用于测试Ai算法过程，每一步都会有注释
        //Ai算棋深度为3，分别对应 接收的棋盘状态->AI下一步->对手下一步->AI下一步
        //传进boardValue对象时，该对象只有 chessOnBoard 和 flag 两个属性  我们将其设为Max层 n=1
        //获得第1层的子树，Max层的子树，即第2层Min层，每个子树对象代表AI下一步时的各种情形
        boardValue.getChildren();
        //对于第2层的每个形势，获得他们的子树，即第3层Max层
        for (BoardValue child : boardValue.children) {//child代表第2层的每个形势
            child.getChildren();
        }
        //对于第3层的每个形势，获得他们的子树，即叶子层，在叶子层我们可以计算他们的每个估值函数
        for (BoardValue child : boardValue.children) {//child代表第1层的每个形势
            for (BoardValue value : child.children) {//value代表第2层的每个形势
                value.getChildren();//获得的链表即第3层的每个形势（不知道会不会爆内存）
            }
        }
        //计算叶子层的各个估值
        for (BoardValue child : boardValue.children) {//child代表第1层的每个形势
            for (BoardValue value : child.children) {//value代表第2层的每个形势
                for (BoardValue leaf : value.children) {
                    leaf.value = Utils.getValue(leaf.getTmpChessBoard());//得出每个叶子节点的值
                }
            }
        }

        //开始剪枝
        boardValue.a = -999999999;     //a值最小
        boardValue.b = 999999999;     //a值最小


    }


}
