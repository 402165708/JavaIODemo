import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by chenzhitao on 2017/12/14
 */
public class NodeDemo {


    public static void main(String[] args) {

        //测试数据
        List<List<String>> inputList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {

            List<String> list = new ArrayList<>();

            for (int j = 0; j < 10 ; j++) {
                String string = String.valueOf(System.currentTimeMillis() + j) ;
                list.add(string);

            }

            inputList.add(list);

        }

        // 建立树
        Node root = new Node(null ,null);

        for (int i = 0; i < inputList.size(); i++) {

            String fistEle = inputList.get(i).get(0);
            Node child = root.addChild(fistEle);

            List<String> list = inputList.get(i);

            Node currentNode = child ;
            for (int j = 0; j < list.size(); j++) {
                String string = list.get(j);

                currentNode = currentNode.addChild(string);
            }
        }

        //打印处理


    }


    //内部类
    static class Node {
        private Object     data;
        private List<Node> childs;
        private Node       parent;
        private int        level;

        public Node(Object data, Node parent) {
            this.data = data;
            this.parent = parent;
            if (parent == null) {
                level = 0;
            } else {
                level = parent.level + 1;
            }
        }

        public List<Node> getchilds() {
            return childs;
        }

        public Node addChild(Object object  ) {
            Node node = new Node(object, this);

            if (childs == null) {
                childs = new ArrayList<>();
                childs.add(node);
                return node;

            }
            childs.add(node);
            return node;
        }

        public void deleteChild(Node node) {
            if (childs != null) {
                Iterator<Node> it = childs.iterator();
                while (it.hasNext()) {
                    Node child = it.next();
                    if (child.equals(node)) {
                        childs.remove(child);
                        break;
                    }
                }
            }
        }

        public Node getChild(Object daObjecta) {
            if (childs != null) {
                Iterator<Node> it = childs.iterator();
                while (it.hasNext()) {
                    Node child = it.next();
                    if (child.data.equals(data)) {
                        return child;
                    }
                }
            }
            return null;
        }

        public Node getParent() {
            return parent;
        }

        public void setData(Object data) {
            this.data = data;
        }

        public Object getData() {
            return data;
        }

        public int getLevel() {
            return level;
        }
    }

    private Node root = new Node(null, null);

    public Node getRoot() {
        return root;
    }
}
