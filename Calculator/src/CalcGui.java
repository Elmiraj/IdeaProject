import java.awt.BorderLayout;

import java.awt.Point;
import java.awt.Color;
import java.awt.TextField;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Stack;
import javax.swing.JFrame;

//计算机程序的图形界面，计算加减乘除，可以有小括号，数字可以为小数
/**
 * CalcGui class
 *
 * @author Jun Zhang
 * @date 2018/2/27
 */
public class CalcGui extends JFrame {

    private static final long serialVersionUID = 1L;
    private TreeNode resultTree;
    private String textFieldString;
    private boolean calcSuccess = true;
    private char ops[][] = {
            {'>', '>', '<', '<', '<', '>', '>'},

            {'>', '>', '<', '<', '<', '>', '>'},

            {'>', '>', '>', '>', '<', '>', '>'},

            {'>', '>', '>', '>', '<', '>', '>'},

            {'<', '<', '<', '<', '<', '=', 'E'},

            {'E', 'E', 'E', 'E', 'E', 'E', 'E'},

            {'<', '<', '<', '<', '<', 'E', '='},
    };

    private Stack<TreeNode> nodeStack = new Stack<>();
    private Stack<Character> opStack = new Stack<>();

    public static void main(String[] args) {
        CalcGui gui = new CalcGui();
        gui.userGUI();
    }

    private void userGUI(){
        this.setLayout(new BorderLayout());
        TextField tf = new TextField("请输入表达式，按Enter开始计算",40);
        tf.selectAll();
        tf.getText();
        tf.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    textFieldString = ((TextField)e.getComponent()).getText();
                    calcSuccess = true;
                    resultTree = null;
                    try{
                        resultTree = calc(textFieldString + "#");
                    }catch (Exception e1){
                        calcSuccess = false;
                    }

                    CalcGui.this.repaint();
                }
            }
        });
        this.add(tf,BorderLayout.NORTH);
        this.setSize(500,500);
        this.setResizable(true);
        this.setTitle("Calculator");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    private int levelHeight = 60;
    private int diameter = 25;
    @Override
    public void paint(Graphics g){
        super.paint(g);
        if(calcSuccess){
            if(resultTree != null){
                g.drawString("计算结果为：" + resultTree.value,10,80);
                int rootBeginX = this.getWidth()/2;
                int rootBeginY = 100;
                Point p = new Point(rootBeginX,rootBeginY);
                drawTree(g,resultTree,p,this.getWidth()/2-20,p);
            }
        }else{
            g.setColor(Color.RED);
            g.drawString("表达式语法有误！",10,80);
        }
    }

    private void drawTree(Graphics g,TreeNode node, Point pme, int width, Point pfather){
        if (node == null){
            return;
        }
        g.setColor(Color.GREEN);
        g.drawLine(pme.x,pme.y,pfather.x,pfather.y);
        if(node.op != 'E'){
            g.setColor(Color.BLACK);
            g.drawString(String.valueOf(node.op),pme.x,pme.y);
        }else {
            g.setColor(Color.BLACK);
            g.drawString(String.valueOf(node.value),pme.x - diameter/2,pme.y);
        }

        drawTree(g,node.lft,new Point(pme.x - width/2,pme.y + levelHeight),width/2, pme);
        drawTree(g,node.rt,new Point(pme.x + width/2,pme.y + levelHeight), width/2, pme);
    }

    private TreeNode calc(String inStr) throws Exception{
        opStack.push('#');
        StringBuilder buf = new StringBuilder();
        int i = 0;
        while(i < inStr.length()){
            if(Character.isDigit(inStr.charAt(i)) || inStr.charAt(i) == '.'){
                buf.delete(0, buf.length());
                while (i < inStr.length() && (Character.isDigit(inStr.charAt(i)) || inStr.charAt(i) == '.')){
                    buf.append(inStr.charAt(i++));
                }
                Double number = Double.parseDouble(buf.toString());
                nodeStack.push(new TreeNode(number));
            }else if(inStr.charAt(i) == ' '){
                i++;
            }else {
                char op = inStr.charAt(i);
                int subNew = getSub(op);
                boolean goOn = true;
                while (goOn){
                    if(opStack.isEmpty()) {
                        throw new Exception("运算符太少了！");
                    }
                    char opFormer = opStack.peek();
                    int subFormer = getSub(opFormer);
                    switch (ops[subFormer][subNew]){
                        case '=':
                            goOn = false;
                            opStack.pop();
                            break;
                        case '<':
                            goOn = false;
                            opStack.push(op);
                            break;
                        case'>':
                            goOn = true;
                            TreeNode n1 = nodeStack.pop();
                            TreeNode n0 = nodeStack.pop();
                            double rs = doOperate(n0.value, n1.value, opFormer);
                            nodeStack.push(new TreeNode(rs, opFormer, n0, n1));
                            opStack.pop();
                            break;
                        default:
                            throw new Exception("没有匹配的操作符：" + op);

                    }
                }

                i++;
            }
        }

        return  nodeStack.pop();
    }

    private double doOperate(double n0, double n1, char op) throws Exception{
        switch (op){
            case '+': return n0 + n1;
            case '-': return n0 - n1;
            case '*': return n0 * n1;
            case '/': return n0 / n1;
            default: throw new Exception("非法操作符：" + op);
        }
    }

    private int getSub(char c){
        switch (c){
            case '+': return 0;
            case '-': return 1;
            case '*': return 2;
            case '/': return 3;
            case '(': return 4;
            case ')': return 5;
            case '#': return 6;
            default: return -1;
        }
    }

}

class TreeNode{
    public double value;
    public char op = 'E';
    public TreeNode lft;
    public TreeNode rt;
    public TreeNode(double value){
        this.value = value;
    }

    public TreeNode(double value, char op, TreeNode lft, TreeNode rt){
        this.value = value;
        this.op = op;
        this.lft = lft;
        this.rt = rt;
    }

    StringBuilder buf = new StringBuilder();

    @Override
    public String toString() {
        out(this);
        return buf.toString();
    }

    private void out(TreeNode node){
        if(node == null) {
            return;
        }
        out(node.lft);
        if(node.op != 'E') {
            buf.append(node.op);
        } else {
            buf.append(node.value);
        }
        out(node.rt);
    }
}
